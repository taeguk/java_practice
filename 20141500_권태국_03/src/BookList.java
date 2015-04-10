import java.util.ArrayList;

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
	
	public Book buyBook(String bookTitle)
	{
		Book book = searchBook(bookTitle);
		if(book == null)
			return null;
		if(book.decreaseInventory() < 0)
			return null;
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
		/*
		Book[] sortedBooks = new Book[bookList.size()];
		for(int i = 0; i < bookList.size(); i++) {
			Book book = bookList.get(i);
			boolean check = false;
			for(int j = 0; j < i; j++) {
				if(sortedBooks[j].getBookTitle().compareTo(book.getBookTitle()) > 0) {
					for(int k = i; k > j; k--)
						sortedBooks[k] = sortedBooks[k-1];
					sortedBooks[j] = book;
					check = true;
					break;
				}
			}
			if(!check)
				sortedBooks[i] = book;
		}
		for(int i = 0; i < sortedBooks.length; i++)
			System.out.println(sortedBooks[i].getBookTitle());
		*/
		for(int i = 0; i < bookList.size(); i++)
			System.out.println(bookList.get(i).getBookTitle());
	}
}
