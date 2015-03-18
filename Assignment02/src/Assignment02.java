import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Assignment02 {
	public static void main(String[] args) throws FileNotFoundException
	{
		String filename = "input.txt";
		Scanner sc = new Scanner(new File(filename));
		int peopleNum = 0;
		
		System.out.println("1. Read 'input.txt' file");
		
		while(sc.hasNextLine()) {
			sc.nextLine();
			peopleNum++;
		}
		
		sc.close();
		sc = new Scanner(new File(filename));
		
		String[] names = new String[peopleNum];
		int[] salarys = new int[peopleNum];
		int totalSalary = 0;
		double avgSalary;
		int exceptNum = 0;

		for(int i=0; i<peopleNum && sc.hasNextLine(); i++) {
			try {
				names[i] = (String)sc.next();
				salarys[i] = (int)sc.nextInt();
				totalSalary += salarys[i];
			} catch(Exception e) {
				System.out.println(" [!] exception! (" + e + ")");
				exceptNum++;
				i--;
			} 
		}
		peopleNum -= exceptNum;
		avgSalary = (double)totalSalary / peopleNum;
		
		sc.close();
		
		System.out.println(" [*] Success to read file!");
		
		System.out.println("\n2. Print all infomations (name, salary)");
		for(int i=0; i<peopleNum; i++)
			System.out.println(names[i] + " " + salarys[i]);
		
		System.out.println("\n3. Print out average of people¡¯s salary");
		System.out.println(" [*] average : " + avgSalary);
		
		System.out.println("\n4. Print out some people who get higher salary than average salary calculated in 2");
		for(int i=0; i<peopleNum; i++) {
			if(avgSalary < salarys[i])
				System.out.println(names[i] + " " + salarys[i]);
		}
	}
}
