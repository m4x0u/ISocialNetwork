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
public class ReviewItemBookTest {

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
	private static int reviewItemBookBadEntryTest(ISocialNetwork sn, String login, String password, String title,
			float mark, String comment, String testId, String errorMessage) {
		int nbBooks = sn.nbBooks();

		try {
			sn.reviewItemBook(login, password, title, mark, comment);

			System.out.println("Err " + testId + " : " + errorMessage);

			return 1;
		} catch (BadEntryException e) {

			if (sn.nbBooks() != nbBooks) {
				System.out.println("Err " + testId + " : BadEntry was thrown but the number of books was changed");
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
	private static int reviewItemBookNotMemberTest(ISocialNetwork sn, String login, String password, String title,
			float mark, String comment, String testId, String errorMessage) {
		int nbBooks = sn.nbBooks();

		try {
			sn.reviewItemBook(login, password, title, mark, comment);

			System.out.println("Err " + testId + " : " + errorMessage);

			return 1;
		} catch (NotMemberException e) {

			if (sn.nbBooks() != nbBooks) {
				System.out.println("Err " + testId + " : NotMember was thrown but the number of books was changed");
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
	private static int reviewItemBookNotItemTest(ISocialNetwork sn, String login, String password, String title,
			float mark, String comment, String testId, String errorMessage) {
		int nbBooks = sn.nbBooks();

		try {
			sn.reviewItemBook(login, password, title, mark, comment);

			System.out.println("Err " + testId + " : " + errorMessage);

			return 1;
		} catch (NotItemException e) {

			if (sn.nbBooks() != nbBooks) {
				System.out.println("Err " + testId + " : NotItem was thrown but the number of books was changed");
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
	private static int reviewItemBookOKTest(ISocialNetwork sn, String login, String password, String title, float mark,
			String comment, String testId) {
		try {
			sn.reviewItemBook(login, password, title, mark, comment);
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
	public static int reviewItemBookAverageRatingTest(ISocialNetwork sn, String login, String password, String title,
			float mark, String comment, String testId, float expectedMark) {

		try {
			float averageMark = 0;
			averageMark = sn.reviewItemBook(login, password, title, mark, comment);
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

		System.out.println("Testing reviewItemBook");
		// Check if parameters throw BadEntryException
		// test 6.1
		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(sn, null, "azer", "poulet", 3.2F, "KFC", "6.1",
				"reviewItemBook() doesn't reject null logins");
		// test 6.2
		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(sn, " ", "azer", "poulet", 3.2F, "KFC", "6.2",
				"reviewItemBook() doesn't reject logins that don't contain at least one character other than space ");
		// test 6.3
		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(sn, "B", null, "poulet", 3.2F, "KFC", "6.3",
				"reviewItemBook() doesn't reject null passwords");
		// test 6.4
		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(sn, "pot", " az  ", "poulet", 3.2F, "KFC", "6.4",
				"reviewItemBook() doesn't reject passwords that don't contain at least 4 characters (not taking into account leading or trailing blanks)");
		// test 6.5
		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(sn, "B", "pore", null, 3.2F, "KFC", "6.5",
				"reviewItemBook() doesn't reject null titles");
		// test 6.6
		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(sn, "B", "azer", " ", 3.2F, "KFC", "6.6",
				"reviewItemBook() doesn't reject titles that don't contain at least one character other than space ");
		// test 6.7
		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(sn, "B", "azer", "poulet", -1.3F, "KFC", "6.7",
				"reviewItemBook() doesn't reject mark inferior to 0.0");
		// test 6.8
		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(sn, "B", "azer", "poulet", 5.2F, "KFC", "6.8",
				"reviewItemBook() doesn't reject mark superiror to 5.0");
		// test 6.9
		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(sn, "B", "azer", "poulet", 3.2F, null, "6.9",
				"reviewItemBook() doesn't null comments");

		// Check if parameters throw NotMemberException
		// test 7.1
		nbTests++;
		nbErrors += reviewItemBookNotMemberTest(sn, "nobody", "paul", "POULET", 4, "good image", "7.1",
				"reviewItemBook() doesn't reject member who doesn't exist");
		// test 7.2
		nbTests++;
		nbErrors += reviewItemBookNotMemberTest(sn, "Paul", "password", "POULET", 4, "good image", "7.2",
				"reviewItemBook() doesn't reject an existing member with wrong password");

		// 8.1a
		nbTests++;
		nbErrors += reviewItemBookOKTest(sn, "Paul", "paul", "Martial Art", 4.5F, "Good book ", "8.1a");
		// 8.1b
		nbTests++;
		nbErrors += reviewItemBookOKTest(sn, "Alice", "alice", "Rugby World Cup", 3.2F, "not complete", "8.1b");
		// 8.1c
		nbTests++;
		nbErrors += reviewItemBookOKTest(sn, "Laura", "laura", "Harry Potter", 4.2F, "good", "8.1c");
		// 8.1d
		nbTests++;
		nbErrors += reviewItemBookOKTest(sn, "Paul", "paul", "Rugby World Cup", 2.1F, "i'm lost", "8.1d");
		// 8.1e
		nbTests++;
		nbErrors += reviewItemBookOKTest(sn, "Alice", "alice", "Martial Art", 4.5F, "Nice surprise", "8.1e");
		// 8.1f
		nbTests++;
		nbErrors += reviewItemBookOKTest(sn, "Laura", "laura", "Martial Art", 2.3F, "Many mistakes", "8.1f");
		// 8.1g
		nbTests++;
		nbErrors += reviewItemBookOKTest(sn, "Paul", "paul", "Harry Potter", 3.7F,
				"good for learn history of rugby world cup", "8.1g");
		// 8.1h
		nbTests++;
		nbErrors += reviewItemBookOKTest(sn, "Alice", "alice", "Harry Potter", 1.5F, "boring", "8.1h");
		// 8.1i
		nbTests++;
		nbErrors += reviewItemBookOKTest(sn, "Laura", "laura", "Rugby World Cup", 3.1F, "yes", "8.1i");

		// Check if parameters throw NotItemException

		// test 8.2
		nbTests++;
		nbErrors += reviewItemBookNotItemTest(sn, "Paul", "paul", " APERO", 4, "good image", "8.2",
				"reviewItemBook() doesn't reject not registered books");

		// Check if parameters throw error message from bad averageRating
		// test 9.1a
		nbTests++;
		nbErrors += reviewItemBookAverageRatingTest(sn, "Paul", "paul", "Martial Art", 0, "okok", "9.1a", 2.266F);
		// test 9.1b
		nbTests++;
		nbErrors += reviewItemBookAverageRatingTest(sn, "Alice", "alice", "Rugby World Cup", 5, "okok", "9.1b", 3.4F);
		// test 9.1c
		nbTests++;
		nbErrors += reviewItemBookAverageRatingTest(sn, "Laura", "laura", "Harry Potter", 1, "okok", "9.1c", 2.06F);

		nbTests++;
		// check that 'sn' was not modified
		if (nbFilms != sn.nbFilms()) {
			System.out.println("Error : the number of films was unexepectedly changed by addItemBook()");
			nbErrors++;
		}
		nbTests++;
		// check that 'sn' was not modified
		if (nbMembers != sn.nbMembers()) {
			System.out.println("Error : the number of Members was unexepectedly changed by addItemBook()");
			nbErrors++;
		}

		// Display final state of 'sn'
		System.out.println("Final state of the social network : " + sn);

		// Print a summary of the tests and return test results
		try {
			TestReport tr = new TestReport(nbTests, nbErrors);
			System.out.println("AddItemBookTest : " + tr);
			return tr;
		} catch (NotTestReportException e) { // This shouldn't happen
			System.out.println("Unexpected error in AddItemBookTest test code - Can't return valuable test results");
			return null;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}

}
