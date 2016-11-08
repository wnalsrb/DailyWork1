package exam_01_composition;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		/*#01. 입력*/
		Scanner s = new Scanner(System.in);
		System.out.println("keyword : ");
		String keyword = s.nextLine();
		
//c		System.out.println("입력된 값은 : " + keyword);
		
		/*#02. 로직처리*/
			//02-1 로직처리를 위한 service객체를 생성
		BookService service = new BookService();
			//02-2 서비스를 이용해서 Business Logic을 수행
		ArrayList<BookEntity> list = service.getListByKeyword(keyword);
		/*#03. 출력처리*/
		for(BookEntity entity : list){
			System.out.println(entity.getBtitle() + " / " + entity.getBauthor());
		}
		// 사용한 resource 해제
		s.close();
	}

}
