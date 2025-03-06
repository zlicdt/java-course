package question1;

public class Student {
    private int ID;
    private boolean sleeping;

    public Student(int ID) {
        this.ID = ID;
        this.sleeping = false;
    }
    public int getID() {
        return this.ID;
    }
    public boolean isSleeping() {
        return this.sleeping;
    }
    public void fallAsleep() {
        this.sleeping = true;
    }
    public void wakeUp() {
        this.sleeping = false;
    }
    public static void testStudent() {
        Student s = new Student(1234567890);
        System.out.println(s.getID() == 1234567890);
        System.out.println(s.isSleeping() == false);
        s.fallAsleep();
        System.out.println(s.isSleeping() == true);
        s.fallAsleep(); // should do nothing because the student is alreadysleeping
        System.out.println(s.isSleeping() == true);
        s.wakeUp();
        System.out.println(s.isSleeping() == false);
        s.wakeUp(); // should do nothing because the student is already awake
        System.out.println(s.isSleeping() == false);
    }
}
