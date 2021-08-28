package com.andybalham.orchestrator.core;

import org.springframework.beans.factory.BeanFactory;

public abstract class OrchestratorActivity<TReq extends IActivityRequest<TRes>, TRes, TData> implements IActivity<TReq, TRes> {

    private final BeanFactory beanFactory;
    private final IInitialiseOrchestration<TData, TReq> initialiseOrchestration;
    private final IFinaliseOrchestration<TData, TRes> finaliseOrchestration;

    public OrchestratorActivity(
            BeanFactory beanFactory,
            IInitialiseOrchestration<TData, TReq> initialiseOrchestration,
            IFinaliseOrchestration<TData, TRes> finaliseOrchestration
    ) {
        this.beanFactory = beanFactory;
        this.initialiseOrchestration = initialiseOrchestration;
        this.finaliseOrchestration = finaliseOrchestration;
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    @Override
    public TRes handle(TReq request) {

        var data = this.initialiseOrchestration.invoke(request);

        var builder = new OrchestrationBuilder<TData>();

        build(builder);

        var orchestration = builder.build();

        orchestration.execute(this.beanFactory, data);

        var response = this.finaliseOrchestration.invoke(data);

        return response;
    }

    protected abstract void build(OrchestrationBuilder<TData> builder);
}
