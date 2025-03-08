package question1;

public class Teacher {
    private String name;

    public Teacher(String name) {
        this.name = name;
    }
    public int grading(Student s) {
        if (!s.getAssignment().isSubmitted()) {
            return 0;
        } else if (!s.getAssignment().getName().equals(s.getName())) {
            return 0;
        } else {
            if (!s.getAssignment().getCode().compile()) {
                return 0;
            } else if (!s.getAssignment().getCode().run()) {
                return 50;
            } else {
                if (s.getAssignment().getCode().countLines() < 100) {
                    return 80;
                } else {
                    return 100;
                }
            }
        }
    }
    // Tests
    public static void testTeacher() {
        // set one teacher named xyz
        Teacher t1 = new Teacher("xyz");

        // tests for grading
        // 4 kinds of code
        Code code1 = new Code(true, true, 50);
        Code code2 = new Code(true, false, 120);
        Code code3 = new Code();
        Code code4 = new Code(true, true, 120);

        // set 6 different students
        // not submit,score 0
        Assignment a1 = new Assignment(code1, false, "abin");
        Student stu1 = new Student("abin", true);
        stu1.writeAssignment(a1);
        System.out.println(t1.grading(stu1) == 0);

        // Copying ,score 0
        Student stu2 = new Student("huanfeng", false);
        stu2.copyAssignment(a1);
        System.out.println(t1.grading(stu1) == 0);

        // submitted , cannot compile, score 0
        Assignment a2 = new Assignment(code3, true, "Potter");
        Student stu3 = new Student("potter", true);
        stu3.writeAssignment(a2);
        System.out.println(t1.grading(stu3) == 0);

        // submitted , can compile, cannot run ,score 50
        Assignment a3 = new Assignment(code2, true, "Ron");
        Student stu4 = new Student("Ron", true);
        stu4.writeAssignment(a3);
        System.out.println(t1.grading(stu4) == 50);

        // submitted , can compile, can run, code lines <100 ,score 80
        Assignment a4 = new Assignment(code1, true, "Lupin");
        Student stu5 = new Student("Lupin", true);
        stu5.writeAssignment(a4);
        System.out.println(t1.grading(stu5) == 80);

        // submitted , can compile, can run, code lines >100 ,score 100
        Assignment a5 = new Assignment(code4, true, "Hermione");
        Student stu6 = new Student("Hermione", true);
        stu6.writeAssignment(a5);
        System.out.println(t1.grading(stu6) == 100);
    }
}
