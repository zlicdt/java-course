package question2;

public class AirConditioner {
    
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
