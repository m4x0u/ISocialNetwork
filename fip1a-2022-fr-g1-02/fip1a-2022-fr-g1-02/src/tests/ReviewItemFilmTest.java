package tests;

import exceptions.BadEntryException;
import exceptions.NotItemException;
import exceptions.NotMemberException;
import exceptions.NotTestReportException;
import opinion.ISocialNetwork;
import opinion.SocialNetwork;

/**
 * @author simon
 *
 */
public class ReviewItemFilmTest {
	
	/**
	 * @param sn
	 * @param login
	 * @param password
	 * @param title
	 * @param mark
	 * @param comment
	 * @param testId
	 * @param errorMessage
	 * @return
	 */
	private static int reviewItemFilmBadEntryTest(ISocialNetwork sn, String login, String password, String title,
			float mark, String comment, String testId, String errorMessage) {
		int nbFilms = sn.nbFilms();

		try {
			sn.reviewItemFilm(login, password, title, mark, comment);

			System.out.println("Err " + testId + " : " + errorMessage);

			return 1;
		} catch (BadEntryException e) {

			if (sn.nbFilms() != nbFilms) {
				System.out.println("Err " + testId + " : BadEntry was thrown but the number of Films was changed");
				return 1;
			} else {
				return 0;
			}

		} catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. " + e);
			e.printStackTrace();
			return 1;
		}
	}
	
	/**
	 * @param sn
	 * @param login
	 * @param password
	 * @param title
	 * @param mark
	 * @param comment
	 * @param testId
	 * @param errorMessage
	 * @return
	 */
	private static int reviewItemFilmNotMemberTest(ISocialNetwork sn, String login, String password, String title,
			float mark, String comment, String testId, String errorMessage) {
		int nbFilms = sn.nbFilms();

		try {
			sn.reviewItemFilm(login, password, title, mark, comment);

			System.out.println("Err " + testId + " : " + errorMessage);

			return 1;
		} catch (NotMemberException e) {

			if (sn.nbFilms() != nbFilms) {
				System.out.println("Err " + testId + " : NotMember was thrown but the number of films was changed");
				return 1;
			} else {
				return 0;
			}

		} catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. " + e);
			e.printStackTrace();
			return 1;
		}
	}
	
	
	/**
	 * @param sn
	 * @param login
	 * @param password
	 * @param title
	 * @param mark
	 * @param comment
	 * @param testId
	 * @param errorMessage
	 * @return
	 */
	private static int reviewItemFilmNotItemTest(ISocialNetwork sn, String login, String password, String title,
			float mark, String comment, String testId, String errorMessage) {
		int nbFilms = sn.nbFilms();

		try {
			sn.reviewItemFilm(login, password, title, mark, comment);

			System.out.println("Err " + testId + " : " + errorMessage);

			return 1;
		} catch (NotItemException e) {

			if (sn.nbFilms() != nbFilms) {
				System.out.println("Err " + testId + " : NotItem was thrown but the number of films was changed");
				return 1;
			} else {
				return 0;
			}

		} catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. " + e);
			e.printStackTrace();
			return 1;
		}
	}
	
	/**
	 * @param sn
	 * @param login
	 * @param password
	 * @param title
	 * @param mark
	 * @param comment
	 * @param testId
	 * @return
	 */
	private static int reviewItemFilmOKTest(ISocialNetwork sn, String login, String password, String title, float mark,
			String comment, String testId) {
		try {
			sn.reviewItemFilm(login, password, title, mark, comment);
			return 0;
		} catch (Exception e) {
			System.out.println(testId + " : unexpected exception. " + e);
			return 1;
		}
	}
	
	/**
	 * @param sn
	 * @param login
	 * @param password
	 * @param title
	 * @param mark
	 * @param comment
	 * @param testId
	 * @param expectedMark
	 * @return
	 */
	public static int reviewItemFilmAverageRatingTest(ISocialNetwork sn, String login, String password, String title,
			float mark, String comment, String testId, float expectedMark) {

		try {
			float averageMark = 0;
			averageMark = sn.reviewItemFilm(login, password, title, mark, comment);
			if (Math.abs(averageMark - expectedMark) < 0.01)
				return 0;
			else {
				System.out.println(testId + " the average mark is not the expected mark");
				return 1;
			}
		} catch (Exception e) {
			System.out.println(testId + " : unexpected exception. " + e);
			return 1;
		}

	}
	
	/**
	 * @return
	 */
	public static TestReport test() {
		ISocialNetwork sn = new SocialNetwork();

		try {
			sn = CreateSnTest.CreateNewSnTest();
		} catch (Exception e) {
			System.out.println("user was not create");
		}

		int nbMembers = sn.nbMembers();
		int nbFilms = sn.nbFilms();

		int nbTests = 0;
		int nbErrors = 0;

		System.out.println("Testing reviewItemFilm");
		// Check if parameters throw BadEntryException
		// test 15.1
		nbTests++;
		nbErrors += reviewItemFilmBadEntryTest(sn, null, "azer", "poulet", 3.2F, "KFC", "15.1",
				"reviewItemFilm() doesn't reject null logins");
		// test 15.2
		nbTests++;
		nbErrors += reviewItemFilmBadEntryTest(sn, " ", "azer", "poulet", 3.2F, "KFC", "15.2",
				"reviewItemFilm() doesn't reject logins that don't contain at least one character other than space ");
		// test 15.3
		nbTests++;
		nbErrors += reviewItemFilmBadEntryTest(sn, "B", null, "poulet", 3.2F, "KFC", "15.3",
				"reviewItemFilm() doesn't reject null passwords");
		// test 15.4
		nbTests++;
		nbErrors += reviewItemFilmBadEntryTest(sn, "pot", " az  ", "poulet", 3.2F, "KFC", "15.4",
				"reviewItemFilm() doesn't reject passwords that don't contain at least 4 characters (not taking into account leading or trailing blanks)");
		// test 15.5
		nbTests++;
		nbErrors += reviewItemFilmBadEntryTest(sn, "B", "pore", null, 3.2F, "KFC", "15.5",
				"reviewItemFilm() doesn't reject null titles");
		// test 15.6
		nbTests++;
		nbErrors += reviewItemFilmBadEntryTest(sn, "B", "azer", " ", 3.2F, "KFC", "15.6",
				"reviewItemFilm() doesn't reject titles that don't contain at least one character other than space ");
		// test 15.7
		nbTests++;
		nbErrors += reviewItemFilmBadEntryTest(sn, "B", "azer", "poulet", -1.3F, "KFC", "15.7",
				"reviewItemFilm() doesn't reject mark inferior to 0.0");
		// test 15.8
		nbTests++;
		nbErrors += reviewItemFilmBadEntryTest(sn, "B", "azer", "poulet", 5.2F, "KFC", "15.8",
				"reviewItemFilm() doesn't reject mark superiror to 5.0");
		// test 15.9
		nbTests++;
		nbErrors += reviewItemFilmBadEntryTest(sn, "B", "azer", "poulet", 3.2F, null, "15.9",
				"reviewItemFilm() doesn't null comments");

		// Check if parameters throw NotMemberException
		// test 16.1
		nbTests++;
		nbErrors += reviewItemFilmNotMemberTest(sn, "nobody", "paul", "POULET", 4, "good image", "16.1",
				"reviewItemFilm() doesn't reject member who doesn't exist");
		// test 16.2
		nbTests++;
		nbErrors += reviewItemFilmNotMemberTest(sn, "Paul", "password", "POULET", 4, "good image", "16.2",
				"reviewItemFilm() doesn't reject an existing member with wrong password");

		// 17.1a
		nbTests++;
		nbErrors += reviewItemFilmOKTest(sn, "Paul", "paul", "Bambi", 4.5F, "Good Film ", "17.1a");
		// 17.1b
		nbTests++;
		nbErrors += reviewItemFilmOKTest(sn, "Alice", "alice", "Inception", 3.2F, "not complete", "17.1b");
		// 17.1c
		nbTests++;
		nbErrors += reviewItemFilmOKTest(sn, "Laura", "laura", "BEBOU", 4.2F, "good", "17.1c");
		// 17.1d
		nbTests++;
		nbErrors += reviewItemFilmOKTest(sn, "Paul", "paul", "Inception", 2.1F, "i'm lost", "17.1d");
		// 17.1e
		nbTests++;
		nbErrors += reviewItemFilmOKTest(sn, "Alice", "alice", "Bambi", 4.5F, "Nice surprise", "17.1e");
		// 17.1f
		nbTests++;
		nbErrors += reviewItemFilmOKTest(sn, "Laura", "laura", "Bambi", 2.3F, "Many mistakes", "17.1f");
		// 17.1g
		nbTests++;
		nbErrors += reviewItemFilmOKTest(sn, "Paul", "paul", "BEBOU", 3.7F,
				"good for learn history of Inception", "17.1g");
		// 17.1h
		nbTests++;
		nbErrors += reviewItemFilmOKTest(sn, "Alice", "alice", "BEBOU", 1.5F, "boring", "17.1h");
		// 17.1i
		nbTests++;
		nbErrors += reviewItemFilmOKTest(sn, "Laura", "laura", "Inception", 3.1F, "yes", "17.1i");

		// Check if parameters throw NotItemException

		// test 17.2
		nbTests++;
		nbErrors += reviewItemFilmNotItemTest(sn, "Paul", "paul", " APERO", 4, "good image", "17.2",
				"reviewItemFilm() doesn't reject not registered Films");

		// Check if parameters throw error message from bad averageRating
		// test 18.1a
		nbTests++;
		nbErrors += reviewItemFilmAverageRatingTest(sn, "Paul", "paul", "Bambi", 0, "okok", "18.1a", 2.266F);
		// test 18.1b
		nbTests++;
		nbErrors += reviewItemFilmAverageRatingTest(sn, "Alice", "alice", "Inception", 5, "okok", "18.1b", 3.4F);
		// test 18.1c
		nbTests++;
		nbErrors += reviewItemFilmAverageRatingTest(sn, "Laura", "laura", "BEBOU", 1, "okok", "18.1c", 2.06F);

		nbTests++;
		// check that 'sn' was not modified
		if (nbFilms != sn.nbFilms()) {
			System.out.println("Error : the number of films was unexepectedly changed by addItemFilm()");
			nbErrors++;
		}
		nbTests++;
		// check that 'sn' was not modified
		if (nbMembers != sn.nbMembers()) {
			System.out.println("Error : the number of Members was unexepectedly changed by addItemFilm()");
			nbErrors++;
		}

		// Display final state of 'sn'
		System.out.println("Final state of the social network : " + sn);

		// Print a summary of the tests and return test results
		try {
			TestReport tr = new TestReport(nbTests, nbErrors);
			System.out.println("AddItemFilmTest : " + tr);
			return tr;
		} catch (NotTestReportException e) { // This shouldn't happen
			System.out.println("Unexpected error in AddItemFilmTest test code - Can't return valuable test results");
			return null;
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		test();
		

	}

}
