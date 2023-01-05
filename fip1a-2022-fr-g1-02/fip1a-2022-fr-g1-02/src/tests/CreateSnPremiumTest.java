package tests;

import exceptions.BadEntryException;
import exceptions.ItemBookAlreadyExistsException;
import exceptions.ItemFilmAlreadyExistsException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotItemException;
import exceptions.NotMemberException;
import opinion.ISocialNetworkPremium;
import opinion.SocialNetwork;

/**
 * @author simon
 *
 */
public class CreateSnPremiumTest {

	/**
	 * @return
	 * @throws BadEntryException
	 * @throws MemberAlreadyExistsException
	 * @throws NotMemberException
	 * @throws ItemBookAlreadyExistsException
	 * @throws ItemFilmAlreadyExistsException
	 * @throws NotItemException
	 */
	public static ISocialNetworkPremium CreateNewSnTest() throws BadEntryException, MemberAlreadyExistsException,
			NotMemberException, ItemBookAlreadyExistsException, ItemFilmAlreadyExistsException, NotItemException {
		ISocialNetworkPremium sn = new SocialNetwork();

		// add member
		sn.addMember("Paul", "paul", "a");
		sn.addMember("Alice", "alice", "b");
		sn.addMember("Laura", "laura", "c");

		// add books
		sn.addItemBook("Paul", "paul", "Martial Art", "Documentary", "Jackie Chan", 120);
		sn.addItemBook("Alice", "alice", "Harry Potter", "Fantasy", "J. K. Rowling", 1000);
		sn.addItemBook("Laura", "laura", "Rugby World Cup", "Sport", "Dan Carter", 150);

		// add films
		sn.addItemFilm("Paul", "paul", "Inception", "psychological", "Nolan", "Nolan", 180);
		sn.addItemFilm("Alice", "alice", "Bambi", "Children", "Disney", "Disney", 80);
		sn.addItemFilm("Laura", "laura", "BEBOU", "GNOC", "GNOGNI", "MOUA", 2);

		sn.reviewItemBook("Paul", "paul", "Martial Art", 4.0f, "cool");
		sn.reviewItemBook("Alice", "alice", "Martial Art", 2.5F, "moyen");
		sn.reviewItemBook("Laura", "laura", "Martial Art", 0.5F, "nul");
		sn.reviewItemBook("Paul", "paul", "Harry Potter", 4.0F, "bien!");
		sn.reviewItemBook("Alice", "alice", "Harry Potter", 2.5F, "moyen");
		sn.reviewItemBook("Laura", "laura", "Harry Potter", 0.5F, "nul");
		sn.reviewItemBook("Alice", "alice", "Rugby World Cup", 2.5F, "moyen");
		
		sn.reviewItemFilm("Paul", "paul", "Inception", 4.0f, "cool");
		sn.reviewItemFilm("Alice", "alice", "Inception", 2.5F, "moyen");
		sn.reviewItemFilm("Laura", "laura", "Inception", 0.5F, "nul");
		sn.reviewItemFilm("Paul", "paul", "Bambi", 4.0F, "bien!");
		sn.reviewItemFilm("Alice", "alice", "Bambi", 2.5F, "moyen");
		sn.reviewItemFilm("Laura", "laura", "Bambi", 0.5F, "nul");
		sn.reviewItemFilm("Alice", "alice", "BEBOU", 2.5F, "moyen");

		return sn;
	}

}
