
public class Book 
{
	private String bookTitle;
	private String author;
	private String publisher;
	private int price;
	private int inventory;
	
	public Book(String bookTitle, String author, String publisher, int price, int inventory)
	{
		this.bookTitle = bookTitle;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
		this.inventory = inventory;
	}
	
	public String getBookTitle() { return this.bookTitle; }
	public String getAuthor() { return this.author; }
	public String getPublisher() { return this.publisher; }
	public int getPrice() { return this.price; }
	public int getInventory() { return this.inventory; }
	
	public int decreaseInventory()
	{
		if(inventory <= 0)
			return -1;
		this.inventory --;
		return this.inventory;
	}
	
	public void printBookInfo()
	{
		System.out.println("Title : " + this.bookTitle);
		System.out.println("Author : " + this.author);
		System.out.println("Publisher : " + this.publisher);
		System.out.println("Price : " + this.price);
		System.out.println("Inventory : " + this.inventory);
	}
}
