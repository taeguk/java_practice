import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Assignment03 {
	public static void main(String[] args) throws FileNotFoundException
	{
		final String bookFileName = "Books.txt";
		final String inputFileName = "input.txt";
		
		BookList bookList = new BookList();
		
		Scanner scBook = new Scanner(new File(bookFileName));
		while(scBook.hasNextLine()) {
			try {
				String line = scBook.nextLine();
				StringTokenizer st = new StringTokenizer(line, "#"); 
	            
				String bookTitle = st.nextToken();
				String author = st.nextToken();
				String publisher = st.nextToken();
				double price = Double.parseDouble((st.nextToken()));
				int inventory = Integer.parseInt(st.nextToken());
				
				bookList.insertBook(bookTitle, author, publisher, price, inventory); 
			} catch(Exception e) {
				System.out.println("Exception occured! ("+e+")");
			} 
		}
		scBook.close();
		
		Scanner scInput = new Scanner(new File(inputFileName));
		while(scInput.hasNextLine()) {
			//try {
				String line = scInput.nextLine();
				
				String[] cmd_arg = line.split(" ", 2);
				String cmd = cmd_arg[0];
				
				if(cmd.equals("search")) {
					String arg = cmd_arg[1];
					Book book;
					if((book = bookList.searchBook(arg)) != null)
						book.printBookInfo();
				} else if(cmd.equals("buy")) {
					String arg = cmd_arg[1];
					bookList.buyBook(arg);
				} else if(cmd.equals("insert")) {
					String arg = cmd_arg[1];
					StringTokenizer st = new StringTokenizer(arg, "#");
					String bookTitle = st.nextToken();
					String author = st.nextToken();
					String publisher = st.nextToken();
					double price = Double.parseDouble((st.nextToken()));
					int inventory = Integer.parseInt(st.nextToken());
					bookList.insertBook(bookTitle, author, publisher, price, inventory);
				} else if(cmd.equals("display")) {
					bookList.display();
				} else {
					System.out.println("else ("+cmd+")");
				}
			/*} catch(Exception e) {
				System.out.println("Exception occured!! ("+e+")");
			}*/
		}
		scBook.close();
		scInput.close();
		
	}
}
