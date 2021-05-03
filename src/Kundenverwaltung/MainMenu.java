package Kundenverwaltung;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.Button;
import java.awt.Component;
import java.awt.Dialog.ModalityType;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JDialog;

public class MainMenu {

	private JFrame frmKundenverwaltung;
	private JDialog createClient;
	private JDialog editClient;
	private int indexToChange;
	private ArrayList<Client> data;

	private SQLiteDatabase db = new SQLiteDatabase("test.db");
	private JTable table;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainMenu window = new MainMenu();
//					window.frmKundenverwaltung.setVisible(true);
//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public MainMenu() {
		initialize();
		MainMenu window = this;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					window.frmKundenverwaltung.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		SQLiteDatabase db = new SQLiteDatabase("test.db");
		System.out.println("Datenbank geladen");

//		db.getClientsFromDatabase();

//		db.insertClientToDatabase("Nils", "Wegner", "Musterstraﬂe 1", "Musterstadt", "12345", "12345/6789");
//
//		db.getClientsFromDatabase();

		data = db.getClientsFromDatabase();

		frmKundenverwaltung = new JFrame();
		frmKundenverwaltung.setTitle("Kundenverwaltung");
		frmKundenverwaltung.setBounds(100, 100, 1024, 768);
		frmKundenverwaltung.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmKundenverwaltung.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Close Program");
		mntmNewMenuItem.addActionListener(new exitApp());

		mnNewMenu.add(mntmNewMenuItem);

		JPanel panel = new JPanel();

		frmKundenverwaltung.getContentPane().add(panel, BorderLayout.NORTH);

		Vector dataV = new Vector();
		for (int i = 0; i < data.size(); i++) {
			Vector row = new Vector();
			row.add(data.get(i).getId());
			row.add(data.get(i).getName());
			row.add(data.get(i).getNachname());
			row.add(data.get(i).getAdresse());
			row.add(data.get(i).getWohnort());
			row.add(data.get(i).getPostleitzahl());
			row.add(data.get(i).getTelefonnummer());
			dataV.add(row);
		}

		Vector titles = new Vector();
		titles.add("id");
		titles.add("Vorname");
		titles.add("Nachname");
		titles.add("Adresse");
		titles.add("Wohnort");
		titles.add("Postleitzahl");
		titles.add("Telefonnummer");

