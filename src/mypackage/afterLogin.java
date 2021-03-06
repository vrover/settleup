package mypackage;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.simple.JSONObject;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class afterLogin
 */
@WebServlet("/afterLogin")
public class afterLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		  HttpSession session=request.getSession(false);  
	        String keyid=(String)session.getAttribute("email");  
	    String name=null;
	    

	    try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/exp","varshant","nitVASU14"); 
			
			
			
			JSONObject o=new JSONObject();
			String q4="Select name from register where email=?";
			PreparedStatement st4=(PreparedStatement) con.prepareStatement(q4);
			st4.setString(1, keyid);
			ResultSet g4=st4.executeQuery();
			while(g4.next()){
				name=g4.getString("name");
			}
			g4.close();
			System.out.println(name);
			JSONArray activegroup= new JSONArray();
			JSONArray hnames= new JSONArray();
			JSONArray mnames= new JSONArray();

			String q1="Select register.name as name,register.contact as contact, group_head.gname as gname ,group_head.date as date from group_head inner join register on group_head.email=register.email  where group_head.status=?";
			PreparedStatement s1=(PreparedStatement) con.prepareStatement(q1);
			s1.setString(1,"A");
			ResultSet grp=s1.executeQuery();
			while(grp.next()){
				JSONObject obj=new JSONObject();
			/*	String qr="Select count(email) as totaluser from group_member where  gname=? and status=?";
				PreparedStatement sr=(PreparedStatement) con.prepareStatement(qr);
				sr.setString(1, grp.getString("gname"));
				sr.setString(2, "A");
				ResultSet r=sr.executeQuery();
				int strength=0;
				if(r.next()){
				strength=r.getInt("totaluser");
					}*/
				
				obj.put("name", grp.getString("name"));
				obj.put("gname", grp.getString("gname"));
				obj.put("contact", grp.getString("contact"));
				//obj.put("email", grp.getString("email"));
				obj.put("createdon", grp.getDate("date"));
				
				activegroup.put(obj);
				
				
			}
			grp.close();
			
			o.put("groups",activegroup);
			
			/*String q1="SELECT group_head.gname FROM group_head LEFT JOIN group_member ON group_head.gname = group_member.gname and group_head.status='A'and group_member.email=? WHERE group_member.gname IS NULL";
			PreparedStatement st1=(PreparedStatement) con.prepareStatement(q1);
			st1.setString(1, keyid);
			ResultSet g1=st1.executeQuery();
			while(g1.next()){
				a.put(g1.getString("gname"));
			}
			g1.close();
			o.put("groups", a);	*/
			
			String qh="Select gname from group_head where email=? and status='A'";
			PreparedStatement sth=(PreparedStatement) con.prepareStatement(qh);
			sth.setString(1, keyid);
			ResultSet gh=sth.executeQuery();
			while(gh.next()){
				hnames.put(gh.getString("gname"));
			}
			gh.close();
			o.put("headofgroup", hnames);	
			
			String qm="Select gname from group_member where email=? and status in('A','RL','RR','OL')";
			PreparedStatement stm=(PreparedStatement) con.prepareStatement(qm);
			stm.setString(1, keyid);
			ResultSet gm=stm.executeQuery();
			while(gm.next()){
				mnames.put(gm.getString("gname"));
			}
			gh.close();
			o.put("memberofgroup", mnames);	
			
			System.out.println("done");
			 
		
		
		o.put("name", new String(name));
		
		String n=o.toString();
		
		System.out.println(n);	
			 
	
	
	response.setContentType("application/json");
	response.getWriter().write(o.toString());

			
			}catch(Exception e){ System.out.println(e);}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
