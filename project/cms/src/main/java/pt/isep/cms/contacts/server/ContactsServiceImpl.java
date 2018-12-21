package pt.isep.cms.contacts.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import pt.isep.cms.contacts.client.ContactsService;
import pt.isep.cms.contacts.shared.Contact;
import pt.isep.cms.contacts.shared.ContactDetails;

@SuppressWarnings("serial")
public class ContactsServiceImpl extends RemoteServiceServlet implements ContactsService {

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

    // private final HashMap<String, Contact> contacts = new HashMap<String,
    // Contact>();
    // private int serialId;

    private EntityManagerFactory emfactory = null;
    private EntityManager entitymanager = null;

    public ContactsServiceImpl() {
        // initContacts();
        // serialId = 0;

        this.emfactory = Persistence.createEntityManagerFactory("CMS");

        this.entitymanager = emfactory.createEntityManager();

        initPersistentContacts();
    }

    private void initPersistentContacts() {
        // We only do this if the database is empty...
        Query query = entitymanager.createQuery("Select COUNT(c) from Contact c");
        Long result = (Long) query.getSingleResult();

        if (result == 0) {
            this.entitymanager.getTransaction().begin();

            for (int i = 0; i < contactsFirstNameData.length && i < contactsLastNameData.length
                    && i < contactsEmailData.length; ++i) {

                Contact contact = new Contact(i, contactsFirstNameData[i], contactsLastNameData[i],
                        contactsEmailData[i]);
                this.entitymanager.persist(contact);
            }

            this.entitymanager.getTransaction().commit();
        }
    }

    public Contact addContact(Contact contact) {
        // Add the new contact to the database
        this.entitymanager.getTransaction().begin();
        this.entitymanager.persist(contact);
        this.entitymanager.getTransaction().commit();

        return contact;
    }

    public Contact updateContact(Contact contact) {
        // Update the contact in the database
        this.entitymanager.getTransaction().begin();
        this.entitymanager.merge(contact);
        this.entitymanager.getTransaction().commit();

        return contact;
    }

    public Boolean deleteContact(int id) {
        // Remove the contact in the database
        this.entitymanager.getTransaction().begin();
        Contact contact = entitymanager.find(Contact.class, id);
        entitymanager.remove(contact);
        this.entitymanager.getTransaction().commit();

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

        Query query = entitymanager.createQuery("Select c from Contact c");

        @SuppressWarnings("unchecked")
        List<Contact> list = query.getResultList();

        for (Contact contact : list) {
            contactDetails.add(contact.getLightWeightContact());
        }

        return contactDetails;
    }

    public Contact getContact(int id) {
        Contact contact = entitymanager.find(Contact.class, id);

        return contact;
    }

    // private void initContacts() {
    // // TODO: Create a real UID for each contact
    // //
    // for (int i = 0; i < contactsFirstNameData.length && i <
    // contactsLastNameData.length
    // && i < contactsEmailData.length; ++i) {
    // Contact contact = new Contact(String.valueOf(i), contactsFirstNameData[i],
    // contactsLastNameData[i],
    // contactsEmailData[i]);
    // addContact(contact);
    // }
    // }

    // public Contact addContact(Contact contact) {
    // contact.setId(String.valueOf(serialId++));
    // contacts.put(contact.getId(), contact);
    // return contact;
    // }

    // public Contact updateContact(Contact contact) {
    // String lid = contact.getId();
    // contacts.remove(contact.getId());
    // contacts.put(contact.getId(), contact);
    // return contact;
    // }

    // public Boolean deleteContact(String id) {
    // contacts.remove(id);
    // return true;
    // }

    // public ArrayList<ContactDetails> deleteContacts(ArrayList<String> ids) {

    // for (int i = 0; i < ids.size(); ++i) {
    // deleteContact(ids.get(i));
    // }

    // return getContactDetails();
    // }

    // public ArrayList<ContactDetails> getContactDetails() {
    // ArrayList<ContactDetails> contactDetails = new ArrayList<ContactDetails>();

    // Iterator<String> it = contacts.keySet().iterator();
    // while (it.hasNext()) {
    // Contact contact = contacts.get(it.next());
    // contactDetails.add(contact.getLightWeightContact());
    // }

    // return contactDetails;
    // }

    // public Contact getContact(String id) {
    // return contacts.get(id);
    // }
}
