/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
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

package org.drools.benchmarks.common;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.StatelessKnowledgeSession;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;

import java.io.StringReader;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.SingleShotTime)
@State(Scope.Thread)
@Warmup(iterations = 30)
@Measurement(iterations = 20)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public abstract class AbstractBenchmark {

    protected boolean holdSessionReferences = false;
    protected boolean reuseKieBase = false;

    protected KnowledgeBase kieBase;
    protected StatefulKnowledgeSession kieSession;
    protected StatelessKnowledgeSession statelessSession;

    protected boolean isSmokeTestsRun = TestUtil.isSmokeTestsRun();

    public abstract void setup();

    @TearDown(Level.Iteration)
    public void tearDown() {
        if (kieSession != null) {
            kieSession.dispose();
            kieSession = null;
        }
        statelessSession = null;
    }

    protected void createKieSession() {
        kieSession = kieBase.newStatefulKnowledgeSession();
    }

    protected void createStatelessSession() {
        statelessSession = kieBase.newStatelessKnowledgeSession();
    }

    protected void createEmptyKieBase() {
        kieBase = KnowledgeBaseFactory.newKnowledgeBase();
    }

    protected void createKieBaseFromDrl(final String drl) {
        final KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newReaderResource(new StringReader(drl)),
                ResourceType.DRL);

        if (kbuilder.hasErrors()) {
            final String lineSeparator = System.getProperty("line.separator");
            final StringBuilder errorsBuilder = new StringBuilder();
            final Iterator<KnowledgeBuilderError> errors = kbuilder.getErrors().iterator();
            while (errors.hasNext()) {
                errorsBuilder.append("kbuilder error: " + errors.next().getMessage());
                errorsBuilder.append(lineSeparator);
            }
            throw new IllegalStateException("DRL contains errors: " + errorsBuilder.toString());
        }

        kieBase = KnowledgeBaseFactory.newKnowledgeBase();
        kieBase.addKnowledgePackages(kbuilder.getKnowledgePackages());
    }
}