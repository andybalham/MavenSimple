package com.andybalham.orchestrator.contracts;

import com.andybalham.orchestrator.core.IActivityRequest;

public class SetYesNoRequest implements IActivityRequest<YesNoResponse> {
    public final YesNoType yesNo;

    public SetYesNoRequest(YesNoType yesNo) {
        this.yesNo = yesNo;
    }
}
