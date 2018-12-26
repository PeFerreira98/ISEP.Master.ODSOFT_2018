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
import pt.isep.cms.turmas.server.TurmasServiceImpl;
import pt.isep.cms.turmas.shared.Turma;
import pt.isep.cms.turmas.shared.TurmaDetails;

import java.util.ArrayList;

public class TurmasServiceAsyncGWTTest extends GWTTestCase {

    public String getModuleName() {
        return "pt.isep.cms.turmas.TestCMSJUnit";
    }

    public void gwtSetUp() {
    }

    public void testAddTurmaToService() {
        // Create the service that we will test.
        TurmasServiceAsync turmasService = GWT.create(TurmasService.class);
        ServiceDefTarget target = (ServiceDefTarget) turmasService;
        target.setServiceEntryPoint(GWT.getModuleBaseURL() + "turmas/turmasService");

        // Since RPC calls are asynchronous, we will need to wait for a response
        // after this test method returns. This line tells the test runner to wait
        // up to 10 seconds before timing out.
        delayTestFinish(20000);

        final Turma c = new Turma();
        c.setFirstName("test");

        // Send a request to the server.
        turmasService.addTurma(c, new AsyncCallback<Turma>() {
            public void onFailure(Throwable caught) {
                // The request resulted in an unexpected error.
                fail("Request failure: " + caught.getMessage());
            }

            public void onSuccess(final Turma resC1) {
                // Verify that the response is correct.
                assertTrue(resC1 != null);

                assertTrue(resC1.getFirstName().equals(c.getFirstName()));
                assertTrue(resC1.getId() != 0);

                TurmasServiceAsync cs1 = GWT.create(TurmasService.class);
                ServiceDefTarget target1 = (ServiceDefTarget) cs1;
                target1.setServiceEntryPoint(GWT.getModuleBaseURL() + "turmas/turmasService");

                cs1.getTurma(resC1.getId(), new AsyncCallback<Turma>() {
                    public void onFailure(Throwable caught) {
                        // The request resulted in an unexpected error.
                        fail("Request failure: " + caught.getMessage());
                    }

                    public void onSuccess(final Turma result) {
                        // Verify that the response is correct.
                        assertTrue(result != null);

                        System.out.println("getTurma " + result.getFirstName().equals(resC1.getFirstName()));
                        assertTrue(result.getFirstName().equals(resC1.getFirstName()));
                        assertTrue(result.getId() == resC1.getId());

                        finishTest();
                    }
                });

            }
        });

    }

