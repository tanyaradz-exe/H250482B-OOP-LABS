public class Student {
    private String name;
    private int marks;

    public Student(String name, int marks) {
        setName(name);
        setMarks(marks);
    }

    public String getName() { return name; }
    public int getMarks()   { return marks; }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name required");
        }
        this.name = name.trim();
    }

    // Reject marks outside 0..100
    public void setMarks(int marks) {
        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100 but was " + marks);
        }
        this.marks = marks;
    }

    // Derived from marks, so no 'grade' field that could go out of sync
    public String getGrade() {
        if (marks >= 75) return "A";
        if (marks >= 60) return "B";
        if (marks >= 50) return "C";
        return "F";
    }

    @Override
    public String toString() { return name + " (" + marks + " -> " + getGrade() + ")"; }
}
