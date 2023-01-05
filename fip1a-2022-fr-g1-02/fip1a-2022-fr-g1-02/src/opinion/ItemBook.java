package opinion;

import java.util.LinkedList;

/**
 * @author simon
 *
 */
public class ItemBook extends Item{

	private String author;
	private int nbPages;

	/**
	 * @param title
	 * @param author
	 * @param kind
	 * @param nbPages
	 */
	public ItemBook(String title, String author, String kind, int nbPages) {

		this.title = title;
		this.author = author;
		this.kind = kind;
		this.nbPages = nbPages;
	}


	/**
	 *
	 */
	public String toString() {
		return title ;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
