package com.andybalham.orchestrator.core;

public abstract class StepDefinition {

    public final String id;

    protected StepDefinition(String id) {
        this.id = id;
    }
}
