package pt.isep.cms.students.shared;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table
public class Student implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public int id;
    public String firstName;
    public String lastName;
    public String emailAddress;

    public Student() {
        this.id = 0;
        this.firstName = null;
        this.lastName = null;
        this.emailAddress = null;
    }

    public Student(int id, String firstName, String lastName, String emailAddress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }

    public StudentDetails getLightWeightStudent() {
        return new StudentDetails(id, getFullName());
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
