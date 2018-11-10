package pt.isep.cms.students.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import pt.isep.cms.students.client.StudentsService;
import pt.isep.cms.students.shared.Student;
import pt.isep.cms.students.shared.StudentDetails;

@SuppressWarnings("serial")
public class StudentsServiceImpl extends RemoteServiceServlet implements
    StudentsService {

  private static final String[] studentsFirstNameData = new String[] {
      "Bino", "Banana", "Tom"};
  
  private final String[] studentsLastNameData = new String[] {
      "Binario", "Split", "Dicks"};
  
  private final String[] studentsEmailData = new String[] {
      "bb@example.com", "bs@example.com", "td@example.com"};
      
  private final HashMap<String, Student> students = new HashMap<String, Student>();

  public StudentsServiceImpl() {
    initStudents();
  }
  
  private void initStudents() {
    // TODO: Create a real UID for each student
    
    for (int i = 0; i < studentsFirstNameData.length && i < studentsLastNameData.length && i < studentsEmailData.length; ++i) {
      Student student = new Student(String.valueOf(i), studentsFirstNameData[i], studentsLastNameData[i], studentsEmailData[i]);
      students.put(student.getId(), student); 
    }
  }
  
  public Student addStudent(Student student) {
    student.setId(String.valueOf(students.size()));
    students.put(student.getId(), student); 
    return student;
  }

  public Student updateStudent(Student student) {
	  String lid=student.getId();
    students.remove(student.getId());
    students.put(student.getId(), student); 
    return student;
  }

  public Boolean deleteStudent(String id) {
    students.remove(id);
    return true;
  }
  
  public ArrayList<StudentDetails> deleteStudents(ArrayList<String> ids) {

    for (int i = 0; i < ids.size(); ++i) {
      deleteStudent(ids.get(i));
    }
    
    return getStudentDetails();
  }
  
  public ArrayList<StudentDetails> getStudentDetails() {
    ArrayList<StudentDetails> studentDetails = new ArrayList<StudentDetails>();
    
    Iterator<String> it = students.keySet().iterator();
    while(it.hasNext()) { 
      Student student = students.get(it.next());          
      studentDetails.add(student.getLightWeightStudent());
    }
    
    return studentDetails;
  }

  public Student getStudent(String id) {
    return students.get(id);
  }
}
