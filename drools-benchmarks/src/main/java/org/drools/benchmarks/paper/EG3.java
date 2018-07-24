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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EG3 extends AbstractPaperBenchmark {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public void setupKieBase(String segments, int nbrAgendaGroups, int nbrObjectsPerType, int exitValue, String engineOption ) {
        String consequence = "    modify(a){setId(a.getId()+1), setOtherValue(a.getOtherValue()+1)};\n";
        consequence       += "    modify(last){setId(last.getId()+1)};\n";
        consequence       += "    System.out.println(\"fired \" + kcontext.getRule() );\n";
        String firstConsequence = consequence;

        String lastConsequence = "    modify(a){setId(0), setOtherValue(a.getOtherValue()+1)};\n";;
        lastConsequence       += "    modify(last){setId(0)};\n";
        lastConsequence     += "    System.out.println(\"fired \" + kcontext.getRule() );\n";

        String lastOfGroupConsequence = consequence;
        lastOfGroupConsequence       += "    " + EG3.class.getName() + ".nextAgendaGroup(kcontext);\n";

        int[] segmentsPerLevel = Iterations.getSegmentsPerLevel(segments);
        int[] nodesPerSegment = Iterations.getNodesPerSegment(segments);

        System.out.println("\n");
        System.out.println("params (segments=" +segments + " agendaGroups=" + nbrAgendaGroups + " objectsPerType=" + nbrObjectsPerType + " : engineOption=" + engineOption + ")");

        super.setupKieBase(firstConsequence, consequence, lastConsequence, lastOfGroupConsequence,
                           false,
                           segmentsPerLevel, nodesPerSegment, nbrAgendaGroups, nbrObjectsPerType , 0, true, 0, exitValue, engineOption);
    }

    public static class EG3_1  extends Iterations.EG1_1 {

        public EG3_1() {
            super(new EG1());
        }

        @Benchmark
        public void test() {
            strategy.test();
        }
    }

    public static class EG3_1_1  extends Iterations.EG1_1_1 {

        public EG3_1_1() {
            super(new EG1());
        }

        @Benchmark
        public void test() {
            strategy.test();
        }
    }

    public static class EG3_1_2  extends Iterations.EG1_1_2 {

        public EG3_1_2() {
            super(new EG1());
        }

        @Benchmark
        public void test() {
            strategy.test();
        }
    }

    public static class EG3_2  extends Iterations.EG1_2 {

        public EG3_2() {
            super(new EG1());
        }

        @Benchmark
        public void test() {
            strategy.test();
        }
    }

    public static class EG3_2_1  extends Iterations.EG1_2_1 {

        public EG3_2_1() {
            super(new EG1());
        }

        @Benchmark
        public void test() {
            strategy.test();
        }
    }

    public static class EG3_2_2  extends Iterations.EG1_2_2 {

        public EG3_2_2() {
            super(new EG1());
        }

        @Benchmark
        public void test() {
            strategy.test();
        }
    }

    public static class EG3_3  extends Iterations.EG1_3 {

        public EG3_3() {
            super(new EG1());
        }

        @Benchmark
        public void test() {
            strategy.test();
        }
    }

    public static class EG3_3_1  extends Iterations.EG1_3_1 {

        public EG3_3_1() {
            super(new EG1());
        }

        @Benchmark
        public void test() {
            strategy.test();
        }
    }

    public static class EG3_3_2  extends Iterations.EG1_3_2 {

        public EG3_3_2() {
            super(new EG1());
        }

        @Benchmark
        public void test() {
            strategy.test();
        }
    }

    public static class EG3_4  extends Iterations.EG1_4 {

        public EG3_4() {
            super(new EG1());
        }

        @Benchmark
        public void test() {
            strategy.test();
        }
    }

    public static class EG3_4_1  extends Iterations.EG1_4_1 {

        public EG3_4_1() {
            super(new EG1());
        }

        @Benchmark
        public void test() {
            strategy.test();
        }
    }

    public static class EG3_4_2  extends Iterations.EG1_4_2 {

        public EG3_4_2() {
            super(new EG1());
        }

        @Benchmark
        public void test() {
            strategy.test();
        }
    }

    public static class EG3_5  extends Iterations.EG1_5 {

        public EG3_5() {
            super(new EG1());
        }

        @Benchmark
        public void test() {
            strategy.test();
        }
    }

    public static class EG3_5_1  extends Iterations.EG1_5_1 {

        public EG3_5_1() {
            super(new EG1());
        }

        @Benchmark
        public void test() {
            strategy.test();
        }
    }

    public static class EG3_5_2  extends Iterations.EG1_5_2 {

        public EG3_5_2() {
            super(new EG1());
        }

        @Benchmark
        public void test() {
            strategy.test();
        }
    }

    public static class EG3_6  extends Iterations.EG1_6 {

        public EG3_6() {
            super(new EG1());
        }

        @Benchmark
        public void test() {
            strategy.test();
        }
    }

    public static class EG3_6_1  extends Iterations.EG1_6_1 {

        public EG3_6_1() {
            super(new EG1());
        }

        @Benchmark
        public void test() {
            strategy.test();
        }
    }

    public static class EG3_6_2  extends Iterations.EG1_6_2 {
        
        public EG3_6_2() {
            super(new EG1());
        }

        @Benchmark
        public void test() {
            strategy.test();
        }
    }
}