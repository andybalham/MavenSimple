package com.andybalham.orchestrator.contracts;

import com.andybalham.orchestrator.core.IActivityRequest;

public class AddThreeNumbersRequest implements IActivityRequest<TotalResponse> {

    public final int a;
    public final int b;
    public final int c;

    public AddThreeNumbersRequest(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}
