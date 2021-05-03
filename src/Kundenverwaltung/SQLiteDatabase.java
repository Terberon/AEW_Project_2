package Kundenverwaltung;

import java.sql.*;
import java.util.ArrayList;

public class SQLiteDatabase {

	private static Connection connection = null;
	private static String dbPath = null;
	private static Statement statement = null;

	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.err.println("Fehler beim Laden des JDBC-Treibers");
			e.printStackTrace();
		}
	}

	public SQLiteDatabase(String dateiname) {
		dbPath = "./" + dateiname;
	}

	//////////////////////////////////////////////
	// Daten in die SQLite-Datenbank schreiben. //
	//////////////////////////////////////////////
	public void insertClientToDatabase(String vorname, String nachname, String adresse, String wohnort,
			String postleitzahl, String telefonnummer) {
		try {
			// verbindung zur DB-Datei aufbauen
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			connection.setAutoCommit(false);
			System.out.println("Opened database successfully");

			// statement setzen
			statement = connection.createStatement();
			String sql = "INSERT INTO KundenData (vorname,nachname,adresse,wohnort,postleitzahl,telefonnummer) "
					+ "VALUES ('" + vorname + "','" + nachname + "','" + adresse + "','" + wohnort + "','"
					+ postleitzahl + "','" + telefonnummer + "');";
			// Daten in die Datenbank schreiben
			statement.execute(sql);
			// statement schliessen
			statement.close();
			// verbindung beenden
			connection.commit();
			connection.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	//////////////////////////////////////////////
	// Daten aus der SQLite-Datenbank lesen. //
	//////////////////////////////////////////////
	public static ArrayList<Client> getClientsFromDatabase() {
		ArrayList<Client> clients = new ArrayList<Client>();
		try {
			// verbindung zur DB-Datei aufbauen
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			connection.setAutoCommit(false);
			System.out.println("Opened database successfully");

			// statement setzen
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM KundenData;");

			// Results pro Zeile ausgeben
			while (rs.next()) {
				
				int id = rs.getInt("id");
				String vorname = rs.getString("vorname");
				String nachname = rs.getString("nachname");
				String adresse = rs.getString("adresse");
				String wohnort = rs.getString("wohnort");
				String postleitzahl = rs.getString("postleitzahl");
				String telefonnummer = rs.getString("telefonnummer");
				System.out.println();
				clients.add(new Client(id, vorname, nachname, adresse, wohnort, postleitzahl, telefonnummer));
			}
			rs.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return clients;
	}
	
	public void editClientInDatabase(int id, String vorname, String nachname, String adresse, String wohnort,
			String postleitzahl, String telefonnummer) {
		try {
			// verbindung zur DB-Datei aufbauen
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			connection.setAutoCommit(false);
			System.out.println("Opened database successfully");

			// statement setzen
			statement = connection.createStatement();
			String sql = "UPDATE KundenData SET vorname = '" + vorname + "',nachname = '" + nachname + "',adresse = '" + adresse + "',wohnort = '" + wohnort + "',postleitzahl = '" + postleitzahl + "',telefonnummer = '" + telefonnummer + "'  WHERE id = '" + id + "';";
			// Daten in die Datenbank schreiben
			statement.execute(sql);
			// statement schliessen
			statement.close();
			// verbindung beenden
			connection.commit();
			connection.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
}
