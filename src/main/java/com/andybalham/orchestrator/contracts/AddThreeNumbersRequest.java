package com.andybalham.orchestrator.contracts;

import com.andybalham.orchestrator.core.IActivityRequest;

public class AddThreeNumbersRequest implements IActivityRequest<TotalResponse> {
    public int a;
    public int b;
    public int c;
}
