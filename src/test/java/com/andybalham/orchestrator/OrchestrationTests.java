package com.andybalham.orchestrator;

import com.andybalham.orchestrator.contracts.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrchestrationTests {

    private static ConfigurableApplicationContext context;

    @BeforeClass
    public static void setup() {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @Test
    public void canExecuteAddThreeNumbersActivity() {

        var addThreeNumbersActivity = context.getBean(IAddThreeNumbersActivity.class);

        var response =
                addThreeNumbersActivity.handle(new AddThreeNumbersRequest() {{
                    a = 1;
                    b = 2;
                    c = 3;
                }});

        Assert.assertEquals("response.total", response.total, 6);
    }

    @Test
    public void canExecuteIsBiggerThanACatActivity() {

        var activity = context.getBean(IIsBiggerThanACatActivity.class);

        var response =
                activity.handle(new IsBiggerThanACatRequest() {{
                    animal = AnimalType.Dog;
                }});

        Assert.assertEquals("response.yesNoType", response.answer, YesNoType.Yes);
    }
}
