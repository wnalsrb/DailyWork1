package dailywork1;

import java.util.ArrayList;

public class BookService {
	
	private BookDAO dao;
	private String keyword;
	
//	inject in a different way
	
	public BookService(BookDAO dao){
		this.dao = dao;
	}
	
	public BookService(){
		
	}
	
	public BookDAO getDao() {
		return dao;
	}

	public void setDao(BookDAO dao) {
		this.dao = dao;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public ArrayList<BookEntity> getListByKeyword(){
		return dao.selectAll(keyword);
	}
}
