package settings;

import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;

public class SettingsDocumentationTest {
  @Test
  public void outputDocumentation() throws IOException {
    String settingsMarkdown = Settings.getMarkdown();

    var userDirPath = Paths.get(System.getProperty("user.dir"));
    var settingsPath = userDirPath.resolve("Settings.md");

    FileWriter fileWriter = new FileWriter(settingsPath.toString());
    PrintWriter printWriter = new PrintWriter(fileWriter);
    printWriter.print(settingsMarkdown);
    printWriter.close();
  }
}
