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

        public final int a;
        public final int b;
        public final int c;
        public int total;

        public Data(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public AddThreeNumbersActivity(BeanFactory beanFactory) {
        super(
                beanFactory,
                (request) -> new Data(request.a, request.b, request.c),
                (data) -> new TotalResponse(data.total)
        );
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

