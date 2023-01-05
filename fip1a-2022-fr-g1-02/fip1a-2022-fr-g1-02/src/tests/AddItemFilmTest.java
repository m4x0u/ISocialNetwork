package tests;

import exceptions.BadEntryException;
import exceptions.ItemFilmAlreadyExistsException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotMemberException;
import exceptions.NotTestReportException;
import opinion.ISocialNetwork;
import opinion.SocialNetwork;

/**
 * @author simon
 *
 */
public class AddItemFilmTest {

	/**
	 * @param sn
	 * @param login
	 * @param password
	 * @param title
	 * @param kind
	 * @param director
	 * @param scriptwriter
	 * @param duration
	 * @param testId
	 * @param errorMessage
	 * @return
	 */
	private static int addItemFilmBadEntryTest(ISocialNetwork sn, String login, String password, String title,
			String kind, String director, String scriptwriter, int duration, String testId, String errorMessage) {
		int nbFilms = sn.nbFilms();

		try {
			sn.addItemFilm(login, password, title, kind, director, scriptwriter, duration);

			System.out.println("Err " + testId + " : " + errorMessage);

			return 1;
		} catch (BadEntryException e) {

			if (sn.nbFilms() != nbFilms) {
				System.out.println("Err " + testId + " : BadEntry was thrown but the number of films was changed");
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
	 * @param kind
	 * @param director
	 * @param scriptwriter
	 * @param duration
	 * @param testId
	 * @param errorMessage
	 * @return
	 */
	private static int addItemFilmNotMemberTest(ISocialNetwork sn, String login, String password, String title,
			String kind, String director, String scriptwriter, int duration, String testId, String errorMessage) {
		int nbFilms = sn.nbFilms();

		try {
			sn.addItemFilm(login, password, title, kind, director, scriptwriter, duration);

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
	 * @param kind
	 * @param director
	 * @param scriptwriter
	 * @param duration
	 * @param testId
	 * @param errorMessage
	 * @return
	 */
	private static int addItemFilmAlreadyExistTest(ISocialNetwork sn, String login, String password, String title,
			String kind, String director, String scriptwriter, int duration, String testId, String errorMessage) {
		int nbFilms = sn.nbFilms();

		try {
			sn.addItemFilm(login, password, title, kind, director, scriptwriter, duration);

			System.out.println("Err " + testId + " : " + errorMessage);

			return 1;
		} catch (ItemFilmAlreadyExistsException e) {

			if (sn.nbFilms() != nbFilms) {
				System.out.println(
						"Err " + testId + " : ItemFilmAlreadyExists was thrown but the number of films was changed");
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
	 * @param kind
	 * @param director
	 * @param scriptwriter
	 * @param duration
	 * @param testId
	 * @return
	 */
	private static int addItemFilmOKTest(ISocialNetwork sn, String login, String password, String title, String kind,
			String director, String scriptwriter, int duration, String testId) {
		int nbFilms = sn.nbFilms();

		try {
			sn.addItemFilm(login, password, title, kind, director, scriptwriter, duration);
			if (sn.nbFilms() != nbFilms + 1) {
				System.out.println("Err " + testId + " : the number of films " + nbFilms + " was not implemented");
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
	 * @return
	 * @throws BadEntryException
	 * @throws MemberAlreadyExistsException
	 */
	public static TestReport test() throws BadEntryException, MemberAlreadyExistsException {
		ISocialNetwork sn = new SocialNetwork();

		try {
			sn = CreateSnTest.CreateNewSnTest();
		} catch (Exception e) {
			System.out.println("user was not create");
		}

		int nbMembers = sn.nbMembers();
		int nbBooks = sn.nbBooks();

		int nbTests = 0;
		int nbErrors = 0;

		System.out.println("Testing addItemFilm");
		// Check if parameters throw BadEntryException
		// test 12.1
		nbTests++;
		nbErrors += addItemFilmBadEntryTest(sn, null, "hefoiq", "HI", "oIH", "js", "vizgv", 15, "12.1",
				"addItemFilm() doesn't reject null logins");
		// test 12.2
		nbTests++;
		nbErrors += addItemFilmBadEntryTest(sn, " ", "hefoiq", "HI", "oIH", "js", "vizgv", 15, "12.2",
				"addItemFilm() doesn't reject logins that don't contain at least one character other than space");
		// test 12.3
		nbTests++;
		nbErrors += addItemFilmBadEntryTest(sn, "B", null, "HI", "oIH", "js", "vizgv", 15, "12.3",
				"addItemFilm() doesn't reject null passwords");
		// test 12.4
		nbTests++;
		nbErrors += addItemFilmBadEntryTest(sn, "B", " qzm  ", "HI", "oIH", "js", "vizgv", 15, "12.4",
				"addItemFilm() doesn't reject passwords that don't contain at least 4 characters (not taking into account leading or trailing blanks)");
		// test 12.5
		nbTests++;
		nbErrors += addItemFilmBadEntryTest(sn, "B", "qzea", null, "oIH", "js", "vizgv", 15, "12.5",
				"addItemBook() doesn't reject null titles");
		// test 12.6
		nbTests++;
		nbErrors += addItemFilmBadEntryTest(sn, "B", "qzea", " ", "oIH", "js", "vizgv", 15, "12.6",
				"addItemBook() doesn't reject title that don't contain at least one character other than space");
		// test 12.7
		nbTests++;
		nbErrors += addItemFilmBadEntryTest(sn, "B", "qzea", "HI", null, "js", "vizgv", 15, "12.7",
				"addItemBook() doesn't reject null kinds");
		// test 12.8
		nbTests++;
		nbErrors += addItemFilmBadEntryTest(sn, "B", "qzea", "HI", "oIH", null, "vizgv", 15, "12.8",
				"addItemBook() doesn't reject null directors");
		// test 12.9
		nbTests++;
		nbErrors += addItemFilmBadEntryTest(sn, "B", "qzea", "HI", "oIH", "js", null, 15, "12.9",
				"addItemBook() doesn't reject null scenarists");
		// test 12.10
		nbTests++;
		nbErrors += addItemFilmBadEntryTest(sn, "B", "qzea", "HI", "oIH", "js", "vizgv", -15, "12.10",
				"addItemBook() doesn't reject negative durations");

		// Check if parameters throw NotMemberException
		// populate 'sn' with 3 films
		// test 13.1(a,b,c)
		nbTests++;
		nbErrors += addItemFilmOKTest(sn, "Paul", "paul", "film one", "kind1", "director", "scenarist", 10, "13.1a");
		nbTests++;
		nbErrors += addItemFilmOKTest(sn, "Paul", "paul", "film two", "kind1", "director", "scenarist", 10, "13.1b");
		nbTests++;
		nbErrors += addItemFilmOKTest(sn, "Paul", "paul", "film three", "kind1", "director", "scenarist", 10, "13.1c");

		// test 13.2
		nbTests++;
		nbErrors += addItemFilmNotMemberTest(sn, "nobody", "paul", "film four", "kind1", "director", "scenarist", 10,
				"13.2", "addItemFilm() doesn't reject member who doesn't exist");
		// test 13.3
		nbTests++;
		nbErrors += addItemFilmNotMemberTest(sn, "Paul", "password", "film four", "kind1", "director", "scenarist", 10,
				"13.3", "addItemFilm() doesn't reject an existing member with wrong password");

		// Check if parameters throw AlreadyExistsTest
		// test 14.1
		nbTests++;
		nbErrors += addItemFilmAlreadyExistTest(sn, "Paul", "paul", "film one", "kind1", "director", "scenarist", 10,
				"14.1", "The title of the first film was accepted as title for a new film");
		// test 14.2
		nbTests++;
		nbErrors += addItemFilmAlreadyExistTest(sn, "Paul", "paul", "film three", "kind1", "director", "scenarist", 10,
				"14.2", "The title of the last film was accepted as title for a new film");
		// test 14.3
		nbTests++;
		nbErrors += addItemFilmAlreadyExistTest(sn, "Paul", "paul", "film THREE", "kind1", "director", "scenarist", 10,
				"14.3", "An already registered title, but with different case, was accepted as title for a new film");
		// test 14.4
		nbTests++;
		nbErrors += addItemFilmAlreadyExistTest(sn, "Paul", "paul", "  film three    ", "kind1", "director",
				"scenarist", 10, "14.4",
				"An already registered title, surrounded by leading/trailing blanks, was accepted as title for a new film");
		// test 14.5
		nbTests++;
		nbErrors += addItemFilmAlreadyExistTest(sn, "Paul", "paul", "film" + " three", "kind1", "director", "scenarist",
				10, "14.5",
				"A String concatenation building an already registered title was accepted as title for a new film");
		nbTests++;
		// check that 'sn' was not modified
		if (nbBooks != sn.nbBooks()) {
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

	public static void main(String[] args) throws BadEntryException, MemberAlreadyExistsException {
		// TODO Auto-generated method stub
		test();
	}

}
