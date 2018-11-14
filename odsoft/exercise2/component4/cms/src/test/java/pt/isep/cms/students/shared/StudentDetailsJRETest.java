package pt.isep.cms.students.shared;

import pt.isep.cms.students.shared.StudentDetails;
import pt.isep.cms.students.shared.Student;

import junit.framework.TestCase;

import static org.easymock.EasyMock.createStrictMock;

public class StudentDetailsJRETest extends TestCase
{
	protected void setUp()
	{
	}

	public void testNewEmptyStudentDetails()
	{
		StudentDetails cd = new StudentDetails();

		assertTrue(cd.getId().equals("0"));
		assertTrue(cd.getDisplayName().equals(""));
	}

	public void testNewStudentDetailsFullInfo()
	{
		StudentDetails cd = new StudentDetails("id", "DisplayName");

		assertTrue(cd.getId().equals("id"));
		assertTrue(cd.getDisplayName().equals("DisplayName"));
	}

	public void testSetStudentInfo()
	{
		StudentDetails cd = new StudentDetails();

		cd.setId("id");
		cd.setDisplayName("DisplayName");

		assertTrue(cd.getId().equals("id"));
		assertTrue(cd.getDisplayName().equals("DisplayName"));
	}
}
