package com.andybalham.orchestrator.core;

public class ActivityStepDefinition<TReq extends IActivityRequest<TRes>, TRes, TData>
        extends StepDefinition {
    Class<IActivity<TReq, TRes>> activityInterface;
    IMapRequest<TData, TReq, TRes> mapRequest;
    IMapResponse<TData, TRes> mapResponse;
}

