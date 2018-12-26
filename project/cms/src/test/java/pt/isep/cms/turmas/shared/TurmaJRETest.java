package pt.isep.cms.turmas.shared;

import pt.isep.cms.turmas.shared.TurmaDetails;
import pt.isep.cms.turmas.shared.Turma;

import junit.framework.TestCase;

//import static org.easymock.EasyMock.createStrictMock;

public class TurmaJRETest extends TestCase {
    protected void setUp() {
    }

    public void testNewEmptyTurma() {
        Turma c = new Turma();

        assertTrue(c.getId() == 0);
        assertTrue(c.getFirstName() == null);
        assertTrue(c.getLastName() == null);
        assertTrue(c.getEmailAddress() == null);
        assertTrue(c.getFullName() == null);
    }

    public void testNewTurmaFullInfo() {
        Turma c = new Turma(1, "firstName", "lastName", "emailAddress");

        assertTrue(c.getId() == 1);
        assertTrue(c.getFirstName().equals("firstName"));
        assertTrue(c.getLastName().equals("lastName"));
        assertTrue(c.getEmailAddress().equals("emailAddress"));
        assertTrue(c.getFullName().equals("firstName lastName"));
    }

    public void testSetTurmaInfo() {
        Turma c = new Turma();

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

    public void testTurmaDetails() {
        Turma c = new Turma(1, "firstName", "lastName", "emailAddress");

        assertTrue(c.getId() == c.getLightWeightTurma().getId());
        assertTrue(c.getFullName().equals(c.getLightWeightTurma().getDisplayName()));
    }
}
