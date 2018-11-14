package pt.isep.cms.students.server;

import pt.isep.cms.students.client.StudentsServiceAsync;
import pt.isep.cms.students.client.presenter.StudentsPresenter;
import pt.isep.cms.students.shared.Student;
import pt.isep.cms.students.shared.StudentDetails;
import pt.isep.cms.students.server.StudentsServiceImpl;

import java.util.ArrayList;
import junit.framework.TestCase;

import static org.easymock.EasyMock.createStrictMock;

public class StudentsServiceImplJRETest extends TestCase
{
	private StudentsServiceImpl cs;

	protected void setUp()
	{
		cs = new StudentsServiceImpl();
	}

	public void testGetUnexistingStudent()
	{
		Student c = new Student();
		assertTrue(cs.getStudent(c.getId()) == null);
	}

	public void testAddStudent()
	{
		Student c = new Student();
		assertTrue(cs.addStudent(c) == c);

		assertTrue(cs.getStudent(c.getId()) == c);
	}
}
