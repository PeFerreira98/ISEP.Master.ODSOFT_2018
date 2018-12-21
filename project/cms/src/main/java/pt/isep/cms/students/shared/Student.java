package pt.isep.cms.students.shared;

import java.io.Serializable;
import pt.isep.cms.turmas.shared.Turma;

@SuppressWarnings("serial")
public class Student implements Serializable {
    public String id;
    public String firstName;
    public String lastName;
    public String emailAddress;
    // @ManyToOne
    // @JoinColumn(name = "id")
    public Turma turma;

    public Student() {
    }

    public Student(String id, String firstName, String lastName, String emailAddress, Turma turma) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.turma = turma;
    }

    public StudentDetails getLightWeightStudent() {
        return new StudentDetails(id, getFullName());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Turma getTurma() {
        return this.turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public String getFullName() {
        return firstName == null || lastName == null ? null : firstName + " " + lastName;
    }
}
