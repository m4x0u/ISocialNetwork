package tests;

import exceptions.BadEntryException;
import exceptions.NotItemException;
import exceptions.NotMemberException;
import exceptions.NotTestReportException;
import exceptions.OpinionAlreadyExistException;
import exceptions.SameMemberException;
import opinion.ISocialNetwork;
import opinion.ISocialNetworkPremium;
import opinion.SocialNetwork;

/**
 * @author simon
 *
 */
public class ReviewOpinionTest {

	/**
	 * @param sn
	 * @param login1
	 * @param password
	 * @param title
	 * @param mark
	 * @param login2
	 * @param typeItem
	 * @param testId
	 * @param errorMessage
	 * @return
	 */
	private static int reviewOpinionBadEntryTest(ISocialNetworkPremium sn, String login1, String password, String title,
			float mark, String login2, String typeItem, String testId, String errorMessage) {

		try {
			sn.reviewOpinion(login1, password, title, typeItem, login2, mark);

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
	 * @param login1
	 * @param password
	 * @param title
	 * @param mark
	 * @param login2
	 * @param typeItem
	 * @param testId
	 * @param errorMessage
	 * @return
	 */
	private static int reviewOpinionNotMemberTest(ISocialNetworkPremium sn, String login1, String password,
			String title, float mark, String login2, String typeItem, String testId, String errorMessage) {

		try {
			sn.reviewOpinion(login1, password, title, typeItem, login2, mark);

			System.out.println("Err " + testId + " : " + errorMessage);

			return 1;
		} catch (NotMemberException e) {


			return 0;

		} catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. " + e);
			e.printStackTrace();
			return 1;
		}
	}

	/**
	 * @param sn
	 * @param login1
	 * @param password
	 * @param title
	 * @param mark
	 * @param login2
	 * @param typeItem
	 * @param testId
	 * @param errorMessage
	 * @return
	 */
	private static int reviewOpinionAlreadyExistTest(ISocialNetworkPremium sn, String login1, String password,
			String title, float mark, String login2, String typeItem, String testId, String errorMessage) {

		try {
			sn.reviewOpinion(login1, password, title, typeItem, login2, mark);

			System.out.println("Err " + testId + " : " + errorMessage);

			return 1;
		} catch (OpinionAlreadyExistException e) {


			return 0;

		} catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. " + e);
			e.printStackTrace();
			return 1;
		}
	}

