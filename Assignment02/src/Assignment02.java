import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Assignment02 {
	public static void main(String[] args) throws FileNotFoundException
	{
		String filename = "input.txt";
		// open file and create scanner using this. 
		Scanner sc = new Scanner(new File(filename));
		int peopleNum = 0;	// this variable is to store people's num.
		
		// first step : read file.
		System.out.println("1. Read 'input.txt' file");
		
		// count file lines.
		while(sc.hasNextLine()) {
			sc.nextLine();
			peopleNum++;
		}
		
		// file and scanner re-open.
		sc.close();
		sc = new Scanner(new File(filename));
		
		// create arrays to use peopleNum.
		String[] names = new String[peopleNum];	// to store names.
		int[] salarys = new int[peopleNum]; // to store salaries.
		int totalSalary = 0;	// sum of all salaries.
		double avgSalary;		// a average of all salaries.
		int exceptNum = 0;		// exception number (exception occured when file content is strange!)

		for(int i=0; i<peopleNum && sc.hasNextLine(); i++) {
			try {
				// read name,salary and store to array.
				names[i] = (String)sc.next();
				salarys[i] = (int)sc.nextInt();
				// add to totalSalary
				totalSalary += salarys[i];
			} catch(Exception e) {
				// data are ignored when exception occur.
				System.out.println(" [!] exception! (" + e + ")");
				exceptNum++;
				i--;
			} 
		}
		// calculate real people number!
		peopleNum -= exceptNum;
		// calculate a average of salaries.
		avgSalary = (double)totalSalary / peopleNum;
		
		// close scanner.
		sc.close();
		
		// read file finish!
		System.out.println(" [*] Success to read file!");
		
		// step2 : print all informations
		System.out.println("\n2. Print all infomations (name, salary)");
		for(int i=0; i<peopleNum; i++)
			System.out.println(names[i] + " " + salarys[i]);
		
		// step3 : print out average of people's salary
		System.out.println("\n3. Print out average of people's salary");
		System.out.println(" [*] average : " + avgSalary);
		
		// step4 : Print out some people who get higher salary than average salary calculated in 2
		System.out.println("\n4. Print out some people who get higher salary than average salary calculated in 2");
		for(int i=0; i<peopleNum; i++) {
			if(avgSalary < salarys[i])	// compare with average salary.
				System.out.println(names[i] + " " + salarys[i]);
		}
	}
}
