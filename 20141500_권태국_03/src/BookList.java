import java.util.ArrayList;
import java.util.StringTokenizer;

public class BookList 
{
	private ArrayList<Book> bookList = new ArrayList<Book>();
	
	public BookList() {}
	
	public Book searchBook(String bookTitle)
	{
		for(int i = 0; i < bookList.size(); i++) {
			Book book = bookList.get(i);
			if(book.getBookTitle().equals(bookTitle)) {
				return book;
			}
		}
		return null;
	}
	
	public Book buyBook(Book book)
	{
		if(book == null)
			return null;
		if(book.decreaseInventory() < 0)
			return null;
		return book;
	}
	
	public Book insertBook(String line) throws Exception
	{
		StringTokenizer st = new StringTokenizer(line, "#");
	    
		String bookTitle = st.nextToken().trim();
		String author = st.nextToken().trim();
		String publisher = st.nextToken().trim();
		double price = Double.parseDouble(st.nextToken().trim());
		int inventory = Integer.parseInt(st.nextToken().trim());
		
		Book book = this.insertBook(bookTitle, author, publisher, price, inventory);
		return book;
	}
	
	public Book insertBook(String bookTitle, String author, String publisher, double price, int inventory)
	{
		Book book = new Book(bookTitle, author, publisher, price, inventory);
		int idx = bookList.size();
		for(int i = 0; i < bookList.size(); i++) {
			if(bookList.get(i).getBookTitle().compareTo(book.getBookTitle()) > 0) {
				idx = i;
			}
		}
		bookList.add(idx, book);
		return book;
	}
	
	public void display()
	{
		System.out.println("----------- Books' titles -----------");
		for(int i = 0; i < bookList.size(); i++)
			System.out.println(bookList.get(i).getBookTitle());
		System.out.println("-------------------------------------");
	}
}
