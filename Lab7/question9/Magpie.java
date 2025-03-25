package question9;

// The class Magpie derives from the class Bird.
public class Magpie extends Bird {
	public Magpie(String name) {
		super(name);
		super.setNumOfEggs(6);
	}
	@Override
	public boolean canFly() {
		return true;
	}
	public static void testMagpie() {
		Magpie m = new Magpie("Maggie");
		// The getName method is inherited from Bird, which inherits it
		// from Animal.
		// The getLegs method is inherited from Bird, which overrides
		// the abstract getLegs method inherited by Bird from Animal.
		// The getNumOfEggs method is inherited from Bird.
		// The canFly method is from Magpie, which overrides the abstract
		// canFly method inherited from Bird, which inherits it from
		// Animal.System.out.println(m.getName() == "Maggie");
		System.out.println(m.getLegs() == 2);
		System.out.println(m.getNumOfEggs() == 6);
		System.out.println(m.canFly() == true);
	}
}