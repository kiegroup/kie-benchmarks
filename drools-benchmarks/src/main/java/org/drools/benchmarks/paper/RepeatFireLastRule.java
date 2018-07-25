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


public class RepeatFireLastRule extends AbstractPaperBenchmark {
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

        int[] segmentsPerLevel = BMConfigurations.getSegmentsPerLevel(segments);
        int[] nodesPerSegment = BMConfigurations.getNodesPerSegment(segments);

        System.out.println("\n");
        System.out.println("params (segments=" +segments + " agendaGroups=" + nbrAgendaGroups + " objectsPerType=" + nbrObjectsPerType + " : engineOption=" + engineOption + ")");

        super.setupKieBase("", firstConsequence, consequence, lastConsequence, lastOfGroupConsequence,
                           false,
                           segmentsPerLevel, nodesPerSegment, nbrAgendaGroups, nbrObjectsPerType , 100, false, nbrAgendaGroups-1, exitValue, engineOption);
    }

    public static class RepeatFireLastRule_1 extends BMConfigurations.BMBaseConf_1 {

        public RepeatFireLastRule_1() {
            super(new RepeatFireFirstRule());
        }

        @Benchmark
        public void test() {
            strategy.test();
        }
    }

    public static class RepeatFireLastRule_2  extends BMConfigurations.BMBaseConf_2 {
        public RepeatFireLastRule_2() {
            super(new RepeatFireFirstRule());
        }

        @Benchmark
        public void test() {
            strategy.test();
        }
    }

    public static class RepeatFireLastRule_3 extends BMConfigurations.BMBaseConf_3 {

        public RepeatFireLastRule_3() {
            super(new RepeatFireFirstRule());
        }

        @Benchmark
        public void test() {
            strategy.test();
        }
    }

    public static class RepeatFireLastRule_4 extends BMConfigurations.BMBaseConf_4 {

        public RepeatFireLastRule_4() {
            super(new RepeatFireFirstRule());
        }

        @Benchmark
        public void test() {
            strategy.test();
        }
    }

    public static class RepeatFireLastRule_5 extends BMConfigurations.BMBaseConf_5 {

        public RepeatFireLastRule_5() {
            super(new RepeatFireFirstRule());
        }

        @Benchmark
        public void test() {
            strategy.test();
        }
    }

}