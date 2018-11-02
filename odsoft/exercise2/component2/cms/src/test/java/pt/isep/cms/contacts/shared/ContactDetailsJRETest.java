package pt.isep.cms.contacts.shared;

import pt.isep.cms.contacts.shared.ContactDetails;
import pt.isep.cms.contacts.shared.Contact;

import junit.framework.TestCase;

import static org.easymock.EasyMock.createStrictMock;

public class ContactDetailsJRETest extends TestCase
{
	protected void setUp()
	{
	}

	public void testNewEmptyContactDetails()
	{
		ContactDetails cd = new ContactDetails();

		assertTrue(cd.getId().equals("0"));
		assertTrue(cd.getDisplayName().equals(""));
	}

	public void testNewContactDetailsFullInfo()
	{
		ContactDetails cd = new ContactDetails("id", "DisplayName");

		assertTrue(cd.getId().equals("id"));
		assertTrue(cd.getDisplayName().equals("DisplayName"));
	}

	public void testSetContactInfo()
	{
		ContactDetails cd = new ContactDetails();

		cd.setId("id");
		cd.setDisplayName("DisplayName");

		assertTrue(cd.getId().equals("id"));
		assertTrue(cd.getDisplayName().equals("DisplayName"));
	}
}
