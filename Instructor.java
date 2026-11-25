public class Instructor extends User {

    public Instructor(String id, String password, String name) {
        super(id, password, name);
    }

    @Override
    public String getRole() {
        return "Instructor";
    }
}