package opinion;

import java.util.LinkedList;

import exceptions.BadEntryException;
import exceptions.ItemBookAlreadyExistsException;
import exceptions.ItemFilmAlreadyExistsException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotItemException;
import exceptions.NotMemberException;
import exceptions.NotReviewException;
import exceptions.OpinionAlreadyExistException;
import exceptions.SameMemberException;
import tests.AddItemBookTest;
import tests.AddMemberTest;
import tests.ReviewOpinionTest;

/* @author - Roux S. , CHARLOT M.
* @version V1.3
*/

/**
* System for collecting opinions on items from various areas (literature,
* cinema, art, gastronomy, etc.). </p>
* <p>
* Access to items and opinions related to them is public. Creating a new item
* or giving an opinion, on the other hand, is limited to users with registered
* profile in the system.
* </p>
* <p>
* When a method can throw two types of exception, and the conditions are met to
* raise both, there is no way to predict which of the two will actually be
* thrown.
* </p>
* 
*/
public class SocialNetwork implements ISocialNetworkPremium {

	private int nbMembers = 0;
	private int nbBooks = 0;
	private int nbFilms = 0;
	private LinkedList<Member> members = new LinkedList<Member>();
//	private LinkedList<ItemBook> books = new LinkedList<ItemBook>();
//	private LinkedList<ItemFilm> films = new LinkedList<ItemFilm>();
	private LinkedList<Item> items = new LinkedList<Item>();
	
	
	
	/**
	 * Get the number of members registered in the <i>SocialNetwork</i>
	 * 
	 * @return number of members
	 */
	@Override
	public int nbMembers() {
		// TODO Auto-generated method stub
		return nbMembers;
	}
	
	/**
	 * Get the number of films registered in the <i>SocialNetwork</i>
	 * 
	 * @return number of films
	 */
	@Override
	public int nbFilms() {
		// TODO Auto-generated method stub
		return nbFilms;
	}
	
	/**
	 * Get the number of books registered in the <i>SocialNetwork</i>
	 * 
	 * @return number of books
	 */
	@Override
	public int nbBooks() {
		// TODO Auto-generated method stub
		return nbBooks;
	}

