package question8;

public class Airplane implements Flyer {
	private String name;
	public Airplane(String name) {
		this.name = name;
	}
	public boolean canFly() {
		return true;
	}
	public String getName() {
		return name;
	}
	public static void testAirplane() {
		Airplane a = new Airplane("Aircar");
		System.out.println(a.getName() == "Aircar");
		System.out.println(a.canFly() == true);
	}
}