package com.andybalham.orchestrator.core;

public abstract class Decision<TInput, TOutput> {
    public abstract TOutput getOutput(TInput input);
}
