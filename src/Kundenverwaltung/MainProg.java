package Kundenverwaltung;

public class MainProg {

	public static void main(String[] args) {
		SQLiteDatabase db = new SQLiteDatabase("test.db");
		System.out.println("Datenbank geladen");

		System.out.println(db);

		new MainMenu();


	}

}
