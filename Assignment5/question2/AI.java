package question2;

public class AI extends Agent {
	// In file, require these two constructors
	public AI(int capacity, int knowledge) throws BadInitialSetting {
		super(capacity, knowledge);
	}
	public AI(int knowledge) throws BadInitialSetting {
		super(1024, knowledge);
	}
	@Override
	public boolean canTalk() {
		return false;
	}
	// AI cannot talk
	public static void testAI() 
	{
		/*test case for constructors */
		try {
			AI gpt1 = new AI(50, 60);
		} catch (BadInitialSetting e) {
			System.out.println(e.getMessage().equals("Knowledge cannot greater than capacity!"));
		}
		
		try {
			AI gpt2 = new AI(50,-10);
		} catch (BadInitialSetting e) {
			System.out.println(e.getMessage().equals("Capacity or knowledge cannot be negative!"));
		}
		
		/* test case for learn, canTalk and getKnowledge methods	*/

		try {
			AI gpt3 = new AI(100, 50);
			System.out.println(gpt3.canTalk()== false);
			gpt3.learn(10);
			System.out.println(gpt3.getKnowledge()== 60);
			gpt3.learn(-20);
			System.out.println(gpt3.getKnowledge()== 40);
			gpt3.learn(70);
			

		} catch (BadInitialSetting e) {
			System.out.println("BUG! THIS MUST NEVER HAPPEN!");
		} catch (ExceedKnowledgeLimit e) 
		{
			System.out.println(e.getMessage().equals("Current knowledge is 40. Learn 70 will exceed the capacity!"));

		} catch (NotEnoughKnowledge e) 
		{
			System.out.println("BUG! THIS MUST NEVER HAPPEN!");
		}
		
		
		try {
			AI gpt4 = new AI(100, 50);
			gpt4.learn(-60);

		} catch (BadInitialSetting e) {
			System.out.println("BUG! THIS MUST NEVER HAPPEN!");
		} catch (ExceedKnowledgeLimit e) 
		{
			System.out.println("BUG! THIS MUST NEVER HAPPEN!");

		} catch (NotEnoughKnowledge e) 
		{
			System.out.println(e.getMessage().equals("Current knowledge is 50, not enough amount to forget!"));
		}
		
	}
}
