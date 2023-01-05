package opinion;

/**
 * @author simon
 *
 */
public class Member {
	private String login, password, profile;
	private float karma;
	private int nbMarkKarma;

	/**
	 * @param login
	 * @param password
	 * @param profile
	 */
	public Member(String login, String password, String profile) {
		this.login = login;
		this.password = password;
		this.profile = profile;
		karma = 2.5f;
		nbMarkKarma = 0;
	}

	/**
	 * @param login
	 * @return
	 */
	public Member isMember(String login) {
		if (login.trim().toLowerCase().equals(this.login.trim().toLowerCase())) {
			return this;
		} else
			return null;
	}

	/**
	 * @param password
	 * @return
	 */
	public boolean isPassword(String password) {
		if (password.trim().toLowerCase().equals(this.password.trim().toLowerCase())) {
			return true;
		} else
			return false;
	}
	
	/**
	 * @param mark
	 */
	public void updateKarma(float mark) {
		float temp = nbMarkKarma*karma;
		float temp2 = temp+mark;
		nbMarkKarma++;
		karma = temp2/nbMarkKarma;
	}
	/**
	 * @return
	 */
	public float getKarma() {
		return karma;
	}
	
	/**
	 * @return
	 */
	public String getLogin() {
		return login;
	}

	/**
	 *
	 */
	public String toString() {
		return login;
	}
}