	/**
	 * Add a new member to the <i>SocialNetwork</i>
	 * 
	 * @param login
	 *            the new member's login
	 * @param password
	 *            the new member's password
	 * @param profile
	 *            a free String describing the new member's profile
	 * 
	 * @throws BadEntryException
	 *             <ul>
	 *             <li>if login is not instantiated or contains less than one
	 *             non-space character</li>
	 *             <li>if password is not instantiated or contains less than
	 *             four characters (not taking into account leading or trailing
	 *             blanks)</li>
	 *             <li>if profile is not instantiated</li>
	 *             </ul>
	 * <br>
	 * 
	 * @throws MemberAlreadyExistsException
	 *             if a member with the same login is already registered in the
	 *             <i>SocialNetwork</i> (same login : not case-sensitive and
	 *             leadings/trailings blanks are not taken into account)
	 * 
	 */
	@Override
	public void addMember(String login, String password, String profile)
			throws BadEntryException, MemberAlreadyExistsException {
		// TODO Auto-generated method stub

		// BadEntryException

		if (login == null || login.isBlank()) { // verified login is not null and length superior to 1 and not a space
			throw new BadEntryException("title can't be null or containt only a space");
		}

		if (password == null || password.trim().length() < 4) { // verified password is not null and length superior to
																// 4 (not taking into account leading or trailing
																// blanks)
			throw new BadEntryException("password cant be null or  less than 4 caracters");
		}

		if (profile == null) { // verified profile is not null
			throw new BadEntryException("profile cant be null");
		}

		// MemberAlreadyExistsException
		for (Member member : members) { // explore LinkedList
			if (member.isMember(login) != null) { // verified if login already exist in the LinkedList
				throw new MemberAlreadyExistsException();
			}
		}
		Member newMember = new Member(login, password, profile); // create member
		members.add(newMember);// add member to LinkedList
		nbMembers++; // increment of nbMembers if a new member was add to LinkedList

	}
	/**
	 * Add a new film to the <i>SocialNetwork</i>
	 * 
	 * @param login
	 *            login of the member adding the film
	 * @param password
	 *            password of the member adding the film
	 * @param title
	 *            the new film's title
	 * @param kind
	 *            the new film's kind (adventure, thriller, etc.)
	 * @param director
	 *            the new film's director
	 * @param scenarist
	 *            the new film's scenarist
	 * @param duration
	 *            the new film's duration (in minutes)
	 * 
	 * @throws BadEntryException
	 *             <ul>
	 *             <li>if login is not instantiated or contains less than one
	 *             non-space character</li>
	 *             <li>if password is not instantiated or contains less than
	 *             four characters (not taking into account leading or trailing
	 *             blanks)</li>
	 *             <li>if title is not instantiated or contains less than one
	 *             non-space character</li>
	 *             <li>if kind is not instanciated</li>
	 *             <li>if director is not instanciated</li>
	 *             <li>if scenarist is not instanciated</li>
	 *             <li>if duration is not stricly positive
	 *             </ul>
	 * <br>
	 * @throws NotMemberException
	 *             if login does not match with the login of a registered member
	 *             in <i>SocialNetwork</i> or if password does not correspond to
	 *             his registered password.
	 * @throws ItemFilmAlreadyExistsException
	 *             : a film with the same title is already registered in the
	 *             <i>SocialNetwork</i> (same title : not case-sensitive and
	 *             leadings/trailings blanks are not taken into account)
	 * 
	 */
	@Override
	public void addItemFilm(String login, String password, String title, String kind, String director,
			String scriptwriter, int duration)
			throws BadEntryException, NotMemberException, ItemFilmAlreadyExistsException {
		// TODO Auto-generated method stub
		Member tempMember = null;
		// BadEntryException
				if (login == null || login.isBlank()) { // verified login is not null and length superior to 1 and not a space
					throw new BadEntryException("login can't be null");
				}

				if (password == null || password.trim().length() < 4) { // verified password is not null and length superior to
																		// 4 (not taking into account leading or trailing
																		// blanks)
					throw new BadEntryException("password cant be null or  less than 4 caracters");
				}
				if (title == null || title.isBlank()) {
					throw new BadEntryException("title can't be null");
				}
				if (kind == null) {
					throw new BadEntryException("kind can't be null");
				}
				if (director == null) {
					throw new BadEntryException("director can't be null");
				}
				if (scriptwriter == null) {
					throw new BadEntryException("scenarist can't be null");
				}
				if (duration < 0) {
					throw new BadEntryException("we can't have negative duration");
				}
				// NotMemberException
				for (Member member : members) {
					tempMember = member.isMember(login);
					if (tempMember != null)
						break;
				}
				if (tempMember == null) {
					throw new NotMemberException("this login is not a member");
				} else {
					if (tempMember.isPassword(password) == false) {
						throw new NotMemberException("the password doesn't match with the login");
					}
				}
				// ItemFilmAlreadyExistsException
				for (Item item : items){
					if (item instanceof ItemFilm && item.isItem(title)) throw new ItemFilmAlreadyExistsException();
				}
				ItemFilm newFilm = new ItemFilm(title, kind, director, scriptwriter,duration);
				items.add(newFilm);
				nbFilms++;
				

	}

