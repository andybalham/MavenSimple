package settings;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SettingsClassDetails {
  String name();

  String description() default "";
}
