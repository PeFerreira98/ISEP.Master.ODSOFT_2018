package pt.isep.cms.students.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import pt.isep.cms.students.shared.Student;
import pt.isep.cms.students.shared.StudentDetails;

import java.util.ArrayList;

public interface StudentsServiceAsync {
    public void addStudent(Student student, AsyncCallback<Student> callback);

    public void deleteStudent(int id, AsyncCallback<Boolean> callback);

    public void deleteStudents(ArrayList<Integer> ids, AsyncCallback<ArrayList<StudentDetails>> callback);

    public void getStudentDetails(AsyncCallback<ArrayList<StudentDetails>> callback);

    public void getStudent(int id, AsyncCallback<Student> callback);

    public void updateStudent(Student student, AsyncCallback<Student> callback);
}
