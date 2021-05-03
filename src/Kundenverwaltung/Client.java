package Kundenverwaltung;

public class Client {
	private int id;
	private String name;
	private String nachname;
	private String adresse;
	private String wohnort;
	private String postleitzahl;
	private String telefonnummer;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getWohnort() {
		return wohnort;
	}
	public void setWohnort(String wohnort) {
		this.wohnort = wohnort;
	}
	public String getPostleitzahl() {
		return postleitzahl;
	}
	public void setPostleitzahl(String postleitzahl) {
		this.postleitzahl = postleitzahl;
	}
	public String getTelefonnummer() {
		return telefonnummer;
	}
	public void setTelefonnummer(String telefonnummer) {
		this.telefonnummer = telefonnummer;
	}
	public Client(int id, String name, String nachname, String adresse, String wohnort, String postleitzahl,
			String telefonnummer) {
		super();
		this.id = id;
		this.name = name;
		this.nachname = nachname;
		this.adresse = adresse;
		this.wohnort = wohnort;
		this.postleitzahl = postleitzahl;
		this.telefonnummer = telefonnummer;
	}


	
}
