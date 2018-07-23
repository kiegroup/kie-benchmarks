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
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Setup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EG2 extends AbstractPaperBenchmark {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Param({"phreak", "reteoo"})
    private String engineOption;

    @Param({"1", "3"})
    private int nbrAgendaGroups = 3;

    @Param({"1, 3|2, 2", "1, 3, 3|2, 2, 2", "1, 3, 3, 3|2, 2, 2, 2"})
    private String segments;

    @Param({"1", "4", "8"})
    private int nbrObjectsPerType = 4;

    @Param({"300"})
    private int exitValue = 300;


    @Setup
    public void setupKieBase() {
        String consequence = "    modify(a){setOtherValue(a.getOtherValue()+1)};";
        String firstConsequence = consequence;
        String lastConsequence = consequence;
        String lastOfGroupConsequence = consequence;

        int[] segmentsPerLevel = EG1.getSegmentsPerLevel(segments);
        int[] nodesPerSegment = EG1.getNodesPerSegment(segments);

        super.setupKieBase(firstConsequence, consequence, lastConsequence, consequence,
                           false,
                           segmentsPerLevel, nodesPerSegment, nbrAgendaGroups, nbrObjectsPerType , 100, false, nbrAgendaGroups-1, exitValue, engineOption);
    }

    @Benchmark
    public void test() {
        super.test();
    }

}