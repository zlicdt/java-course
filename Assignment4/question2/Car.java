package question2;

public abstract class Car implements Movable {
	private String brand;
    private double speed;
    // Constructor
    public Car(String brand) {
        this.brand = brand;
        this.speed = 0;
    }
    public String getBrand() {
        return this.brand;
    }
    public double getSpeed() {
        return this.speed;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    public abstract void getSupply();
    // start(): since we don’t know what type the car is, we don’t know how fast it is. Therefore, this method is not implemented.
    @Override
    public void stop() {
        this.speed = 0.0;
    }
}
