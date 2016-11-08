package exam_01_composition;

import java.util.ArrayList;

public class BookService {

	public ArrayList<BookEntity> getListByKeyword(String keyword) {
		
		BookDAO dao = new BookDAO();
		ArrayList<BookEntity> list = dao.selectAll(keyword);
		
		return list;
	}
}
