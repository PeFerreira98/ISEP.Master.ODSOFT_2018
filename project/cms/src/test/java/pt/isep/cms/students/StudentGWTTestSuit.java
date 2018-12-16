package pt.isep.cms.students;

import com.google.gwt.junit.tools.GWTTestSuite;

import junit.framework.Test;
import junit.framework.TestSuite;
import pt.isep.cms.students.client.ExampleGWTTest;
import pt.isep.cms.students.client.StudentsServiceAsyncGWTTest;

public class StudentGWTTestSuit extends GWTTestSuite {
    public static Test suite() {
        TestSuite suite = new TestSuite("Test for the Students Application");
        suite.addTestSuite(ExampleGWTTest.class);
        suite.addTestSuite(StudentsServiceAsyncGWTTest.class);
        return suite;
    }
}
