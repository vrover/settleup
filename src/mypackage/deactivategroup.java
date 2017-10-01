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
 * Servlet implementation class deactivategroup
 */
@WebServlet("/deactivategroup")
public class deactivategroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(false);  
        String keyid=(String)session.getAttribute("email");
        
        String groupname=null;
        StringBuilder sb = new StringBuilder();
 		BufferedReader br = request.getReader();
 		String str = null;
 		while ((str = br.readLine()) != null) {
 		    sb.append(str);
 		}
 		String s=sb.toString();
 		try {
 			JSONObject ob = new JSONObject(s);
 		
 			groupname=ob.getString("name");
 			
 		} catch (JSONException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
       
        try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/exp","varshant","nitVASU14");
			String q2="Update group_head set status='D' where gname=? ";
			PreparedStatement s2=(PreparedStatement) con.prepareStatement(q2);
			s2.setString(1, groupname);
			
			s2.executeUpdate();
			
			
			String q3="Update group_member set status='D' where gname=?";
			PreparedStatement s3=(PreparedStatement) con.prepareStatement(q3);
			s3.setString(1, groupname);
			s3.executeUpdate();
			
        }
        catch(Exception e){ System.out.println(e);}
        response.setContentType("text/plain");
		response.getWriter().write("verified");
	}

}
