package com.andybalham.orchestrator.core;

public class ActivityStepDefinition<TReq extends IActivityRequest<TRes>, TRes, TData>
        extends StepDefinition {

    public final Class<IActivity<TReq, TRes>> activityInterface;
    public final IMapRequest<TData, TReq, TRes> mapRequest;
    public final IMapResponse<TData, TRes> mapResponse;

    public ActivityStepDefinition(
            String id,
            Class<IActivity<TReq, TRes>> activityInterface,
            IMapRequest<TData, TReq, TRes> mapRequest,
            IMapResponse<TData, TRes> mapResponse
    ) {
        super(id);
        this.activityInterface = activityInterface;
        this.mapRequest = mapRequest;
        this.mapResponse = mapResponse;
    }
}

