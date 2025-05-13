package question3;

public abstract class Agent implements Trainable {
	private int knowledge;
	private int capacity;
	public Agent(int capacity, int knowledge) throws BadInitialSetting {
		if (capacity < 0 || knowledge < 0) {
			throw new BadInitialSetting("Capacity or knowledge cannot be negative!");
		}
		if (knowledge > capacity) {
			throw new BadInitialSetting("Knowledge cannot greater than capacity!");
		}
		this.knowledge = knowledge;
		this.capacity = capacity;
	}
	// These two exceptions cannot be wrote as Exception
	public int learn(int amount) throws ExceedKnowledgeLimit, NotEnoughKnowledge {
		if (knowledge + amount > capacity) {
			throw new ExceedKnowledgeLimit("Current knowledge is " + knowledge + ". Learn " + amount + " will exceed the capacity!");
		}
		if (knowledge + amount < 0) {
			throw new NotEnoughKnowledge("Current knowledge is " + knowledge + ", not enough amount to forget!");
		}
		knowledge += amount;
		return knowledge;
	}
	public int getKnowledge() {
		return knowledge;
	}
	// Since we don't know if the agent can talk or not, the method is abstract
	public abstract boolean canTalk();
	public static void testAgent() {}
}
