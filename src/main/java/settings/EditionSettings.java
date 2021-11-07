package settings;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@SettingsClassDetails(name = "Edition Creation")
public class EditionSettings extends Settings {

  @SettingsFieldDetails(description = "The default subjects to use for the edition, mandatory")
  private List<EditionSubjectType> editionSubjectTypes;

  public EditionSettings() {
    super(SettingsType.EDITION);
  }

  @Override
  public Settings getExample() {
    var settings = new EditionSettings();
    settings.setEditionSubjectTypes(List.of(EditionSubjectType.CAMPAIGN_TITLE));
    return settings;
  }
}
