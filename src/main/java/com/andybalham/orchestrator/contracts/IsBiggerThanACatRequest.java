package com.andybalham.orchestrator.contracts;

import com.andybalham.orchestrator.core.IActivityRequest;

public class IsBiggerThanACatRequest implements IActivityRequest<YesNoResponse> {
    public AnimalType animal;
}
