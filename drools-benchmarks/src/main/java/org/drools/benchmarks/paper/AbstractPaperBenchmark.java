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
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.drools.benchmarks.common.AbstractBenchmark;
import org.drools.benchmarks.common.providers.RulesWithSegmentsProvider;
import org.drools.benchmarks.domain.A;
import org.drools.benchmarks.domain.AbstractBean;
import org.drools.benchmarks.domain.B;
import org.drools.benchmarks.domain.BeanType;
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
import org.kie.api.runtime.rule.FactHandle;
import org.kie.internal.builder.conf.RuleEngineOption;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.Warmup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Warmup(iterations = 1)
@Measurement(iterations = 1)
@Fork(0)
public class AbstractPaperBenchmark extends AbstractBenchmark {
    private Logger logger = LoggerFactory.getLogger(getClass());
    protected RulesWithSegmentsProvider drlProvider;

    protected A                  a;
    protected List<AbstractBean> beanList = new ArrayList<>();
    protected List<AbstractBean> lastBeanList = new ArrayList<>();
    protected int exitValue;
    protected int agendaGroup;
    protected int nbrAgendaGroups;



    public void setupKieBase(final String firstConsequence, final String consequence, final String lastConsequence, final String lastOfGroupConsequence,
                             boolean constrainToPatternA,
                             int[] segmentsPerLevel, int[] nodesPerSegment, int nbrAgendaGroups, int nbrObjectsPerType,int lastRuleSalience, boolean singleLastBean,
                             int agendaGroup, int exitValue, String engineOption) {
        String suffixDrl = "";

        drlProvider = new RulesWithSegmentsProvider( suffixDrl, firstConsequence, consequence, lastConsequence, lastOfGroupConsequence,
                                                     lastRuleSalience, true, constrainToPatternA);

        String rules = drlProvider.getDrl(segmentsPerLevel, nodesPerSegment, nbrAgendaGroups);
        //System.out.println(rules);

        if (engineOption != null) {
            createKieBaseFromDrl( rules, RuleEngineOption.determineOption( engineOption ) );
        } else {
            createKieBaseFromDrl( rules );
        }

        System.out.println("\n");
        createData(nbrObjectsPerType, singleLastBean);

        this.agendaGroup = agendaGroup;
        this.nbrAgendaGroups = nbrAgendaGroups;
        this.exitValue = exitValue;
    }

    @Setup(Level.Iteration)
    @Override
    public void setup() {
        System.out.println("\n");
        System.out.println("setup ksession run()");

        // reset these, as tests change them.
        a.setId(0);
        a.setOtherValue(0);

        int id = 0;
        for ( AbstractBean bean : lastBeanList ) {
            bean.setId(id++);
        }

        Collections.shuffle(beanList, new Random(0L));

        createKieSession();
    }

    public void test() {
        System.out.println("\n");
        System.out.println("test run()");

        FactHandle fhA = kieSession.insert(a);
        for (AbstractBean bean : beanList) {
            kieSession.insert(bean);
        }

        kieSession.getAgenda().getAgendaGroup("Group" + agendaGroup).setFocus();

        int fired = kieSession.fireAllRules(exitValue);

        if (logger.isDebugEnabled()) {
            logger.debug( "fired {}", fired );
        }
    }

    private void createData(int objectsPerType, boolean singleLastBean) {
        int nbrPatterns = drlProvider.getNbrPatterns();
        int nbrRules = drlProvider.getNbrRules();

        a = new A(0, 0);

        int id = 0;
        int value = 0; // Every bean has a unique value, A has 0


        char beanType = 'A';

        newObject(BeanType.valueOf(String.valueOf(beanType)), id++, value++);


        // A and last reserve IDs that match rule number. The others must start from the rule number count.
        // except first and last. Those are test control patterns
        for (int i = nbrRules; i < nbrRules+nbrPatterns-2; i++) {
            beanType++;
            for (int j = 0; j < objectsPerType; j++) {
                AbstractBean bean = newObject(BeanType.valueOf(String.valueOf(beanType)), id++, value++);
                beanList.add(bean);
            }
        }

        // there are as many last beans as there are rules. One per rule.
        // id matches the rule id
        id = 0;
        beanType++;
        int lastBeanCount = singleLastBean ? 1 : nbrRules;
        for (int j = 0; j < lastBeanCount; j++) {
            AbstractBean bean = newObject(BeanType.valueOf(String.valueOf(beanType)), id++, value++);
            beanList.add(bean);
            lastBeanList.add(bean);
        }

    }

    private AbstractBean newObject(BeanType beanType, int id, int value)
    {
        if (logger.isDebugEnabled()) {
            logger.debug("new {}({},{})", beanType, id, value);
        }

        AbstractBean bean = null;
        switch(beanType) {
            case A: bean = new A(id, value); break;
            case B: bean = new B(id, value); break;
            case C: bean = new C(id, value); break;
            case D: bean = new D(id, value); break;
            case E: bean = new E(id, value); break;
            case F: bean = new F(id, value); break;
            case G: bean = new G(id, value); break;
            case H: bean = new H(id, value); break;
            case I: bean = new I(id, value); break;
            case J: bean = new J(id, value); break;
            case K: bean = new K(id, value); break;
            case L: bean = new L(id, value); break;
        }

        return bean;
    }

}