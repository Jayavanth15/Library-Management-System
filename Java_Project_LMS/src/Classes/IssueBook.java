package Classes;

public class IssueBook {
	
	private int bookId;
	private int userId;
	
	IssueBook(int bookId, int userId){
		this.bookId = bookId;
		this.userId = userId;
	}
	
	public void setBookId(int bookId) { this.bookId = bookId;}
	public int getBookId() { return bookId;}
	public void setUserId(int userId) { this.userId = userId;}
	public int getUserId() { return userId;}

	public String toString() {
		return "IssueBook [bookId=" + bookId + ", userId=" + userId + "]";
	}
}
