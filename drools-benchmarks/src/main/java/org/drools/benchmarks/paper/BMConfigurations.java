package org.drools.benchmarks.paper;

import java.util.concurrent.TimeUnit;

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
import org.openjdk.jmh.annotations.Timeout;
import org.openjdk.jmh.annotations.Warmup;

public class BMConfigurations {

    @BenchmarkMode(Mode.SingleShotTime)
    @State(Scope.Thread)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(1)
    @Warmup(iterations = 30)
    @Measurement(iterations = 20)
    @Timeout(time = 10, timeUnit = TimeUnit.MINUTES)
    public static class BMBaseConf {
        //@Param({"phreak", "reteoo"})
        //@Param({"phreak"})
        //@Param({"reteoo"})
        protected String engineOption;

        @Param({"8", "16", "32"})
        protected int nbrObjectsPerType;

        @Param({"100"})
        protected int exitValue;

        protected AbstractPaperBenchmark strategy;

        public BMBaseConf(AbstractPaperBenchmark strategy) {
            this.strategy = strategy;
        }


        @Setup(Level.Iteration)
        public void setup() {
            strategy.setup();
        }

        @TearDown(Level.Iteration)
        public void tearDown() {
            strategy.tearDown();
        }
    }

    @Warmup(iterations = 2)
    @Measurement(iterations = 2)
    public static class BMBaseConf_1 extends BMBaseConf {
        @Param({"1"})
        protected int nbrAgendaGroups;

        @Param({"1|8"})
        protected String segments;

        public BMBaseConf_1(AbstractPaperBenchmark strategy) {
            super(strategy);
        }

        @Setup(Level.Trial)
        public void setupKieBase() {
            strategy.setupKieBase( segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
        }
    }

    @Warmup(iterations = 2)
    @Measurement(iterations = 2)
    public static class BMBaseConf_2 extends BMBaseConf {
        //@Param({"1", "2"})
        @Param({"1"})
        protected int nbrAgendaGroups;

        @Param({"1, 2, 2|2, 2, 2"})
        protected String segments;

        public BMBaseConf_2(AbstractPaperBenchmark strategy) {
            super(strategy);
        }

        @Setup(Level.Trial)
        public void setupKieBase() {
            strategy.setupKieBase( segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
        }
    }

    @Warmup(iterations = 2)
    @Measurement(iterations = 2)
    public static class BMBaseConf_3 extends BMBaseConf {
        //@Param({"1", "4"})
        @Param({"1"})
        protected int nbrAgendaGroups;

        @Param({"1, 2, 2, 2|2, 2, 2, 2"})
        protected String segments;

        public BMBaseConf_3(AbstractPaperBenchmark strategy) {
            super(strategy);
        }

        @Setup(Level.Trial)
        public void setupKieBase() {
            strategy.setupKieBase( segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
        }
    }

    @Warmup(iterations = 2)
    @Measurement(iterations = 2)
    public static class BMBaseConf_4 extends BMBaseConf {
        //@Param({"1", "3"})
        @Param({"1"})
        protected int nbrAgendaGroups;

        @Param({"1, 3, 3|2, 2, 2"})
        protected String segments;

        public BMBaseConf_4(AbstractPaperBenchmark strategy) {
            super(strategy);
        }

        @Setup(Level.Trial)
        public void setupKieBase() {
            strategy.setupKieBase( segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
        }
    }

    @Warmup(iterations = 2)
    @Measurement(iterations = 2)
    public static class BMBaseConf_5 extends BMBaseConf {
        //@Param({"1", "3"})
        @Param({"1"})
        protected int nbrAgendaGroups;

        @Param({"1, 3, 3, 3|2, 2, 2, 2"})
        protected String segments;

        public BMBaseConf_5(AbstractPaperBenchmark strategy) {
            super(strategy);
        }

        @Setup(Level.Trial)
        public void setupKieBase() {
            strategy.setupKieBase( segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
        }
    }

    public static int[] getNodesPerSegment(String nodesPerSegmentStr) {
        int split = nodesPerSegmentStr.indexOf('|');

        String[] segs = nodesPerSegmentStr.substring(split+1).split(",");
        int[] nodesPerSegment = new int[segs.length];
        for (int i = 0; i < segs.length; i++) {
            nodesPerSegment[i] = Integer.valueOf(segs[i].trim());
        }
        return nodesPerSegment;
    }

    public static int[] getSegmentsPerLevel(String segmentsPerLevelStr) {
        int split = segmentsPerLevelStr.indexOf('|');

        String[] segs = segmentsPerLevelStr.substring(0, split).split(",");
        int[] segmentsPerLevel = new int[segs.length];
        for (int i = 0; i < segs.length; i++) {
            segmentsPerLevel[i] = Integer.valueOf(segs[i].trim());
        }
        return segmentsPerLevel;
    }
}
