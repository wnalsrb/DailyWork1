package exam_02_beans_xml_tag;

import java.util.Set;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
	public static void main(String[] args) {	
		String config = "classpath:applicationCtx_beans_xml_tag.xml";
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(config);
		
		BookShelf shelf = ctx.getBean("bookshelf", BookShelf.class);
		
		System.out.println("bookshelf name : " + shelf.getShelfName());
		System.out.println("bookshelf price : " + shelf.getShelfPrice());
		for(BookEntity entity : shelf.getList()){
			System.out.println(entity.getBtitle() + " / "+ entity.getBauthor());
		}
		
		Set<Integer> keyset = shelf.getMap().keySet();
		for(Integer i : keyset){
			System.out.println(shelf.getMap().get(i).getBtitle());
		}
	}
}
