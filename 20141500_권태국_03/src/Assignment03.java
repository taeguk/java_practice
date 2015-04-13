import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Assignment03 {
	// this is main function.
	public static void main(String[] args) throws FileNotFoundException
	{
		/*
		 * two constants for file names
		 */
		final String bookFileName = "Books.txt";
		final String inputFileName = "input.txt";
		
		// create instance of BookList.
		BookList bookList = new BookList();
		
		/* 1. processing book file (reading book information) */
		Scanner scBook = new Scanner(new File(bookFileName));
		while(scBook.hasNextLine()) {
			try {
				// insert book from string line one by one.
				String line = scBook.nextLine();
				bookList.insertBook(line); 
			} catch(Exception e) {
				// if exception occurs, alert to user.
				// probably file content is wrong.
				System.out.println("Exception occured when processing " + bookFileName + " (" + e + ")");
			} 
		}
		scBook.close();
		/* processing book file finish */
		
		/* 2. processing input file (reading command information)*/
		Scanner scInput = new Scanner(new File(inputFileName));
		while(scInput.hasNextLine()) {
			try {
				// get a string line
				String line = scInput.nextLine();
				
				// split line into two. 
				String[] cmd_arg = line.split(" ", 2);
				String cmd = cmd_arg[0];
				
				// if command is "search"
				if(cmd.equals("search")) {
					String bookTitle = cmd_arg[1].trim();
					// search book by title 
					Book book = bookList.searchBook(bookTitle);
					
					if(book != null)
						book.printBookInfo();
					else
						System.out.println("invalid book title!! ("+bookTitle+")");
				} 
				// if command is "buy"
				else if(cmd.equals("buy")) {
					String bookTitle = cmd_arg[1].trim();
					// search book by title 
					Book book = bookList.searchBook(bookTitle);
					
					if(book == null) {
						// when book title is invalid
						System.out.println("invalid book title!! ("+bookTitle+")");
					} else {
						if(bookList.buyBook(book) != null)
							System.out.println("Buy Book ("+book.getBookTitle()+") success!!");
						else 	// when book is not in stock
							System.out.println("Not in stock!! ("+book.getBookTitle()+")");
					}
				} 
				// if command is "insert"
				else if(cmd.equals("insert")) {
					String infoLine = cmd_arg[1].trim();
					Book book = bookList.insertBook(infoLine);
					System.out.println("Insert Book ("+book.getBookTitle()+") success!!");
				} 
				// if command is "display"
				else if(cmd.equals("display")) {
					bookList.display();
				} 
				// if command is invalid
				else {
					System.out.println("invalid command!! ("+cmd+")");
				}
			} catch(Exception e) {
				System.out.println("Exception occured when processing " + inputFileName + " (" + e + ")");
			}
		}
		scInput.close();
	}
	/* processing input file finish */
}
