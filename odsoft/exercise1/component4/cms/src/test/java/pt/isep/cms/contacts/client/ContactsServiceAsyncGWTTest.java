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
import pt.isep.cms.contacts.shared.Contact;
import pt.isep.cms.contacts.shared.ContactDetails;

// Nao se pode usar o easymock com testes GWT pois este usar reflection e o GWT não consegue "transpile"....
//import static org.easymock.EasyMock.createStrictMock;

import java.util.ArrayList;

public class ContactsServiceAsyncGWTTest extends GWTTestCase
{
	private ContactsPresenter contactsPresenter;
	private ContactsServiceAsync rpcService;
	private HandlerManager eventBus;
	private ContactsPresenter.Display mockDisplay;
	
	private ContactsServiceAsync contactsService;

	public String getModuleName() {
		return "pt.isep.cms.contacts.ContactsServiceAsyncTest";
	}

	public void gwtSetUp() {
		contactsService = GWT.create(ContactsService.class);
	}

	public void testContactsService() {
		// Create the service that we will test.
		ServiceDefTarget target = (ServiceDefTarget) contactsService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "contacts/contactsService");

		// Since RPC calls are asynchronous, we will need to wait for a response
		// after this test method returns. This line tells the test runner to wait
		// up to 10 seconds before timing out.
		delayTestFinish(10000);

		// fail("Ainda nao implementado");

		// Send a request to the server.
		contactsService.getContact("2", new AsyncCallback<Contact>() {
			public void onFailure(Throwable caught) {
				// The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
			}

			public void onSuccess(Contact result) {
				// Verify that the response is correct.
				assertTrue(result != null);

				// Now that we have received a response, we need to tell the test runner
				// that the test is complete. You must call finishTest() after an
				// asynchronous test finishes successfully, or the test will time out.
				finishTest();
			}
		});
	}

	public void testAddContactToService() {
		// Create the service that we will test.
		ServiceDefTarget target = (ServiceDefTarget) contactsService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "contacts/contactsService");

		// Since RPC calls are asynchronous, we will need to wait for a response
		// after this test method returns. This line tells the test runner to wait
		// up to 10 seconds before timing out.
		delayTestFinish(10000);

		Contact c = new Contact();
		c.setFirstName("test");

		// Send a request to the server.
		contactsService.addContact(c, new AsyncCallback<Contact>() {
			public void onFailure(Throwable caught) {
				// The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
			}

			public void onSuccess(Contact result) {
				// Verify that the response is correct.
				assertTrue(result != null);

				assertTrue(result.getFirstName().equals("test"));
				assertTrue(result.getId() != null);

				// Now that we have received a response, we need to tell the test runner
				// that the test is complete. You must call finishTest() after an
				// asynchronous test finishes successfully, or the test will time out.
				finishTest();
			}
		});

		delayTestFinish(10000);

		// Send a request to the server.
		contactsService.getContact(c.getId(), new AsyncCallback<Contact>() {
			public void onFailure(Throwable caught) {
				// The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
			}

			public void onSuccess(Contact result) {
				// Verify that the response is correct.
				assertTrue(result != null);

				assertTrue(result.getFirstName().equals(c.getFirstName()));
				assertTrue(result.getId().equals(c.getId()));	

				// Now that we have received a response, we need to tell the test runner
				// that the test is complete. You must call finishTest() after an
				// asynchronous test finishes successfully, or the test will time out.
				finishTest();
			}
		});
	}

	// I've found a serious bug in this shitty project
	public void testAddRemoveAddContactToService() {
		// Create the service that we will test.
		ServiceDefTarget target = (ServiceDefTarget) contactsService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "contacts/contactsService");

		// Since RPC calls are asynchronous, we will need to wait for a response
		// after this test method returns. This line tells the test runner to wait
		// up to 10 seconds before timing out.
		delayTestFinish(10000);

		Contact c1 = new Contact();
		c1.setFirstName("test1");

		Contact c2 = new Contact();
		c2.setFirstName("test2");

		// add test1
		contactsService.addContact(c1, new AsyncCallback<Contact>() {
			public void onFailure(Throwable caught) {
				// The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
			}

			public void onSuccess(Contact result) {
				// Verify that the response is correct.
				assertTrue(result != null);

				assertTrue(result.getFirstName().equals(c1.getFirstName()));
				assertTrue(result.getId().equals(c1.getId()));

				finishTest();
			}
		});

		delayTestFinish(10000);

		// check
		contactsService.getContact(c1.getId(), new AsyncCallback<Contact>() {
			public void onFailure(Throwable caught) {
				// The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
			}

			public void onSuccess(Contact result) {
				// Verify that the response is correct.
				assertTrue(result != null);

				assertTrue(result.getFirstName().equals(c1.getFirstName()));
				assertTrue(result.getId().equals(c1.getId()));

				finishTest();
			}
		});

		delayTestFinish(10000);

		// delete contact with lower ID
		contactsService.deleteContact("0", new AsyncCallback<Boolean>() {
			public void onFailure(Throwable caught) {
				// The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
			}

			public void onSuccess(Boolean result) {
				// Verify that the response is correct.
				assertTrue(result != null);

				assertTrue(result);

				finishTest();
			}
		});

		delayTestFinish(10000);

		// add test2
		contactsService.addContact(c2, new AsyncCallback<Contact>() {
			public void onFailure(Throwable caught) {
				// The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
			}

			public void onSuccess(Contact result) {
				// Verify that the response is correct.
				assertTrue(result != null);

				assertTrue(result.getFirstName().equals(c2.getFirstName()));
				assertTrue(result.getId().equals(c2.getId()));

				finishTest();
			}
		});

		delayTestFinish(10000);

		// check test contact 2
		contactsService.getContact(c2.getId(), new AsyncCallback<Contact>() {
			public void onFailure(Throwable caught) {
				// The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
			}

			public void onSuccess(Contact result) {
				// Verify that the response is correct.
				assertTrue(result != null);

				assertTrue(result.getFirstName().equals(c2.getFirstName()));
				assertTrue(result.getId().equals(c2.getId()));

				finishTest();
			}
		});

		delayTestFinish(10000);

		// check test contact 1 again just to be sure (no way it will just vanish right?)
		contactsService.getContact(c1.getId(), new AsyncCallback<Contact>() {
			public void onFailure(Throwable caught) {
				// The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
			}

			public void onSuccess(Contact result) {
				// Verify that the response is correct.
				assertTrue(result != null);

				assertTrue(result.getFirstName().equals(c1.getFirstName()));
				assertTrue(result.getId().equals(c1.getId()));

				finishTest();
			}
		});

		// can't have the same id (in theory, but not in this project)
		assertTrue(!c1.getId().equals(c2.getId()));
	}
	
}
