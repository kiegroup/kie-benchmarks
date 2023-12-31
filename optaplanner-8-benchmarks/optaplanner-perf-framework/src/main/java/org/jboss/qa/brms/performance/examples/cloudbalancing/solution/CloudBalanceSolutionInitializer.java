package org.jboss.qa.brms.performance.examples.cloudbalancing.solution;

import org.optaplanner.core.api.score.director.ScoreDirector;
import org.optaplanner.core.impl.score.director.InnerScoreDirector;
import org.optaplanner.examples.cloudbalancing.domain.CloudBalance;
import org.optaplanner.examples.cloudbalancing.domain.CloudProcess;
import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.impl.phase.custom.CustomPhaseCommand;

public class CloudBalanceSolutionInitializer implements CustomPhaseCommand<CloudBalance> {

    @Override
    public void changeWorkingSolution(ScoreDirector<CloudBalance> scoreDirector) {
        CloudBalance cb = scoreDirector.getWorkingSolution();
        int i = 0;
        for (CloudProcess p : cb.getProcessList()) {
            scoreDirector.beforeVariableChanged(p, "computer");
            p.setComputer(cb.getComputerList().get(i % cb.getComputerList().size()));
            scoreDirector.afterVariableChanged(p, "computer");
            i++;
        }
        scoreDirector.triggerVariableListeners();

        InnerScoreDirector<CloudBalance, ?> innerScoreDirector =
                (InnerScoreDirector<CloudBalance, ?>) scoreDirector;
        Score<?> score = innerScoreDirector.calculateScore();

        if (!score.isSolutionInitialized()) {
            throw new IllegalStateException(
                    "The solution [" + CloudBalance.class.getSimpleName()
                    + "] was not fully initialized by CustomSolverPhase: "
                    + this.getClass().getCanonicalName());
        }
    }
}
