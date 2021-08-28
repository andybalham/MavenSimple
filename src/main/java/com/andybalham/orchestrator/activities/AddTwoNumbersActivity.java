package com.andybalham.orchestrator.activities;

import com.andybalham.orchestrator.contracts.AddTwoNumbersRequest;
import com.andybalham.orchestrator.contracts.IAddTwoNumbersActivity;
import com.andybalham.orchestrator.contracts.TotalResponse;
import org.springframework.stereotype.Service;

@Service
public class AddTwoNumbersActivity implements IAddTwoNumbersActivity {
    @Override
    public TotalResponse handle(AddTwoNumbersRequest request) {
        var total = request.x + request.y;
        return new TotalResponse(total);
    }
}

