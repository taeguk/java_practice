/*
 * Class Name : Book
 * Description : this is for storing and controlling book's informations.
 */
public class Book 
{
	// 5 private member variables exist.
	private String bookTitle;	// book title
	private String author;		// book author
	private String publisher;	// book publisher
	private double price;		// book price
	private int inventory;		// book inventory (stock)
	
	// Book Class's constructor
	// initialize member variables.
	public Book(String bookTitle, String author, String publisher, double price, int inventory)
	{
		// if inventory is over 10, sale the price 10% off.
		if(inventory >= 10) {
			price *= 0.9;
		}
		this.bookTitle = bookTitle;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
		this.inventory = inventory;
	}
	
	/*
	 * 5 functions below for encapsulation and indirect access
	 */
	public String getBookTitle() { return this.bookTitle; }
	public String getAuthor() { return this.author; }
	public String getPublisher() { return this.publisher; }
	public double getPrice() { return this.price; }
	public int getInventory() { return this.inventory; }
	
	// This function is for decreasing inventory
	// if success, return remain inventory
	// else, return -1 (lack of inventory)
	public int decreaseInventory()
	{
		if(inventory <= 0)
			return -1;
		this.inventory --;
		return this.inventory;
	}
	
	// print book's information
	public void printBookInfo()
	{
		System.out.println("------------- Book Info -------------");
		System.out.println("Title : " + this.bookTitle);
		System.out.println("Author : " + this.author);
		System.out.println("Publisher : " + this.publisher);
		System.out.println("Price : " + this.price);
		System.out.println("Inventory : " + this.inventory);
		System.out.println("-------------------------------------");
	}
}