	/**
	 * @param sn
	 * @param login1
	 * @param password
	 * @param title
	 * @param mark
	 * @param login2
	 * @param typeItem
	 * @param testId
	 * @param errorMessage
	 * @return
	 */
	private static int reviewOpinionNotItemTest(ISocialNetworkPremium sn, String login1, String password, String title,
			float mark, String login2, String typeItem, String testId, String errorMessage) {

		try {
			sn.reviewOpinion(login1, password, title, typeItem, login2, mark);

			System.out.println("Err " + testId + " : " + errorMessage);

			return 1;
		} catch (NotItemException e) {

			return 0;

		} catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. " + e);
			e.printStackTrace();
			return 1;
		}
	}

	/**
	 * @param sn
	 * @param login1
	 * @param password
	 * @param title
	 * @param mark
	 * @param login2
	 * @param typeItem
	 * @param testId
	 * @param errorMessage
	 * @return
	 */
	private static int reviewOpinionSameMemberTest(ISocialNetworkPremium sn, String login1, String password,
			String title, float mark, String login2, String typeItem, String testId, String errorMessage) {

		try {
			sn.reviewOpinion(login1, password, title, typeItem, login2, mark);

			System.out.println("Err " + testId + " : " + errorMessage);

			return 1;
		} catch (SameMemberException e) {


			return 0;

		} catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. " + e);
			e.printStackTrace();
			return 1;
		}
	}

	/**
	 * @param sn
	 * @param login1
	 * @param password
	 * @param title
	 * @param mark
	 * @param login2
	 * @param typeItem
	 * @param testId
	 * @param expectedKarma
	 * @param expectedRating
	 * @return
	 */
	public static int reviewOpinionOKTest(ISocialNetworkPremium sn, String login1, String password, String title,
			float mark, String login2, String typeItem, String testId, float expectedKarma, float expectedRating) {
		try {
			float meanReview;
			meanReview = sn.reviewOpinion(login1, password, title, typeItem, login2, mark);
			if (sn.getKarma(login2) != expectedKarma) {
				System.out.println("Err " + testId + " : " + "Karma doesn't correspond to expected Karma");
				return 1;
			}
			if(meanReview != expectedRating) { 
				System.out.println("Err " + testId + " : " + "mean doesn't correspond to expected Mean");
				return 1;
			}
			return 0;

		} catch (BadEntryException e) {
			System.out.println(testId + " badEntryException");
			return 1;
		} catch (Exception e) {
			System.out.println(testId + "Exception non prevue");
			return 1;
		}
	}

	/**
	 * @return
	 */
	public static TestReport test() {
		ISocialNetworkPremium sn = new SocialNetwork();

		try {
			sn = CreateSnPremiumTest.CreateNewSnTest();
		} catch (Exception e) {
			System.out.println("user was not create");
		}

		int nbTests = 0;
		int nbErrors = 0;

		System.out.println("Testing reviewOpinion");
		// Check if parameters throw BadEntryException
		// test 21.1
		nbTests++;
		nbErrors += reviewOpinionBadEntryTest(sn, null, "oijbfgz", "title", 5.0f, "login2", "book", "21.1",
				"reviewOpinion() doesn't reject null logins");
		// test 21.2
		nbTests++;
		nbErrors += reviewOpinionBadEntryTest(sn, " ", "oijbfgz", "title", 5.0f, "login2", "book", "21.2",
				"reviewOpinion() doesn't reject logins that don't contain at least one character other than space ");
		// test 21.3
		nbTests++;
		nbErrors += reviewOpinionBadEntryTest(sn, "B", null, "title", 5.0f, "login2", "book", "21.3",
				"reviewOpinion() doesn't reject null passwords");
		// test 21.4
		nbTests++;
		nbErrors += reviewOpinionBadEntryTest(sn, "B", "  zf  ", "title", 5.0f, "login2", "book", "21.4",
				"reviewOpinion() doesn't reject passwords that don't contain at least "
						+ "4 characters (not taking into account leading or trailing blanks)");
		// test 21.5
		nbTests++;
		nbErrors += reviewOpinionBadEntryTest(sn, "B", "oijbfgz", null, 5.0f, "login2", "book", "21.5",
				"reviewOpinion() doesn't reject null titles");
		// test 21.6
		nbTests++;
		nbErrors += reviewOpinionBadEntryTest(sn, "B", "oijbfgz", " ", 5.0f, "login2", "book", "21.6",
				"reviewOpinion() doesn't reject titles that don't contain at least one character other than space ");
		// test 21.7
		nbTests++;
		nbErrors += reviewOpinionBadEntryTest(sn, "B", "oijbfgz", "title", -1.3f, "login2", "book", "21.7",
				"reviewOpinion() doesn't reject mark inferior to 0.0");
		// test 21.8
		nbTests++;
		nbErrors += reviewOpinionBadEntryTest(sn, "B", "oijbfgz", "title", 5.1f, "login2", "book", "21.8",
				"reviewOpinion() doesn't reject mark superior to 5.0");
		// test 21.9
		nbTests++;
		nbErrors += reviewOpinionBadEntryTest(sn, "B", "oijbfgz", "title", 5.0f, null, "book", "21.9",
				"reviewOpinion() doesn't reject null logins2");
		// test 21.10
		nbTests++;
		nbErrors += reviewOpinionBadEntryTest(sn, "B", "oijbfgz", "title", 5.0f, " ", "book", "21.10",
				"reviewOpinion() doesn't reject logins2 that don't contain at least one character other than space ");
		// test 21.11
		nbTests++;
		nbErrors += reviewOpinionBadEntryTest(sn, "B", "oijbfgz", "title", 5.0f, "login2", null, "21.11",
				"reviewOpinion() doesn't reject null typeItems");
		// test 21.12
		nbTests++;
		nbErrors += reviewOpinionBadEntryTest(sn, "B", "oijbfgz", "title", 5.0f, "login2", "poulet", "21.12",
				"reviewOpinion() doesn't reject typeItems not equals to book or film");

		// Check if parameters throw NotMemberException
		// test 22.1
		nbTests++;
		nbErrors += reviewOpinionNotMemberTest(sn, "nobody", "paul", "title", 3.0f, "Alice", "book", "22.1",
				"reviewOpinion() doesn't reject login1 who doesn't exist");
		//test 22.2
		nbTests++;
		nbErrors += reviewOpinionNotMemberTest(sn, "Paul", "paul", "title", 3.0f, "nobody", "book", "22.2", 
				"reviewOpinion() doesn't reject login2 who doesn't exist");
		// test 22.3
		nbTests++;
		nbErrors += reviewOpinionNotMemberTest(sn, "Paul", "password", "title", 3.0f, "Alice", "book", "22.3",
				"reviewOpinion() doesn't reject an existing member with wrong password");

		// check if karma is OK
		// test 23.1
		nbTests++;
		nbErrors += reviewOpinionOKTest(sn, "Alice", "alice", "Martial Art", 3.0f, "Paul", "book", "23.1", 3.0F, 3.0F);
		// test 23.2
		nbTests++;
		nbErrors += reviewOpinionOKTest(sn, "Laura", "laura", "Martial Art", 1.0f, "Paul", "book", "23.2", 2.0F, 2.0F);
		// test 23.3
		nbTests++;
		nbErrors += reviewOpinionOKTest(sn, "Alice", "alice", "Bambi", 5.0f, "Paul", "film", "23.3", 3.0F, 5.0F);
		// 23.4
		nbTests++;
		nbErrors += reviewOpinionOKTest(sn, "Laura", "laura", "Bambi", 2.3f, "Paul", "film", "23.4", 2.825F,
				3.65F);

		// check if parameters throw OpinionAlreadyExistException
		// 24.1
		nbTests++;
		nbErrors += reviewOpinionAlreadyExistTest(sn, "Alice", "alice", "Martial Art", 3.0f, "Paul", "book", "24.1",
				"reviewOpinion() doesn't reject a member rate the same review");

		// check if parameters throw SameMemberException
		// 25.1
		nbTests++;
		nbErrors += reviewOpinionSameMemberTest(sn, "Paul", "paul", "Martial Art", 3.0f, "Paul", "book", "25.1",
				"reviewOpinion() doesn't reject a member rate his own review");

		// Display final state of 'sn'
		System.out.println("Final state of the social network : "+ "\n" + sn);

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