	/**
	 * Add a new book to the <i>SocialNetwork</i>
	 * 
	 * @param login
	 *            login of the member adding the book
	 * @param password
	 *            password of the member adding the book
	 * @param title
	 *            the new book's title
	 * @param kind
	 *            the new book's kind (adventure, thriller, etc.)
	 * @param author
	 *            the new book's author
	 * @param nbPages
	 *            number of pages of the new book's
	 * 
	 * @throws BadEntryException
	 *             <ul>
	 *             <li>if login is not instantiated or contains less than one
	 *             non-space character</li>
	 *             <li>if password is not instantiated or contains less than
	 *             four characters (not taking into account leading or trailing
	 *             blanks)</li>
	 *             <li>if title is not instantiated or contains less than one
	 *             non-space character</li>
	 *             <li>if kind is not instanciated</li>
	 *             <li>if author is not instanciated</li>
	 *             <li>if nbPages is not stricly positive
	 *             </ul>
	 * <br>
	 * @throws NotMemberException
	 *             if login does not match with the login of a registered member
	 *             in <i>SocialNetwork</i> or if password does not correspond to
	 *             his registered password.
	 * @throws ItemBookAlreadyExistsException
	 *             a book with the same title is already registered in the
	 *             <i>SocialNetwork</i> (same title : not case-sensitive and
	 *             leadings/trailings blanks are not taken into account)
	 * 
	 */
	@Override
	public void addItemBook(String login, String password, String title, String kind, String author, int nbPages)
			throws BadEntryException, NotMemberException, ItemBookAlreadyExistsException {
		// TODO Auto-generated method stub
		Member tempMember = null;
		// BadEntryException

		if (login == null || login.isBlank()) { // verified login is not null and length superior to 1 and not a space
			throw new BadEntryException("title can't be null or containt only a space");
		}

		if (password == null || password.trim().length() < 4) { // verified password is not null and length superior to
																// 4 (not taking into account leading or trailing
																// blanks)
			throw new BadEntryException("password cant be null or  less than 4 caracters");
		}
		if (title == null || title.isBlank()) {
			throw new BadEntryException("title can't be null or containt only a space");
		}
		if (kind == null) {
			throw new BadEntryException("kind can't be null");
		}
		if (author == null) {
			throw new BadEntryException("author can't be null");
		}
		if (nbPages <= 0) {
			throw new BadEntryException("we can't have negative numbers of pages");
		}

		// NotMemberException
		for (Member member : members) {
			tempMember = member.isMember(login);
			if (tempMember != null)
				break;
		}
		if (tempMember == null) {
			throw new NotMemberException("this login is not a member");
		} else {
			if (tempMember.isPassword(password) == false) {
				throw new NotMemberException("the password doesn't match with the login");
			}
		}

		// ItemBookAlreadyExistsException
		for (Item item : items){
			if (item instanceof ItemBook && item.isItem(title)) throw new ItemBookAlreadyExistsException ();
		}

		// add itemBook
		ItemBook newBook = new ItemBook(title, kind, author, nbPages);
		items.add(newBook);
		nbBooks++;

	}
	
	/**
	 * Add in the <i>SocialNetwork</i> a new review for a film on behalf of a
	 * specific member.</br> If this member has already given a review for this
	 * same film before, the new review replaces the previous one.
	 * 
	 * @param login
	 *            login of the member adding the review
	 * @param password
	 *            password of the member adding the review
	 * @param title
	 *            the reviewed film's title
	 * @param mark
	 *            the mark given by the member for this film
	 * @param comment
	 *            the comment given by the member for this film
	 * 
	 * @throws BadEntryException
	 *             <ul>
	 *             <li>if login is not instantiated or contains less than one
	 *             non-space character</li>
	 *             <li>if password is not instantiated or contains less than
	 *             four characters (not taking into account leading or trailing
	 *             blanks)</li>
	 *             <li>if title is not instantiated or contains less than one
	 *             non-space character</li>
	 *             <li>if mark is not greater or equals to 0.0 and lesser or
	 *             equals to 5.0.</li>
	 *             <li>if comment is not instantiated</li>
	 *             </ul>
	 * <br>
	 * @throws NotMemberException
	 *             if login does not match with the login of a registered member
	 *             in <i>SocialNetwork</i> or if password does not correspond to
	 *             his registered password.
	 * @throws NotItemException
	 *             if title is not registered as a film's title in the
	 *             <i>SocialNetwork</i>
	 * 
	 * @return mean of the marks for this film
	 */
	@Override
	public float reviewItemFilm(String login, String password, String title, float mark, String comment)
			throws BadEntryException, NotMemberException, NotItemException {
		// TODO Auto-generated method stub
		Member tempMember = null;
		// BadEntryException
		if (login == null || login.isBlank()) { // verified login is not null and length superior to 1 and not a space
			throw new BadEntryException("title can't be null or containt only a space");
		}
		if (password == null || password.trim().length() < 4) { // verified password is not null and length superior to
			// 4 (not taking into account leading or trailing
			// blanks)
			throw new BadEntryException("password cant be null or  less than 4 caracters");
		}

		if (title == null || title.isBlank()) {
			throw new BadEntryException("title can't be null or containt only a space");
		}

		if (mark < 0.0F || mark > 5.0F) {
			throw new BadEntryException("the mark is not between 0 and 5");

		}
		if (comment == null || comment.isBlank()) {
			throw new BadEntryException("comment can't be null");
		}
		// NotMemberException
		for (Member member : members) {
			tempMember = member.isMember(login);
			if (tempMember != null)
				break;
		}
		if (tempMember == null) {
			throw new NotMemberException("this login is not a member");
		} else {
			if (tempMember.isPassword(password) == false) {
				throw new NotMemberException("the password doesn't match with the login");
			}
		}
		// NotItemException
		boolean tempfilm = false;
		for (Item item : items){
			if (item instanceof ItemFilm && item.isItem(title)) tempfilm=true;
		}
		if (!tempfilm) {
			throw new NotItemException("this film is not known");
		}

		float tempavg = 0;
		for (Item itemfilm : items) {
			if (itemfilm.isItem(title) == true) {
				itemfilm.removeReview(login, comment, mark);
				itemfilm.addReview(login, comment, mark);
				tempavg = itemfilm.avgMarks();
			}
		}
		return tempavg;
	}
	
