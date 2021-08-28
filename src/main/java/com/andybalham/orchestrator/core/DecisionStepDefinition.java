package com.andybalham.orchestrator.core;

import java.util.ArrayList;

public class DecisionStepDefinition<TData>
        extends StepDefinition {

    OrchestrationBuilder<TData> orchestrationBuilder;
    ArrayList<Branch<TData>> branches = new ArrayList<Branch<TData>>();
    String otherwiseTargetId;

    public static class Branch<TData> {
        IIsMatch<TData> isMatch;
        String targetId;
    }

    public DecisionStepDefinition(OrchestrationBuilder<TData> orchestrationBuilder) {
        this.orchestrationBuilder = orchestrationBuilder;
    }

    public void addBranch(IIsMatch<TData> isMatch, String targetId) {

        var branchIsMatch = isMatch;
        var branchTargetId = targetId;

        this.branches.add(new Branch<TData>() {{
            isMatch = branchIsMatch;
            targetId = branchTargetId;
        }});
    }

    public void setOtherwise(String targetId) {
        this.otherwiseTargetId = targetId;
    }
}

