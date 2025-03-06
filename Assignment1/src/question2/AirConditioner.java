package question2;

public class AirConditioner {
    private boolean powerOn;
    private int mode;
    private int temperature;

    public AirConditioner() {
        this.powerOn = false;
        this.mode = 0;
        this.temperature = 16;
    }
    public void turnOn() {
        this.powerOn = true;
    }
    public void turnOff() {
        this.powerOn = false;
    }
    public boolean getPower() {
        return powerOn;
    }
    public void setMode(int mode) {
        if (!this.powerOn) {
            System.out.println("Please turn on the AC first.");
            return;
        }
        if (mode != 0 && mode != 1 && mode != 2) {
            System.out.println("Invalid mode.");
            return;
        }
        this.mode = mode;
    }
    public int getMode() {
        return this.mode;
    }
    public void setTemperature(int temp) {
        if (!this.powerOn) {
            System.out.println("Please turn on the AC first.");
            return;
        }
        if (this.mode == 2) {
            System.out.println("Cannot set the temperature in the dry mode.");
            return;
        }
        if (temp < 16 || temp > 30) {
            System.out.println("Invalid temperature.");
            return;
        }
        this.temperature = temp;
    }
    public int getTemperature() {
        return this.temperature;
    }
    // testAirConditioner is a public static method that tests all the code in the
    // AirConditioner class.
    public static void testAirConditioner() {
        AirConditioner ac = new AirConditioner();
        // test the constructor
        System.out.println(ac.getPower() == false);
        System.out.println(ac.getMode() == 0);
        System.out.println(ac.getTemperature() == 16);
        // test the power functions
        ac.turnOn();
        System.out.println(ac.getPower() == true);
        ac.turnOff();
        System.out.println(ac.getPower() == false);
        // test the mode functions
        ac.setMode(1);
        System.out.println(ac.getMode() == 0);
        ac.turnOn();
        ac.setMode(5);
        System.out.println(ac.getMode() == 0);
        ac.setMode(2);
        System.out.println(ac.getMode() == 2);
        // test the temperature functions
        ac.turnOff();
        ac.setTemperature(20);
        System.out.println(ac.getTemperature() == 16);
        ac.turnOn();
        ac.setTemperature(20);
        System.out.println(ac.getTemperature() == 16);
        ac.setMode(1);
        ac.setTemperature(31);
        System.out.println(ac.getTemperature() == 16);
        ac.setTemperature(20);
        System.out.println(ac.getTemperature() == 20);
    }
}
