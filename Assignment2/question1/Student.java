package question1;

public class Student {
    private Assignment myAssignment;
    private boolean honest = true;
    private String name;

    public Student(String name, Boolean honest) {
        this.name = name;
        this.honest = honest;
    }
    public Assignment getAssignment() {
        return this.myAssignment;
    }
    public void writeAssignment(Assignment a) {
        this.myAssignment = a;
    }
    public void copyAssignment(Assignment a) {
        if (this.name == a.getName()) {
            return;
        } else {
            this.myAssignment = a;
            this.honest = false;
        }
    }
    public String getName() {
        return this.name;
    }
    // Tests
    public static void testStudent() {
        Assignment a1 = new Assignment(null, true, "xiaoxue");
        
        // create 2 students to test
        Student stu1 = new Student("xiaoxue", true);
        Student stu2 = new Student("xiaoyu", false);
        
        System.out.println(stu1.getName() == "xiaoxue");
        System.out.println(stu2.getName() == "xiaoyu");
        
        // Student1 Write assignment
        stu1.writeAssignment(a1);
        
        // get Student1's assignment
        Assignment assignment1 = stu1.getAssignment();
        
        // Student2 Copy that assignment
        stu2.copyAssignment(assignment1);
        
        // stu2's name should NOT be equal with the name variable in the copied assignment object
        System.out.println((stu2.getName() == assignment1.getName()) == false);
    }
}