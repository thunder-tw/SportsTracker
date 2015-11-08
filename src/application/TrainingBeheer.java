/**
 * @Autor: Ward Truyen, Ben Vanden Dries
 * @Team: Team08
 * @Date: 10/26/2015
 * @Project: SportsTracker
 * @Purpose: Console programma voor het beheren van Trainingen 
 */
package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

import model.Address;
import model.ESportType;
import model.Training;
import model.TrainingManager;

public class TrainingBeheer {
	TrainingManager manager;

	public TrainingBeheer() {
		manager = new TrainingManager();
	}

	public TrainingBeheer(int x) {
		manager = new TrainingManager();
		for( int i = 0; i < x; i++)
		{
			manager.addTraining(new Training( ESportType.lopen,
					Calendar.getInstance(), 100.1,120,null));
		}
	}

	public static void main(String[] args) {
		// TODO: gegevens ophalen van bestand of factory naargelang gebruikte
		// constructor?
		TrainingBeheer beheerder = new TrainingBeheer(3);
		beheerder.startMenu();
	}

	public void startMenu() {
		boolean runningLoop = true;

		do {
			try {
				printMenu();
				switch (vraagInteger("Uw menu keuze > ")) {
				case 1: // Nieuwe training invoeren
					optieNieuweTrainingInvoeren();
					break;

				case 2: // Overzicht trainingen
					optieOverzichtTrainingenWeergeven();
					break;

				case 3: // Training verwijderen
					optieTrainingVerwijderen();
					break;

				case 9: // Stoppen
					runningLoop = false;
					break;
				default: // Ongeldige keuze
					System.out.println("\nOngeldige keuze.");
				}
				// System.out.
				// System.out.println("Ingevoerde keuze: " + getal);
			} catch (Exception ex) {

				// if any error occurs
				ex.printStackTrace();
				return;
			}
		} while (runningLoop);
		System.out.println("\nSporten is gezond!");
		System.out.println("Tot ziens.");
	}

	private int vraagInteger(String vraag) {
		do {
			try {
				System.out.print(vraag);
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				return Integer.parseInt(reader.readLine());
			} catch (IOException ex) {
				System.out.println("General I/O exception: " + ex.getMessage());
				ex.printStackTrace();
			} catch (NumberFormatException ex) {
				System.out.println("Gelieve een geheel getal in te geven.");
			}
		} while (true);
	}
	
	private long vraagLong(String vraag) {
		do {
			try {
				System.out.print(vraag);
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				return Long.parseLong(reader.readLine());
			} catch (IOException ex) {
				System.out.println("General I/O exception: " + ex.getMessage());
				ex.printStackTrace();
			} catch (NumberFormatException ex) {
				System.out.println("Gelieve een geheel getal in te geven.");
			}
		} while (true);
	}

	private double vraagDouble(String vraag) {
		do {
			try {
				System.out.print(vraag);
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				return Double.parseDouble(reader.readLine());
			} catch (IOException ex) {
				System.out.println("General I/O exception: " + ex.getMessage());
				ex.printStackTrace();
			} catch (NumberFormatException ex) {
				System.out.println("Gelieve een decimaal getal in te geven.");
			}
		} while (true);
	}
	
