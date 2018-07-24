/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.benchmarks.paper;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.annotations.TearDown;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EG2 extends AbstractPaperBenchmark {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public void setupKieBase(String segments, int nbrAgendaGroups, int nbrObjectsPerType, int exitValue, String engineOption ) {
        String firstConsequence = ""; //"
        String lastConsequence = ""; //"
        String lastOfGroupConsequence = ""; //"

        String consequence = "    modify(a){setOtherValue(a.getOtherValue()+1)};\n";
        consequence       += "    System.out.println(\"fired \" + kcontext.getRule() );\n";
        firstConsequence = consequence;
        lastConsequence = consequence;
        lastOfGroupConsequence = consequence;

        int[] segmentsPerLevel = Iterations.getSegmentsPerLevel(segments);
        int[] nodesPerSegment = Iterations.getNodesPerSegment(segments);

        System.out.println("\n");
        System.out.println("params (segments=" +segments + " agendaGroups=" + nbrAgendaGroups + " objectsPerType=" + nbrObjectsPerType + " : engineOption=" + engineOption + ")");

        super.setupKieBase(firstConsequence, consequence, lastConsequence, lastOfGroupConsequence,
                           false,
                           segmentsPerLevel, nodesPerSegment, nbrAgendaGroups, nbrObjectsPerType , 100, false, nbrAgendaGroups-1, exitValue, engineOption);
    }

    public static class EG2_1  extends Iterations.EG1_1 {
        private AbstractPaperBenchmark strategy;

        public EG2_1() {
            this.strategy = new EG1();
        }

        @Setup(Level.Trial)
        public void setupKieBase() {
            strategy.setupKieBase( segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
        }

        @Setup(Level.Iteration)
        public void setup() {
            strategy.setup();
        }

        @Benchmark
        public void test() {
            strategy.test();
        }

        @TearDown(Level.Iteration)
        public void tearDown() {
            strategy.tearDown();
        }
    }

    public static class EG2_2  extends Iterations.EG1_2 {
        private AbstractPaperBenchmark strategy;

        public EG2_2() {
            this.strategy = new EG1();
        }

        @Setup(Level.Trial)
        public void setupKieBase() {
            strategy.setupKieBase( segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
        }

        @Setup(Level.Iteration)
        public void setup() {
            strategy.setup();
        }

        @Benchmark
        public void test() {
            strategy.test();
        }

        @TearDown(Level.Iteration)
        public void tearDown() {
            strategy.tearDown();
        }
    }

    public static class EG2_2_1  extends Iterations.EG1_2_1 {
        private AbstractPaperBenchmark strategy;

        public EG2_2_1() {
            this.strategy = new EG1();
        }

        @Setup(Level.Trial)
        public void setupKieBase() {
            strategy.setupKieBase( segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
        }

        @Setup(Level.Iteration)
        public void setup() {
            strategy.setup();
        }

        @Benchmark
        public void test() {
            strategy.test();
        }

        @TearDown(Level.Iteration)
        public void tearDown() {
            strategy.tearDown();
        }
    }

    public static class EG2_2_2  extends Iterations.EG1_2_2 {
        private AbstractPaperBenchmark strategy;

        public EG2_2_2() {
            this.strategy = new EG1();
        }

        @Setup(Level.Trial)
        public void setupKieBase() {
            strategy.setupKieBase( segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
        }

        @Setup(Level.Iteration)
        public void setup() {
            strategy.setup();
        }

        @Benchmark
        public void test() {
            strategy.test();
        }

        @TearDown(Level.Iteration)
        public void tearDown() {
            strategy.tearDown();
        }
    }

//    public static class EG2_3  extends Iterations.EG1_3 {
//        private AbstractPaperBenchmark strategy;
//
//        public EG2_3() {
//            this.strategy = new EG1();
//        }
//
//        @Setup(Level.Trial)
//        public void setupKieBase() {
//            strategy.setupKieBase( segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
//        }
//
//        @Setup(Level.Iteration)
//        public void setup() {
//            strategy.setup();
//        }
//
//        @Benchmark
//        public void test() {
//            strategy.test();
//        }
//    }
//
//    public static class EG2_3_1  extends Iterations.EG1_3_1 {
//        private AbstractPaperBenchmark strategy;
//
//        public EG2_3_1() {
//            this.strategy = new EG1();
//        }
//
//        @Setup(Level.Trial)
//        public void setupKieBase() {
//            strategy.setupKieBase( segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
//        }
//
//        @Setup(Level.Iteration)
//        public void setup() {
//            strategy.setup();
//        }
//
//        @Benchmark
//        public void test() {
//            strategy.test();
//        }
//    }
//
//    public static class EG2_3_2  extends Iterations.EG1_3_2 {
//        private AbstractPaperBenchmark strategy;
//
//        public EG2_3_2() {
//            this.strategy = new EG1();
//        }
//
//        @Setup(Level.Trial)
//        public void setupKieBase() {
//            strategy.setupKieBase( segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
//        }
//
//        @Setup(Level.Iteration)
//        public void setup() {
//            strategy.setup();
//        }
//
//        @Benchmark
//        public void test() {
//            strategy.test();
//        }
//    }
//
//    public static class EG2_4  extends Iterations.EG1_4 {
//        private AbstractPaperBenchmark strategy;
//
//        public EG2_4() {
//            this.strategy = new EG1();
//        }
//
//        @Setup(Level.Trial)
//        public void setupKieBase() {
//            strategy.setupKieBase( segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
//        }
//
//        @Setup(Level.Iteration)
//        public void setup() {
//            strategy.setup();
//        }
//
//        @Benchmark
//        public void test() {
//            strategy.test();
//        }
//    }
//
//    public static class EG2_4_1  extends Iterations.EG1_4_1 {
//        private AbstractPaperBenchmark strategy;
//
//        public EG2_4_1() {
//            this.strategy = new EG1();
//        }
//
//        @Setup(Level.Trial)
//        public void setupKieBase() {
//            strategy.setupKieBase( segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
//        }
//
//        @Setup(Level.Iteration)
//        public void setup() {
//            strategy.setup();
//        }
//
//        @Benchmark
//        public void test() {
//            strategy.test();
//        }
//    }
//
//    public static class EG2_4_2  extends Iterations.EG1_4_2 {
//        private AbstractPaperBenchmark strategy;
//
//        public EG2_4_2() {
//            this.strategy = new EG1();
//        }
//
//        @Setup(Level.Trial)
//        public void setupKieBase() {
//            strategy.setupKieBase( segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
//        }
//
//        @Setup(Level.Iteration)
//        public void setup() {
//            strategy.setup();
//        }
//
//        @Benchmark
//        public void test() {
//            strategy.test();
//        }
//    }
//
//    public static class EG2_5  extends Iterations.EG1_5 {
//        private AbstractPaperBenchmark strategy;
//
//        public EG2_5() {
//            this.strategy = new EG1();
//        }
//
//        @Setup(Level.Trial)
//        public void setupKieBase() {
//            strategy.setupKieBase( segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
//        }
//
//        @Setup(Level.Iteration)
//        public void setup() {
//            strategy.setup();
//        }
//
//        @Benchmark
//        public void test() {
//            strategy.test();
//        }
//    }
//
//    public static class EG2_5_1  extends Iterations.EG1_5_1 {
//        private AbstractPaperBenchmark strategy;
//
//        public EG2_5_1() {
//            this.strategy = new EG1();
//        }
//
//        @Setup(Level.Trial)
//        public void setupKieBase() {
//            strategy.setupKieBase( segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
//        }
//
//        @Setup(Level.Iteration)
//        public void setup() {
//            strategy.setup();
//        }
//
//        @Benchmark
//        public void test() {
//            strategy.test();
//        }
//    }
//
//    public static class EG2_5_2  extends Iterations.EG1_5_2 {
//        private AbstractPaperBenchmark strategy;
//
//        public EG2_5_2() {
//            this.strategy = new EG1();
//        }
//
//        @Setup(Level.Trial)
//        public void setupKieBase() {
//            strategy.setupKieBase( segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
//        }
//
//        @Setup(Level.Iteration)
//        public void setup() {
//            strategy.setup();
//        }
//
//        @Benchmark
//        public void test() {
//            strategy.test();
//        }
//    }
//
//    public static class EG2_6  extends Iterations.EG1_6 {
//        private AbstractPaperBenchmark strategy;
//
//        public EG2_6() {
//            this.strategy = new EG1();
//        }
//
//        @Setup(Level.Trial)
//        public void setupKieBase() {
//            strategy.setupKieBase( segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
//        }
//
//        @Setup(Level.Iteration)
//        public void setup() {
//            strategy.setup();
//        }
//
//        @Benchmark
//        public void test() {
//            strategy.test();
//        }
//    }
//
//    public static class EG2_6_1  extends Iterations.EG1_6_1 {
//        private AbstractPaperBenchmark strategy;
//
//        public EG2_6_1() {
//            this.strategy = new EG1();
//        }
//
//        @Setup(Level.Trial)
//        public void setupKieBase() {
//            strategy.setupKieBase( segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
//        }
//
//        @Setup(Level.Iteration)
//        public void setup() {
//            strategy.setup();
//        }
//
//        @Benchmark
//        public void test() {
//            strategy.test();
//        }
//    }
//
//    public static class EG2_6_2  extends Iterations.EG1_6_2 {
//        private AbstractPaperBenchmark strategy;
//
//        public EG2_6_2() {
//            this.strategy = new EG1();
//        }
//
//        @Setup(Level.Trial)
//        public void setupKieBase() {
//            strategy.setupKieBase( segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
//        }
//
//        @Setup(Level.Iteration)
//        public void setup() {
//            strategy.setup();
//        }
//
//        @Benchmark
//        public void test() {
//            strategy.test();
//        }
//    }
}