    // I've found a serious bug in this shitty project
    // Welcome to callback hell!
    public void testAddRemoveAddTurmaToService() {
        // Create the service that we will test.
        TurmasServiceAsync cs = GWT.create(TurmasService.class);
        ServiceDefTarget target = (ServiceDefTarget) cs;
        target.setServiceEntryPoint(GWT.getModuleBaseURL() + "turmas/turmasService");

        // Since RPC calls are asynchronous, we will need to wait for a response
        // after this test method returns. This line tells the test runner to wait
        // up to 10 seconds before timing out.
        delayTestFinish(50000);

        final Turma c1 = new Turma();
        c1.setFirstName("test1");

        final Turma c2 = new Turma();
        c2.setFirstName("test2");

        // add test1
        cs.addTurma(c1, new AsyncCallback<Turma>() {
            public void onFailure(Throwable caught) {
                // The request resulted in an unexpected error.
                fail("Request failure: " + caught.getMessage());
            }

            // result = c1
            public void onSuccess(final Turma resC1) {
                // Verify that the response is correct.
                assertTrue(resC1 != null);

                assertTrue(resC1.getFirstName().equals(c1.getFirstName()));
                assertTrue(resC1.getId() != 0);

                // verify that it was added
                TurmasServiceAsync cs3 = GWT.create(TurmasService.class);
                ServiceDefTarget target3 = (ServiceDefTarget) cs3;
                target3.setServiceEntryPoint(GWT.getModuleBaseURL() + "turmas/turmasService");

                cs3.getTurma(resC1.getId(), new AsyncCallback<Turma>() {
                    public void onFailure(Throwable caught) {
                        // The request resulted in an unexpected error.
                        fail("Request failure: " + caught.getMessage());
                    }

                    public void onSuccess(final Turma resC1_2) {
                        // Verify that the response is correct.
                        assertTrue(resC1_2 != null);

                        assertTrue(resC1_2.getFirstName().equals(resC1.getFirstName()));
                        assertTrue(resC1_2.getId() == resC1.getId());

                        // Create the service that we will test.
                        TurmasServiceAsync cs1 = GWT.create(TurmasService.class);
                        ServiceDefTarget target1 = (ServiceDefTarget) cs1;
                        target1.setServiceEntryPoint(GWT.getModuleBaseURL() + "turmas/turmasService");

                        // delete turma with lowest ID
                        cs1.deleteTurma(0, new AsyncCallback<Boolean>() {
                            public void onFailure(Throwable caught) {
                                // The request resulted in an unexpected error.
                                fail("Request failure: " + caught.getMessage());
                            }

                            public void onSuccess(final Boolean resDel) {
                                // Verify that the response is correct.
                                assertTrue(resDel != null);
                                assertTrue(resDel);

                                // Create the service that we will test.
                                TurmasServiceAsync cs2 = GWT.create(TurmasService.class);
                                ServiceDefTarget target2 = (ServiceDefTarget) cs2;
                                target2.setServiceEntryPoint(GWT.getModuleBaseURL() + "turmas/turmasService");

                                // add test2
                                cs2.addTurma(c2, new AsyncCallback<Turma>() {
                                    public void onFailure(Throwable caught) {
                                        // The request resulted in an unexpected error.
                                        fail("Request failure: " + caught.getMessage());
                                    }

                                    public void onSuccess(Turma resC2) {
                                        // Verify that the response is correct.
                                        assertTrue(resC2 != null);

                                        assertTrue(resC2.getFirstName().equals(c2.getFirstName()));
                                        assertTrue(resC2.getId() != 0);

                                        TurmasServiceAsync cs4 = GWT.create(TurmasService.class);
                                        ServiceDefTarget target4 = (ServiceDefTarget) cs4;
                                        target4.setServiceEntryPoint(
                                                GWT.getModuleBaseURL() + "turmas/turmasService");

                                        cs4.getTurma(resC2.getId(), new AsyncCallback<Turma>() {
                                            public void onFailure(Throwable caught) {
                                                // The request resulted in an unexpected error.
                                                fail("Request failure: " + caught.getMessage());
                                            }

                                            public void onSuccess(final Turma resC2_2) {
                                                // Verify that the response is correct.
                                                assertTrue(resC2_2 != null);

                                                assertTrue(resC2_2.getFirstName().equals(resC2.getFirstName()));
                                                assertTrue(resC2_2.getId() == resC2.getId());

                                                // check test turma 1 again just to be sure (no way it will just
                                                // vanish right?)
                                                TurmasServiceAsync cs5 = GWT.create(TurmasService.class);
                                                ServiceDefTarget target5 = (ServiceDefTarget) cs5;
                                                target5.setServiceEntryPoint(
                                                        GWT.getModuleBaseURL() + "turmas/turmasService");

                                                cs5.getTurma(resC1.getId(), new AsyncCallback<Turma>() {
                                                    public void onFailure(Throwable caught) {
                                                        // The request resulted in an unexpected error.
                                                        fail("Request failure: " + caught.getMessage());
                                                    }

                                                    public void onSuccess(final Turma resC1Error) {
                                                        // Verify that the response is correct.
                                                        assertTrue(resC1Error != null);

                                                        assertTrue(
                                                                resC1Error.getFirstName().equals(resC1.getFirstName()));
                                                        assertTrue(resC1Error.getId() == resC1.getId());

                                                        // can't have the same id (in theory, but not in this project)
                                                        assertFalse(resC1.getId() == resC2.getId());

                                                        finishTest();
                                                    }
                                                });

                                            }
                                        });

                                    }
                                });

                            }
                        });

                    }
                });

            }
        });

    }

}
