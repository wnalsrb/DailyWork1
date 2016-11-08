package dailywork1;

import java.util.ArrayList;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import dailywork1.BookService;

public class Main {

	public static void main(String[] args) {
		
		String config = "classpath:applicationCtx.xml";
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(config);
		
		BookService service = ctx.getBean("BookService", BookService.class);
		
		ArrayList<BookEntity> list = service.getListByKeyword();
				
		
		for(BookEntity entity : list){
			System.out.println(entity.getBtitle() + " / " + entity.getBauthor());
			entity.getCtext();
		}
		
		ctx.close();
	}
}
