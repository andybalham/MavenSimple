package com.andybalham.orchestrator.core;

@FunctionalInterface
public interface IFinaliseOrchestration<TData, TRes> {
    TRes invoke(TData data);
}
