package question1;

public class Police {
	private int ID;
    private String name;
    public Police(String name, int ID) {
        this.ID = ID;
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public int getID() {
        return ID;
    }
}
