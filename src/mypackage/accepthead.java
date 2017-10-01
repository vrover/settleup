package mypackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class accepthead
 */
@WebServlet("/accepthead")
public class accepthead extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session=request.getSession(false);  
        String keyid=(String)session.getAttribute("email");
       String approvegroup=null,email=null,name=null;
        StringBuilder sb = new StringBuilder();
		BufferedReader br = request.getReader();
		String str = null;
		while ((str = br.readLine()) != null) {
		    sb.append(str);
		}
		String s=sb.toString();
		try {
			JSONObject ob = new JSONObject(s);
		
			approvegroup=ob.getString("approvegroup");
			email=ob.getString("email");
			name=ob.getString("name");
			
			
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
        try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/exp","varshant","nitVASU14"); 
			
			
			String q2="Update group_head set status='A',date=current_date() where gname=?";
			PreparedStatement s2=(PreparedStatement) con.prepareStatement(q2);
			
			s2.setString(1,approvegroup);
			s2.executeUpdate();
			
			
			String q3="Insert into group_member values(?,?,'A',?)";
			PreparedStatement s3=(PreparedStatement) con.prepareStatement(q3);
			s3.setString(1,email);
			s3.setString(2,approvegroup);
			s3.setString(3,name);
			s3.executeUpdate();
			
			String q31="Insert into tran_summary values(?,?,0,0,0,0,0)";
			PreparedStatement s31=(PreparedStatement) con.prepareStatement(q31);
			s31.setString(1,email);
			s31.setString(2,approvegroup);
			s31.executeUpdate();
			
        }
        catch(Exception e){ System.out.println(e);}
        response.setContentType("text/plain");
		response.getWriter().write("verified");
		
	}

}
