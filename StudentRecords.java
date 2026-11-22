
import java.util.ArrayList;

public class StudentRecords {

    public ArrayList<STRIVELOG.Violation> getStudentRecords(
            STRIVELOG.Student student,
            ArrayList<STRIVELOG.Violation> violations) {

        ArrayList<STRIVELOG.Violation> myRecords = new ArrayList<>();

        for (STRIVELOG.Violation v : violations) {
            if (v.getStudentId().equalsIgnoreCase(student.getId())) {
                myRecords.add(v);
            }
        }

        return myRecords;
    }
}
