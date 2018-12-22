package pt.isep.cms.turmas.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TurmaDetails implements Serializable {
    private String id;
    private String displayName;

    public TurmaDetails() {
        this("0", ""); // this is how you actually do it!
    }

    public TurmaDetails(String id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
