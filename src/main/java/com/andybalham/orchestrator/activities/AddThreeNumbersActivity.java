package com.andybalham.orchestrator.activities;

import com.andybalham.orchestrator.contracts.*;
import com.andybalham.orchestrator.core.OrchestrationBuilder;
import com.andybalham.orchestrator.core.OrchestratorActivity;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

@Service
public class AddThreeNumbersActivity
        extends OrchestratorActivity<AddThreeNumbersRequest, TotalResponse, AddThreeNumbersActivity.Data>
        implements IAddThreeNumbersActivity {

    public static class Data {
        public int a;
        public int b;
        public int c;
        public int total;
    }

    public AddThreeNumbersActivity(BeanFactory beanFactory) {
        super(
                beanFactory,
                (request) -> new Data() {{
                    a = request.a;
                    b = request.b;
                    c = request.c;
                }},
                (data) -> new TotalResponse() {{
                    total = data.total;
                }});
    }

    @Override
    protected void build(OrchestrationBuilder<Data> builder) {
        builder
                .addActivity(
                        "AddA&B", IAddTwoNumbersActivity.class,
                        data -> new AddTwoNumbersRequest() {{
                            x = data.a;
                            y = data.b;
                        }},
                        (data, response) -> data.total = response.total
                )
                .addActivity(
                        "AddC&Total", IAddTwoNumbersActivity.class,
                        data -> new AddTwoNumbersRequest() {{
                            x = data.c;
                            y = data.total;
                        }},
                        (data, response) -> data.total = response.total
                )
        ;
    }
}

