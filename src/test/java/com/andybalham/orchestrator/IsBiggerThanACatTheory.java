package com.andybalham.orchestrator;

import com.andybalham.orchestrator.contracts.AnimalType;
import com.andybalham.orchestrator.contracts.YesNoType;

public class IsBiggerThanACatTheory {
    public final AnimalType animal;
    public final YesNoType expectedAnswer;

    public IsBiggerThanACatTheory(AnimalType animal, YesNoType expectedAnswer) {
        this.animal = animal;
        this.expectedAnswer = expectedAnswer;
    }
}
