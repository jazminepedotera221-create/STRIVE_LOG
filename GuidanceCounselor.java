public class GuidanceCounselor extends User {

    public GuidanceCounselor(String id, String password, String name) {
        super(id, password, name);
    }

    @Override
    public String getRole() {
        return "Guidance Counselor";
    }
}