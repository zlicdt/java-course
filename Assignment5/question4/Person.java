package question4;

public class Person extends Agent{
	private int age;
	private String name;
	// Constructor for Person
	public Person(int capacity, int knowledge, String name, int age) throws BadInitialSetting, BadAgeSetting {
		super(capacity, knowledge);
		if (age < 18) {
			throw new BadAgeSetting("Age cannot smaller than 18!");
		}
		// capacity and knowledge are already checked in the super constructor
		this.age = age;
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public String getName() {
		return name;
	}
	@Override
	public boolean canTalk() {
		return true;
	}
	// Person can talk
    public static void testPerson() 
    {
    	/*test case for constructors */
		try {
			Person p1 = new Person(50, 60,"Alice",22);
		} catch (BadInitialSetting e) {
			System.out.println(e.getMessage().equals("Knowledge cannot greater than capacity!"));
		} catch (BadAgeSetting e) 
		{
			System.out.println("BUG! THIS MUST NEVER HAPPEN!");
		}
		
		try {
			Person p2 = new Person(50,-10,"Bob",23);
		} catch (BadInitialSetting e) {
			System.out.println(e.getMessage().equals("Capacity or knowledge cannot be negative!"));
		} catch (BadAgeSetting e) 
		{
			System.out.println("BUG! THIS MUST NEVER HAPPEN!");
		}
		
		try {
			Person p3 = new Person(100,50,"Cherry",17);
		} catch (BadInitialSetting e) {
			System.out.println("BUG! THIS MUST NEVER HAPPEN!");
		} catch (BadAgeSetting e) {
			System.out.println(e.getMessage().equals("Age cannot smaller than 18!"));
		}
		
		/* test case for learn, canTalk, getKnowledge, getAge and getName methods	*/

		try {
			Person p4 = new Person(100, 50,"Dave",25);
			System.out.println(p4.canTalk()== true);
			System.out.println(p4.getAge()== 25);
			System.out.println(p4.getName()=="Dave");

			p4.learn(10);
			System.out.println(p4.getKnowledge()== 60);
			p4.learn(-20);
			System.out.println(p4.getKnowledge()== 40);
			p4.learn(70);

		} catch (BadInitialSetting e) {
			System.out.println("BUG! THIS MUST NEVER HAPPEN!");
		} catch (ExceedKnowledgeLimit e) 
		{
			System.out.println(e.getMessage().equals("Current knowledge is 40. Learn 70 will exceed the capacity!"));
		} catch (NotEnoughKnowledge e) 
		{
			System.out.println("BUG! THIS MUST NEVER HAPPEN!");
		} catch (BadAgeSetting e) 
		{
			System.out.println("BUG! THIS MUST NEVER HAPPEN!");
		}
		
		
		try {
			Person p5 = new Person(100, 50,"Ella",24);
			p5.learn(-60);

		} catch (BadInitialSetting e) {
			System.out.println("BUG! THIS MUST NEVER HAPPEN!");
		} catch (ExceedKnowledgeLimit e) 
		{
			System.out.println("BUG! THIS MUST NEVER HAPPEN!");

		} catch (NotEnoughKnowledge e) 
		{
			System.out.println(e.getMessage().equals("Current knowledge is 50, not enough amount to forget!"));
		} catch (BadAgeSetting e) 
		{
			System.out.println("BUG! THIS MUST NEVER HAPPEN!");
		}
		
	}
 }	

