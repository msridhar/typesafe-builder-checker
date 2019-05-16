import java.io.File;
import java.util.List;

import org.checkerframework.checker.returnsreceiver.ReturnsReceiverChecker;
import org.checkerframework.framework.test.CheckerFrameworkPerDirectoryTest;
import org.junit.runners.Parameterized.Parameters;

public class RetrevTest extends CheckerFrameworkPerDirectoryTest{
	public RetrevTest(List<File> testFiles) {
		super(testFiles,
				ReturnsReceiverChecker.class,
				"returnsreceiver",
				"-Anomsgtext",
				"-nowarn"
				);
	}
	
	@Parameters
	public static String[] getTestDirs() {
		return new String[] {"returnsreceiver"};
	}
}

