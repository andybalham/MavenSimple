package settings;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static org.reflections.scanners.Scanners.SubTypes;

public abstract class Settings {
  @Getter
  private final SettingsType settingsType;

  public Settings(SettingsType settingsType) {
    this.settingsType = settingsType;
  }

  @SneakyThrows
  public static String getMarkdown() {
    var markdownBuilder = new StringBuilder();
    markdownBuilder.append("# Settings\n");

    Reflections reflections = new Reflections("settings");

    Set<Class<?>> settingsClasses =
        reflections.get(SubTypes.of(Settings.class).asClass());

    for (Class<?> settingsClass : settingsClasses) {

      var settingsClassDetails = settingsClass.getAnnotation(SettingsClassDetails.class);

      if (settingsClassDetails == null) {
        throw new IllegalStateException(settingsClass.getName() + " missing annotation");
      }
      var settingsConstructor = settingsClass.getConstructors()[0];

      var settings = (Settings) settingsConstructor.newInstance();

      markdownBuilder.append("## " + settingsClassDetails.name() + "\n");

      if (!Objects.equals(settingsClassDetails.description(), "")) {
        markdownBuilder.append(settingsClassDetails.description() + "\n\n");
      }

      markdownBuilder.append("Type: `" + settings.getSettingsType() + "`\n\n");

      markdownBuilder.append("### Properties: \n");

      var fields = settingsClass.getDeclaredFields();
      for (var field : fields) {
        var settingsFieldDetails = field.getAnnotation(SettingsFieldDetails.class);

        if (settingsFieldDetails == null) {
          throw new IllegalStateException(settingsClass.getName() + "." + field.getName() + " missing annotation");
        }

        var fieldTypeDescription = getFieldTypeDescription(field);

        String fieldSummary =
            "`" + field.getName()
                + "`: " + settingsFieldDetails.description()
                + " (" + fieldTypeDescription + ")";

        markdownBuilder.append("* " + fieldSummary + "\n");
      }

      markdownBuilder.append("### Example: \n");

      markdownBuilder.append("```json\n");

      Gson gson = new GsonBuilder()
          .setPrettyPrinting()
          .create();
      var exampleJSON = gson.toJson(settings.getExample());
      markdownBuilder.append(exampleJSON + "\n");

      markdownBuilder.append("```\n");
    }

    return markdownBuilder.toString();
  }

  private static String getFieldTypeDescription(Field field) {

    Class<?> fieldClass = field.getType();

    String fieldDescription;

    if (fieldClass == List.class) {
      var listType = (ParameterizedType) field.getGenericType();
      var itemClass = (Class<?>) listType.getActualTypeArguments()[0];
      fieldDescription = "array of " + getClassDescription(itemClass);
    } else {
      fieldDescription = getClassDescription(fieldClass);
    }

    return fieldDescription;
  }

  private static String getClassDescription(Class<?> clazz) {

    var classDescription = "`object`";

    if (clazz == Boolean.class) {
      classDescription = "`boolean`";
    } else if (clazz == String.class) {
      classDescription = "`string`";
    } else if (clazz == Integer.class
        || clazz == Long.class
        || clazz == Double.class) {
      classDescription = "`number`";
    } else if (clazz.isEnum()) {
      classDescription =
          "[`" + Arrays.stream(clazz.getEnumConstants())
              .map(Object::toString).collect(Collectors.joining("` | `")) + "`]";
    }

    return classDescription;
  }


  public abstract Settings getExample();
}
