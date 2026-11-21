public class GuidanceCounselor extends User {

    public GuidanceCounselor(String id, String password, String name) {
        super(id, password, name);
    }

    public String getRole() {
        return "Guidance Counselor";
    }
}