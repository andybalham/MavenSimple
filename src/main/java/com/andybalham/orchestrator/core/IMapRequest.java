package com.andybalham.orchestrator.core;

@FunctionalInterface
public interface IMapRequest<TData, TReq extends IActivityRequest<TRes>, TRes> {
    TReq invoke(TData data);
}
