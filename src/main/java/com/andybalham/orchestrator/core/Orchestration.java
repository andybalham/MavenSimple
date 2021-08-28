package com.andybalham.orchestrator.core;

import org.springframework.beans.factory.BeanFactory;

import java.util.List;
import java.util.Objects;

public class Orchestration<TData> {

    private final List<StepDefinition> stepDefinitionList;

    public Orchestration(List<StepDefinition> stepDefinitionList) {
        this.stepDefinitionList = stepDefinitionList;
    }

    @SuppressWarnings("unchecked")
    protected TData execute(BeanFactory beanFactory, TData data) {

        int stepIndex = 0;

        do {
            var stepDefinition = this.stepDefinitionList.get(stepIndex);

            if (stepDefinition instanceof ActivityStepDefinition) {

                var activityDefinition = (ActivityStepDefinition<?, ?, TData>) stepDefinition;

                var request =
                        (activityDefinition.mapRequest == null)
                                ? null
                                : activityDefinition.mapRequest.invoke(data);

                var activity =
                        (IActivity<IActivityRequest<?>, ?>) beanFactory.getBean(activityDefinition.activityInterface);

                var response = activity.handle(request);

                var mapResponse = (IMapResponse<TData, Object>) activityDefinition.mapResponse;

                if (mapResponse != null) {
                    mapResponse.invoke(data, response);
                }

                stepIndex += 1;
            }

            if (stepDefinition instanceof EndStepDefinition) {
                stepIndex = Integer.MAX_VALUE;
            }

            if (stepDefinition instanceof DecisionStepDefinition) {

                var decisionStepDefinition = (DecisionStepDefinition<TData>) stepDefinition;

                String matchingTargetId = null;

                for (var branch : decisionStepDefinition.branches) {
                    if (branch.isMatch.invoke(data)) {
                        matchingTargetId = branch.targetId;
                        break;
                    }
                }

                var targetId =
                        matchingTargetId == null
                                ? decisionStepDefinition.otherwiseTargetId
                                : matchingTargetId;

                stepIndex = Integer.MAX_VALUE;

                for (int i = 0; i < this.stepDefinitionList.size(); i++) {
                    if (Objects.equals(this.stepDefinitionList.get(i).id, targetId)) {
                        stepIndex = i;
                        break;
                    }
                }
            }

        } while (stepIndex < this.stepDefinitionList.size());

        return data;
    }
}
