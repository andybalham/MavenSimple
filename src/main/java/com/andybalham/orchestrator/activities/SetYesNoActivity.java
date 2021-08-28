package com.andybalham.orchestrator.activities;

import com.andybalham.orchestrator.contracts.SetYesNoRequest;
import com.andybalham.orchestrator.contracts.YesNoResponse;
import com.andybalham.orchestrator.core.IActivity;
import org.springframework.stereotype.Service;

@Service
public class SetYesNoActivity implements IActivity<SetYesNoRequest, YesNoResponse> {
    @Override
    public YesNoResponse handle(SetYesNoRequest request) {
        return new YesNoResponse() {{
            answer = request.yesNo;
        }};
    }
}
