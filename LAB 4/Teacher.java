class Teacher extends Person {
    String subject;

    Teacher(String name, int age, String subject) {
        super(name, age);
        this.subject = subject;
    }

    @Override
    void display() {
        super.display();
        System.out.println("Subject: " + subject);
    }
}