package opinion;

import java.util.LinkedList;

/**
 * @author simon
 *
 */
public class Item {

	protected String title;
	protected String kind;
	protected float avgMarks;
	protected LinkedList<Review> reviews = new LinkedList<>();

	/**
	 * @param title
	 * @return
	 */
	public boolean isItem(String title) {
		if (title.trim().toLowerCase().equals(this.title.trim().toLowerCase())) {
			return true;
		} else
			return false;
	}
	/**
	 * @param login
	 * @return
	 */
	public boolean isMemberReview(String login){
		for (Review review : reviews){
			if (review.getLogin().trim().toLowerCase().equals(login.trim().toLowerCase())) {
				return true;
			}	
		}
		return false;
	}
	
	/**
	 * @param pseudo1
	 * @param pseudo2
	 * @return
	 */
	public boolean memberAlreadyReviewOpinion(String pseudo1, String pseudo2){
		for (Review review : reviews){
			if (review.getLogin().trim().toLowerCase().equals(pseudo2.trim().toLowerCase())) return review.memberAlreadyReviewOpinion(pseudo1);
		}
		return false;
	}
	
	/**
	 * @param login1
	 * @param login2
	 * @param mark
	 */
	public void addMarkToReview(String login1, String login2, float mark){
		for (Review review : reviews){
			if (review.getLogin().trim().toLowerCase().equals(login2.trim().toLowerCase())){
				review.markReviewUpdate(login1, mark);
			}
		}
	}


	/**
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * @return
	 */
	public float getAvgMrks(){
		return avgMarks;
	}

	/**
	 * @return
	 */
	public float avgMarks() {
		float sum = 0;
		float coef = 0;
		for (Review review : reviews) {
			sum += review.getMark();
		}
		avgMarks = sum / reviews.size();
		return avgMarks;
	}

	/**
	 * @param login
	 * @param comment
	 * @param mark
	 */
	public void addReview(String login, String comment, float mark) {
		Review review = new Review(mark, comment, login,0);
		reviews.add(review);
	}

	/**
	 * @param login
	 * @param comment
	 * @param mark
	 */
	public void removeReview(String login, String comment, float mark) {
		Review tempReview = new Review(0, null, null,0);
		for (Review review : reviews) {
			if (review.getLogin().trim().toLowerCase().equals(login.trim().toLowerCase())) {

				tempReview = review;
			}
		}
		reviews.remove(tempReview);
	}

	/**
	 * @param pseudo1
	 * @param pseudo2
	 * @return
	 */
	public float getMarkReview(String pseudo1, String pseudo2) {
		for (Review review : reviews) {
			if (review.getLogin().trim().toLowerCase().equals(pseudo2.trim().toLowerCase()))
				return review.getAvgMark();
		}
		return 0;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
