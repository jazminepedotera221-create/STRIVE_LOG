public class SchoolStaff extends User {

    public SchoolStaff(String id, String password, String name) {
        super(id, password, name);
    }

    @Override
    public String getRole() {
        return "School Staff";
    }
}