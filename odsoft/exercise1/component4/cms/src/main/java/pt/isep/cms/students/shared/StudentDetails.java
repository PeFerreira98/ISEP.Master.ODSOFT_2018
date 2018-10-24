package pt.isep.cms.students.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class StudentDetails implements Serializable
{
  private String id;
  private String displayName;
  
  public StudentDetails()
  {
    this("0", ""); // this is how you actually do it!
  }

  public StudentDetails(String id, String displayName)
  {
    this.id = id;
    this.displayName = displayName;
  }
  
  public String getId() { return id; }
  public void setId(String id) { this.id = id; }

  public String getDisplayName() { return displayName; }
  public void setDisplayName(String displayName) { this.displayName = displayName; } 
}
