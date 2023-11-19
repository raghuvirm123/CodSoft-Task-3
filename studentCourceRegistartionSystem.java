package codSoft;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class studentCourceRegistartionSystem {


	static String url="jdbc:mysql://localhost:3306/codsoft";
	static String uname="root";
	static String pwd="root";
	
	static Connection con=null;
	static PreparedStatement pst=null;
	static ResultSet re=null;
	
	static String qry="insert into student values(?,?,?)";
	static String qry1="update student set  name=?,sub=? where id=?" ;
	static String qry2="select * from student where id=?";
	static String qry3="delete from student where id=?";
	static String qry4="select * from student";
	
	public static void index()
	{
		System.out.println("1.insert data");
		System.out.println("2.update data");
		System.out.println("3.Remove data");
		System.out.println("4.Find data");
		System.out.println("5.Find all data");
		System.out.println("6.exit");	
	}
	
	public static void insert() throws SQLException, ClassNotFoundException
	{
		
		pst=con.prepareStatement(qry);
		Scanner sc=new Scanner(System.in);
		System.out.println("enter id:");
		int id=sc.nextInt();
		System.out.println("enter name:");
		String name=sc.next();
		System.out.println("enter sub:");
		String sub=sc.next();
		
		pst.setInt(1, id);
		pst.setString(2, name);
		pst.setString(3, sub);
		pst.execute();
		System.out.println("data inserted.....");
		
	}
	
	public static void update() throws SQLException, ClassNotFoundException
	{
		
		pst=con.prepareStatement(qry1);
		Scanner sc=new Scanner(System.in);
		
		System.out.println("enter name:");
		String name=sc.next();
		System.out.println("enter sub:");
		String sub=sc.next();
		System.out.println("enster id:");
		int id=sc.nextInt();
		
		
		pst.setString(1, name);
		pst.setString(2, sub);
		pst.setInt(3, id);
		pst.execute();
		System.out.println("data updated.....");
		
	}
	
	public static void delete() throws SQLException, ClassNotFoundException
	{
		
		pst=con.prepareStatement(qry3);
		Scanner sc=new Scanner(System.in);
		
		
		System.out.println("enster id:");
		int id=sc.nextInt();
		
		
		
		pst.setInt(1, id);
		pst.execute();
		System.out.println("data deleted.....");
		
	}
	
	public static void find() throws SQLException, ClassNotFoundException
	{
		
		pst=con.prepareStatement(qry2);
		System.out.println("enster id:");
		Scanner sc=new Scanner(System.in);
		int id=sc.nextInt();
		
		pst.setInt(1, id);
		re=pst.executeQuery();
		
		while(re.next())
		{
			System.out.println(re.getInt(1)+" "+ re.getString(2)+" "+re.getString(3));
		}
	
	
		
		
	}
	
	public static void findAll() throws SQLException, ClassNotFoundException
	{
		
		pst=con.prepareStatement(qry4);
		
		re=pst.executeQuery();
		
		if(re==null) {
			System.out.println("No data present in table");
		}
		else
		{
			System.out.println("----------------------------");
			while(re.next())
			{
				System.out.println(re.getInt(1)+" "+ re.getString(2)+" "+re.getString(3));
			}
			System.out.println("----------------------------");
		}
		
	
	
		
		
	}
	
	public static void abc() throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection(url, uname, pwd);
		index();
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		
		switch (n)
		{
		case 1: 
			insert();
			abc();
			break;
		case 2:
			update() ;
			abc();
			break;
			
		case 3:
			delete();
			abc();
			break;
		case 4:
			find();
			abc();
			break;	
			
		case 5:
			findAll();
			abc();
			break;
			
		case 6:
			System.out.println("Thank You");
			break;
			
			
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + n);
		}
		
	}
	
	
        public static void main(String[] args) throws ClassNotFoundException, SQLException  {
		
		abc();
		
	}
	
	
}
