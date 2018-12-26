package pt.isep.cms.turmas.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import pt.isep.cms.turmas.client.TurmasService;
import pt.isep.cms.turmas.client.TurmasServiceAsync;
import pt.isep.cms.turmas.client.presenter.TurmasPresenter;
import pt.isep.cms.turmas.client.view.TurmasView;
import pt.isep.cms.turmas.shared.Turma;
import pt.isep.cms.turmas.shared.TurmaDetails;

// Nao se pode usar o easymock com testes GWT pois este usar reflection e o GWT não consegue "transpile"....
//import static org.easymock.EasyMock.createStrictMock;

import java.util.ArrayList;

public class ExampleGWTTest extends GWTTestCase {
    private TurmasPresenter turmasPresenter;
    private TurmasServiceAsync rpcService;
    private HandlerManager eventBus;
    private TurmasPresenter.Display mockDisplay;

    public String getModuleName() {
        return "pt.isep.cms.turmas.TestCMSJUnit";
    }

    public void gwtSetUp() {
        rpcService = GWT.create(TurmasService.class);
        mockDisplay = new TurmasView();
        turmasPresenter = new TurmasPresenter(rpcService, eventBus, mockDisplay);
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

    public void testTurmasService() {
        // Create the service that we will test.
        TurmasServiceAsync turmasService = GWT.create(TurmasService.class);
        ServiceDefTarget target = (ServiceDefTarget) turmasService;
        target.setServiceEntryPoint(GWT.getModuleBaseURL() + "turmas/turmasService");

        // Since RPC calls are asynchronous, we will need to wait for a response
        // after this test method returns. This line tells the test runner to wait
        // up to 10 seconds before timing out.
        delayTestFinish(10000);

        // fail("Ainda nao implementado");

        // Send a request to the server.
        turmasService.getTurma(2, new AsyncCallback<Turma>() {
            public void onFailure(Throwable caught) {
                // The request resulted in an unexpected error.
                fail("Request failure: " + caught.getMessage());
            }

            public void onSuccess(Turma result) {
                // Verify that the response is correct.
                assertTrue(result != null);

                // Now that we have received a response, we need to tell the test runner
                // that the test is complete. You must call finishTest() after an
                // asynchronous test finishes successfully, or the test will time out.
                finishTest();
            }
        });
    }
}
