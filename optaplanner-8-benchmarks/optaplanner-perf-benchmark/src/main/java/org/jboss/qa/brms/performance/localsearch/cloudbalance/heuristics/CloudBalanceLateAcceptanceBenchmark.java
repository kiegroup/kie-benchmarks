package org.jboss.qa.brms.performance.localsearch.cloudbalance.heuristics;

import org.jboss.qa.brms.performance.configuration.AcceptorConfigurations;
import org.optaplanner.examples.cloudbalancing.domain.CloudBalance;
import org.openjdk.jmh.annotations.Benchmark;
import org.optaplanner.core.config.localsearch.decider.acceptor.LocalSearchAcceptorConfig;

public class CloudBalanceLateAcceptanceBenchmark extends AbstractCloudBalanceHeuristicBenchmark {

    @Override
    public LocalSearchAcceptorConfig createAcceptorConfig() {
        return AcceptorConfigurations.createLateAcceptanceAcceptor(100);
    }

    @Benchmark
    public CloudBalance benchmark() {
        return runBenchmark();
    }
}
