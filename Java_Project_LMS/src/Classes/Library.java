package Classes;

import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Library {
	
	static int id = 6;
	static Scanner sc = new Scanner(System.in);
	
	public void addBook(ArrayList<Book> al) {
		int f=0;

		System.out.println("Enter book name : ");
		String name = sc.next();
		
		System.out.println("Enter book writer name : ");
		String author = sc.next();
		
		System.out.println("Enter book quantity : ");
		int quantity = sc.nextInt();
		
		System.out.println("Enter book price : ");
		Double price = sc.nextDouble();
		
		Iterator<Book> itr = al.iterator();
		
		while(itr.hasNext()) {
			Book b = itr.next();
			
			if(b.getBookName().equals(name) && b.getWriterName().equals(author)) {
				
				System.out.println("Book already exists! Quantity is increased");
				
				b.setQuantity((b.getQuantity()+quantity));
				f=1;
				break;
			}
		}
		if(f == 0) {
			
			System.out.println("New Book added successfully");
			
			Book b = new Book(id, name, author, quantity, price);
			try {
				
				FileOutputStream fos = new FileOutputStream("bookDetails.txt");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				
				oos.writeObject(b);
				
				oos.close();
				fos.close();
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			al.add(b);
			id++;
		}
	}
	
	public void deleteBook(ArrayList<Book> al) {
		int f=0;
		
		System.out.println("Enter book name : ");
		String name = sc.next();
		
		System.out.println("Enter book quantity : ");
		int quantity = sc.nextInt();
		
		Iterator<Book> itr = al.iterator();
		
		while(itr.hasNext()) {
			Book b = itr.next();
			
			if(b.getBookName().equals(name)) {
				
				int q = b.getQuantity();
				if(q >= quantity) {
					
					b.setQuantity((q-quantity));
					
					System.out.println("Entered quantity books removed successfully");
					f=1;
					
				}else {
					
					System.out.println("Only " + q + " books are available");
				}
				break;
			}
		}
		if(f == 0) {
			System.out.println("Book not found");
		}
	}
	
	public void searchBook(ArrayList<Book> al, int p) {
		
		Iterator<Book> itr = al.iterator();
		int f=0;
		
		if(p == 1) {
			
			System.out.println("Enter book name : ");
			String s = sc.next();
			
			while(itr.hasNext()) {
				Book b = itr.next();
				
				if(b.getBookName().contains(s)) {
					System.out.println(b);
					f=1;
				}
			}
		}
		else if(p == 2) {
			
			System.out.println("Enter writer name : ");
			String s = sc.next();
			
			while(itr.hasNext()) {
				Book b = itr.next();
				
				if(b.getWriterName().contains(s)) {
					System.out.println(b);
					f=1;
				}
			}
		}
		else {
			System.out.println("Invalid Choice!");
		}
		
		if(f == 0 && (p == 1 || p == 2)) System.out.println("Book not found");
	}
	
	public boolean checkAdmin(ArrayList<User> al, String password) {
		
		Iterator<User> itr = al.iterator();
		
		while(itr.hasNext()) {
			User u = itr.next();
			
			if(u.getpassword().equals(password) && u.getRole().equals("Admin")) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkUId(ArrayList<User> al, int uid) {
		
		for(User u : al) {
			if(u.getUserId() == uid) return true;
		}
		return false;
	}
	
	public boolean checkBId(ArrayList<Book> al, int bid) {
		
		for(Book b : al) {
			if(b.getId() == bid) return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		
		ArrayList<Book> lib = new ArrayList<>();
		ArrayList<User> users = new ArrayList<>();
		ArrayList<IssueBook> issueBook = new ArrayList<>();
		
		Library l = new Library();
		
		lib.add(new Book(1, "SilentPatient", "AlexMichaelides", 125, 299.00));
		lib.add(new Book(2, "EndlessNight", "AgathaChristie", 107, 320.95));
		lib.add(new Book(3, "TheGirlOnTheTrain", "PaulaHawkins", 134, 254.50));
		lib.add(new Book(4, "TheMoonStone", "wilkieCollins", 156, 387.25));
		lib.add(new Book(5, "DarlingGirls", "SallyHepworth", 241, 197.81));
		
		users.add(new User("admin123", "Admin"));
		users.add(new User("jayavanth", "Jay"));
		users.add(new User("pastor", "Rahul"));
		
		while(true) {
			System.out.println("1. Admin\n2. User\n3. Exit");
			int c1 = sc.nextInt();
			
			switch(c1) {
			case 1:
				
				Console console = System.console();
				char passA[] = console.readPassword("Enter Password : ");
				String password = new String(passA);
				boolean check = l.checkAdmin(users, password);
				
				if(check) {
					Loop :
					while(true) {
						System.out.println("1. Add Book\n2. Delete Book\n3. Search Book\n4. Display All Books\n5. Issue Book\n6. Back");
						int c = sc.nextInt();
						
						switch(c) {
						
						case 1:
							l.addBook(lib);
							break;
							
						case 2:
							l.deleteBook(lib);
							break;
							
						case 3:
							System.out.println("1. Search with book name\n2. Search with writer name");
							int p = sc.nextInt();
							l.searchBook(lib, p);
							break;
							
						case 4:
							for(Book b : lib) {
								System.out.println(b);
							}
							break;
							
						case 5:
							System.out.println("Enter User Id to issue book : ");
							int uid = sc.nextInt();
							
							boolean checkUID = l.checkUId(users, uid);
							
							if(!checkUID) {
								System.out.println("User not found");
								continue;
							}
							
							System.out.println("Enter Book Id : ");
							int bid = sc.nextInt();
							
							boolean checkBID = l.checkBId(lib, bid);
							
							if(!checkBID) {
								System.out.println("Book not found");
								continue;
							}
							
							int f=0;
							for(IssueBook i : issueBook) {
								
								if(i.getBookId() == bid && i.getUserId() == uid) {
									System.out.println("Book already issued to user");
									f=1;
									break;
								}
							}
							if(f == 0) {
								System.out.println("Book Issued successfully");
								issueBook.add(new IssueBook(bid, uid));
							}
							break;
							
						case 6:
							System.out.println("Going...");
							break Loop;
							
						default:
							System.out.println("Enter valid choice!");
							break;
						}
					}
				}
				else {
					System.out.println("Incorrect Password!");
				}
				break;
			case 2:
				Loop2 :
				while(true) {
					System.out.println("1. Search Books\n2. Back");
					int c = sc.nextInt();
					
					switch(c) {
					
					case 1:
						System.out.println("1. Search with book name\n2. Search with writer name");
						int p = sc.nextInt();
						l.searchBook(lib, p);
						break;
						
					case 2:
						System.out.println("Going...");
						break Loop2;
					}
				}
				break;
				
			case 3:
				System.out.println("Exiting...");
				System.exit(0);
			}
		}
	}
}
