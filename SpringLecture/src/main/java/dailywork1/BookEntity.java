package dailywork1;

import java.util.ArrayList;

public class BookEntity {
	private String bisbn;
	private String btitle;
	private String bauthor;
	private ArrayList<String> Ctext;
	
	public BookEntity(){
		
	}

	public String getBisbn(){
		return bisbn;
	}
	
	public void setBisbn(String bisbn){
		this.bisbn = bisbn;
	}
	
	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBauthor() {
		return bauthor;
	}

	public void setBauthor(String bauthor) {
		this.bauthor = bauthor;
	}

	public void getCtext() {
		for(int i = 0; i<Ctext.size(); i++){
			System.out.println(Ctext.get(i));
		}
	}

	public void setCtext(ArrayList<String> ctext) {
		Ctext = ctext;
	}
}
