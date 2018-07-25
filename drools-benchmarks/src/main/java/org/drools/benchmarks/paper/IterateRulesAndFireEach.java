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


public class IterateRulesAndFireEach extends AbstractPaperBenchmark {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public void setupKieBase(String segments, int nbrAgendaGroups, int nbrObjectsPerType, int exitValue, String engineOption ) {
        String consequence = "    modify(a){setId(a.getId()+1), setOtherValue(a.getOtherValue()+1)};\n";
        consequence       += "    modify(last){setId(last.getId()+1)};\n";
        //consequence       += "    System.out.println(\"fired \" + kcontext.getRule() );\n";
        String firstConsequence = consequence;

        String lastConsequence = "    modify(a){setId(0), setOtherValue(a.getOtherValue()+1)};\n";;
        lastConsequence       += "    modify(last){setId(0)};\n";
        //lastConsequence     += "      System.out.println(\"fired \" + kcontext.getRule() );\n";

        String lastOfGroupConsequence = consequence;
        lastOfGroupConsequence       += "    " + IterateRulesAndFireEach.class.getName() + ".nextAgendaGroup(kcontext);\n";

        int[] segmentsPerLevel = BMConfigurations.getSegmentsPerLevel(segments);
        int[] nodesPerSegment = BMConfigurations.getNodesPerSegment(segments);

        System.out.println("\n");
        System.out.println("params (segments=" +segments + " agendaGroups=" + nbrAgendaGroups + " objectsPerType=" + nbrObjectsPerType + " : engineOption=" + engineOption + ")");

        super.setupKieBase("", firstConsequence, consequence, lastConsequence, lastOfGroupConsequence,
                           false,
                           segmentsPerLevel, nodesPerSegment, nbrAgendaGroups, nbrObjectsPerType , -1, true, 0, exitValue, engineOption);
    }

    public static class IterateRulesAndFireEach_1 extends BMConfigurations.BMBaseConf_1 {

        public IterateRulesAndFireEach_1() {
            super(new IterateRulesAndFireEach());
        }

        @Benchmark
        public void test() {
            strategy.test();
        }
    }

    public static class IterateRulesAndFireEach_2  extends BMConfigurations.BMBaseConf_2 {

        public IterateRulesAndFireEach_2() {
            super(new IterateRulesAndFireEach());
        }

        @Benchmark
        public void test() {
            strategy.test();
        }
    }

    public static class IterateRulesAndFireEach_3 extends BMConfigurations.BMBaseConf_3 {

        public IterateRulesAndFireEach_3() {
            super(new IterateRulesAndFireEach());
        }

        @Benchmark
        public void test() {
            strategy.test();
        }
    }

    public static class IterateRulesAndFireEach_4 extends BMConfigurations.BMBaseConf_4 {

        public IterateRulesAndFireEach_4() {
            super(new IterateRulesAndFireEach());
        }

        @Benchmark
        public void test() {
            strategy.test();
        }
    }

    public static class IterateRulesAndFireEach_5 extends BMConfigurations.BMBaseConf_5 {

        public IterateRulesAndFireEach_5() {
            super(new IterateRulesAndFireEach());
        }

        @Benchmark
        public void test() {
            strategy.test();
        }
    }

}