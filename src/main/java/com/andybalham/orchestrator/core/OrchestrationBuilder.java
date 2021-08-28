package com.andybalham.orchestrator.core;

import java.util.ArrayList;
import java.util.List;

public class OrchestrationBuilder<TData> {

    private final List<StepDefinition> stepDefinitionList = new ArrayList<>();

    public Orchestration<TData> build() {
        return new Orchestration<>(this.stepDefinitionList);
    }

    @SuppressWarnings("unchecked")
    public <TAct extends IActivity<TReq, TRes>, TReq extends IActivityRequest<TRes>, TRes> OrchestrationBuilder<TData> addActivity(
            String id,
            Class<TAct> activityInterface,
            IMapRequest<TData, TReq, TRes> mapRequest,
            IMapResponse<TData, TRes> mapResponse) {

        var activityDefinition =
                new ActivityStepDefinition<>(
                        id, (Class<IActivity<TReq, TRes>>) activityInterface, mapRequest, mapResponse);

        this.stepDefinitionList.add(activityDefinition);

        return this;
    }

    public DecisionBranch<TData> addDecision(String id) {

        var stepDefinition = new DecisionStepDefinition<>(id, this);

        this.stepDefinitionList.add(stepDefinition);

        return new DecisionBranch<>(stepDefinition);
    }

    public OrchestrationBuilder<TData> addEnd() {

        var endStepCount =
                this.stepDefinitionList.stream().filter(sd -> sd instanceof EndStepDefinition).count();

        this.stepDefinitionList.add(new EndStepDefinition(String.format("End_%d", endStepCount)));

        return this;
    }
}
