package Classes;

public class User {
	
	private static int id = 1;
	private int userId;
	private String password;
	private String role;
	
	User(String password, String role){
		this.userId = id++;
		this.password = password;
		this.role = role;
	}
	
	public void setUserId(int userId) {this.userId = userId;}
	public int getUserId() {return userId;}
	public void setPassword(String password) {this.password = password;}
	public String getpassword() {return password;}
	public void setRole(String role) {this.role = role;}
	public String getRole() {return role;}

	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", role=" + role + "]";
	}
}
