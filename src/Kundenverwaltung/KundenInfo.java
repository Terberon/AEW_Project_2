package Kundenverwaltung;

public class KundenInfo {
	private int id;

	private enum InformationType {
		HARDWARE, SOFTWARE, PASSWORD, ACCOUNT
	};

	private Information info;

	public KundenInfo(int id, InformationType type, String infoName, String username, String password) {
		this.id = id;
		this.info.setType(type.toString());
		this.info.setInfoName(infoName);
		this.info.setUsername(username);
		this.info.setPassword(password);

	}
}
