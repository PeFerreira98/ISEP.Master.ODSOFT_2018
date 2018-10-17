package pt.isep.cms.contacts;

import com.google.gwt.event.shared.HandlerManager;

import pt.isep.cms.contacts.client.ContactsServiceAsync;
import pt.isep.cms.contacts.client.presenter.ContactsPresenter;
import pt.isep.cms.contacts.shared.ContactDetails;
import pt.isep.cms.contacts.shared.Contact;

import java.util.ArrayList;
import junit.framework.TestCase;

import static org.easymock.EasyMock.createStrictMock;

public class ContactJRETest extends TestCase {
	private ContactsPresenter contactsPresenter;
	private ContactsServiceAsync mockRpcService;
	private HandlerManager eventBus;
	private ContactsPresenter.Display mockDisplay;

	protected void setUp() {
		mockRpcService = createStrictMock(ContactsServiceAsync.class);
		eventBus = new HandlerManager(null);
		mockDisplay = createStrictMock(ContactsPresenter.Display.class);
		contactsPresenter = new ContactsPresenter(mockRpcService, eventBus, mockDisplay);
	}

	public void testContactSort() {
		ArrayList<ContactDetails> contactDetails = new ArrayList<ContactDetails>();
		contactDetails.add(new ContactDetails("0", "c_contact"));
		contactDetails.add(new ContactDetails("1", "b_contact"));
		contactDetails.add(new ContactDetails("2", "a_contact"));
		contactsPresenter.setContactDetails(contactDetails);
		contactsPresenter.sortContactDetails();
		assertTrue(contactsPresenter.getContactDetail(0).getDisplayName().equals("a_contact"));
		assertTrue(contactsPresenter.getContactDetail(1).getDisplayName().equals("b_contact"));
		assertTrue(contactsPresenter.getContactDetail(2).getDisplayName().equals("c_contact"));
	}

	public void testNewEmptyContact() {
		Contact c = new Contact();

		assertTrue(c.getId() == null);
		assertTrue(c.getFirstName() == null);
		assertTrue(c.getLastName() == null);
		assertTrue(c.getEmailAddress() == null);
		//assertTrue(c.getFullName() == null);
	}

	public void testNewContact() {
		Contact c = new Contact("id", "firstName", "lastName", "emailAddress");
		assertTrue(c.getId().equals("id"));
		assertTrue(c.getFirstName().equals("firstName"));
		assertTrue(c.getLastName().equals("lastName"));
		assertTrue(c.getEmailAddress().equals("emailAddress"));
		assertTrue(c.getFullName().equals("firstName lastName"));
	}

	public void testSetContact() {
		Contact c = new Contact();

		c.setId("id");
		c.setFirstName("firstName");
		c.setLastName("lastName");
		c.setEmailAddress("emailAddress");

		assertTrue(c.getId().equals("id"));
		assertTrue(c.getFirstName().equals("firstName"));
		assertTrue(c.getLastName().equals("lastName"));
		assertTrue(c.getEmailAddress().equals("emailAddress"));
		assertTrue(c.getFullName().equals("firstName lastName"));
	}

	public void testContactDetails() {
		Contact c = new Contact("id", "firstName", "lastName", "emailAddress");
		assertTrue(c.getId().equals(c.getLightWeightContact().getId()));
		assertTrue(c.getFullName().equals(c.getLightWeightContact().getDisplayName()));
	}
}
