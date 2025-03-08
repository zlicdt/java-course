package question1;

public class Assignment {
    private Code myCode;
    private int score;
    private boolean submitted;
    private String name;

    public Assignment(Code myCode, Boolean submitted, String name) {
        this.myCode = myCode;
        this.submitted = submitted;
        this.name = name;
    }
    public void submit() {
        this.submitted = true;
    }
    public boolean isSubmitted() {
        return this.submitted;
    }
    public int getScore() {
        return this.score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public String getName() {
        return this.name;
    }
    public Code getCode() {
        return this.myCode;  
    }
    // Tests
    public static void testAssignment() {
        // Test for the constructor
        Code code0 = new Code();
        Assignment a0 = new Assignment(code0, false, "xiaoyu");
        System.out.println(a0.getName() == "xiaoyu");
        System.out.println(a0.isSubmitted() == false);

        // Test for code
        // the default code can not be run
        System.out.println(a0.getCode().run() == false);

        // Test for submit
        a0.submit();
        System.out.println(a0.isSubmitted() == true);

        // Test for score
        a0.setScore(15);
        System.out.println(a0.getScore() == 15);
    }
}
