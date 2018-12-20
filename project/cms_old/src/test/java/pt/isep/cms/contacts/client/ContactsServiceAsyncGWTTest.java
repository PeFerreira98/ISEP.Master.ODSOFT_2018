package pt.isep.cms.contacts.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import pt.isep.cms.contacts.client.ContactsService;
import pt.isep.cms.contacts.client.ContactsServiceAsync;
import pt.isep.cms.contacts.client.presenter.ContactsPresenter;
import pt.isep.cms.contacts.client.view.ContactsView;
import pt.isep.cms.contacts.server.ContactsServiceImpl;
import pt.isep.cms.contacts.shared.Contact;
import pt.isep.cms.contacts.shared.ContactDetails;

import java.util.ArrayList;

public class ContactsServiceAsyncGWTTest extends GWTTestCase {

    public String getModuleName() {
        return "pt.isep.cms.contacts.TestCMSJUnit";
    }

    public void gwtSetUp() {
    }

    public void testAddContactToService() {
        // Create the service that we will test.
        ContactsServiceAsync contactsService = GWT.create(ContactsService.class);
        ServiceDefTarget target = (ServiceDefTarget) contactsService;
        target.setServiceEntryPoint(GWT.getModuleBaseURL() + "contacts/contactsService");

        // Since RPC calls are asynchronous, we will need to wait for a response
        // after this test method returns. This line tells the test runner to wait
        // up to 10 seconds before timing out.
        delayTestFinish(20000);

        final Contact c = new Contact();
        c.setFirstName("test");

        // Send a request to the server.
        contactsService.addContact(c, new AsyncCallback<Contact>() {
            public void onFailure(Throwable caught) {
                // The request resulted in an unexpected error.
                fail("Request failure: " + caught.getMessage());
            }

            public void onSuccess(final Contact resC1) {
                // Verify that the response is correct.
                assertTrue(resC1 != null);

                assertTrue(resC1.getFirstName().equals(c.getFirstName()));
                assertTrue(resC1.getId() != 0);

                ContactsServiceAsync cs1 = GWT.create(ContactsService.class);
                ServiceDefTarget target1 = (ServiceDefTarget) cs1;
                target1.setServiceEntryPoint(GWT.getModuleBaseURL() + "contacts/contactsService");

                cs1.getContact(resC1.getId(), new AsyncCallback<Contact>() {
                    public void onFailure(Throwable caught) {
                        // The request resulted in an unexpected error.
                        fail("Request failure: " + caught.getMessage());
                    }

                    public void onSuccess(final Contact result) {
                        // Verify that the response is correct.
                        assertTrue(result != null);

                        System.out.println("getContact " + result.getFirstName().equals(resC1.getFirstName()));
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
    public void testAddRemoveAddContactToService() {
        // Create the service that we will test.
        ContactsServiceAsync cs = GWT.create(ContactsService.class);
        ServiceDefTarget target = (ServiceDefTarget) cs;
        target.setServiceEntryPoint(GWT.getModuleBaseURL() + "contacts/contactsService");

        // Since RPC calls are asynchronous, we will need to wait for a response
        // after this test method returns. This line tells the test runner to wait
        // up to 10 seconds before timing out.
        delayTestFinish(50000);

        final Contact c1 = new Contact();
        c1.setFirstName("test1");

        final Contact c2 = new Contact();
        c2.setFirstName("test2");

        // add test1
        cs.addContact(c1, new AsyncCallback<Contact>() {
            public void onFailure(Throwable caught) {
                // The request resulted in an unexpected error.
                fail("Request failure: " + caught.getMessage());
            }

            // result = c1
            public void onSuccess(final Contact resC1) {
                // Verify that the response is correct.
                assertTrue(resC1 != null);

                assertTrue(resC1.getFirstName().equals(c1.getFirstName()));
                assertTrue(resC1.getId() != 0);

                // verify that it was added
                ContactsServiceAsync cs3 = GWT.create(ContactsService.class);
                ServiceDefTarget target3 = (ServiceDefTarget) cs3;
                target3.setServiceEntryPoint(GWT.getModuleBaseURL() + "contacts/contactsService");

                cs3.getContact(resC1.getId(), new AsyncCallback<Contact>() {
                    public void onFailure(Throwable caught) {
                        // The request resulted in an unexpected error.
                        fail("Request failure: " + caught.getMessage());
                    }

                    public void onSuccess(final Contact resC1_2) {
                        // Verify that the response is correct.
                        assertTrue(resC1_2 != null);

                        assertTrue(resC1_2.getFirstName().equals(resC1.getFirstName()));
                        assertTrue(resC1_2.getId() == resC1.getId());

                        // Create the service that we will test.
                        ContactsServiceAsync cs1 = GWT.create(ContactsService.class);
                        ServiceDefTarget target1 = (ServiceDefTarget) cs1;
                        target1.setServiceEntryPoint(GWT.getModuleBaseURL() + "contacts/contactsService");

                        // delete contact with lowest ID
                        cs1.deleteContact(0, new AsyncCallback<Boolean>() {
                            public void onFailure(Throwable caught) {
                                // The request resulted in an unexpected error.
                                fail("Request failure: " + caught.getMessage());
                            }

                            public void onSuccess(final Boolean resDel) {
                                // Verify that the response is correct.
                                assertTrue(resDel != null);
                                assertTrue(resDel);

                                // Create the service that we will test.
                                ContactsServiceAsync cs2 = GWT.create(ContactsService.class);
                                ServiceDefTarget target2 = (ServiceDefTarget) cs2;
                                target2.setServiceEntryPoint(GWT.getModuleBaseURL() + "contacts/contactsService");

                                // add test2
                                cs2.addContact(c2, new AsyncCallback<Contact>() {
                                    public void onFailure(Throwable caught) {
                                        // The request resulted in an unexpected error.
                                        fail("Request failure: " + caught.getMessage());
                                    }

                                    public void onSuccess(Contact resC2) {
                                        // Verify that the response is correct.
                                        assertTrue(resC2 != null);

                                        assertTrue(resC2.getFirstName().equals(c2.getFirstName()));
                                        assertTrue(resC2.getId() != 0);

                                        ContactsServiceAsync cs4 = GWT.create(ContactsService.class);
                                        ServiceDefTarget target4 = (ServiceDefTarget) cs4;
                                        target4.setServiceEntryPoint(
                                                GWT.getModuleBaseURL() + "contacts/contactsService");

                                        cs4.getContact(resC2.getId(), new AsyncCallback<Contact>() {
                                            public void onFailure(Throwable caught) {
                                                // The request resulted in an unexpected error.
                                                fail("Request failure: " + caught.getMessage());
                                            }

                                            public void onSuccess(final Contact resC2_2) {
                                                // Verify that the response is correct.
                                                assertTrue(resC2_2 != null);

                                                assertTrue(resC2_2.getFirstName().equals(resC2.getFirstName()));
                                                assertTrue(resC2_2.getId() == resC2.getId());

                                                // check test contact 1 again just to be sure (no way it will just
                                                // vanish right?)
                                                ContactsServiceAsync cs5 = GWT.create(ContactsService.class);
                                                ServiceDefTarget target5 = (ServiceDefTarget) cs5;
                                                target5.setServiceEntryPoint(
                                                        GWT.getModuleBaseURL() + "contacts/contactsService");

                                                cs5.getContact(resC1.getId(), new AsyncCallback<Contact>() {
                                                    public void onFailure(Throwable caught) {
                                                        // The request resulted in an unexpected error.
                                                        fail("Request failure: " + caught.getMessage());
                                                    }

                                                    public void onSuccess(final Contact resC1Error) {
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
