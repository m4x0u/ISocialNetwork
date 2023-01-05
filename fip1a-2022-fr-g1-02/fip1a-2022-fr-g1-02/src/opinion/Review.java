package opinion;

import java.util.LinkedList;

/**
 * @author simon
 *
 */
public class Review {
	private float mark;
	private float avgMark;
	private float loginKarma;
	private String comment;
	private String login;
	private LinkedList<String> reviewOpinions; 


	/**
	 * @param mark
	 * @param comment
	 * @param login
	 * @param loginKarma
	 */
	public Review(float mark, String comment, String login, float loginKarma) {
		this.mark = mark;
		this.comment = comment;
		this.login = login;
		this.loginKarma = loginKarma;
		reviewOpinions = new LinkedList<String>();
	}
	
	/**
	 * @param pseudo1
	 * @param note
	 */
	public void markReviewUpdate(String pseudo1, float note){
		float temp1 = avgMark*reviewOpinions.size();
		float temp2 = temp1+note;
		reviewOpinions.add(pseudo1); 
		avgMark=temp2/reviewOpinions.size(); 
	}

	/**
	 * @return
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @return
	 */
	public float getMark() {
		return mark;
	}
	/**
	 * @return
	 */
	public float getLoginKarma() {
		return loginKarma;
	}
	/**
	 * @return
	 */
	public float getAvgMark() {
		return avgMark;
	}
	
	/**
	 * @param pseudo1
	 * @return
	 */
	public boolean memberAlreadyReviewOpinion(String pseudo1){
		for (String reviewOpinion : reviewOpinions){
			if (reviewOpinion.trim().toLowerCase().equals(pseudo1.trim().toLowerCase())) return true;  
		}
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
