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

import org.kie.api.runtime.KieContext;
import org.kie.api.runtime.rule.RuleContext;
import org.kie.internal.definition.rule.InternalRule;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EG3 extends AbstractPaperBenchmark {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Param({"1", "3"})
    private int nbrAgendaGroups = 1;

    @Param({"1, 3, 3, 3|2, 2, 2, 2"})
    private String segments;

    @Param({"4"})
    private int nbrObjectsPerType = 4;

    @Param({"300"})
    private int exitValue = 300;


    @Setup
    public void setupKieBase() {
        String consequence = "    modify(a){setId(a.getId()+1), setOtherValue(a.getOtherValue()+1)};\n";
        consequence       += "    modify(last){setId(last.getId()+1)};\n";
        //consequence       += "    System.out.println(\"fired \" + kcontext.getRule() );\n";
        String firstConsequence = consequence;

        String lastConsequence = "    modify(a){setId(0), setOtherValue(a.getOtherValue()+1)};\n";;
        lastConsequence       += "    modify(last){setId(0)};\n";
        //lastConsequence       += "    System.out.println(\"fired \" + kcontext.getRule() );\n";

        String lastOfGroupConsequence = consequence;
        lastOfGroupConsequence       += "    " + EG3.class.getName() + ".nextAgendaGroup(kcontext);\n";

        int[] segmentsPerLevel = EG1.getSegmentsPerLevel(segments);
        int[] nodesPerSegment = EG1.getNodesPerSegment(segments);

        super.setupKieBase(firstConsequence, consequence, lastConsequence, lastOfGroupConsequence,
                           false,
                           segmentsPerLevel, nodesPerSegment, nbrAgendaGroups, nbrObjectsPerType , 0, true, 0, exitValue);
    }

    public static void nextAgendaGroup(RuleContext kContext) {
        int group = Integer.valueOf(((InternalRule)(kContext.getRule())).getAgendaGroup().substring(5));

        kContext.getKieRuntime().getAgenda().getAgendaGroup("Group" + (group+1) ).setFocus();
    }

    @Benchmark
    public void test() {
        super.test();
    }

}