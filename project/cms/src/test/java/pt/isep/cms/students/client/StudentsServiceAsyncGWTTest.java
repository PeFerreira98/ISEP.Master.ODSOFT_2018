package pt.isep.cms.students.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import pt.isep.cms.students.client.StudentsService;
import pt.isep.cms.students.client.StudentsServiceAsync;
import pt.isep.cms.students.client.presenter.StudentsPresenter;
import pt.isep.cms.students.client.view.StudentsView;
import pt.isep.cms.students.server.StudentsServiceImpl;
import pt.isep.cms.students.shared.Student;
import pt.isep.cms.students.shared.StudentDetails;

import java.util.ArrayList;

public class StudentsServiceAsyncGWTTest extends GWTTestCase {

    public String getModuleName() {
        return "pt.isep.cms.students.TestCMSJUnit";
    }

    public void gwtSetUp() {
    }

    public void testAddStudentToService() {
        // Create the service that we will test.
        StudentsServiceAsync studentsService = GWT.create(StudentsService.class);
        ServiceDefTarget target = (ServiceDefTarget) studentsService;
        target.setServiceEntryPoint(GWT.getModuleBaseURL() + "students/studentsService");

        // Since RPC calls are asynchronous, we will need to wait for a response
        // after this test method returns. This line tells the test runner to wait
        // up to 10 seconds before timing out.
        delayTestFinish(20000);

        final Student c = new Student();
        c.setFirstName("test");

        // Send a request to the server.
        studentsService.addStudent(c, new AsyncCallback<Student>() {
            public void onFailure(Throwable caught) {
                // The request resulted in an unexpected error.
                fail("Request failure: " + caught.getMessage());
            }

            public void onSuccess(final Student resC1) {
                // Verify that the response is correct.
                assertTrue(resC1 != null);

                assertTrue(resC1.getFirstName().equals(c.getFirstName()));
                assertTrue(resC1.getId() != null);

                StudentsServiceAsync cs1 = GWT.create(StudentsService.class);
                ServiceDefTarget target1 = (ServiceDefTarget) cs1;
                target1.setServiceEntryPoint(GWT.getModuleBaseURL() + "students/studentsService");

                cs1.getStudent(resC1.getId(), new AsyncCallback<Student>() {
                    public void onFailure(Throwable caught) {
                        // The request resulted in an unexpected error.
                        fail("Request failure: " + caught.getMessage());
                    }

                    public void onSuccess(final Student result) {
                        // Verify that the response is correct.
                        assertTrue(result != null);

                        System.out.println("getStudent " + result.getFirstName().equals(resC1.getFirstName()));
                        assertTrue(result.getFirstName().equals(resC1.getFirstName()));
                        assertTrue(result.getId().equals(resC1.getId()));

                        finishTest();
                    }
                });

            }
        });

    }

