package pt.isep.cms.contacts.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import pt.isep.cms.contacts.client.ContactsService;
import pt.isep.cms.contacts.shared.Contact;
import pt.isep.cms.contacts.shared.ContactDetails;

//as the app uses ObjectFactory 2 get class who implements Contacts service, Only one must be activated
//this exists just for future stuff

@SuppressWarnings("serial")
public class ContactsServiceInMemoryImpl extends RemoteServiceServlet //implements ContactsService 
{

    private static final String[] contactsFirstNameData = new String[] { "Hollie", "Emerson", "Healy", "Brigitte",
            "Elba", "Claudio", "Dena", "Christina", "Gail", "Orville", "Rae", "Mildred", "Candice", "Louise", "Emilio",
            "Geneva", "Heriberto", "Bulrush", "Abigail", "Chad", "Terry", "Bell" };

    private final String[] contactsLastNameData = new String[] { "Voss", "Milton", "Colette", "Cobb", "Lockhart",
            "Engle", "Pacheco", "Blake", "Horton", "Daniel", "Childers", "Starnes", "Carson", "Kelchner", "Hutchinson",
            "Underwood", "Rush", "Bouchard", "Louis", "Andrews", "English", "Snedden" };

    private final String[] contactsEmailData = new String[] { "mark@example.com", "hollie@example.com",
            "boticario@example.com", "emerson@example.com", "healy@example.com", "brigitte@example.com",
            "elba@example.com", "claudio@example.com", "dena@example.com", "brasilsp@example.com", "parker@example.com",
            "derbvktqsr@example.com", "qetlyxxogg@example.com", "antenas_sul@example.com", "cblake@example.com",
            "gailh@example.com", "orville@example.com", "post_master@example.com", "rchilders@example.com",
            "buster@example.com", "user31065@example.com", "ftsgeolbx@example.com" };

    private final HashMap<Integer, Contact> contacts = new HashMap<Integer, Contact>();
    private int serialId;

    public ContactsServiceInMemoryImpl() {
        initContacts();
        serialId = 0;
    }

    private void initContacts() {
        // TODO: Create a real UID for each contact
        //
        for (int i = 0; i < contactsFirstNameData.length && i < contactsLastNameData.length
                && i < contactsEmailData.length; ++i) {
            Contact contact = new Contact(i, contactsFirstNameData[i], contactsLastNameData[i],
                    contactsEmailData[i]);
            addContact(contact);
        }
    }

    public Contact addContact(Contact contact) {
        contact.setId(serialId++);
        contacts.put(contact.getId(), contact);
        return contact;
    }

    public Contact updateContact(Contact contact) {
        contacts.remove(contact.getId());
        contacts.put(contact.getId(), contact);
        return contact;
    }

    public Boolean deleteContact(int id) {
        contacts.remove(id);
        return true;
    }

    public ArrayList<ContactDetails> deleteContacts(ArrayList<Integer> ids) {

        for (int i = 0; i < ids.size(); ++i) {
            deleteContact(ids.get(i));
        }

        return getContactDetails();
    }

    public ArrayList<ContactDetails> getContactDetails() {
        ArrayList<ContactDetails> contactDetails = new ArrayList<ContactDetails>();

        Iterator<Integer> it = contacts.keySet().iterator();
        while (it.hasNext()) {
            Contact contact = contacts.get(it.next());
            contactDetails.add(contact.getLightWeightContact());
        }

        return contactDetails;
    }

    public Contact getContact(int id) {
        return contacts.get(id);
    }
}
