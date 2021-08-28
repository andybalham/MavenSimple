package com.andybalham.orchestrator.contracts;

import com.andybalham.orchestrator.core.IActivityRequest;

public class SetYesNoRequest implements IActivityRequest<YesNoResponse> {
    public final YesNoType value;

    public SetYesNoRequest(YesNoType value) {
        this.value = value;
    }
}
