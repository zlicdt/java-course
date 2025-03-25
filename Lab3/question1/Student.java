package question1;

public class Student {
    // Instance variable
    private int ID;
    public Student(int id) {
        ID = id;
    }
    // Getter
    public int getID() {
        return this.ID;
    }
    // Test
    public static void testStudent() {
        Student s = new Student(9999999);
        System.out.println(s.getID() == 9999999);
    }
}
