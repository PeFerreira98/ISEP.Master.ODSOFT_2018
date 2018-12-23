package pt.isep.cms.turmas.server;

import pt.isep.cms.turmas.client.TurmasServiceAsync;
import pt.isep.cms.turmas.client.presenter.TurmasPresenter;
import pt.isep.cms.turmas.shared.Turma;
import pt.isep.cms.turmas.shared.TurmaDetails;
import pt.isep.cms.turmas.server.TurmasServiceImpl;

import java.util.ArrayList;
import junit.framework.TestCase;

import static org.easymock.EasyMock.createStrictMock;

public class TurmasServiceImplJRETest extends TestCase {
    private TurmasServiceImpl cs;

    protected void setUp() {
        cs = new TurmasServiceImpl();
    }

    public void testGetUnexistingTurma() {
        Turma c = new Turma();
        assertTrue(cs.getTurma(c.getId()) == null);
    }

    public void testAddTurma() {
        Turma c = new Turma();
        assertTrue(cs.addTurma(c) == c);

        assertTrue(cs.getTurma(c.getId()) == c);
    }
}
