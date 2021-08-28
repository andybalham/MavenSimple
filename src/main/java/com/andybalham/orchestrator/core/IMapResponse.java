package com.andybalham.orchestrator.core;

@FunctionalInterface
public interface IMapResponse<TData, TRes> {
    void invoke(TData data, TRes response);
}
