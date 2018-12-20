package pt.isep.cms.contacts.shared;

import pt.isep.cms.contacts.shared.ContactDetails;
import pt.isep.cms.contacts.shared.Contact;

import junit.framework.TestCase;

import static org.easymock.EasyMock.createStrictMock;

public class ContactDetailsJRETest extends TestCase {
    protected void setUp() {
    }

    public void testNewEmptyContactDetails() {
        ContactDetails cd = new ContactDetails();

        assertTrue(cd.getId() == 0);
        assertTrue(cd.getDisplayName().equals(""));
    }

    public void testNewContactDetailsFullInfo() {
        ContactDetails cd = new ContactDetails(1, "DisplayName");

        assertTrue(cd.getId() == 1);
        assertTrue(cd.getDisplayName().equals("DisplayName"));
    }

    public void testSetContactInfo() {
        ContactDetails cd = new ContactDetails();

        cd.setId(1);
        cd.setDisplayName("DisplayName");

        assertTrue(cd.getId() == 1);
        assertTrue(cd.getDisplayName().equals("DisplayName"));
    }
}
