package com.andybalham.orchestrator.contracts;

import com.andybalham.orchestrator.core.IActivityRequest;

public class AddTwoNumbersRequest implements IActivityRequest<TotalResponse> {

    public final int x;
    public final int y;

    public AddTwoNumbersRequest(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
