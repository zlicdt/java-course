package question1;

public class Animal {
	private String name;
	private String fastOrslow;
	private int nLegs;
	private int topSpeed;
	private boolean endangered;

	public Animal(String name, int nlegs, int topSpeed, boolean endangered) {
		this.name = name;
		this.nLegs = nlegs;
		this.topSpeed = topSpeed;
		this.endangered = endangered;
		if (topSpeed > 30) {
			this.fastOrslow = "fast";
		} else {
			this.fastOrslow = "slow";
		}
	}
	public String getname() {
		return this.name;
	}
	public void setname(String name) {
		this.name = name;
	}
	public int getnLegs() {
		return this.nLegs;
	}
	public void setnLegs(int nLegs) {
		this.nLegs = nLegs;
	}
	public int gettopSpeed() {
		return this.topSpeed;
	}
	public void settopSpeed(int topSpeed) {
		this.topSpeed = topSpeed;
		if (topSpeed > 30) {
			this.fastOrslow = "fast";
		}
	}
	public boolean getendangered() {
		return this.endangered;
	}
	public void setendangered(boolean endangered) {
		this.endangered = endangered;
	}
	public String getfastOrslow() {
		return this.fastOrslow;
	}
	public void info() {
		System.out.println("The " + this.name + " has a top speed of " + this.topSpeed + " mph, a " + this.fastOrslow + " moving animal.");
	}

	// testAnimal is a public static method that tests all the code in the Animal class
	public static void testAnimal() {
	 	Animal animal = new Animal("Elephant",4,25,true);
	 	
	 	// test the constructor
	 	System.out.println(animal.getname() == "Elephant");
	 	System.out.println(animal.getnLegs() == 4);
	 	System.out.println(animal.gettopSpeed() == 25);
	 	System.out.println(animal.getendangered() == true);
	 	System.out.println(animal.getfastOrslow() == "slow");	 	
	 	animal.info();
	 
	 	// test the setters
	    animal.setname("Lion");
	    animal.settopSpeed(50);
	    animal.setendangered(false); 	
	 	System.out.println(animal.getname() == "Lion");
	 	System.out.println(animal.getnLegs() == 4);
	 	System.out.println(animal.gettopSpeed() == 50);
	 	System.out.println(animal.getendangered() == false);
	 	System.out.println(animal.getfastOrslow() == "fast");	 	
		animal.info();
	}
}