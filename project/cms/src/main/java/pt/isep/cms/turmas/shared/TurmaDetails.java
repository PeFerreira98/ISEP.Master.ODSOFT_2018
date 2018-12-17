package pt.isep.cms.turmas.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TurmaDetails implements Serializable {
    private int id;
    private String displayName;

    public TurmaDetails() {
        this(0, "");
    }

    public TurmaDetails(int id, String displayName) {
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
