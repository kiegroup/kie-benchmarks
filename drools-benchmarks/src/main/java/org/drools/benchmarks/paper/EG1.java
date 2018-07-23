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

import java.util.ArrayList;
import java.util.List;

import org.drools.benchmarks.common.AbstractBenchmark;
import org.drools.benchmarks.common.providers.RulesWithSegmentsProvider;
import org.drools.benchmarks.domain.A;
import org.drools.benchmarks.domain.AbstractBean;
import org.drools.benchmarks.domain.B;
import org.drools.benchmarks.domain.C;
import org.drools.benchmarks.domain.D;
import org.drools.benchmarks.domain.E;
import org.drools.benchmarks.domain.F;
import org.drools.benchmarks.domain.G;
import org.drools.benchmarks.domain.H;
import org.drools.benchmarks.domain.I;
import org.drools.benchmarks.domain.J;
import org.drools.benchmarks.domain.K;
import org.drools.benchmarks.domain.L;
import org.drools.benchmarks.domain.BeanType;
import org.kie.api.runtime.rule.FactHandle;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EG1 extends AbstractPaperBenchmark {
    private Logger logger = LoggerFactory.getLogger(getClass());

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
        String firstConsequence = ""; //"
        String lastConsequence = ""; //"
        String lastOfGroupConsequence = ""; //"

        String consequence = "    modify(a){setOtherValue(a.getOtherValue()+1)};";
        firstConsequence = consequence;
        lastConsequence = consequence;
        lastOfGroupConsequence = consequence;

        int[] segmentsPerLevel = getSegmentsPerLevel(segments);
        int[] nodesPerSegment = getNodesPerSegment(segments);

        super.setupKieBase(firstConsequence, consequence, lastConsequence, lastOfGroupConsequence,
                           false,
                           segmentsPerLevel, nodesPerSegment, nbrAgendaGroups, nbrObjectsPerType, 0, false, 0, exitValue);
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

    @Benchmark
    public void test() {
        super.test();
    }

}