package pt.isep.cms.contacts.shared;

import pt.isep.cms.contacts.shared.ContactDetails;
import pt.isep.cms.contacts.shared.Contact;

import junit.framework.TestCase;

//import static org.easymock.EasyMock.createStrictMock;

public class ContactJRETest extends TestCase
{
	protected void setUp()
	{
	}

	public void testNewEmptyContact()
	{
		Contact c = new Contact();

		assertTrue(c.getId() == null);
		assertTrue(c.getFirstName() == null);
		assertTrue(c.getLastName() == null);
		assertTrue(c.getEmailAddress() == null);
		assertTrue(c.getFullName() == null);
	}

	public void testNewContactFullInfo()
	{
		Contact c = new Contact("id", "firstName", "lastName", "emailAddress");

		assertTrue(c.getId().equals("id"));
		assertTrue(c.getFirstName().equals("firstName"));
		assertTrue(c.getLastName().equals("lastName"));
		assertTrue(c.getEmailAddress().equals("emailAddress"));
		assertTrue(c.getFullName().equals("firstName lastName"));
	}

	public void testSetContactInfo()
	{
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

	public void testContactDetails()
	{
		Contact c = new Contact("id", "firstName", "lastName", "emailAddress");

		assertTrue(c.getId().equals(c.getLightWeightContact().getId()));
		assertTrue(c.getFullName().equals(c.getLightWeightContact().getDisplayName()));
	}
}
