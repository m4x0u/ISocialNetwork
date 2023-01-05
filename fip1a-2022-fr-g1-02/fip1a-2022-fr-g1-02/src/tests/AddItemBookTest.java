package tests;

import exceptions.BadEntryException;
import exceptions.ItemBookAlreadyExistsException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotMemberException;
import exceptions.NotTestReportException;
import opinion.ISocialNetwork;
import opinion.SocialNetwork;

/**
 * @author simon
 *
 */
public class AddItemBookTest {

	/**
	 * @param sn
	 * @param login
	 * @param password
	 * @param title
	 * @param kind
	 * @param author
	 * @param nbPages
	 * @param testId
	 * @param errorMessage
	 * @return
	 */
	private static int addItemBookBadEntryTest(ISocialNetwork sn, String login, String password, String title,
			String kind, String author, int nbPages, String testId, String errorMessage) {
		int nbBooks = sn.nbBooks();

		try {
			sn.addItemBook(login, password, title, kind, author, nbPages);

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
	 * @param kind
	 * @param author
	 * @param nbPages
	 * @param testId
	 * @param errorMessage
	 * @return
	 */
	private static int addItemBookNotMemberTest(ISocialNetwork sn, String login, String password, String title,
			String kind, String author, int nbPages, String testId, String errorMessage) {
		int nbBooks = sn.nbBooks();
		try {
			sn.addItemBook(login, password, title, kind, author, nbPages);
			System.out.println("Err " + testId + " : " + errorMessage);
			return 1;
		} catch (NotMemberException e) {
			if (sn.nbBooks() != nbBooks) {
				System.out.println("Err " + testId + " : NotMember was thrown, but the number of books was changed"); // message
				return 1;
			} else
				return 0;
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
	 * @param author
	 * @param nbPages
	 * @param testId
	 * @param errorMessage
	 * @return
	 */
	private static int addItemBookAlreadyExistsTest(ISocialNetwork sn, String login, String password, String title,
			String kind, String author, int nbPages, String testId, String errorMessage) {
		int nbBooks = sn.nbBooks();
		try {
			sn.addItemBook(login, password, title, kind, author, nbPages);
			System.out.println("Err " + testId + " : " + errorMessage);
			return 1;
		} catch (ItemBookAlreadyExistsException e) {
			if (sn.nbBooks() != nbBooks) {
				System.out.println(
						"Err " + testId + " : ItemBookAlreadyExists was thrown, but the number of books was changed");

				return 1;
			} else
				return 0;
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
	 * @param author
	 * @param nbPages
	 * @param testId
	 * @return
	 */
	private static int addItemBookOKTest(ISocialNetwork sn, String login, String password, String title, String kind,
			String author, int nbPages, String testId) {
		int nbBooks = sn.nbBooks();
		try {
			sn.addItemBook(login, password, title, kind, author, nbPages);
			if (sn.nbBooks() != nbBooks + 1) {
				System.out.println("Err " + testId + " : the number of books " + nbBooks + " was not incremented");
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

		System.out.println("Testing addItemBooks");
		// Check if parameters throw BadEntryException
		// test 3.1
		nbTests++;
		nbErrors += addItemBookBadEntryTest(sn, null, "hefoiq", "HI", "", "", 1, "3.1",
				"addItemBook() doesn't reject null logins");
		// test3.2
		nbTests++;
		nbErrors += addItemBookBadEntryTest(sn, " ", "hefoiq", "HI", "", "", 1, "3.2",
				"addItemBook() doesn't reject logins that don't contain " + "at least one character other than space");
		// test 3.3
		nbTests++;
		nbErrors += addItemBookBadEntryTest(sn, "B", null, "HI", "", "", 1, "3.3",
				"addItemBook() doesn't reject null passwords");
		// test 3.4
		nbTests++;
		nbErrors += addItemBookBadEntryTest(sn, "B", "  qjd  ", "HI", "", "", 1, "3.4",
				"addItemBook() doesn't reject passwords that don't contain at least 4 characters (not taking into account leading or trailing blanks)");
		// test 3.5
		nbTests++;
		nbErrors += addItemBookBadEntryTest(sn, "B", "jhsvegu", null, "", "", 1, "3.5",
				"addItemBook() doesn't reject null titles");
		// test 3.6
		nbTests++;
		nbErrors += addItemBookBadEntryTest(sn, "B", "jhsvegu", " ", "", "", 1, "3.6",
				"addItemBook() doesn't reject titles that don't contain at least one character other than space");
		// test 3.7
		nbTests++;
		nbErrors += addItemBookBadEntryTest(sn, "B", "jhsvegu", "HI", null, "", 1, "3.7",
				"addItemBook() doesn't reject null kinds");
		// test 3.8
		nbTests++;
		nbErrors += addItemBookBadEntryTest(sn, "B", "jhsvegu", "HI", "", null, 1, "3.8",
				"addItemBook() doesn't reject null authors");
		// test 3.9
		nbTests++;
		nbErrors += addItemBookBadEntryTest(sn, "B", "jhsvegu", "HI", "", "", 0, "3.9",
				"addItemBook() doesn't reject negative numbers of pages");

		// Check if parameters throw NotMemberException
		// populate 'sn' with 3 books
		// test 4.1(a,b,c)
		nbTests++;
		nbErrors += addItemBookOKTest(sn, "Paul", "paul", "title one", "kind1", "Author1", 10, "4.1a");
		nbTests++;
		nbErrors += addItemBookOKTest(sn, "Alice", "alice", "title two", "kind2", "Author2", 10, "4.1b");
		nbTests++;
		nbErrors += addItemBookOKTest(sn, "Laura", "laura", "title three", "kind3", "Author3", 10, "4.1c");
		// test 4.2
		nbTests++;
		nbErrors += addItemBookNotMemberTest(sn, "nobody", "paul", "title four", "kind4", "Author4", 10, "4.2",
				"addItemBook() doesn't reject member who doesn't exist");
		// test 4.3
		nbTests++;
		nbErrors += addItemBookNotMemberTest(sn, "Paul", "password", "title four", "kind4", "Author4", 10, "4.3",
				"addItemBook() doesn't reject an existing member with wrong password");
		// Check if parameters throw AlreadyExistsTest
		// test 5.1
		nbTests++;
		nbErrors += addItemBookAlreadyExistsTest(sn, "Paul", "paul", "title one", "kind1", "Author1", 150, "5.1",
				"The title of the first book was accepted as title for a new book");
		// test 5.2
		nbTests++;
		nbErrors += addItemBookAlreadyExistsTest(sn, "Paul", "paul", "title three", "kind3", "Author3", 150, "5.2",
				"The title of the last book was accepted as title for a new book");
		// test 5.3
		nbTests++;
		nbErrors += addItemBookAlreadyExistsTest(sn, "Paul", "paul", "title TWO", "kind2", "Author2", 150, "5.3",
				"An already registered title, but with different case, was accepted as title for a new book");
		// test 5.4
		nbTests++;
		nbErrors += addItemBookAlreadyExistsTest(sn, "Paul", "paul", " title two     ", "kind2", "Author2", 150, "5.4",
				"An already registered title, surrounded by leading/trailing blanks, was accepted as title for a new book");
		// test 5.5
		nbTests++;
		nbErrors += addItemBookAlreadyExistsTest(sn, "Paul", "paul", "title " + "two", "kind1", "Author1", 150, "5.5",
				"A String concatenation building an already registered title was accepted as title for a new book");
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
