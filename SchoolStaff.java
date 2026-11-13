class SchoolStaff extends User {
    public SchoolStaff(String id, String password, String name) {
        super(id, password, name);
    }

    public String getRole() {
        return "School Staff";
    }
}