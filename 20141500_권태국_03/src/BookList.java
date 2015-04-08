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
}
