package question3;

public class Student {
    private int ID;
    // Instance variable
    private String name;
    // Constructor
    public Student(int id, String name) {
        this.ID = Math.max(id, 0);
        this.name = name;
    }
    // Getter
    public void setName(String name) {
        this.name = name;
    }
    // Getter
    public int getID() {
        return this.ID;
    }
    public String getName() { return this.name; }
    // Test
    public static void testStudent() {
        Student s1 = new Student(9999999, "a");
        System.out.println(s1.getID() == 9999999);
        System.out.println(s1.getName() == "a");
        s1.setName("b");
        System.out.println(s1.getName() == "b");
        Student s2 = new Student(-9999999, "c");
        System.out.println(s2.getID() == 0);
        Student s3 = new Student(0, "c");
        System.out.println(s3.getID() == 0);
    }
}
