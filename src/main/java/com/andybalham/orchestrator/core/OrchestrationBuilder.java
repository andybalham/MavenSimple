package com.andybalham.orchestrator.core;

import java.util.ArrayList;
import java.util.List;

public class OrchestrationBuilder<TData> {

    private final List<StepDefinition> stepDefinitionList = new ArrayList<StepDefinition>();

    public Orchestration<TData> build() {
        return new Orchestration<TData>(this.stepDefinitionList);
    }

    @SuppressWarnings("unchecked")
    public <TAct extends IActivity<TReq, TRes>, TReq extends IActivityRequest<TRes>, TRes> OrchestrationBuilder<TData> addActivity(
            String id,
            Class<TAct> _activityInterface,
            IMapRequest<TData, TReq, TRes> _mapRequest,
            IMapResponse<TData, TRes> _mapResponse) {

        var _id = id;

        var activityDefinition = new ActivityStepDefinition<TReq, TRes, TData>() {{
            id = _id;
            activityInterface = (Class<IActivity<TReq, TRes>>) _activityInterface;
            mapRequest = _mapRequest;
            mapResponse = _mapResponse;
        }};

        this.stepDefinitionList.add(activityDefinition);

        return this;
    }

    public DecisionBranch<TData> addDecision(String id) {

        final var stepId = id;

        var stepDefinition = new DecisionStepDefinition<TData>(this) {{
            id = stepId;
        }};

        this.stepDefinitionList.add(stepDefinition);

        return new DecisionBranch<TData>(stepDefinition);
    }

    public OrchestrationBuilder<TData> addEnd() {

        var endStepCount =
                this.stepDefinitionList.stream().filter(sd -> sd instanceof EndStepDefinition).count();

        this.stepDefinitionList.add(new EndStepDefinition() {{
            id = String.format("End_%d", endStepCount);
        }});

        return this;
    }
}
