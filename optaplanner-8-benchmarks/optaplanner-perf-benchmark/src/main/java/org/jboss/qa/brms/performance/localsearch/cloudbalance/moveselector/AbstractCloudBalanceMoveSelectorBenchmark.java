package org.jboss.qa.brms.performance.localsearch.cloudbalance.moveselector;

import org.jboss.qa.brms.performance.configuration.AcceptorConfigurations;
import org.jboss.qa.brms.performance.examples.cloudbalancing.termination.CloudBalanceCalculateCountTermination;
import org.jboss.qa.brms.performance.localsearch.cloudbalance.AbstractCloudBalanceLocalSearchBenchmark;
import org.optaplanner.core.config.localsearch.decider.acceptor.LocalSearchAcceptorConfig;
import org.optaplanner.core.config.solver.termination.TerminationConfig;

public abstract class AbstractCloudBalanceMoveSelectorBenchmark extends AbstractCloudBalanceLocalSearchBenchmark {

    @Override
    public LocalSearchAcceptorConfig createAcceptorConfig() {
        return AcceptorConfigurations.createSimulatedAnnealingAcceptor("0hard/0soft");
    }

    @Override
    public int getAcceptedCountLimit() {
        return 1000000;
    }

    @Override
    public TerminationConfig getTerminationConfig() {
        TerminationConfig terminationConfig = new TerminationConfig();
        terminationConfig.setTerminationClass(CloudBalanceCalculateCountTermination.class);
        return terminationConfig;
    }
}
