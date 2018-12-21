package pt.isep.cms.students.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import pt.isep.cms.students.client.StudentsService;
import pt.isep.cms.students.shared.Student;
import pt.isep.cms.students.shared.StudentDetails;
import pt.isep.cms.turmas.shared.Turma;

@SuppressWarnings("serial")
public class StudentsServiceImpl extends RemoteServiceServlet implements StudentsService {

    private static final String[] studentsFirstNameData = new String[] { "Hollie", "Emerson", "Healy", "Brigitte",
            "Elba", "Claudio", "Dena", "Christina", "Gail", "Orville", "Rae", "Mildred", "Candice", "Louise", "Emilio",
            "Geneva", "Heriberto", "Bulrush", "Abigail", "Chad", "Terry", "Bell" };

    private final String[] studentsLastNameData = new String[] { "Voss", "Milton", "Colette", "Cobb", "Lockhart",
            "Engle", "Pacheco", "Blake", "Horton", "Daniel", "Childers", "Starnes", "Carson", "Kelchner", "Hutchinson",
            "Underwood", "Rush", "Bouchard", "Louis", "Andrews", "English", "Snedden" };

    private final String[] studentsEmailData = new String[] { "mark@example.com", "hollie@example.com",
            "boticario@example.com", "emerson@example.com", "healy@example.com", "brigitte@example.com",
            "elba@example.com", "claudio@example.com", "dena@example.com", "brasilsp@example.com", "parker@example.com",
            "derbvktqsr@example.com", "qetlyxxogg@example.com", "antenas_sul@example.com", "cblake@example.com",
            "gailh@example.com", "orville@example.com", "post_master@example.com", "rchilders@example.com",
            "buster@example.com", "user31065@example.com", "ftsgeolbx@example.com" };

    private final HashMap<String, Student> students;
    private int serialId;

    public StudentsServiceImpl() {
        students = new HashMap<String, Student>();
        serialId = 0;

        initStudents();
    }

    private void initStudents() {
        for (int i = 0; i < studentsFirstNameData.length && i < studentsLastNameData.length
                && i < studentsEmailData.length; ++i) {
            Student student = new Student(String.valueOf(i), studentsFirstNameData[i], studentsLastNameData[i],
                    studentsEmailData[i], null);
            addStudent(student);
        }
    }

    // fixed this Id = list.size stupidity
    public Student addStudent(Student student) {
        student.setId(String.valueOf(serialId++));
        students.put(student.getId(), student);

        return student;
    }

    public Student updateStudent(Student student) {
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

        for (Student student : students.values()) // yeah, this exists...
        {
            studentDetails.add(student.getLightWeightStudent());
        }

        return studentDetails;
    }

    public Student getStudent(String id) {
        return students.get(id);
    }
}