	private Address vraagAdres_Address()
	{
		System.out.println("Adres:");
		Address adres = new Address();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("straat > ");
		try {
			adres.setStreet(reader.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.print("bus nr. > ");
		try {
			adres.setNumber(reader.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.print("Post code > ");
		try {
			adres.setZipCode(reader.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.print("Gemeente > ");
		try {
			adres.setCity(reader.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return adres;
	}
	
	private Calendar vraagDatum_Calendar()
	{
		Calendar cal = Calendar.getInstance();
		
		// Jaar
		int jaar = vraagInteger("Jaar van training(0=" + cal.get(Calendar.YEAR) + ") > ");
		if( jaar == 0 ) 
		{
		jaar = cal.get(Calendar.YEAR);
		System.out.println(" = " + jaar);
		}
		
		// Maand
		int maand;
		do
		{
			maand = vraagInteger("Maand van training(1>=x<=12) > ");
		} while( maand < 1 || maand > 12 );
		
		// Dag
		int dag;
		do
		{
			dag = vraagInteger("Dag van training(1>=x<=31) > ");
		} while( dag < 1 || dag > 31 );
		return new GregorianCalendar(jaar, maand-1, dag);
	}
	
	private ESportType vraagTypeSport_ESportType()
	{
		do {
			try {
				System.out.print("Type sport(w,l,s,z,f,t,?) > ");
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String antwoord = reader.readLine();
				switch(antwoord.toLowerCase()){
				case "w":
					return ESportType.wandelen;
				case "l":
					return ESportType.lopen;
				case "s":
					return ESportType.sprinten;
				case "z":
					return ESportType.zwemmen;
				case "f":
					return ESportType.fietsen;
				case "t":
					return ESportType.triathlon;
				case "?":
					System.out.println("Sport types:");
					System.out.println(" w = Wandelen");
					System.out.println(" l = Lopen");
					System.out.println(" s = Sprinten");
					System.out.println(" z = Zwemmen");
					System.out.println(" f = Fietsen");
					System.out.println(" t = Triathlon");
					break;
				default:
					System.out.println("Ongeldige keuze. Gebruik \"?\" voor meer informatie.");
				}

			} catch (IOException ex) {
				System.out.println("General I/O exception: " + ex.getMessage());
				ex.printStackTrace();
			}
		} while (true);
	}

	private boolean akkoordVragenOmOpdrachtDoorTeVoeren(String vraag)
	{
		do {
			try {
				System.out.print(vraag + "(j/n) > ");
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String antwoord = reader.readLine();
				switch(antwoord.toLowerCase()){
				case "j":
				case "1":
					return true;
				case "n":
				case "0":
					return false;
				case "?":
					System.out.println("Opties:");
					System.out.println(" j = ja / true");
					System.out.println(" n = neen / false");
					System.out.println(" 1 = ja / true");
					System.out.println(" 0 = neen / false");
					break;
				default:
					System.out.println("Ongeldige keuze. Gebruik \"?\" voor meer informatie.");
				}

			} catch (IOException ex) {
				System.out.println("General I/O exception: " + ex.getMessage());
				ex.printStackTrace();
			}
		} while (true);
	}

	private void bevestigingVragenOmVerderTeGaan()
	{
		System.out.println("Druk [enter] om verder te gaan.");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			@SuppressWarnings("unused")
			String temp = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void printMenu() {
		System.out.println("TrainingBeheerder:");
		System.out.println("------------------");
		System.out.println("\nMenu:");
		System.out.println(" 1.\tNieuwe training invoeren");
		System.out.println(" 2.\tOverzicht trainingen");
		System.out.println(" 3.\tTraining verwijderen");
		System.out.println(" 9.\tStoppen\n");
	}

	private void optieNieuweTrainingInvoeren() {
		System.out.println("\nNieuwe training:");

		ESportType type = vraagTypeSport_ESportType();
		System.out.println(" = " + type.toString());
		
		Calendar datum = vraagDatum_Calendar();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(" = " + format1.format(datum.getTime()));
		
		double afstand = vraagDouble("Afstand in km > ");
		
		long tijdInSec = vraagLong("Tijd in sec > ");
		
		Address adres = vraagAdres_Address();
		
		Training training = new Training(type, datum, afstand, tijdInSec, adres);
		//System.out.println(" = " + training.toString());
		
		if( akkoordVragenOmOpdrachtDoorTeVoeren( 
				"Training = " + training.toString()
				+ "\nAdres:\n" + training.getAdres().toString() + "\nZijn deze gegevens correct?") == true )
		{
			manager.addTraining(training);
			overzichtAlleTrainingenWeergeven();
			System.out.println("Training ingevoerd.\n");
			bevestigingVragenOmVerderTeGaan();
		}
	}

	private void optieOverzichtTrainingenWeergeven() {
		System.out.println("\nOverzicht trainingen:");
		overzichtAlleTrainingenWeergeven();
		bevestigingVragenOmVerderTeGaan();
	}
	
	private void overzichtAlleTrainingenWeergeven()
	{
		ArrayList<Training> list = manager.getAlleTrainingen();
		if( list.size() > 0 )
		{
			int index = 1;
			for(Iterator<Training> i = list.iterator(); i.hasNext(); )
			{
				Training t = i.next();
				System.out.println(" " + index + ".\t" + t);
				index++;
			}
		}
		System.out.println("<LIST_END>\n");	
	}

	private void optieTrainingVerwijderen() {
		System.out.println("\nTraining verwijderen:");
		bevestigingVragenOmVerderTeGaan();
	}
}
