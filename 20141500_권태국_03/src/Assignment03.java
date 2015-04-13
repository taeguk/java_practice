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
				bookList.insertBook(line); 
			} catch(Exception e) {
				System.out.println("Exception occured when processing " + bookFileName + " (" + e + ")");
			} 
		}
		scBook.close();
		
		Scanner scInput = new Scanner(new File(inputFileName));
		while(scInput.hasNextLine()) {
			try {
				String line = scInput.nextLine();
				
				String[] cmd_arg = line.split(" ", 2);
				String cmd = cmd_arg[0];
				
				if(cmd.equals("search")) {
					String arg = cmd_arg[1].trim();
					Book book = bookList.searchBook(arg);
					
					if(book != null)
						book.printBookInfo();
					else
						System.out.println("Unvalid book title!! ("+arg+")");
				} else if(cmd.equals("buy")) {
					String arg = cmd_arg[1].trim();
					Book book = bookList.searchBook(arg);
					
					if(book == null) {
						System.out.println("Unvalid book title!! ("+arg+")");
					} else {
						if(bookList.buyBook(book) != null)
							System.out.println("Buy Book ("+book.getBookTitle()+") success!!");
						else 
							System.out.println("Not in stock!! ("+book.getBookTitle()+")");
					}
				} else if(cmd.equals("insert")) {
					String arg = cmd_arg[1].trim();
					Book book = bookList.insertBook(arg);
					System.out.println("Insert Book ("+book.getBookTitle()+") success!!");
				} else if(cmd.equals("display")) {
					bookList.display();
				} else {
					System.out.println("else ("+cmd+")");
				}
			} catch(Exception e) {
				System.out.println("Exception occured when processing " + inputFileName + " (" + e + ")");
			}
		}
		scInput.close();
	}
}
