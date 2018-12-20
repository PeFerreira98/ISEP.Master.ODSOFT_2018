package pt.isep.cms.students.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import pt.isep.cms.students.client.StudentsService;
import pt.isep.cms.students.shared.Student;
import pt.isep.cms.students.shared.StudentDetails;

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

    private EntityManagerFactory emfactory = null;
    private EntityManager entitymanager = null;

    public StudentsServiceImpl() {
        this.emfactory = Persistence.createEntityManagerFactory("CMS");

        this.entitymanager = emfactory.createEntityManager();

        initPersistentStudents();
    }

    private void initPersistentStudents() {
        // We only do this if the database is empty...
        Query query = entitymanager.createQuery("Select COUNT(c) from Student c");
        Long result = (Long) query.getSingleResult();

        if (result == 0) {
            this.entitymanager.getTransaction().begin();

            for (int i = 0; i < studentsFirstNameData.length && i < studentsLastNameData.length
                    && i < studentsEmailData.length; ++i) {

                Student turma = new Student(i, studentsFirstNameData[i], studentsLastNameData[i],
                        studentsEmailData[i]);
                addStudent(turma);
            }
        }
    }

    public Student addStudent(Student turma) {
        // Add the new turma to the database
        this.entitymanager.getTransaction().begin();
        this.entitymanager.persist(turma);
        this.entitymanager.getTransaction().commit();

        return turma;
    }

    public Student updateStudent(Student turma) {
        // Update the turma in the database
        this.entitymanager.getTransaction().begin();
        this.entitymanager.merge(turma);
        this.entitymanager.getTransaction().commit();

        return turma;
    }

    public Boolean deleteStudent(int id) {
        // Remove the turma in the database
        this.entitymanager.getTransaction().begin();
        Student turma = entitymanager.find(Student.class, id);
        entitymanager.remove(turma);
        this.entitymanager.getTransaction().commit();

        return true;
    }

    public ArrayList<StudentDetails> deleteStudents(ArrayList<Integer> ids) {
        for (int i = 0; i < ids.size(); ++i) {
            deleteStudent(ids.get(i));
        }

        return getStudentDetails();
    }

    public ArrayList<StudentDetails> getStudentDetails() {
        ArrayList<StudentDetails> turmaDetails = new ArrayList<StudentDetails>();

        Query query = entitymanager.createQuery("Select c from Student c");

        @SuppressWarnings("unchecked")
        List<Student> list = query.getResultList();

        for (Student turma : list) {
            turmaDetails.add(turma.getLightWeightStudent());
        }

        return turmaDetails;
    }

    public Student getStudent(int id) {
        return entitymanager.find(Student.class, id);
    }
}
