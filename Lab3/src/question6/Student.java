package question6;

public class Student {
    private int ID;
    private String name;
    private char grade = 'A';
    private boolean sleeping = false;
    // Constructor
    public Student(int id, String name) {
        this.ID = Math.max(id, 0);
        this.name = name;
    }
    public Student(int id, String name, char grade) {
        this.ID = Math.max(id, 0);
        this.name = name;
        this.grade = grade;
    }
    public int getID() {
        return this.ID;
    }
    public String getName() { return this.name; }
    public void setName(String name) {
        this.name = name;
    }
    public char getGrade() { return this.grade; };
    public void setGrade(char grade) { this.grade = grade; }
    public boolean isSleeping() { return this.sleeping; }
    public void goToSleep() {
        this.sleeping = true;
        switch (this.grade) {
            case 'A':
                this.grade = 'B';
                break;
            case 'B':
                this.grade = 'C';
                break;
            case 'C':
                this.grade = 'D';
                break;
            case 'D', 'F':
                this.grade = 'F';
                break;
            default:
                this.grade = 'F';
                break;
        }
        // TODO: Downgrade set
    }
    public void wakeUp() { this.sleeping = false; }
    // Test
    public static void testStudent() {
        Student s1 = new Student(9999999, "a");
        System.out.println(s1.getID() == 9999999);

        System.out.println(s1.getName() == "a");
        s1.setName("b");
        System.out.println(s1.getName() == "b");

        System.out.println(s1.getGrade() == 'A');
        s1.setGrade('B');
        System.out.println(s1.getGrade() == 'B');

        System.out.println(!s1.isSleeping());
        s1.goToSleep();
        System.out.println(s1.isSleeping());
        System.out.println(s1.getGrade() == 'C');
        s1.wakeUp();
        System.out.println(!s1.isSleeping());
        System.out.println(s1.getGrade() == 'C');

        s1.setGrade('X');
        s1.goToSleep();
        System.out.println(s1.isSleeping());
        System.out.println(s1.getGrade() == 'F');
        s1.wakeUp();
        System.out.println(!s1.isSleeping());
        System.out.println(s1.getGrade() == 'F');


        // TODO: Copy

        Student s2 = new Student(-9999999, "c");
        System.out.println(s2.getID() == 0);
        Student s3 = new Student(0, "c");
        System.out.println(s3.getID() == 0);

        Student s4 = new Student(9999999, "a", 'B');
        System.out.println(s4.getID() == 9999999);
        System.out.println(s4.getName() == "a");
        System.out.println(s4.getGrade() == 'B');

        Student s5 = new Student(-9999999, "c", 'C');
        System.out.println(s5.getID() == 0);
        Student s6 = new Student(0, "c", 'C');
        System.out.println(s6.getID() == 0);
    }
}
