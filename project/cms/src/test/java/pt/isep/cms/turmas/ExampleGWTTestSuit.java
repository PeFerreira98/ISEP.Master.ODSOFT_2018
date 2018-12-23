package pt.isep.cms.turmas;

import com.google.gwt.junit.tools.GWTTestSuite;

import junit.framework.Test;
import junit.framework.TestSuite;
import pt.isep.cms.turmas.client.ExampleGWTTest;
import pt.isep.cms.turmas.client.TurmasServiceAsyncGWTTest;

public class ExampleGWTTestSuit extends GWTTestSuite {
    public static Test suite() {
        TestSuite suite = new TestSuite("Test for the Turmas Application");
        suite.addTestSuite(ExampleGWTTest.class);
        suite.addTestSuite(TurmasServiceAsyncGWTTest.class);
        return suite;
    }
}
