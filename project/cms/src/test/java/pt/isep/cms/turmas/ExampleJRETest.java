package pt.isep.cms.turmas;

import com.google.gwt.event.shared.HandlerManager;

import pt.isep.cms.turmas.client.TurmasServiceAsync;
import pt.isep.cms.turmas.client.presenter.TurmasPresenter;
import pt.isep.cms.turmas.shared.TurmaDetails;

import java.util.ArrayList;
import junit.framework.TestCase;

import static org.easymock.EasyMock.createStrictMock;

public class ExampleJRETest extends TestCase {
    private TurmasPresenter turmasPresenter;
    private TurmasServiceAsync mockRpcService;
    private HandlerManager eventBus;
    private TurmasPresenter.Display mockDisplay;

    protected void setUp() {
        mockRpcService = createStrictMock(TurmasServiceAsync.class);
        eventBus = new HandlerManager(null);
        mockDisplay = createStrictMock(TurmasPresenter.Display.class);
        turmasPresenter = new TurmasPresenter(mockRpcService, eventBus, mockDisplay);
    }

    public void testTurmaSort() {
        ArrayList<TurmaDetails> turmaDetails = new ArrayList<TurmaDetails>();

        turmaDetails.add(new TurmaDetails(0, "c_turma"));
        turmaDetails.add(new TurmaDetails(1, "b_turma"));
        turmaDetails.add(new TurmaDetails(2, "a_turma"));

        turmasPresenter.setTurmaDetails(turmaDetails);
        turmasPresenter.sortTurmaDetails();

        assertTrue(turmasPresenter.getTurmaDetail(0).getDisplayName().equals("a_turma"));
        assertTrue(turmasPresenter.getTurmaDetail(1).getDisplayName().equals("b_turma"));
        assertTrue(turmasPresenter.getTurmaDetail(2).getDisplayName().equals("c_turma"));
    }
}
