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

public class ContactsServiceAsyncGWTTest extends GWTTestCase
{

	public String getModuleName()
	{
		return "pt.isep.cms.contacts.TestContactsServiceAsync";
	}

	public void gwtSetUp()
	{
	}

	public void testAddContactToService()
	{
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
		contactsService.addContact(c, new AsyncCallback<Contact>()
		{
			public void onFailure(Throwable caught)
			{
				// The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
			}

			public void onSuccess(final Contact result) {
				// Verify that the response is correct.
				assertTrue(result != null);

				assertTrue(result.getFirstName().equals(c.getFirstName()));
				assertTrue(result.getId() != null);
				
				testGetContact(result);

				finishTest();
			}
		});
	}

	// I've found a serious bug in this shitty project
	// Welcome to callback hell!
	public void testAddRemoveAddContactToService()
	{
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
		cs.addContact(c1, new AsyncCallback<Contact>()
		{
			public void onFailure(Throwable caught)
			{
				// The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
			}

			// result = c1
			public void onSuccess(final Contact resC1)
			{
				// Verify that the response is correct.
				assertTrue(resC1 != null);

				assertTrue(resC1.getFirstName().equals(c1.getFirstName()));
				assertTrue(resC1.getId() != null);

				// verify that it was added
				testGetContact(resC1);


				// Create the service that we will test.
				ContactsServiceAsync cs1 = GWT.create(ContactsService.class);
				ServiceDefTarget target1 = (ServiceDefTarget) cs1;
				target1.setServiceEntryPoint(GWT.getModuleBaseURL() + "contacts/contactsService");

				// delete contact with lowest ID
				cs1.deleteContact("0", new AsyncCallback<Boolean>() {
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
								assertTrue(resC2.getId() != null);

								testGetContact(resC2);

								/*
								// check test contact 1 again just to be sure (no way it will just vanish right?)
								testGetContact(resC1);

								// can't have the same id (in theory, but not in this project)
								assertTrue(!resC1.getId().equals(resC2.getId()));
								*/

								finishTest();
							}
						});
					}
				});
			}
		});

	}

	private void testGetContact(final Contact c)
	{
		ContactsServiceAsync contactsService = GWT.create(ContactsService.class);
		ServiceDefTarget target = (ServiceDefTarget) contactsService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "contacts/contactsService");

		contactsService.getContact(c.getId(), new AsyncCallback<Contact>()
		{
			public void onFailure(Throwable caught)
			{
				// The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
			}

			public void onSuccess(Contact result)
			{
				// Verify that the response is correct.
				assertTrue(result != null);

				assertTrue(result.getFirstName().equals(c.getFirstName()));
				assertTrue(result.getId().equals(c.getId()));
			}
		});
	}
	
}
