package question4;

public class Robot extends Agent {
	public Robot(int capacity, int knowledge) throws BadInitialSetting {
		super(capacity, knowledge);
	}
	@Override
	public boolean canTalk() {
		return false;
	}
	// Robot cannot talk
	@Override
	public int learn(int amount) {
		return super.getKnowledge();
	}
	// Robot cannot learn, the capacity cannot be changed
	public static void testRobot() 
	{
		/*test case for constructors */
		try {
			Robot r1 = new Robot(50, 60);
		} catch (BadInitialSetting e) {
			System.out.println(e.getMessage().equals("Knowledge cannot greater than capacity!"));
		}
		
		try {
			Robot r2 = new Robot(50,-10);
		} catch (BadInitialSetting e) {
			System.out.println(e.getMessage().equals("Capacity or knowledge cannot be negative!"));
		}
		
		/* test case for learn, canTalk and getKnowledge methods	*/

		try {
			Robot r3 = new Robot(100, 50);
			System.out.println(r3.canTalk()== false);
			System.out.println(r3.getKnowledge()==50);
			r3.learn(20);
			System.out.println(r3.getKnowledge()==50);
		} catch (BadInitialSetting e) {
			System.out.println("BUG! THIS MUST NEVER HAPPEN!");
		} 
	}
}
