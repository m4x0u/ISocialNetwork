package tests;

import exceptions.BadEntryException;
import exceptions.ItemBookAlreadyExistsException;
import exceptions.ItemFilmAlreadyExistsException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotMemberException;
import opinion.ISocialNetwork;
import opinion.SocialNetwork;


/**
 * @author simon
 *
 */
public class CreateSnTest {
	
	/**
	 * @return
	 * @throws BadEntryException
	 * @throws MemberAlreadyExistsException
	 * @throws NotMemberException
	 * @throws ItemBookAlreadyExistsException
	 * @throws ItemFilmAlreadyExistsException
	 */
	public static ISocialNetwork CreateNewSnTest() throws BadEntryException,MemberAlreadyExistsException, NotMemberException,ItemBookAlreadyExistsException, ItemFilmAlreadyExistsException{
		 ISocialNetwork sn = new SocialNetwork();
		 
		 
		 // add member 
		 sn.addMember("Paul", "paul", "a");
		 sn.addMember("Alice", "alice", "b");
		 sn.addMember("Laura", "laura", "c");
		 
		 // add books
		 sn.addItemBook("Paul", "paul", "Martial Art", "Documentary", "Jackie Chan", 120);
		 sn.addItemBook("Alice", "alice", "Harry Potter", "Fantasy", "J. K. Rowling", 1000);
		 sn.addItemBook("Laura", "laura", "Rugby World Cup", "Sport", "Dan Carter", 150);
		 
		 //add films
		 sn.addItemFilm("Paul", "paul", "Inception", "psychological", "Nolan", "Nolan", 180);
		 sn.addItemFilm("Alice", "alice", "Bambi", "Children", "Disney", "Disney", 80);
		 sn.addItemFilm("Laura", "laura", "BEBOU", "GNOC", "GNOGNI", "MOUA", 2);
		 
		 
		 return sn;
	}

}
