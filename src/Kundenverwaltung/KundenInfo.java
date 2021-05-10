package Kundenverwaltung;

public class KundenInfo {
	private int id;

	private enum InformationType {
		HARDWARE, SOFTWARE, PASSWORD, ACCOUNT
	};

	private Information info;

	public KundenInfo(int id, InformationType type, String information) {
		switch(type) {
		case HARDWARE:
			id = this.id;
			break;
		case SOFTWARE:
			break;
		case PASSWORD:
			break;
		case ACCOUNT:
			break;
		}
	}
}
