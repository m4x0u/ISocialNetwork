package tests;

import exceptions.NotTestReportException;
import opinion.ISocialNetwork;
import opinion.SocialNetwork;

/**
 * Tools for testing a ISocialNetwork
 * 
 *
 */
public class Tools {

	private static java.util.Random generator = new java.util.Random();

	/*
	 * These counters allow specific members/books/films/reviews/opinions to be generated
	 * Depending on other actions made on the ISocialNetwork, they may differ from the 'numbers' of members/books/films/reviews
	 */
	private static int nextMember=1; 
	private static int nextBook=1; 
	private static int nextFilm=1;

	/*
	 * These constants are String or prefix-String to be used when generating new members/books/films/reviews
	 */
	private static final String LOGIN ="member";
	private static final String PASSWORD = "passwd";
	private static final String PROFILE ="profile";

	private static final String TITLE = "title";
	private static final String KIND = "kind"; 
	private static final String AUTHOR = "author";
	private static final int NB_PAGES = 12;

	private static final String DIRECTOR = "director";
	private static final String SCENARIST = "scenarist";
	private static final int DURATION = 15;

	private static final String COMMENT = "comment";

	
 


	/**
	 * Adds members, books, films and reviews to the ISocialNetwork.
	 * This action is mainly driven randomly. Therefore, depending on "luck", a member can add a review on an item that he already had reviewed, which will replace this previous review.
	 * It is therefore not guaranteed that the total number of reviews stored in the ISocialNetwork will have a nbReviews increase.
	 * @param sn the ISocialNetwork to work with
	 * @param nbMembers the number of members to add to sn
	 * @param nbBooks the number of books to add to sn
	 * @param nbFilms the number of films to add to sn
	 * @param nbReviews the number of reviews to make to sn
	 * @return meantime of each operation 
	 */
	public static long populate (ISocialNetwork sn, int nbMembers, int nbBooks, int nbFilms, int nbReviews) 
			throws Exception {
		
		long elapsedTime=0;
		long before, after; //timestamps
		int nbOperations = nbMembers + nbBooks +nbFilms + nbReviews;


		//
		// add members
		//
		for (int i=0; i<nbMembers;i++) {
			before = System.nanoTime();
			sn.addMember(LOGIN+nextMember, PASSWORD+nextMember, PROFILE);
			after = System.nanoTime();
			elapsedTime += after - before; 
			nextMember++;	
		}

		//
		// add books
		//
		if (nbBooks>0) { 
			//we don't care about WHO adds the books, so we only use last added member to do it
			if (nextMember==1) throw new Exception ("populate() : must add at leat a member before being able to add books");
			for (int i=0;i<nbBooks;i++) {
				int lastMemberNumber = nextMember-1;
				before = System.nanoTime();
				sn.addItemBook(LOGIN+lastMemberNumber, PASSWORD+lastMemberNumber, TITLE+nextBook, KIND, AUTHOR, NB_PAGES);
				after = System.nanoTime();
				elapsedTime += after - before; 
				nextBook++;			
			}
		}

		//
		// add films 
		//
		if (nbFilms>0) {
			// we don't care about WHO adds the films, so we only use last added member to do it
			if (nextMember==1) throw new Exception ("populate(): must add at leat a member before being able to add films");
			for (int i=0;i<nbFilms;i++) {
				int lastMemberNumber = nextMember-1;
				before = System.nanoTime();
				sn.addItemFilm(LOGIN+lastMemberNumber, PASSWORD+lastMemberNumber, TITLE+nextFilm, KIND, DIRECTOR, SCENARIST, DURATION);
				after = System.nanoTime();
				elapsedTime += after - before; 
				nextFilm++;			
			}
		}

		//
		// add reviews
		//
		if (nbReviews>0) {
			// try to fairly distribute reviews over members that were generated this time and previously
			// try to fairly distribute reviews among items

			if (nextMember==1) throw new Exception ("populate() : must add at leat a member before being able to add reviews");
			if ((nextBook==1)&&(nextFilm==1)) throw new Exception ("populate() : must add at leat an item before being able to add reviews");
			int currentMemberNum; // number of the member that do the review
			int itemNum; // number of the item that is reviewed

			// reviews will be splited between books reviews and film reviews
			int nbBookReviews, nbFilmReviews; // number of book reviews  and film reviews
			nbBookReviews = nbReviews / (nextBook + nextFilm) ; // proportional distribution 
			nbFilmReviews = nbReviews - nbBookReviews;

			currentMemberNum = nextMember-1; // work downward so as to have new members contribute first


			for (int i = 0; i<nbBookReviews;i++) {// add a book review			
				itemNum = 1 + generator.nextInt(nextBook-2); // choose randomly a book to review - some tricks here, dealing with boundaries			
				before = System.nanoTime();
				sn.reviewItemBook(LOGIN+currentMemberNum, PASSWORD+currentMemberNum, TITLE+itemNum, 5*generator.nextFloat(), COMMENT);
				currentMemberNum = (currentMemberNum>1)  ? currentMemberNum-1 : nextMember-1; // decrementing modulo nextMember
				after = System.nanoTime();
				elapsedTime += after - before; 
				nbReviews--;
			}

			for (int i = 0; i<nbFilmReviews;i++) {// add a film review			
				itemNum = 1 + generator.nextInt(nextFilm-2); // choose randomly a film to review - some tricks here, dealing with boundaries		
				before = System.nanoTime();
				sn.reviewItemFilm(LOGIN+currentMemberNum, PASSWORD+currentMemberNum, TITLE+itemNum, 5*generator.nextFloat(), COMMENT);
				currentMemberNum = (currentMemberNum>1)  ? currentMemberNum-1 : nextMember-1; // decrementing modulo nextMember
				after = System.nanoTime();
				elapsedTime += after - before; 
				nbReviews--;
			}
		}

		return elapsedTime/nbOperations; 

	}
		
	public static void main(String[] args) throws Exception {
		ISocialNetwork sn = new SocialNetwork();
		
		long temp = 0;
		temp = populate(sn, 5000, 25000, 25000, 50000);
		System.out.println(temp);
		
	}
		
	
}



