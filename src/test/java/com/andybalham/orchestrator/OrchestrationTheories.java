package com.andybalham.orchestrator;

import com.andybalham.orchestrator.contracts.AnimalType;
import com.andybalham.orchestrator.contracts.IIsBiggerThanACatActivity;
import com.andybalham.orchestrator.contracts.IsBiggerThanACatRequest;
import com.andybalham.orchestrator.contracts.YesNoType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@RunWith(Theories.class)
public class OrchestrationTheories {

    private static ConfigurableApplicationContext context;

    @BeforeClass
    public static void setup() {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @DataPoints
    public static IsBiggerThanACatTheory[] isBiggerThanACatTheories =
            {
                    new IsBiggerThanACatTheory(AnimalType.Pig, YesNoType.Yes),
                    new IsBiggerThanACatTheory(AnimalType.Dog, YesNoType.Yes),
                    new IsBiggerThanACatTheory(AnimalType.Cat, YesNoType.Yes),
                    new IsBiggerThanACatTheory(AnimalType.Rabbit, YesNoType.Yes),
                    new IsBiggerThanACatTheory(AnimalType.Mouse, YesNoType.Yes),
            };

    @Theory
    public void canExecuteIsBiggerThanACatActivityTheory(IsBiggerThanACatTheory theory) throws JsonProcessingException {

        var theoryDescription = new ObjectMapper().writeValueAsString(theory);

//        GsonBuilder builder = new GsonBuilder();
//        Gson gson = builder.create();
//        var theoryDescription = gson.toJson(theory);

        var activity = context.getBean(IIsBiggerThanACatActivity.class);

        var response =
                activity.handle(new IsBiggerThanACatRequest() {{
                    animal = theory.animal;
                }});

        Assert.assertEquals(theoryDescription + ": response.yesNoType", response.answer, theory.expectedAnswer);
    }
}

