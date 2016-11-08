package exam_01_aggregation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BookDAO implements DAO{
	
	public ArrayList<BookEntity> selectAll(String keyword){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BookEntity> list = null;
		
		try {
			//1. Driver loading
			Class.forName("com.mysql.jdbc.Driver");
			//2. connect DB
			String url = "jdbc:mysql://localhost:3306/library";
			String id = "jQuery";
			String password = "jQuery";
			con = DriverManager.getConnection(url, id, password);
			//3. prepared statement
			String sql = "select bisbn, btitle, bauthor from book where btitle like ? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			//4. execute query
			rs = pstmt.executeQuery();
			//5. handle result
			list = new ArrayList<BookEntity>();
			
			while(rs.next()){
				BookEntity entity = new BookEntity();
				entity.setBisbn(rs.getString("bisbn"));
				entity.setBtitle(rs.getString("btitle"));
				entity.setBauthor(rs.getString("bauthor"));
				
				list.add(entity);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
		
		return list;
	}
}
