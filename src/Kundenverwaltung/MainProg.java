package Kundenverwaltung;

public class MainProg {

	public static void main(String[] args) {
		SQLiteDatabase db = new SQLiteDatabase("test.db");
		System.out.println("Datenbank geladen");
		
		System.out.println(db);
		
		
		new MainMenu();
//		db.getClientsFromDatabase();
//		
//		db.insertClientToDatabase("Nils", "Wegner", "Musterstraße 1", "Musterstadt", "12345", "12345/6789");
//		
//		db.getClientsFromDatabase();

	}

}
