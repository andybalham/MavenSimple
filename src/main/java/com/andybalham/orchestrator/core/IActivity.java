package com.andybalham.orchestrator.core;

public interface IActivity<TReq extends IActivityRequest<TRes>, TRes> {
    TRes handle(TReq request);
}

