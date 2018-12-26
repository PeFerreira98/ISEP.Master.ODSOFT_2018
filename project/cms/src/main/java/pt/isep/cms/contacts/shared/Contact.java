package pt.isep.cms.contacts.shared;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@SuppressWarnings("serial")
@Entity
@Table
public class Contact implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    public String firstName;
    public String lastName;
    @Column(unique = true)
    public String emailAddress;

    public Contact() {
        super();
    }

    public Contact(int id, String firstName, String lastName, String emailAddress) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }

    public ContactDetails getLightWeightContact() {
        return new ContactDetails(id, getFullName());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFullName() {
        return firstName == null || lastName == null ? null : firstName + " " + lastName;
    }
}
