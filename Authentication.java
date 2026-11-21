public class Authentication {

    public STRIVELOG.User authenticate(
            String id, String password, String selectedRole) {

        if (selectedRole.equals("Guidance")
                && id.equals("GC-1001")
                && password.equals("1234")) {

            return new STRIVELOG.GuidanceCounselor(
                    id, password, "Guidance Counselor");

        } else if (selectedRole.equals("Instructor")
                && id.equals("INS-1001")
                && password.equals("1234")) {

            return new STRIVELOG.Instructor(
                    id, password, "Instructor");

        } else if (selectedRole.equals("Staff")
                && id.equals("SS-1001")
                && password.equals("1234")) {

            return new STRIVELOG.SchoolStaff(
                    id, password, "School Staff");

        } else if (selectedRole.equals("Student")
                && id.startsWith("SR-")) {

            return new STRIVELOG.Student(
                    id, password, "Student");
        }

        return null;
    }
}