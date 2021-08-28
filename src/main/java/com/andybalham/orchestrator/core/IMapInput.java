package com.andybalham.orchestrator.core;

@FunctionalInterface
public interface IMapInput<TData, TCriteria> {
    TCriteria invoke(TData data);
}
