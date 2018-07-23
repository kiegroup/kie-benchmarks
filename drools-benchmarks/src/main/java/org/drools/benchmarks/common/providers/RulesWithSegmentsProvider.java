/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.benchmarks.common.providers;

import org.drools.benchmarks.common.DrlProvider;
import org.drools.benchmarks.domain.A;

/**
 * Provides rule(s) with simple JoinNodes. Can provide rules with JoinNodes also for event processing.
 */
public class RulesWithSegmentsProvider implements DrlProvider {
    private final boolean appendDrlHeader;
    private final String global;
    private final String firstConsequence;
    private final String consequence;
    private final String lastOfGroupConsequence;
    private final String lastConsequence;
    private int nbrPatterns;
    private int nbrRules;
    private int nbrRulesPerGroup;
    private int sharingOffsetStart = 100000000;
    private int lastRuleSalience = 0;

    private boolean constrainlastPatternToA = true;

    private int lastPatternCounter = 0;

    public RulesWithSegmentsProvider(final String global, final String firstConsequence, final String consequence, final String lastConsequence, final String lastOfGroupConsequence,
                                     int lastRuleSalience, boolean appendDrlHeader, boolean constrainlastPatternToA) {
        this.global = global;
        this.firstConsequence = firstConsequence;
        this.consequence = consequence;
        this.lastConsequence = lastConsequence;
        this.lastOfGroupConsequence = lastOfGroupConsequence;
        this.appendDrlHeader = appendDrlHeader;
        this.constrainlastPatternToA = constrainlastPatternToA;
        this.lastRuleSalience = lastRuleSalience;
    }

    @Override
    public String getDrl() {
        return getDrl(1);
    }

    @Override
    public String getDrl(final int numberOfRules) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getDrl(int[] segmentsPerLevel, int[] nodesPerSegment, int nbrAgendaGroups) {
        final StringBuilder drlBuilder = new StringBuilder();

        if (appendDrlHeader) {
            drlBuilder.append("import " + A.class.getPackage().getName() + ".*;\n");
        }
        drlBuilder.append( global + "\n" );


        String[][] patterns = buildPatterns(segmentsPerLevel, nodesPerSegment);

        if (nbrRules % nbrAgendaGroups != 0) {
            throw new IllegalArgumentException("Number of AgendaGroups must divide rules evenly " + nbrRules + " : " + nbrAgendaGroups + " : " + (nbrRules / nbrAgendaGroups));
        }

        nbrRulesPerGroup = nbrRules / nbrAgendaGroups;

        int agendaGroup = 0;
        for (int i = 0; i < patterns.length; i++) {
            if ( i != 0 && i % nbrRulesPerGroup == 0) {
                agendaGroup++;
            }
            drlBuilder.append("rule R" + i + " agenda-group \"Group" + agendaGroup + "\" ");

            if(i == patterns.length  -1) {
                // on last rule
                drlBuilder.append("salience " + lastRuleSalience + " ");
            }

            drlBuilder.append( "  when\n");


            for (int j = 0; j < patterns[i].length; j++) {
                drlBuilder.append(patterns[i][j]);
            }
            drlBuilder.append( "then\n" );
            if(i==0) {
                drlBuilder.append(firstConsequence);
            } else if(i == patterns.length  -1) {
                drlBuilder.append(lastConsequence);
            } else if ( (i+1) % nbrRulesPerGroup == 0) {
                // next rule would be a new group
                drlBuilder.append(lastOfGroupConsequence);
            }  else {
                drlBuilder.append(consequence);
            }

            drlBuilder.append( "end\n" );
        }
        return drlBuilder.toString();
    }


