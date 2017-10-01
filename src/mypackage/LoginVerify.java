package mypackage;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class LoginVerify {
	public static String key;
	
	public static boolean validateLogin(String e,String p,String m){
		boolean status=false;
	
	
	try{  
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/exp","varshant","nitVASU14");
		String q=null;
		if(m.equals("user")){
		 q="SELECT * FROM register where email='"+e+"' and password='"+p+"'";
		 }
		else{
			q="SELECT * FROM admin where username='"+e+"' and password='"+p+"'";
		}
	Statement stmt=con.createStatement();
		
	ResultSet rs=stmt.executeQuery(q);
	status=rs.next();
	
	if(status==true && m.equals("user")){
		
	String qr="Update register set login_status='Login'  where email=?";
	PreparedStatement sup=(PreparedStatement) con.prepareStatement(qr);
	sup.setString(1, e);
	sup.executeUpdate();
	}
	else if(status==true && m.equals("admin")){
		String qr="Update admin set login_status='Login'  where username=?";
		PreparedStatement sup=(PreparedStatement) con.prepareStatement(qr);
		sup.setString(1, e);
		sup.executeUpdate();
		
	}
		}catch(Exception ex){ System.out.println(ex);}
	
	
	return status;
	}
}
