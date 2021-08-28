package com.andybalham.orchestrator.core;

import java.util.ArrayList;

public class DecisionStepDefinition<TData>
        extends StepDefinition {

    OrchestrationBuilder<TData> orchestrationBuilder;
    ArrayList<Branch<TData>> branches = new ArrayList<>();
    String otherwiseTargetId;

    public static class Branch<TData> {

        public final IIsMatch<TData> isMatch;
        public final String targetId;

        public Branch(IIsMatch<TData> isMatch, String targetId) {
            this.isMatch = isMatch;
            this.targetId = targetId;
        }
    }

    public DecisionStepDefinition(String id, OrchestrationBuilder<TData> orchestrationBuilder) {
        super(id);
        this.orchestrationBuilder = orchestrationBuilder;
    }

    public void addBranch(IIsMatch<TData> isMatch, String targetId) {
        this.branches.add(new Branch<>(isMatch, targetId));
    }

    public void setOtherwise(String targetId) {
        this.otherwiseTargetId = targetId;
    }
}