    public void buildPatterns(int[] segmentsPerLevel, int[] nodesPerSegment, int nbrRulesForSegment, char typeName, String[][] patterns, int level, int ruleStartPos, int nodeStartPos, int sharingOffsetStart, int sharingOffset) {
        for ( int i = 0; i < nodesPerSegment[level]; i++) {
            char prevLowerLetter = Character.toLowerCase((char) (typeName - 1));

            String constraint = "";
            if (nodeStartPos+i != 0 ) {
                constraint = "value != " + prevLowerLetter + ".value";
                constraint = constraint + " + " + (sharingOffsetStart + sharingOffset);
            }

            String varName = String.valueOf(Character.toLowerCase(typeName));

            if (nodeStartPos+i+1 ==  nbrPatterns) {
                String originalConstraint = constraint;
                constraint = "id == " + lastPatternCounter++;
                if (constrainlastPatternToA) {
                    constraint = constraint + " && id == a.id";
                }
                constraint = constraint + " && " + originalConstraint;

                varName = "last";
            }


            String pattern =  "    "  + varName + " : " + typeName + "(" + constraint + ")\n";
            for (int k = 0; k < nbrRulesForSegment; k++) {
                // set pattern here
                patterns[ruleStartPos+k][nodeStartPos+i] = pattern;
                //System.out.print( (ruleStartPos+k) + ":" + (nodeStartPos+i) + ": " + pattern );
            }
            typeName++;
        }

        if ( level + 1 < segmentsPerLevel.length) {
            nbrRulesForSegment = nbrRulesForSegment / segmentsPerLevel[level+1];
            for ( int i = 0; i < segmentsPerLevel[level+1]; i++) {
                buildPatterns(segmentsPerLevel, nodesPerSegment, nbrRulesForSegment, typeName, patterns, level + 1, ruleStartPos, nodeStartPos + nodesPerSegment[level], sharingOffsetStart, i);
                ruleStartPos = ruleStartPos + nbrRulesForSegment;
            }
        }
    }

    private String[][] buildPatterns(int[] segmentsPerLevel, int[] nodesPerSegment) {
        nbrRules = 1;

        for ( int j = 0; j < segmentsPerLevel.length; j++ ) {
            if ( j > 0 && segmentsPerLevel[j] <= 1) {
                throw new IllegalArgumentException("Segments Per Level is " + segmentsPerLevel[j] + ". Only root can be 1, all thers must be greater than 1");
            }
            nbrRules = nbrRules * segmentsPerLevel[j];
        }

        for ( int j = 0; j < nodesPerSegment.length; j++ ) {
            nbrPatterns = nbrPatterns + nodesPerSegment[j];
        }

        String[][] patterns = new String[nbrRules][nbrPatterns];

        for ( int l = 0; l < nbrRules; l++ ) {
            patterns[l] = new String[nbrPatterns];
        }


        int level = 0;
        int nbrRulesForSegment = nbrRules / segmentsPerLevel[level];

        //int sharingOffsetStart = nbrRules;
        int sharingOffsetStart = 100000000;

        if ( segmentsPerLevel.length == 1) {
            buildPatterns(segmentsPerLevel, nodesPerSegment, nbrRulesForSegment, 'A', patterns, level, 0, 0, sharingOffsetStart, 0);
        } else {
            // TODO Was going to make a forrest of trees work, but ran out of time (mdp)
            //for (int i = 0; i < segmentsPerLevel[level+1]; i++) {
                buildPatterns(segmentsPerLevel, nodesPerSegment, nbrRulesForSegment, 'A', patterns, level, 0, 0, sharingOffsetStart, 0);
            //}
        }

        return patterns;
    }

    public int getNbrPatterns() {
        return nbrPatterns;
    }

    public void setNbrPatterns(int nbrPatterns) {
        this.nbrPatterns = nbrPatterns;
    }

    public int getNbrRules() {
        return nbrRules;
    }

    public void setNbrRules(int nbrRules) {
        this.nbrRules = nbrRules;
    }

    public int getNbrRulesPerGroup() {
        return nbrRulesPerGroup;
    }

    public void setNbrRulesPerGroup(int nbrRulesPerGroup) {
        this.nbrRulesPerGroup = nbrRulesPerGroup;
    }


}
