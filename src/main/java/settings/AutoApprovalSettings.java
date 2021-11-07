package settings;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SettingsClassDetails(name = "Auto-Approval", description = "Controls aspects of auto-approval of editions")
public class AutoApprovalSettings extends Settings {

  @SettingsFieldDetails(description = "Whether auto-approval is enabled")
  private Boolean isEnabled;

  public AutoApprovalSettings() {
    super(SettingsType.AUTO_APPROVAL);
  }

  @Override
  public Settings getExample() {
    var settings = new AutoApprovalSettings();
    settings.setIsEnabled(true);
    return settings;
  }
}
