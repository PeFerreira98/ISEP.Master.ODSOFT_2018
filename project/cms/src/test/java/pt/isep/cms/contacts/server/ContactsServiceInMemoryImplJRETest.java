package pt.isep.cms.contacts.server;

import pt.isep.cms.contacts.client.ContactsServiceAsync;
import pt.isep.cms.contacts.client.presenter.ContactsPresenter;
import pt.isep.cms.contacts.shared.Contact;
import pt.isep.cms.contacts.shared.ContactDetails;
import pt.isep.cms.contacts.server.ContactsServiceImpl;

import java.util.ArrayList;
import junit.framework.TestCase;

import static org.easymock.EasyMock.createStrictMock;

public class ContactsServiceInMemoryImplJRETest extends TestCase {
    private ContactsServiceImpl cs;

    protected void setUp() {
        cs = new ContactsServiceImpl();
    }

    public void testGetUnexistingContact() {
        Contact c = new Contact();
        assertTrue(cs.getContact(c.getId()) == null);
    }

    public void testAddContact() {
        Contact c = new Contact();
        assertTrue(cs.addContact(c) == c);

        assertTrue(cs.getContact(c.getId()) == c);
    }
}
