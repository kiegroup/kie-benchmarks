package org.jbpm.test.performance.scenario.load;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.jbpm.test.performance.kieserver.KieServerClient;
import org.jbpm.test.performance.kieserver.constant.ProcessStorage;
import org.jbpm.test.performance.kieserver.constant.UserStorage;
import org.kie.server.api.model.instance.ProcessInstance;
import org.kie.server.api.model.instance.TaskSummary;
import org.kie.server.client.ProcessServicesClient;
import org.kie.server.client.QueryServicesClient;
import org.kie.server.client.UserTaskServicesClient;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 1, time = 1)
@Measurement(iterations = 1, time = 1)
@Threads(1)
public class LServerHumanTaskTimerProcess {
    // ! Must be overridden using -p from command line
    @Param("")
    public String remoteAPI;

    private KieServerClient client;
    private ProcessServicesClient processClient;
    private UserTaskServicesClient taskClient;
    private QueryServicesClient queryClient;

    @Setup(Level.Iteration)
    public void init() {
        System.setProperty("remoteAPI", remoteAPI);

        client = new KieServerClient();
        processClient = client.getProcessClient();
        taskClient = client.getTaskClient();
        queryClient = client.getQueryClient();

        // Single thread setup. Otherwise there will be a primary key violation when running parallel.
        execute();
    }

    private void execute() {
        // start process instance
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("timerDuration", new String("10m"));
        long pid = processClient.startProcess(KieServerClient.containerId, ProcessStorage.HumanTaskTimer.getProcessDefinitionId(), params);

        // complete task
        boolean completeTask = Boolean.getBoolean("timertest.completetask");
        if(completeTask) {
            List<String> status = new ArrayList<String>();
            List<TaskSummary> tasks = taskClient.findTasksByStatusByProcessInstanceId(pid, status, 0, 1);
            Long taskSummaryId = tasks.get(0).getId();

            taskClient.startTask(KieServerClient.containerId, taskSummaryId, UserStorage.PerfUser.getUserId());

            taskClient.completeTask(KieServerClient.containerId, taskSummaryId, UserStorage.PerfUser.getUserId(), null);
        }
        ProcessInstance plog = queryClient.findProcessInstanceById(pid);

        if (plog == null || (completeTask && plog.getState() != org.kie.api.runtime.process.ProcessInstance.STATE_COMPLETED) || (!completeTask && plog.getState() != org.kie.api.runtime.process.ProcessInstance.STATE_ACTIVE)) {
            throw new RuntimeException("Unexpected state for processInstance " + (plog!=null?plog.getState():""));
        }
    }

    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Benchmark
    public void AverageTime() {
        execute();
    }

    @TearDown(Level.Iteration)
    public void close() {

    }
}