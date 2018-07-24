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

public class Iterations {

    @BenchmarkMode(Mode.SingleShotTime)
    @State(Scope.Thread)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(1)
    @Warmup(iterations = 30)
    @Measurement(iterations = 20)
    //@Timeout()
    public static class BaseInteration {
        protected String engineOption;

        protected AbstractPaperBenchmark strategy;

        public BaseInteration(AbstractPaperBenchmark strategy) {
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

    @Warmup(iterations = 0)
    @Measurement(iterations = 1)
    public static class EG1_1  extends BaseInteration  {
        @Param({"1"})
        protected int nbrAgendaGroups;

        @Param({"1|8"})
        protected String segments;

        @Param({"32"})
        protected int nbrObjectsPerType;

        @Param({"100"})
        protected int exitValue;

        public EG1_1(AbstractPaperBenchmark strategy) {
            super(strategy);
        }

        @Setup(Level.Trial)
        public void setupKieBase() {
            strategy.setupKieBase( segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
        }
    }

//    @Warmup(iterations = 0)
//    @Measurement(iterations = 1)
//    public static class EG1_1_1  extends EG1_1  {
//        @Param({"8"})
//        //@Param({"1"})
//        protected int nbrObjectsPerType;
//
//        public EG1_1_1(AbstractPaperBenchmark strategy) {
//            super(strategy);
//        }
//
//        @Param({"1"})
//        protected int exitValue;
//    }
//
//    @Warmup(iterations = 1)
//    @Measurement(iterations = 1)
//    public static class EG1_1_2  extends EG1_1  {
//        @Param({"16"})
//        //@Param({"1"})
//        protected int nbrObjectsPerType;
//
//        public EG1_1_2(AbstractPaperBenchmark strategy) {
//            super(strategy);
//        }
//
//        @Param({"1"})
//        protected int exitValue;
//    }

    @Warmup(iterations = 0)
    @Measurement(iterations = 1)
    public static class EG1_2  extends BaseInteration  {
        //@Param({"1", "2"})
        @Param({"1"})
        protected int nbrAgendaGroups;

        @Param({"1, 2, 2|2, 2, 2"})
        protected String segments;

        @Param({"32"}) // 128 Rete doesn't return
        protected int nbrObjectsPerType;

        @Param({"100"})
        protected int exitValue;

        public EG1_2(AbstractPaperBenchmark strategy) {
            super(strategy);
        }

        @Setup(Level.Trial)
        public void setupKieBase() {
            strategy.setupKieBase( segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
        }
    }

//    @Warmup(iterations = 0)
//    @Measurement(iterations = 1)
//    public static class EG1_2_RETEOO  extends BaseInteration  {
//        @Param({"reteoo"})
//        protected String engineOption;
//
//        //@Param({"1", "2"})
//        @Param({"1"})
//        protected int nbrAgendaGroups;
//
//        @Param({"1, 2, 2|2, 2, 2"})
//        protected String segments;
//
//        @Param({"32"}) // 128 Rete doesn't return
//        protected int nbrObjectsPerType;
//
//        @Param({"10"})
//        protected int exitValue;
//
//        public EG1_2(AbstractPaperBenchmark strategy) {
//            super(strategy);
//        }
//
//        @Setup(Level.Trial)
//        public void setupKieBase() {
//            strategy.setupKieBase( segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
//        }
//    }

//    @Warmup(iterations = 5)
//    @Measurement(iterations = 5)
//    public static class EG1_2_1 extends EG1_2 {
//        @Param({"8"})
//        protected int nbrObjectsPerType;
//
//        public EG1_2_1(AbstractPaperBenchmark strategy) {
//            super(strategy);
//        }
//    }
//
//    @Warmup(iterations = 1)
//    @Measurement(iterations = 5)
//    public static class EG1_2_2 extends EG1_2 {
//        @Param({"16"})
//        protected int nbrObjectsPerType;
//
//        public EG1_2_2(AbstractPaperBenchmark strategy) {
//            super(strategy);
//        }
//    }

    @Warmup(iterations = 0)
    @Measurement(iterations = 1)
    public static class EG1_3 extends BaseInteration {
        //@Param({"1", "4"})
        @Param({"1"})
        protected int nbrAgendaGroups;

        @Param({"1, 2, 2, 2|2, 2, 2, 2"})
        protected String segments;

        @Param({"32"}) // 64 Rete doesn't return
        protected int nbrObjectsPerType;

        @Param({"100"})
        protected int exitValue;

        public EG1_3(AbstractPaperBenchmark strategy) {
            super(strategy);
        }

        @Setup(Level.Trial)
        public void setupKieBase() {
            strategy.setupKieBase( segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
        }
    }
//
//    @Warmup(iterations = 1)
//    @Measurement(iterations = 3)
//    public static class EG1_3_1 extends EG1_3 {
//        @Param({"8"})
//        protected int nbrObjectsPerType;
//
//        public EG1_3_1(AbstractPaperBenchmark strategy) {
//            super(strategy);
//        }
//    }
//
//    @Warmup(iterations = 1)
//    @Measurement(iterations = 3)
//    public static class EG1_3_2 extends EG1_3 {
//        @Param({"16"})
//        protected int nbrObjectsPerType;
//
//        public EG1_3_2(AbstractPaperBenchmark strategy) {
//            super(strategy);
//        }
//    }

    @Warmup(iterations = 0)
    @Measurement(iterations = 1)
    public static class EG1_4 extends BaseInteration {
        //@Param({"1", "3"})
        @Param({"1"})
        protected int nbrAgendaGroups;

        @Param({"1, 3, 3|2, 2, 2"})
        protected String segments;

        @Param({"32"})
        protected int nbrObjectsPerType;

        @Param({"100"})
        protected int exitValue;

        public EG1_4(AbstractPaperBenchmark strategy) {
            super(strategy);
        }

        @Setup(Level.Trial)
        public void setupKieBase() {
            strategy.setupKieBase( segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
        }
    }

//    @Warmup(iterations = 1)
//    @Measurement(iterations = 3)
//    public static class EG1_4_1 extends EG1_4 {
//        @Param({"8"})
//        protected int nbrObjectsPerType;
//
//        public EG1_4_1(AbstractPaperBenchmark strategy) {
//            super(strategy);
//        }
//    }
//
//    @Warmup(iterations = 1)
//    @Measurement(iterations = 3)
//    public static class EG1_4_2 extends EG1_4 {
//        @Param({"16"})
//        protected int nbrObjectsPerType;
//
//        public EG1_4_2(AbstractPaperBenchmark strategy) {
//            super(strategy);
//        }
//    }



    @Warmup(iterations = 0)
    @Measurement(iterations = 1)
    public static class EG1_5 extends BaseInteration {
        //@Param({"1", "3"})
        @Param({"1"})
        protected int nbrAgendaGroups;

        @Param({"1, 3, 3, 3|2, 2, 2, 2"})
        protected String segments;

        @Param({"32"})
        protected int nbrObjectsPerType;

        @Param({"100"})
        protected int exitValue;

        public EG1_5(AbstractPaperBenchmark strategy) {
            super(strategy);
        }

        @Setup(Level.Trial)
        public void setupKieBase() {
            strategy.setupKieBase( segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
        }
    }

//    @Warmup(iterations = 10)
//    @Measurement(iterations = 20)
//    public static class EG1_5_RETEOO extends BaseInteration {
//        @Param({"reteoo"})
//        protected String engineOption;
//
//        //@Param({"1", "3"})
//        @Param({"1"})
//        protected int nbrAgendaGroups;
//
//        @Param({"1, 3, 3, 3|2, 2, 2, 2"})
//        protected String segments;
//
//        @Param({"32"})
//        protected int nbrObjectsPerType;
//
//        @Param({"100"})
//        protected int exitValue;
//
//        public EG1_5_RETEOO(AbstractPaperBenchmark strategy) {
//            super(strategy);
//        }
//
//        @Setup(Level.Trial)
//        public void setupKieBase() {
//            strategy.setupKieBase( segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
//        }
//    }
//
//    @Warmup(iterations = 100)
//    @Measurement(iterations = 20)
//    public static class EG1_5_PHREAK extends EG1_5_RETEOO {
//        @Param({"phreak"})
//        protected String engineOption;
//
//        public EG1_5_PHREAK(AbstractPaperBenchmark strategy) {
//            super(strategy);
//        }
//    }

//    @Warmup(iterations = 1)
//    @Measurement(iterations = 3)
//    public static class EG1_5_1 extends EG1_5 {
//        @Param({"8"})
//        protected int nbrObjectsPerType;
//
//        public EG1_5_1(AbstractPaperBenchmark strategy) {
//            super(strategy);
//        }
//    }
//
//    @Warmup(iterations = 1)
//    @Measurement(iterations = 3)
//    public static class EG1_5_2 extends EG1_5 {
//        @Param({"16"})
//        protected int nbrObjectsPerType;
//
//        public EG1_5_2(AbstractPaperBenchmark strategy) {
//            super(strategy);
//        }
//    }

//    @Warmup(iterations = 0)
//    @Measurement(iterations = 1)
//    public static class EG1_6 extends BaseInteration {
//        //@Param({"1", "3"})
//        @Param({"1"})
//        protected int nbrAgendaGroups;
//
//        @Param({"1, 3, 3, 3|3, 3, 3, 3"})
//        protected String segments;
//
//        @Param({"64"})
//        protected int nbrObjectsPerType;
//
//        @Param({"1"})
//        protected int exitValue;
//
//        public EG1_6(AbstractPaperBenchmark strategy) {
//            super(strategy);
//        }
//
//        @Setup(Level.Trial)
//        public void setupKieBase() {
//            strategy.setupKieBase( segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
//        }
//    }

//    @Warmup(iterations = 1)
//    @Measurement(iterations = 3)
//    public static class EG1_6_1 extends EG1_6 {
//        @Param({"8"})
//        protected int nbrObjectsPerType;
//
//        public EG1_6_1(AbstractPaperBenchmark strategy) {
//            super(strategy);
//        }
//    }
//
//    @Warmup(iterations = 1)
//    @Measurement(iterations = 3)
//    public static class EG1_6_2 extends EG1_6 {
//        @Param({"16"})
//        protected int nbrObjectsPerType;
//
//        public EG1_6_2(AbstractPaperBenchmark strategy) {
//            super(strategy);
//        }
//    }

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
