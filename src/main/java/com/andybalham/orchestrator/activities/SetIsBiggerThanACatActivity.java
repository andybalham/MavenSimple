package com.andybalham.orchestrator.activities;

import com.andybalham.orchestrator.contracts.BooleanResponse;
import com.andybalham.orchestrator.contracts.SetIsBiggerThanACatRequest;
import com.andybalham.orchestrator.core.IActivity;
import org.springframework.stereotype.Service;

@Service
public class SetIsBiggerThanACatActivity implements IActivity<SetIsBiggerThanACatRequest, BooleanResponse> {
    @Override
    public BooleanResponse handle(SetIsBiggerThanACatRequest request) {

        final boolean isBiggerThanACat;

        switch (request.animal) {
            case Dog:
            case Pig:
                isBiggerThanACat = true;
                break;
            default:
                isBiggerThanACat = false;
                break;
        }

        return new BooleanResponse(isBiggerThanACat);
    }
}
