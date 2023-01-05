package tests;

import java.util.LinkedList;

import exceptions.BadEntryException;
import exceptions.NotTestReportException;
import opinion.ISocialNetwork;
import opinion.SocialNetwork;

/**
 * @author simon
 *
 */
public class ConsultItemFilmTest {
	/**
	 * @param sn
	 * @param title
	 * @param testId
	 * @param errorMessage
	 * @return
	 */
	private static int ConsultItemFilmBadEntryTest(ISocialNetwork sn, String title, String testId,
			String errorMessage) {
		try {
			sn.consultItems(title);
			System.out.println("Err " + testId + " : " + errorMessage);
			return 1;
		} catch (BadEntryException e) {
			return 0;
		} catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. " + e);
			e.printStackTrace();
			return 1;
		}
	}

	/**
	 * @param sn
	 * @param title
	 * @param testId
	 * @return
	 */
	private static int ConsultItemFilmOKTest(ISocialNetwork sn, String title, String testId) {
		try {
			LinkedList<String> itemsFindList = new LinkedList<String>();
			itemsFindList = sn.consultItems(title);

			if (title == "inexistingTitle") {
				if (itemsFindList.size() != 0) {
					System.out.println(testId + " the returned list is not empty when we search an inexisting title");
					return 1;
				}
			}

			return 0;
		} catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. " + e);
			e.printStackTrace();
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
			System.out.println("user not create");
		}

		int nbMembers = sn.nbMembers();
		int nbFilms = sn.nbFilms();
		int nbBooks = sn.nbBooks();

		int nbTests = 0;
		int nbErrors = 0;

		// Check if parameters throw BadEntryExceptions
		// test 19.1
		nbTests++;
		nbErrors += ConsultItemFilmBadEntryTest(sn, null, "19.1", "ConsultItem() doesn't reject null logins");
		// test 19.2
		nbTests++;
		nbErrors += ConsultItemFilmBadEntryTest(sn, " ", "19.1",
				"ConsultItem() doesn't reject logins that don't contain at least one character other than space");

		// Check if good parameters work
		// test 20.1
		nbTests++;
		nbErrors += ConsultItemFilmOKTest(sn, "Bambi", "20.1");
		// test 20.2
		nbTests++;
		nbErrors += ConsultItemFilmOKTest(sn, "Inception", "20.1");
		// test 20.3
		nbTests++;
		nbErrors += ConsultItemFilmOKTest(sn, "BEBOU", "20.1");

		// Check with inexisting title
		// test 20.4
		nbTests++;
		nbErrors += ConsultItemFilmOKTest(sn, "Inexisting title", "20.4");

		nbTests++;
		// check that 'sn' was not modified
		if (nbBooks != sn.nbBooks()) {
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
