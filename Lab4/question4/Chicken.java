package question4;

public class Chicken {
    private double weight;
    private boolean sleeping;

    public Chicken(double weight) {
        // TODO: UML next sentence
        this.weight = weight;
        this.sleeping = true;
    }
    public double getWeight() {
        return this.weight;
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

    public static void testChicken() {
        Chicken c = new Chicken(2.3);
        System.out.println(c.getWeight() == 2.3);
        System.out.println(c.isSleeping() == true);
        c.wakeUp();
        System.out.println(c.isSleeping() == false);
        c.wakeUp(); // should do nothing because the chicken is already awake
        System.out.println(c.isSleeping() == false);
        c.fallAsleep();
        System.out.println(c.isSleeping() == true);
        c.fallAsleep(); // should do nothing because the chicken is already sleeping
        System.out.println(c.isSleeping() == true);
    }
}
