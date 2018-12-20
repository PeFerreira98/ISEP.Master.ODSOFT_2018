package pt.isep.cms.students.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class StudentDetails implements Serializable {
    private int id;
    private String displayName;

    public StudentDetails() {
        this(0, "");
    }

    public StudentDetails(int id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