	/**
	 * Add in the <i>SocialNetwork</i> a new review for a book on behalf of a
	 * specific member.</br> If this member has already given a review for this
	 * same book before, the new review replaces the previous one.
	 * 
	 * @param login
	 *            login of the member adding the review
	 * @param password
	 *            password of the member adding the review
	 * @param title
	 *            the reviewed book's title
	 * @param mark
	 *            the mark given by the member for this book
	 * @param comment
	 *            the comment given by the member for this book
	 * 
	 * @throws BadEntryException
	 *             <ul>
	 *             <li>if login is not instantiated or contains less than one
	 *             non-space character</li>
	 *             <li>if password is not instantiated or contains less than
	 *             four characters (not taking into account leading or trailing
	 *             blanks)</li>
	 *             <li>if title is not instantiated or contains less than one
	 *             non-space character</li>
	 *             <li>if mark is not greater or equals to 0.0 and lesser or
	 *             equals to 5.0.</li>
	 *             <li>if comment is not instantiated</li>
	 *             </ul>
	 * <br>
	 * @throws NotMemberException
	 *             if login does not match with the login of a registered member
	 *             in <i>SocialNetwork</i> or if password does not correspond to
	 *             his registered password.
	 * @throws NotItemException
	 *             if title is not registered as a book's title in the
	 *             <i>SocialNetwork</i>
	 * 
	 * @return mean of the marks for this book
	 */
	@Override
	public float reviewItemBook(String login, String password, String title, float mark, String comment)
			throws BadEntryException, NotMemberException, NotItemException {
		// TODO Auto-generated method stub
		Member tempMember = null;
		// BadEntryException
		if (login == null || login.isBlank()) { // verified login is not null and length superior to 1 and not a space
			throw new BadEntryException("title can't be null or containt only a space");
		}
		if (password == null || password.trim().length() < 4) { // verified password is not null and length superior to
			// 4 (not taking into account leading or trailing
			// blanks)
			throw new BadEntryException("password cant be null or  less than 4 caracters");
		}

		if (title == null || title.isBlank()) {
			throw new BadEntryException("title can't be null or containt only a space");
		}

		if (mark < 0.0F || mark > 5.0F) {
			throw new BadEntryException("the mark is not between 0 and 5");

		}
		if (comment == null || comment.isBlank()) {
			throw new BadEntryException("comment can't be null");
		}
		// NotMemberException
		for (Member member : members) {
			tempMember = member.isMember(login);
			if (tempMember != null)
				break;
		}
		if (tempMember == null) {
			throw new NotMemberException("this login is not a member");
		} else {
			if (tempMember.isPassword(password) == false) {
				throw new NotMemberException("the password doesn't match with the login");
			}
		}
		// NotItemException
		boolean tempbook = false;
		for (Item item : items){
			if (item instanceof ItemBook && item.isItem(title)) tempbook=true;
		}
		if (!tempbook) {
			throw new NotItemException("this book is not known");
		}

		float tempavg = 0;
		for (Item itembook : items) {
			if (itembook.isItem(title) == true) {
				itembook.removeReview(login, comment, mark);
				itembook.addReview(login, comment, mark);
				tempavg = itembook.avgMarks();
			}
		}
		return tempavg;
	}
	
	/**
	 * Search for items in the <i>SocialNetwork</i>.
	 * 
	 * @param title
	 *            title of searched item(s)
	 * 
	 * @throws BadEntryException
	 *             if title is not instantiated or contains less than one
	 *             non-space character
	 * 
	 * @return LinkedList <String> a list of String representing all items (book
	 *         and/or film) matching the searched name.</br> Each String will
	 *         contain at least the score of the corresponding item.
	 */
	@Override
	public LinkedList<String> consultItems(String title) throws BadEntryException {
		// BadEntryException
		if (title == null || title.isBlank()) {
			throw new BadEntryException("title can't be null or containt only a space");
		}

		LinkedList<String> itemsFindList = new LinkedList<String>();		
		for(Item item : items){ 
			if (item.getTitle().trim().toLowerCase().equals(title.trim().toLowerCase())) itemsFindList.add(item.title + " - note moyenne : " + item.avgMarks);
		}
		return itemsFindList;

	}
	
