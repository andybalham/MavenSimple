package com.andybalham.orchestrator.activities;

import com.andybalham.orchestrator.contracts.*;
import com.andybalham.orchestrator.core.OrchestrationBuilder;
import com.andybalham.orchestrator.core.OrchestratorActivity;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

@Service
public class IsBiggerThanACatActivity
    extends OrchestratorActivity<IsBiggerThanACatRequest, YesNoResponse, IsBiggerThanACatActivity.Data>
    implements IIsBiggerThanACatActivity {

  public IsBiggerThanACatActivity(BeanFactory beanFactory) {
    super(
        beanFactory,
        request -> new Data(request.animal),
        data -> new YesNoResponse(data.answer)
    );
  }

  public static class Data {

    public final AnimalType animal;
    public boolean isBiggerThanACat;
    public YesNoType answer;

    public Data(AnimalType animal) {
      this.animal = animal;
    }
  }

  @Override
  protected void build(OrchestrationBuilder<Data> builder) {
    builder
        .perform(
            "SetIsBiggerThanACat", SetIsBiggerThanACatActivity.class,
            data -> new SetIsBiggerThanACatRequest(data.animal),
            (data, response) -> data.isBiggerThanACat = response.value)

        .choice("IsBiggerThanACat")
        .when(data -> data.isBiggerThanACat, "SetYes")
        .otherwise("SetNo")

        .perform(
            "SetYes", SetYesNoActivity.class,
            data -> new SetYesNoRequest(YesNoType.Yes),
            (data, response) -> data.answer = response.value)
        .end()

        .perform(
            "SetNo", SetYesNoActivity.class,
            data -> new SetYesNoRequest(YesNoType.No),
            (data, response) -> data.answer = response.value)
        .end()
    ;
  }
}
