package pt.isep.cms.turmas.shared;

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
public class Turma implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column(unique = true)
    public String name;
    public String teacher;
    public String description;

    public Turma() {
    }

    public Turma(int id, String name, String teacher, String description) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.description = description;
    }

    public TurmaDetails getLightWeightTurma() {
        return new TurmaDetails(id, getFullName());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return name;
    }

    public void setFirstName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return teacher;
    }

    public void setLastName(String teacher) {
        this.teacher = teacher;
    }

    public String getEmailAddress() {
        return description;
    }

    public void setEmailAddress(String description) {
        this.description = description;
    }

    public String getFullName() {
        return name;
    }
}