	/**
	 *
	 */
	@Override
	public float reviewOpinion(String login1, String password, String title, String typeItem, String login2, float mark)
			throws BadEntryException, NotMemberException, NotItemException, OpinionAlreadyExistException,SameMemberException, NotReviewException{
		// TODO Auto-generated method stub
		Member tempMember1 = null;
		Member tempMember2 = null;
		float tempMark = 0;
		//BadEntryException
		if (login1 == null || login1.isBlank()) { // verified login is not null and length superior to 1 and not a space
			throw new BadEntryException("title can't be null or containt only a space");
		}
		if (login2 == null || login2.isBlank()) { // verified login is not null and length superior to 1 and not a space
			throw new BadEntryException("title can't be null or containt only a space");
		}
		if (password == null || password.trim().length() < 4) { // verified password is not null and length superior to
			// 4 (not taking into account leading or trailing
			// blanks)
			throw new BadEntryException("password cant be null or  less than 4 caracters");
		}

		if (title == null || title.isBlank()) {
			throw new BadEntryException("title can't be null or containt only a space");
		}
		
		
		if (mark < 0.0F || mark > 5.0F) {
			throw new BadEntryException("the mark is not between 0 and 5");

		}
		if (typeItem == null || typeItem.isBlank()) {
			throw new BadEntryException("typeItem can't be null or containt only a space");

		}
		if(!(typeItem.equals("book") || typeItem.equals("film"))){
			throw new BadEntryException("typeItem can't be another than 'book' or 'film' ");
		}
		
		//NotMemberException
		for (Member member : members) {
			tempMember1 = member.isMember(login1);
			if (tempMember1 != null)
				break;
		}
		if (tempMember1 == null) {
			throw new NotMemberException("this login1 is not a member");
		} else {
			if (tempMember1.isPassword(password) == false) {
				throw new NotMemberException("the password doesn't match with the login");
			}
		}
		for(Member member : members) {
			tempMember2 = member.isMember(login2);
			if (tempMember2 != null)
				break;
		}
		if (tempMember2 == null) {
			throw new NotMemberException("this login2 is not a member");
		}
		//SameMemberException
		if(login1.trim().toLowerCase().equals(login2.trim().toLowerCase())){
			throw new SameMemberException();
		}
		//type book
		if (typeItem.equals("book")){
			//__NotItem__\\
			boolean isbook = false;
			for (Item item : items){
				if (item instanceof ItemBook && item.isItem(title)) isbook=true;
			}
			if(!isbook) {
				throw new NotItemException ("this title is not an existing item");
			}
			
			for (Item itembook : items){
				if (itembook.getTitle().trim().toLowerCase().equals(title.trim().toLowerCase()) && itembook instanceof ItemBook){
					//login2 comment this item ?
					if (!itembook.isMemberReview(login2)) {
						throw new NotReviewException();
					}
					//login1 already rate this review ?
					if (itembook.memberAlreadyReviewOpinion(login1, login2)) {
						throw new OpinionAlreadyExistException();
					}
					//update mark
					itembook.addMarkToReview(login1, login2, mark);
					//avgMean
					tempMark=itembook.getMarkReview(login1, login2);
				}
			}
			
			tempMember2.updateKarma(mark);				
			return tempMark;
		}
		//type film
		if (typeItem.equals("film")){
			//__NotItem__\\
			boolean isfilm = false;
			for (Item item : items){
				if (item instanceof ItemFilm && item.isItem(title)) isfilm=true;
			}
			if(!isfilm) {
				throw new NotItemException ("this title is not an existing item");
			}
			
			for (Item itemfilm : items){
				if (itemfilm.getTitle().trim().toLowerCase().equals(title.trim().toLowerCase()) && itemfilm instanceof ItemFilm){
					//login2 comment this item ?
					if (!itemfilm.isMemberReview(login2)) {
						throw new NotReviewException();
					}
					//login1 already rate this review ?
					if (itemfilm.memberAlreadyReviewOpinion(login1, login2)) {
						throw new OpinionAlreadyExistException();
					}
					//update mark
					itemfilm.addMarkToReview(login1, login2, mark);
					//avgMean
					tempMark = itemfilm.getMarkReview(login1,login2);
				}
			}
			
			tempMember2.updateKarma(mark);				
			return tempMark;
		}
		
		return 0;
	}

