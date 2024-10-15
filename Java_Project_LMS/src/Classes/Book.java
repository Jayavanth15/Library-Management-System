package Classes;

import java.io.Serializable;

public class Book implements Serializable{
	
	private int bookId;
	private String bookName;
	private String writerName;
	private int quantity;
	private Double price;
	
	Book(int id, String bn, String wn, int q, Double p){
		this.bookId = id;
		this.bookName = bn;
		this.writerName = wn;
		this.quantity = q;
		this.price = p;
	}
	
	public void setId(int bookId) {this.bookId = bookId;}
	public int getId() {return bookId;}
	public void setQuantity(int quantity) {this.quantity = quantity;}
	public int getQuantity() {return quantity;}
	public void setBookName(String bookName) {this.bookName = bookName;}
	public String getBookName() {return bookName;}
	public void setWriterName(String writerName) {this.writerName = writerName;}
	public String getWriterName() {return writerName;}
	public void setPrice(Double price) {this.price = price;}
	public Double getPrice() {return price;}
	
	public String toString() {
		return "Book [bookId = " + bookId + ", bookName = " + bookName + ", writerName = " + writerName + ", quantity = " + quantity
				+ ", price = " + price + "]";
	}
	
}