		table = new JTable(dataV, titles);
		table.setRowHeight(35);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel selectionModel = table.getSelectionModel();
		selectionModel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				handleSelectionEvent(e);
			}
		});

		JScrollPane pane = new JScrollPane(table);

		// table.setFillsViewportHeight(true);
		panel.add(pane);

		JButton btnAddClient = new JButton("Add Client");
		btnAddClient.addActionListener(new addClient());
		panel.add(btnAddClient);
		
		JButton btnEditClient = new JButton("Edit Client");
		btnEditClient.addActionListener(new editClient());
		panel.add(btnEditClient);

	}

	public void renewClientTable() {

		data = db.getClientsFromDatabase();

		Vector dataV = new Vector();
		for (int i = 0; i < data.size(); i++) {
			Vector row = new Vector();
			row.add(data.get(i).getId());
			row.add(data.get(i).getName());
			row.add(data.get(i).getNachname());
			row.add(data.get(i).getAdresse());
			row.add(data.get(i).getWohnort());
			row.add(data.get(i).getPostleitzahl());
			row.add(data.get(i).getTelefonnummer());
			dataV.add(row);
		}

		Vector titles = new Vector();
		titles.add("id");
		titles.add("Vorname");
		titles.add("Nachname");
		titles.add("Adresse");
		titles.add("Wohnort");
		titles.add("Postleitzahl");
		titles.add("Telefonnummer");

		
		table.invalidate();
		table.revalidate();
		table.setModel(new DefaultTableModel(dataV, titles));
		table.repaint();
		this.frmKundenverwaltung.repaint();
	}

	// exit app
	static class exitApp implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	class addClient implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			createClient = new JDialog(frmKundenverwaltung, "Kunden Anlegen", ModalityType.APPLICATION_MODAL);
			createClient.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			createClient.setBounds(120, 120, 500, 300);
			createClient.setLayout(null);

			//////////////////////////////////
			// VornamenLabel und -Textfeld //
			//////////////////////////////////
			// Vornamen-Label //
			JLabel labelVorname = new JLabel("Vorname:");
			labelVorname.setBounds(10, 10, 100, 20);
			labelVorname.setHorizontalAlignment(SwingConstants.RIGHT);
			createClient.add(labelVorname);
			// Vornamen-Textfeld //
			JTextField textVorname = new JTextField();
			textVorname.setBounds(115, 10, 300, 20);
			createClient.add(textVorname);

			///////////////////////////////////
			// NachnamenLabel und -Textfeld //
			///////////////////////////////////
			// Nachnamen-Label //
			JLabel labelNachname = new JLabel("Nachname:");
			labelNachname.setBounds(10, 30, 100, 20);
			labelNachname.setHorizontalAlignment(SwingConstants.RIGHT);
			createClient.add(labelNachname);

			////////////////////////
			// Nachnamen-Textfeld //
			JTextField textNachname = new JTextField();
			textNachname.setBounds(115, 30, 300, 20);
			createClient.add(textNachname);

			///////////////////////////////////
			// AdressenLabel und -Textfeld //
			///////////////////////////////////
			// Adresse-Label //
			JLabel labelAdresse = new JLabel("Adresse:");
			labelAdresse.setBounds(10, 50, 100, 20);
			labelAdresse.setHorizontalAlignment(SwingConstants.RIGHT);
			createClient.add(labelAdresse);

			// Adresse-Textfeld //
			JTextField textAdresse = new JTextField();
			textAdresse.setBounds(115, 50, 300, 20);
			createClient.add(textAdresse);

			///////////////////////////////////
			// AdressenLabel und -Textfeld //
			///////////////////////////////////
			// Adresse-Label //
			JLabel labelPostleitzahl = new JLabel("PLZ:");
			labelPostleitzahl.setBounds(10, 70, 100, 20);
			labelPostleitzahl.setHorizontalAlignment(SwingConstants.RIGHT);
			createClient.add(labelPostleitzahl);

			// Adresse-Textfeld //
			JTextField textPostleitzahl = new JTextField();
			textPostleitzahl.setBounds(115, 70, 300, 20);
			createClient.add(textPostleitzahl);

			///////////////////////////////////
			// AdressenLabel und -Textfeld //
			///////////////////////////////////
			// Adresse-Label //
			JLabel labelWohnort = new JLabel("Wohnort:");
			labelWohnort.setBounds(10, 90, 100, 20);
			labelWohnort.setHorizontalAlignment(SwingConstants.RIGHT);
			createClient.add(labelWohnort);

			// Adresse-Textfeld //
			JTextField textWohnort = new JTextField();
			textWohnort.setBounds(115, 90, 300, 20);
			createClient.add(textWohnort);

			///////////////////////////////////
			// AdressenLabel und -Textfeld //
			///////////////////////////////////
			// Adresse-Label //
			JLabel labelTelefonnummer = new JLabel("Telefonnummer:");
			labelTelefonnummer.setBounds(10, 110, 100, 20);
			labelTelefonnummer.setHorizontalAlignment(SwingConstants.RIGHT);
			createClient.add(labelTelefonnummer);

			// Adresse-Textfeld //
			JTextField textTelefonnummer = new JTextField();
			textTelefonnummer.setBounds(115, 110, 300, 20);
			createClient.add(textTelefonnummer);

			//////////////////
			// Submitbutton //
			//////////////////
			JButton submitButton = new JButton("Kunden Anlegen!");
			submitButton.setBounds(115, 130, 150, 50);
			submitButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					addNewClient(textVorname.getText(), textNachname.getText(), textAdresse.getText(),
							textWohnort.getText(), textPostleitzahl.getText(), textTelefonnummer.getText());
					renewClientTable();
					createClient.dispose();
				}
			});
			createClient.add(submitButton);

			createClient.setVisible(true);
			// db.insertClientToDatabase("Nils", "Wegner", "MusterStraﬂe 1", "Musterstadt",
			// "12345", "12345/6789");
			// renewClientTable();

		}
	}

	class editClient implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			editClient = new JDialog(frmKundenverwaltung, "Kunden bearbeiten", ModalityType.APPLICATION_MODAL);
			editClient.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			editClient.setBounds(120, 120, 500, 300);
			editClient.setLayout(null);

			//////////////////////////////////
			// VornamenLabel und -Textfeld //
			//////////////////////////////////
			// Vornamen-Label //
			JLabel labelVorname = new JLabel("Vorname:");
			labelVorname.setBounds(10, 10, 100, 20);
			labelVorname.setHorizontalAlignment(SwingConstants.RIGHT);
			editClient.add(labelVorname);
			// Vornamen-Textfeld //
			JTextField textVorname = new JTextField();
			textVorname.setBounds(115, 10, 300, 20);
			textVorname.setText((String) table.getValueAt(indexToChange, 1));
			editClient.add(textVorname);

			///////////////////////////////////
			// NachnamenLabel und -Textfeld //
			///////////////////////////////////
			// Nachnamen-Label //
			JLabel labelNachname = new JLabel("Nachname:");
			labelNachname.setBounds(10, 30, 100, 20);
			labelNachname.setHorizontalAlignment(SwingConstants.RIGHT);
			editClient.add(labelNachname);

			////////////////////////
			// Nachnamen-Textfeld //
			JTextField textNachname = new JTextField();
			textNachname.setBounds(115, 30, 300, 20);
			textNachname.setText((String) table.getValueAt(indexToChange, 2));
			editClient.add(textNachname);

			///////////////////////////////////
			// AdressenLabel und -Textfeld //
			///////////////////////////////////
			// Adresse-Label //
			JLabel labelAdresse = new JLabel("Adresse:");
			labelAdresse.setBounds(10, 50, 100, 20);
			labelAdresse.setHorizontalAlignment(SwingConstants.RIGHT);
			editClient.add(labelAdresse);

			// Adresse-Textfeld //
			JTextField textAdresse = new JTextField();
			textAdresse.setBounds(115, 50, 300, 20);
			textAdresse.setText((String) table.getValueAt(indexToChange, 3));
			editClient.add(textAdresse);

			///////////////////////////////////
			// AdressenLabel und -Textfeld //
			///////////////////////////////////
			// Adresse-Label //
			JLabel labelPostleitzahl = new JLabel("PLZ:");
			labelPostleitzahl.setBounds(10, 70, 100, 20);
			labelPostleitzahl.setHorizontalAlignment(SwingConstants.RIGHT);
			editClient.add(labelPostleitzahl);

			// Adresse-Textfeld //
			JTextField textPostleitzahl = new JTextField();
			textPostleitzahl.setBounds(115, 70, 300, 20);
			textPostleitzahl.setText((String) table.getValueAt(indexToChange, 5));
			editClient.add(textPostleitzahl);

			///////////////////////////////////
			// AdressenLabel und -Textfeld //
			///////////////////////////////////
			// Adresse-Label //
			JLabel labelWohnort = new JLabel("Wohnort:");
			labelWohnort.setBounds(10, 90, 100, 20);
			labelWohnort.setHorizontalAlignment(SwingConstants.RIGHT);
			editClient.add(labelWohnort);

			// Adresse-Textfeld //
			JTextField textWohnort = new JTextField();
			textWohnort.setBounds(115, 90, 300, 20);
			textWohnort.setText((String) table.getValueAt(indexToChange, 4));
			editClient.add(textWohnort);

			///////////////////////////////////
			// AdressenLabel und -Textfeld //
			///////////////////////////////////
			// Adresse-Label //
			JLabel labelTelefonnummer = new JLabel("Telefonnummer:");
			labelTelefonnummer.setBounds(10, 110, 100, 20);
			labelTelefonnummer.setHorizontalAlignment(SwingConstants.RIGHT);
			editClient.add(labelTelefonnummer);

			// Adresse-Textfeld //
			JTextField textTelefonnummer = new JTextField();
			textTelefonnummer.setBounds(115, 110, 300, 20);
			textTelefonnummer.setText((String) table.getValueAt(indexToChange, 6));
			editClient.add(textTelefonnummer);

			//////////////////
			// Submitbutton //
			//////////////////
			JButton submitButton = new JButton("Kunden ‰ndern!");
			submitButton.setBounds(115, 130, 150, 50);
			submitButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					editClient((int)table.getValueAt(indexToChange, 0), textVorname.getText(), textNachname.getText(), textAdresse.getText(),
							textWohnort.getText(), textPostleitzahl.getText(), textTelefonnummer.getText());
					renewClientTable();
					editClient.dispose();
				}
			});
			editClient.add(submitButton);

			editClient.setVisible(true);
			// db.insertClientToDatabase("Nils", "Wegner", "MusterStraﬂe 1", "Musterstadt",
			// "12345", "12345/6789");
			// renewClientTable();

		}
	}
	
	public void addNewClient(String vorname, String nachname, String adresse, String wohnort, String postleitzahl,
			String telefonnummer) {
		db.insertClientToDatabase(vorname, nachname, adresse, wohnort, postleitzahl, telefonnummer);
	}
	
	public void editClient(int id, String vorname, String nachname, String adresse, String wohnort, String postleitzahl,
			String telefonnummer) {
		db.editClientInDatabase(id, vorname, nachname, adresse, wohnort, postleitzahl, telefonnummer);
	}

	public void handleSelectionEvent(ListSelectionEvent e) {
	    if (e.getValueIsAdjusting())
	        return;

	    // e.getSource() returns an object like this
	    // javax.swing.DefaultListSelectionModel 1052752867 ={11}
	    // where 11 is the index of selected element when mouse button is released

	    String strSource= e.getSource().toString();
	    int start = strSource.indexOf("{")+1,
	        stop  = strSource.length()-1;
	    int iSelectedIndex = Integer.parseInt(strSource.substring(start, stop));
	    System.out.println(iSelectedIndex);
	    System.out.println(table.getValueAt(iSelectedIndex, 0));
	    indexToChange =  iSelectedIndex;
	}

	
}