	/**
	 * A <i>SocialNetwork</i> is described at least by member's names, book's
	 * titles/scores and film's titles/scores.
	 * 
	 * @return a String representing the <i>SocialNetwork</i>, with at least
	 *         member's names, book's titles/scores and film's titles/scores.
	 */
	public String toString() {
		
		//filmList :
				LinkedList <Item> filmList = new LinkedList<Item>();
				for (Item item : items){
					if (item instanceof ItemFilm) filmList.add(item);
					
				}
				//bookList :
				LinkedList <Item> bookList = new LinkedList<Item>();
				for (Item item : items){
					if (item instanceof ItemBook) bookList.add(item);
				}
						
				return ("Number of Members : "+nbMembers+ "\n" 
						+ "List of Members : " + members.toString() + "\n"
						+ "Number of Books : "+nbBooks+ "\n"
						+ "List of Books : "+bookList.toString()+"\n"
						+ "Number of Films : "+nbFilms+ "\n"
						+ "List of Films : "+filmList.toString() + "\n"
						);
			
		//return members.toString() + items.toString();// return the members in the LinkedList
	}

	public static void main(String[] args) throws BadEntryException, MemberAlreadyExistsException, NotMemberException,
			ItemBookAlreadyExistsException, NotItemException, ItemFilmAlreadyExistsException, OpinionAlreadyExistException, SameMemberException, NotReviewException {
		// TODO Auto-generated method stub
		SocialNetwork sn = new SocialNetwork();
		
		// add member
				sn.addMember("Paul", "paul", "a");
				sn.addMember("Alice", "alice", "b");
				sn.addMember("Laura", "laura", "c");

				// add books
				sn.addItemBook("Paul", "paul", "Martial Art", "Documentary", "Jackie Chan", 120);

				// add films
				sn.addItemFilm("Paul", "paul", "Inception", "psychological", "Nolan", "Nolan", 180);
				sn.addItemFilm("Alice", "alice", "Bambi", "Children", "Disney", "Disney", 80);
				sn.addItemFilm("Laura", "laura", "BEBOU", "GNOC", "GNOGNI", "MOUA", 2);

				sn.reviewItemBook("Paul", "paul", "Martial Art", 4.0f, "cool");
				sn.reviewItemBook("Alice", "alice", "Martial Art", 4.0F, "moyen");
				sn.reviewItemBook("Laura", "laura", "Martial Art", 4.0F, "nul");
				
				sn.reviewItemFilm("Paul", "paul", "Inception", 4.0f, "cool");
				sn.reviewItemFilm("Alice", "alice", "Inception", 2.5F, "moyen");
				sn.reviewItemFilm("Laura", "laura", "Inception", 0.5F, "nul");
				sn.reviewItemFilm("Paul", "paul", "Bambi", 4.0F, "bien!");
				sn.reviewItemFilm("Alice", "alice", "Bambi", 2.5F, "moyen");
				sn.reviewItemFilm("Laura", "laura", "Bambi", 0.5F, "nul");
				sn.reviewItemFilm("Alice", "alice", "BEBOU", 2.5F, "moyen");
				
				System.out.println(sn.reviewItemBook("Paul", "paul", "Martial Art", 1, "ok"));
				System.out.println(sn.getKarma("Paul"));
				float mean = sn.reviewOpinion("Alice","alice", "Martial Art", "book", "Paul", 2.5f);
				float mean2 = sn.reviewOpinion("Laura","laura", "Martial Art", "book", "Paul", 1);
				System.out.println(sn.getKarma("Paul"));
				System.out.println(sn.reviewItemBook("Paul", "paul", "Martial Art", 4, "ok"));
				System.out.println();
				System.out.println(sn.getKarma("Paul"));
				
				System.out.println(sn.toString());
	}
	
	/**
	 *
	 */
	public float getKarma(String login){
		
		for (Member member : members){
			if (member.getLogin().trim().toLowerCase().equals(login.trim().toLowerCase())) return member.getKarma();
		}
		return 0;
	}

}