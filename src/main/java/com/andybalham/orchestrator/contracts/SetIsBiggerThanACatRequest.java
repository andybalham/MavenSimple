package com.andybalham.orchestrator.contracts;

import com.andybalham.orchestrator.core.IActivityRequest;

public class SetIsBiggerThanACatRequest implements IActivityRequest<YesNoResponse> {
    public final AnimalType animal;

    public SetIsBiggerThanACatRequest(AnimalType animal) {
        this.animal = animal;
    }
}
