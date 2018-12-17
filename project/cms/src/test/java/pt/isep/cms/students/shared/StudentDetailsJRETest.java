package pt.isep.cms.students.shared;

import pt.isep.cms.students.shared.StudentDetails;
import pt.isep.cms.students.shared.Student;

import junit.framework.TestCase;

import static org.easymock.EasyMock.createStrictMock;

public class StudentDetailsJRETest extends TestCase {
    protected void setUp() {
    }

    public void testNewEmptyStudentDetails() {
        StudentDetails cd = new StudentDetails();

        assertTrue(cd.getId() == 0);
        assertTrue(cd.getDisplayName().equals(""));
    }

    public void testNewStudentDetailsFullInfo() {
        StudentDetails cd = new StudentDetails(1, "DisplayName");

        assertTrue(cd.getId() == 1);
        assertTrue(cd.getDisplayName().equals("DisplayName"));
    }

    public void testSetStudentInfo() {
        StudentDetails cd = new StudentDetails();

        cd.setId(1);
        cd.setDisplayName("DisplayName");

        assertTrue(cd.getId() == 1);
        assertTrue(cd.getDisplayName().equals("DisplayName"));
    }
}
