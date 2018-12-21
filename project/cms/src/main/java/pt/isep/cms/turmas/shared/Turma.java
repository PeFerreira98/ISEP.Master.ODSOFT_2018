package pt.isep.cms.turmas.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Turma implements Serializable {
    public String id;
    public String name;
    public String teacher;
    public String description;

    public Turma() {
    }

    public Turma(String id, String name, String teacher, String description) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.description = description;
    }

    public TurmaDetails getLightWeightTurma() {
        return new TurmaDetails(id, getFullName());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
