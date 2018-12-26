package pt.isep.cms.turmas.shared;

import pt.isep.cms.turmas.shared.TurmaDetails;
import pt.isep.cms.turmas.shared.Turma;

import junit.framework.TestCase;

import static org.easymock.EasyMock.createStrictMock;

public class TurmaDetailsJRETest extends TestCase {
    protected void setUp() {
    }

    public void testNewEmptyTurmaDetails() {
        TurmaDetails cd = new TurmaDetails();

        assertTrue(cd.getId() == 0);
        assertTrue(cd.getDisplayName().equals(""));
    }

    public void testNewTurmaDetailsFullInfo() {
        TurmaDetails cd = new TurmaDetails(1, "DisplayName");

        assertTrue(cd.getId() == 1);
        assertTrue(cd.getDisplayName().equals("DisplayName"));
    }

    public void testSetTurmaInfo() {
        TurmaDetails cd = new TurmaDetails();

        cd.setId(1);
        cd.setDisplayName("DisplayName");

        assertTrue(cd.getId() == 1);
        assertTrue(cd.getDisplayName().equals("DisplayName"));
    }
}
