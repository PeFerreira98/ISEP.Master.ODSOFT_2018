package pt.isep.cms.students.shared;

import pt.isep.cms.students.shared.StudentDetails;
import pt.isep.cms.students.shared.Student;

import junit.framework.TestCase;

//import static org.easymock.EasyMock.createStrictMock;

public class StudentJRETest extends TestCase {
    protected void setUp() {
    }

    public void testNewEmptyStudent() {
        Student c = new Student();

        assertTrue(c.getId() == 0);
        assertTrue(c.getFirstName() == null);
        assertTrue(c.getLastName() == null);
        assertTrue(c.getEmailAddress() == null);
        assertTrue(c.getFullName() == null);
    }

    public void testNewStudentFullInfo() {
        Student c = new Student(1, "firstName", "lastName", "emailAddress");

        assertTrue(c.getId() == 1);
        assertTrue(c.getFirstName().equals("firstName"));
        assertTrue(c.getLastName().equals("lastName"));
        assertTrue(c.getEmailAddress().equals("emailAddress"));
        assertTrue(c.getFullName().equals("firstName lastName"));
    }

    public void testSetStudentInfo() {
        Student c = new Student();

        c.setId(1);
        c.setFirstName("firstName");
        c.setLastName("lastName");
        c.setEmailAddress("emailAddress");

        assertTrue(c.getId() == 1);
        assertTrue(c.getFirstName().equals("firstName"));
        assertTrue(c.getLastName().equals("lastName"));
        assertTrue(c.getEmailAddress().equals("emailAddress"));
        assertTrue(c.getFullName().equals("firstName lastName"));
    }

    public void testStudentDetails() {
        Student c = new Student(1, "firstName", "lastName", "emailAddress");

        assertTrue(c.getId() == c.getLightWeightStudent().getId());
        assertTrue(c.getFullName().equals(c.getLightWeightStudent().getDisplayName()));
    }
}
