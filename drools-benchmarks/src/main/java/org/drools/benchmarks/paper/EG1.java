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
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.Warmup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EG1  extends AbstractPaperBenchmark {
    private Logger logger = LoggerFactory.getLogger(getClass());

    //@Param({"phreak", "reteoo"})
    protected String engineOption;


    @Warmup(iterations = 10)
    @Measurement(iterations = 10)
    public static class EG1_1  extends EG1 {
        @Param({"1"})
        protected int nbrAgendaGroups;

        @Param({"1|4", "1|8"})
        protected String segments;

        //@Param({"1", "4", "8", "16"})
        @Param({"2"})
        protected int nbrObjectsPerType;

        @Param({"300"})
        private int exitValue;

        @Setup
        public void setupKieBase() {
            super.setupKieBase( segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
        }

        @Benchmark
        public void test() {
            super.test();
        }
    }

    @Warmup(iterations = 10)
    @Measurement(iterations = 10)
    public static class EG1_2 extends EG1 {
        @Param({"1", "2"})
        protected int nbrAgendaGroups;

        @Param({"1, 2, 2|2, 2, 2"})
        protected String segments;

        @Param({"1", "4", "8", "16"})
        protected int nbrObjectsPerType;

        @Param({"300"})
        protected int exitValue;

        @Setup
        public void setupKieBase() {
            super.setupKieBase(segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
        }

        @Benchmark
        public void test() {
            super.test();
        }
    }

    @Warmup(iterations = 10)
    @Measurement(iterations = 10)
    public static class EG1_3 extends EG1 {
        @Param({"1", "2", "4"})
        protected int nbrAgendaGroups;

        @Param({"1, 2, 2, 2|2, 2, 2, 2"})
        protected String segments;

        @Param({"1", "4", "8", "16"})
        protected int nbrObjectsPerType;

        @Param({"300"})
        private int exitValue;

        @Setup
        public void setupKieBase() {
            super.setupKieBase(segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
        }

        @Benchmark
        public void test() {
            super.test();
        }
    }

    @Warmup(iterations = 10)
    @Measurement(iterations = 10)
    public static class EG1_4 extends EG1 {
        @Param({"1", "3"})
        protected int nbrAgendaGroups;

        @Param({"1, 3, 3|2, 2, 2"})
        protected String segments;

        @Param({"1", "4", "8", "16"})
        protected int nbrObjectsPerType;

        @Param({"300"})
        protected int exitValue;

        @Setup
        public void setupKieBase() {
            super.setupKieBase(segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
        }

        @Benchmark
        public void test() {
            super.test();
        }
    }

    @Warmup(iterations = 5)
    @Measurement(iterations = 5)
    public static class EG1_5 extends EG1 {
        @Param({"1", "3"})
        protected int nbrAgendaGroups;

        @Param({"1, 3, 3, 3|2, 2, 2, 2"})
        protected String segments;

        @Param({"1", "4", "8", "16"})
        protected int nbrObjectsPerType;

        @Param({"300"})
        protected int exitValue;

        @Setup
        public void setupKieBase() {
            super.setupKieBase(segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
        }

        @Benchmark
        public void test() {
            super.test();
        }
    }

    @Warmup(iterations = 3)
    @Measurement(iterations = 5)
    public static class EG1_6 extends EG1 {
        @Param({"1", "3"})
        protected int nbrAgendaGroups;

        @Param({"1, 3, 3, 3|3, 3, 3, 3"})
        protected String segments;

        @Param({"1", "4", "8", "16"})
        protected int nbrObjectsPerType;

        @Param({"300"})
        protected int exitValue;

        @Setup
        public void setupKieBase() {
            super.setupKieBase(segments, nbrAgendaGroups, nbrObjectsPerType, exitValue, engineOption);
        }

        @Benchmark
        public void test() {
            super.test();
        }
    }


    public void setupKieBase(String segments, int nbrAgendaGroups, int nbrObjectsPerType, int exitValue, String engineOption ) {
        String firstConsequence = ""; //"
        String lastConsequence = ""; //"
        String lastOfGroupConsequence = ""; //"

        String consequence = "    modify(a){setOtherValue(a.getOtherValue()+1)};\n";
        //consequence       += "    System.out.println(\"fired \" + kcontext.getRule() );\n";
        firstConsequence = consequence;
        lastConsequence = consequence;
        lastOfGroupConsequence = consequence;

        int[] segmentsPerLevel = getSegmentsPerLevel(segments);
        int[] nodesPerSegment = getNodesPerSegment(segments);

        super.setupKieBase(firstConsequence, consequence, lastConsequence, lastOfGroupConsequence,
                           false,
                           segmentsPerLevel, nodesPerSegment, nbrAgendaGroups, nbrObjectsPerType, 0, false, 0, exitValue, engineOption);
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