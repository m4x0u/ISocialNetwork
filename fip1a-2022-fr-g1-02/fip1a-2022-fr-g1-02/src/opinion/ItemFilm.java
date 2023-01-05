package opinion;

import java.util.LinkedList;

/**
 * @author simon
 *
 */
public class ItemFilm extends Item {
	
	private String director,scriptwriter;
	int duration;
	
	/**
	 * @param title
	 * @param kind
	 * @param director
	 * @param scriptwriter
	 * @param duration
	 */
	public ItemFilm(String title, String kind, String director, String scriptwriter, int duration) {
		this.title = title;
		this.kind = kind;
		this.director = director;
		this.scriptwriter = scriptwriter;
		this.duration = duration;
	}
	
	/**
	 *
	 */
	public String toString() {
		return title;
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
