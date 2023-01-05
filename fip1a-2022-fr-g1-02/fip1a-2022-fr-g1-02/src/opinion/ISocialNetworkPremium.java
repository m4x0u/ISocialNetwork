package opinion;

import exceptions.BadEntryException;
import exceptions.NotItemException;
import exceptions.NotMemberException;
import exceptions.NotReviewException;
import exceptions.OpinionAlreadyExistException;
import exceptions.SameMemberException;

/**
 * @author simon
 *
 */
public interface ISocialNetworkPremium extends ISocialNetwork {

	/**
	 * @param login1
	 * @param password
	 * @param title
	 * @param typeItem
	 * @param login2
	 * @param mark
	 * @return
	 * @throws BadEntryException
	 * @throws NotMemberException
	 * @throws NotItemException
	 * @throws OpinionAlreadyExistException
	 * @throws SameMemberException
	 * @throws NotReviewException
	 */
	public float reviewOpinion(String login1, String password, String title, String typeItem, String login2, float mark)
			throws BadEntryException, NotMemberException, NotItemException, OpinionAlreadyExistException,
			SameMemberException, NotReviewException;

	/**
	 * @param login
	 * @return
	 */
	public float getKarma(String login);
}
