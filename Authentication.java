
public class Authentication {

        public User authenticate(
                        final String id,
                        final String password,
                        final String role) {

                if (id == null || password == null || role == null
                                || id.trim().isEmpty() || password.isEmpty()) {
                        return null;
                }

                if (role.equals("Student") && id.startsWith("SR-")) {
                        return new Student(id, password, "Student");
                }

                if (role.equals("Guidance Counselor") && id.startsWith("GC-")) {
                        return new GuidanceCounselor(
                                        id, password, "Guidance Counselor");
                }

                if (role.equals("Instructor") && id.startsWith("INS-")) {
                        return new Instructor(id, password, "Instructor");
                }

                if (role.equals("School Staff") && id.startsWith("SS-")) {
                        return new SchoolStaff(id, password, "School Staff");
                }

                return null;
        }
}
