import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * Class Name : BookList
 * Description : this is for managing books.
 */
public class BookList 
{
	// Internally, book list is stored in ArrayList.
	private ArrayList<Book> bookList = new ArrayList<Book>();
	
	public BookList() {}
	
	// search book
	// if success, return book
	// else, return null (book is not found in bookList)
	public Book searchBook(String bookTitle)
	{
		// loop for all books.
		for(int i = 0; i < bookList.size(); i++) {
			Book book = bookList.get(i);
			if(book.getBookTitle().equals(bookTitle)) {
				// if founded, return book
				return book;
			}
		}
		// if not founded, return null that means fail.
		return null;
	}
	
	// buy book
	// if success, return book
	// else, return null (not in stock or argument invalid)
	public Book buyBook(Book book)
	{
		// if book is invalid
		if(book == null)
			return null;
		// if not in stock
		if(book.decreaseInventory() < 0)
			return null;
		// if success
		return book;
	}
	
	// insert book to bookList.
	// return added book
	// arguments : book's information
	public Book insertBook(String bookTitle, String author, String publisher, double price, int inventory)
	{
		// make new book from arguments.
		Book book = new Book(bookTitle, author, publisher, price, inventory);
		int idx = bookList.size();
		// find proper book's index in bookList for ordering.
		for(int i = 0; i < bookList.size(); i++) {
			if(bookList.get(i).getBookTitle().compareTo(book.getBookTitle()) > 0) {
				idx = i;
			}
		}
		// add book to bookList.
		bookList.add(idx, book);
		return book;
	}
	
	// insert book to bookList
	// return added book
	// arguments : a string line included book's informations
	public Book insertBook(String line) throws Exception 
	{	
		// split line to several tokens. (separate character : '#')
		StringTokenizer st = new StringTokenizer(line, "#");

		String bookTitle = st.nextToken().trim();
		String author = st.nextToken().trim();
		String publisher = st.nextToken().trim();
		double price = Double.parseDouble(st.nextToken().trim());
		int inventory = Integer.parseInt(st.nextToken().trim());

		// call overloaded other function.
		Book book = this.insertBook(bookTitle, author, publisher, price, inventory);
		return book;
	}
	
	// display books' titles orderly
	// don't concern order in this function because books are stored in bookList orderly.
	public void display()
	{
		System.out.println("----------- Books' titles -----------");
		for(int i = 0; i < bookList.size(); i++)
			System.out.println(bookList.get(i).getBookTitle());
		System.out.println("-------------------------------------");
	}
}
