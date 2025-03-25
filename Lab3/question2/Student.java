package question2;

public class Student {
    private int ID;
    // Constructor
    public Student(int id) {
        ID = Math.max(id, 0);
    }
    // Getter
    public int getID() {
        return this.ID;
    }
    // Test
    public static void testStudent() {
        Student s1 = new Student(9999999);
        System.out.println(s1.getID() == 9999999);
        Student s2 = new Student(-9999999);
        System.out.println(s2.getID() == 0);
        Student s3 = new Student(0);
        System.out.println(s3.getID() == 0);
    }
}
