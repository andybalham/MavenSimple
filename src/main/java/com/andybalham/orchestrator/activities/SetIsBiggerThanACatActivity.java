package com.andybalham.orchestrator.activities;

import com.andybalham.orchestrator.contracts.SetIsBiggerThanACatRequest;
import com.andybalham.orchestrator.contracts.YesNoResponse;
import com.andybalham.orchestrator.contracts.YesNoType;
import com.andybalham.orchestrator.core.IActivity;
import org.springframework.stereotype.Service;

@Service
public class SetIsBiggerThanACatActivity implements IActivity<SetIsBiggerThanACatRequest, YesNoResponse> {
    @Override
    public YesNoResponse handle(SetIsBiggerThanACatRequest request) {

        final YesNoType isBiggerThanACat;

        switch (request.animal) {
            case Dog:
            case Pig:
                isBiggerThanACat = YesNoType.Yes;
                break;
            default:
                isBiggerThanACat = YesNoType.No;
                break;
        }

        return new YesNoResponse(isBiggerThanACat);
    }
}
