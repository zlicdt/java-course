package question1;

public class PoliceDog extends Dog{
    private int weekHours;
    private static int totalHoursforAll;
    private int times;
    private Police trainer;
    public PoliceDog(String name, double weight) {
        super(name, weight);
        weekHours = 0;
        totalHoursforAll = 0;
        times = 0;
    }
    public void train(int hours) {
        times++;
        if (times > 7) {
            times = times % 7;
            weekHours = 0;
        }
        weekHours += hours;
        totalHoursforAll += hours;
    }
    public Police getTrainer() {
        return this.trainer;
    }
    public void setTrainer(Police trainer) {
        this.trainer = trainer;
    }
    public int getWeekHours() {
        if(this.times < 7) {
            System.out.println("No a week yet");
        }
        return weekHours;
    }
    public static int getTotalHours() {
        return totalHoursforAll;
    }
}
