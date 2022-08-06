package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.PrintWriter;
import java.io.StringWriter;

class AppTest {

	@Test
	public void testApp() throws Exception {
		App app = new App();
		StringWriter sw = new StringWriter();
		CommandLine cmd = new CommandLine(app);
		cmd.setOut(new PrintWriter(sw));

		int exitCode = cmd.execute("-format ./file1.json ./file2.json");
		//Assertions.assertEquals(0, exitCode);
		//Assertions.assertEquals("Your output is abc...", sw.toString());
	}
}
