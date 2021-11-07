package settings;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SettingsClassDetails(
    name = "Subscriber Confirmation",
    description = "Controls aspects of subscriber confirmation")
public class SubscriberConfirmationSettings extends Settings {

  @SettingsFieldDetails(description = "Whether subscriber confirmation is enabled, mandatory")
  private Boolean isEnabled;
  @SettingsFieldDetails(description = "The subject line for the confirmation email, mandatory when `isEnabled` is `true`")
  private String emailSubject;

  public SubscriberConfirmationSettings() {
    super(SettingsType.SUBSCRIBER_CONFIRMATION);
  }

  @Override
  public Settings getExample() {
    var settings = new SubscriberConfirmationSettings();
    settings.setIsEnabled(true);
    settings.setEmailSubject("Email Subject");
    return settings;
  }
}
