public class StudentDemo {
    public static void main(String[] args) {
        Student s = new Student("Tariro", 82);
        System.out.println(s);
        s.setMarks(55);
        System.out.println(s);

        for (int bad : new int[]{-5, 101}) {
            try {
                s.setMarks(bad);
            } catch (IllegalArgumentException e) {
                System.out.println("Rejected: " + e.getMessage());
            }
        }
        System.out.println("Marks unchanged: " + s.getMarks());

        for (int m : new int[]{80, 65, 50, 49}) {
            System.out.println(m + " -> " + new Student("X", m).getGrade());
        }
    }
}
