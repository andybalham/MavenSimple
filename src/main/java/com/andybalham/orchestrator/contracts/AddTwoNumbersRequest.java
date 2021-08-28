package com.andybalham.orchestrator.contracts;

import com.andybalham.orchestrator.core.IActivityRequest;

public class AddTwoNumbersRequest implements IActivityRequest<TotalResponse> {
    public int x;
    public int y;
}
