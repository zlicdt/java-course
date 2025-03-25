package question3;

public class Start {
    public static void main(String[] args) {
        Student.testStudent();
        Chicken.testChicken();
        Student s = new Student(1234567890);
        System.out.println(check(s) == "need coffee");
        s.fallAsleep();
        System.out.println(check(s) == "sweet dreams");
    }
    public static String check(Student someStudent) {
        // TODO: Implement this method according to the question 2 description.
        if (someStudent.isSleeping()) {
            return "sweet dreams";
        } else {
            return "need coffee";
        }
    }
}