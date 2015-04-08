import java.util.ArrayList;

public class BookList 
{
	private ArrayList<Book> bookList = new ArrayList<Book>();
	
	public BookList() {}
	
	public Book searchBook(String bookTitle)
	{
		for(int i = 0; i < bookList.size(); i++) {
			Book book = bookList.get(i);
			if(book.getBookTitle() == bookTitle) {
				return book;
			}
		}
		return null;
	}
	
	public Book buyBook(String bookTitle)
	{
		Book book = searchBook(bookTitle);
		if(book == null)
			return null;
		if(book.decreaseInventory() < 0)
			return null;
		return book;
	}
	
	public Book insertBook(String bookTitle, String author, String publisher, int price, int inventory)
	{
		Book book = new Book(bookTitle, author, publisher, price, inventory);
		bookList.add(book);
		return book;
	}
	
	public void display()
	{
		Book[] orderedBooks = new Book[bookList.size()];
		for(int i = 0; i < bookList.size(); i++) {
			Book book = bookList.get(i);
			for(int j = 0; j < i; j++) {
				if(orderedBooks[j].getBookTitle())
			}
		}
	}
}
