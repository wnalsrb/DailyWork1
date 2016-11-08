package exam_02_beans_xml_tag;

import java.util.ArrayList;
import java.util.Map;

public class BookShelf {
	private String shelfName; 
	private int shelfPrice; 
	private ArrayList<BookEntity> list;
	private Map<Integer, BookEntity> map;
	
	public BookShelf(){
		
	}
	
	public BookShelf(int shelfPrice, ArrayList<BookEntity> list){
		this.shelfPrice = shelfPrice;
		this.list = list;
	}
	
	public Map<Integer, BookEntity> getMap() {
		return map;
	}

	public void setMap(Map<Integer, BookEntity> map) {
		this.map = map;
	}

	// getter & setter
	public String getShelfName() {
		return shelfName;
	}
	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}
	public int getShelfPrice() {
		return shelfPrice;
	}
	public void setShelfPrice(int shelfPrice) {
		this.shelfPrice = shelfPrice;
	}
	public ArrayList<BookEntity> getList() {
		return list;
	}
	public void setList(ArrayList<BookEntity> list) {
		this.list = list;
	}
}
