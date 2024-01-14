package fotso_CSCI201_Assignment4;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;

public class JDBCcode extends HttpServlet {


	public static void main (String[] args) {
		Connection conn = null;
		Statement st= null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/StudentGrades?user=root&password=root");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT g.ClassName, s.name, g.Grade from StudentInfo s, Grades g WHERE s.SID=g.SID");
			while(rs.next()) {
				String clname = rs.getString("ClassName");
				String name = rs.getString("name");
				String grade = rs.getString("Grade");
				System.out.println("["+clname+" : "+name+" : "+grade+"]");
			}
			ps = conn.prepareStatement("SELECT ClassName, count(ClassName) AS\"studentCount\"FROM Grades GROUP By ClassName");
			rs = ps.executeQuery();
			System.out.println();
			while(rs.next()) {
				String clname = rs.getString("ClassName");
				int studentCnt = rs.getInt("studentCount");
				System.out.println("["+clname+" : "+studentCnt+"]");
			}
		} catch(SQLException sqle) {
			System.out.println("woah");
		} finally {
			try {
				if(rs!= null) {
					rs.close();
				}
				if(st!= null) {
					st.close();
				}
				if(conn!= null) {
					conn.close();
				}
			} catch(SQLException sqle) {
				System.out.println("woa");
			}
		}
	}
}
