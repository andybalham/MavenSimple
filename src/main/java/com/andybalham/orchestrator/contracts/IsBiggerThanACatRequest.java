package com.andybalham.orchestrator.contracts;

import com.andybalham.orchestrator.core.IActivityRequest;

public class IsBiggerThanACatRequest implements IActivityRequest<YesNoResponse> {

    public final AnimalType animal;

    public IsBiggerThanACatRequest(AnimalType animal) {
        this.animal = animal;
    }
}