    // I've found a serious bug in this shitty project
    // Welcome to callback hell!
    public void testAddRemoveAddStudentToService() {
        // Create the service that we will test.
        StudentsServiceAsync cs = GWT.create(StudentsService.class);
        ServiceDefTarget target = (ServiceDefTarget) cs;
        target.setServiceEntryPoint(GWT.getModuleBaseURL() + "students/studentsService");

        // Since RPC calls are asynchronous, we will need to wait for a response
        // after this test method returns. This line tells the test runner to wait
        // up to 10 seconds before timing out.
        delayTestFinish(50000);

        final Student c1 = new Student();
        c1.setFirstName("test1");

        final Student c2 = new Student();
        c2.setFirstName("test2");

        // add test1
        cs.addStudent(c1, new AsyncCallback<Student>() {
            public void onFailure(Throwable caught) {
                // The request resulted in an unexpected error.
                fail("Request failure: " + caught.getMessage());
            }

            // result = c1
            public void onSuccess(final Student resC1) {
                // Verify that the response is correct.
                assertTrue(resC1 != null);

                assertTrue(resC1.getFirstName().equals(c1.getFirstName()));
                assertTrue(resC1.getId() != null);

                // verify that it was added
                StudentsServiceAsync cs3 = GWT.create(StudentsService.class);
                ServiceDefTarget target3 = (ServiceDefTarget) cs3;
                target3.setServiceEntryPoint(GWT.getModuleBaseURL() + "students/studentsService");

                cs3.getStudent(resC1.getId(), new AsyncCallback<Student>() {
                    public void onFailure(Throwable caught) {
                        // The request resulted in an unexpected error.
                        fail("Request failure: " + caught.getMessage());
                    }

                    public void onSuccess(final Student resC1_2) {
                        // Verify that the response is correct.
                        assertTrue(resC1_2 != null);

                        assertTrue(resC1_2.getFirstName().equals(resC1.getFirstName()));
                        assertTrue(resC1_2.getId().equals(resC1.getId()));

                        // Create the service that we will test.
                        StudentsServiceAsync cs1 = GWT.create(StudentsService.class);
                        ServiceDefTarget target1 = (ServiceDefTarget) cs1;
                        target1.setServiceEntryPoint(GWT.getModuleBaseURL() + "students/studentsService");

                        // delete student with lowest ID
                        cs1.deleteStudent("0", new AsyncCallback<Boolean>() {
                            public void onFailure(Throwable caught) {
                                // The request resulted in an unexpected error.
                                fail("Request failure: " + caught.getMessage());
                            }

                            public void onSuccess(final Boolean resDel) {
                                // Verify that the response is correct.
                                assertTrue(resDel != null);
                                assertTrue(resDel);

                                // Create the service that we will test.
                                StudentsServiceAsync cs2 = GWT.create(StudentsService.class);
                                ServiceDefTarget target2 = (ServiceDefTarget) cs2;
                                target2.setServiceEntryPoint(GWT.getModuleBaseURL() + "students/studentsService");

                                // add test2
                                cs2.addStudent(c2, new AsyncCallback<Student>() {
                                    public void onFailure(Throwable caught) {
                                        // The request resulted in an unexpected error.
                                        fail("Request failure: " + caught.getMessage());
                                    }

                                    public void onSuccess(Student resC2) {
                                        // Verify that the response is correct.
                                        assertTrue(resC2 != null);

                                        assertTrue(resC2.getFirstName().equals(c2.getFirstName()));
                                        assertTrue(resC2.getId() != null);

                                        StudentsServiceAsync cs4 = GWT.create(StudentsService.class);
                                        ServiceDefTarget target4 = (ServiceDefTarget) cs4;
                                        target4.setServiceEntryPoint(
                                                GWT.getModuleBaseURL() + "students/studentsService");

                                        cs4.getStudent(resC2.getId(), new AsyncCallback<Student>() {
                                            public void onFailure(Throwable caught) {
                                                // The request resulted in an unexpected error.
                                                fail("Request failure: " + caught.getMessage());
                                            }

                                            public void onSuccess(final Student resC2_2) {
                                                // Verify that the response is correct.
                                                assertTrue(resC2_2 != null);

                                                assertTrue(resC2_2.getFirstName().equals(resC2.getFirstName()));
                                                assertTrue(resC2_2.getId().equals(resC2.getId()));

                                                // check test student 1 again just to be sure (no way it will just
                                                // vanish right?)
                                                StudentsServiceAsync cs5 = GWT.create(StudentsService.class);
                                                ServiceDefTarget target5 = (ServiceDefTarget) cs5;
                                                target5.setServiceEntryPoint(
                                                        GWT.getModuleBaseURL() + "students/studentsService");

                                                cs5.getStudent(resC1.getId(), new AsyncCallback<Student>() {
                                                    public void onFailure(Throwable caught) {
                                                        // The request resulted in an unexpected error.
                                                        fail("Request failure: " + caught.getMessage());
                                                    }

                                                    public void onSuccess(final Student resC1Error) {
                                                        // Verify that the response is correct.
                                                        assertTrue(resC1Error != null);

                                                        assertTrue(
                                                                resC1Error.getFirstName().equals(resC1.getFirstName()));
                                                        assertTrue(resC1Error.getId().equals(resC1.getId()));

                                                        // can't have the same id (in theory, but not in this project)
                                                        assertFalse(resC1.getId().equals(resC2.getId()));

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
