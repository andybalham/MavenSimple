package com.andybalham.orchestrator.core;

@FunctionalInterface
public interface IInitialiseOrchestration<TData, TReq> {
    TData invoke(TReq request);
}
