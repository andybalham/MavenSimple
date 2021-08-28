package com.andybalham.orchestrator.core;

public class DecisionBranch<TData> {

    final DecisionStepDefinition<TData> stepDefinition;

    public DecisionBranch(DecisionStepDefinition<TData> stepDefinition) {
        this.stepDefinition = stepDefinition;
    }

    public DecisionBranch<TData> when(
            IIsMatch<TData> isMatch,
            String targetId
    ) {
        this.stepDefinition.addBranch(isMatch, targetId);
        return this;
    }

    public OrchestrationBuilder<TData> otherwise(String targetId) {
        this.stepDefinition.setOtherwise(targetId);
        return this.stepDefinition.orchestrationBuilder;
    }
}
