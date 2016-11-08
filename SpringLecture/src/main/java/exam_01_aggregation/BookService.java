package exam_01_aggregation;

import java.util.ArrayList;

public class BookService {
	
	private BookDAO dao;
	
	public BookService(BookDAO dao){
		this.dao = dao;
	}
	
	public ArrayList<BookEntity> getListByKeyword(String keyword) {
		
		ArrayList<BookEntity> list = dao.selectAll(keyword);
		
		return list;
	}
}