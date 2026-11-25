
import java.util.ArrayList;

public class StudentManagement {

    private final ArrayList<Student> students;

    public StudentManagement() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        if (student != null) {
            students.add(student);
        }
    }

    public ArrayList<Student> getStudents() {
        return new ArrayList<>(students);
    }

    public Student searchStudent(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }

        return null;
    }

    public void removeStudent(String id) {
        students.removeIf(student -> student.getId().equals(id));
    }
}
