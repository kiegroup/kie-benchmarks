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

public class MatchAllRulesAndFireThenRepeat extends AbstractPaperBenchmark {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public void setupKieBase(String segments, int nbrAgendaGroups, int nbrObjectsPerType, int exitValue, String engineOption ) {
        String firstConsequence = ""; //"
        String lastConsequence = ""; //"
        String lastOfGroupConsequence = ""; //"

        String consequence = "    counter.increment();\n";//"    modify(a) {setOtherValue(a.getOtherValue()+1)};\n";
        //consequence       += "    System.out.println(\"fired \" + kcontext.getRule() );\n";
        firstConsequence = consequence;
        lastConsequence = consequence + "    modify(a) {setValue(a.getValue()+1)};\n";
        //lastConsequence       += "    System.out.println(\"fired \" + kcontext.getRule() );\n";

        lastOfGroupConsequence = consequence;

        int[] segmentsPerLevel = BMConfigurations.getSegmentsPerLevel(segments);
        int[] nodesPerSegment = BMConfigurations.getNodesPerSegment(segments);

        String prefix = "rule exit agenda-group \"Group0\" salience 1000 when\n    A(value == " + exitValue + ")\nthen\n    kcontext.getKieRuntime().halt();\nend\n";

        System.out.println("\n");
        System.out.println("params (segments=" +segments + " agendaGroups=" + nbrAgendaGroups + " objectsPerType=" + nbrObjectsPerType + " : engineOption=" + engineOption + ")");

        // override the exit value, we need this one to be based on udpates to a fact, rather than rule count.
        super.setupKieBase(prefix, firstConsequence, consequence, lastConsequence, lastOfGroupConsequence,
                           false,
                           segmentsPerLevel, nodesPerSegment, nbrAgendaGroups, nbrObjectsPerType, -1, false, 0, -1, engineOption);
    }

//    public static class MatchAllRulesAndFireThenRepeat_1 extends BMConfigurations.BMBaseConf_1 {
//        public MatchAllRulesAndFireThenRepeat_1() {
//            super(new MatchAllRulesAndFireThenRepeat());
//        }
//
//        @Benchmark
//        public void test() {
//            strategy.test();
//        }
//    }
//
//    public static class MatchAllRulesAndFireThenRepeat_2  extends BMConfigurations.BMBaseConf_2 {
//
//        public MatchAllRulesAndFireThenRepeat_2() {
//            super(new MatchAllRulesAndFireThenRepeat());
//        }
//
//        @Benchmark
//        public void test() {
//            strategy.test();
//        }
//
//    }
//
//    public static class MatchAllRulesAndFireThenRepeat_3 extends BMConfigurations.BMBaseConf_3 {
//
//        public MatchAllRulesAndFireThenRepeat_3() {
//            super(new MatchAllRulesAndFireThenRepeat());
//        }
//
//        @Benchmark
//        public void test() {
//            strategy.test();
//        }
//    }
//
//    public static class MatchAllRulesAndFireThenRepeat_4 extends BMConfigurations.BMBaseConf_4 {
//
//        public MatchAllRulesAndFireThenRepeat_4() {
//            super(new MatchAllRulesAndFireThenRepeat());
//        }
//
//        @Benchmark
//        public void test() {
//            strategy.test();
//        }
//    }
//
//    public static class MatchAllRulesAndFireThenRepeat_5 extends BMConfigurations.BMBaseConf_5 {
//
//        public MatchAllRulesAndFireThenRepeat_5() {
//            super(new MatchAllRulesAndFireThenRepeat());
//        }
//
//        @Benchmark
//        public void test() {
//            strategy.test();
//        }
//    }

}