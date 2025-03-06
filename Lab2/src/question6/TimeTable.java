package question6;

public class TimeTable {
	public static void main(String[] args) {
		// Print the multiplication table
		System.out.println("*\t|\t1\t2\t3\t4\t5\t6\t7\t8\t9");
		// Print the line
		System.out.println("---------------------------------------");
		for (int i = 1; i <= 9; i++) {
			System.out.print("1\t|");
			for (int j = 1; j <= 9; j++) {
				// Print the multiplication table
				System.out.print("\t" + i * j);
			}
			System.out.println();
		}
	}
}
