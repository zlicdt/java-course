package question1;

public class Start {
	public static void main(String[] args) {
		
		PoliceDog d1= new PoliceDog("little", 30.0), d2= new PoliceDog("super", 40.0);
		Police p1 = new Police("Sir O", 101), p2 = new Police("Sir T", 102);
		int hours;
		int week1d1hours[] = {1, 1, 1, 1, 1, 1, 1}; // training hours in the first week for d1
		int week1d2hours[] = {2, 2, 2, 2, 2, 2, 2}; // training hours in the first week for d2
		
		int week2d1hours[] = {1, 1}; // training hours in the second week for d1
		int week2d2hours[] = {2, 2}; // training hours in the second week for d2
		
		d1.setTrainer(p1);
		d2.setTrainer(p2);
		
		d1.setBreed("Rottweiler");
		d2.setBreed("Boxer");
		
		//first week test
		for (int i = 0; i <= 6; i++) {
			System.out.println(d1.getTrainer().getName() + ": the training hours for " + d1.getName() + " in " + (i+1) + "th day:" + week1d1hours[i]);
			d1.train(week1d1hours[i]);
			
			System.out.println(d2.getTrainer().getName() + ": the training hours for " + d2.getName() + " in " + (i+1) + "th day:" + week1d2hours[i]);
			d2.train(week1d2hours[i]);
			
		}
		System.out.println(d1.getName() + " First week: " + d1.getWeekHours());
		System.out.println(d2.getName() + " First week: " + d2.getWeekHours());
		System.out.println("Total hours: " + PoliceDog.getTotalHours());
		//second week test
		for (int i = 0; i <= 1; i++) {
			System.out.println(d1.getTrainer().getName() + ": the training hours for " + d1.getName() + " in " + (i+1) + "th day:" + week2d1hours[i]);
			d1.train(week2d1hours[i]);
			
			System.out.println(d2.getTrainer().getName() + ": the training hours for " + d2.getName() + " in " + (i+1) + "th day:" + week2d2hours[i]);
			d2.train(week2d2hours[i]);
		}
		System.out.println(d1.getName() + " Second week: " + d1.getWeekHours());
		System.out.println(d2.getName() + " Second week: " + d2.getWeekHours());
    	System.out.println("Total hours: " + PoliceDog.getTotalHours());
	}
	
}