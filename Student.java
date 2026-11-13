class Student extends User {
    public Student(String id, String password, String name) {
        super(id, password, name);
    }

    public String getRole() {
        return "Student";
    }
}
