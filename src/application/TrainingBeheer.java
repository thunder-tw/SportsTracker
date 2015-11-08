/**
 * @Autor: Ward Truyen, Ben Vanden Dries
 * @Team: Team08
 * @Date: 10/26/2015
 * @Project: SportsTracker
 * @Purpose: Console programma voor het beheren van Trainingen 
 */
package application;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Random;

import model.Address;
import model.ESportType;
import model.Training;
import model.TrainingManager;

public class TrainingBeheer {
	TrainingManager manager;
	IOTool ioTool;

	public TrainingBeheer() {
		manager = new TrainingManager();
		ioTool = new IOTool();
	}

	public TrainingBeheer(int aantalTestTrainingen) {
		manager = new TrainingManager();
		ioTool = new IOTool();

		if (aantalTestTrainingen == 0)
			aantalTestTrainingen = ioTool.vraagInteger("Aantal test elementen > ");

		Random rand = new Random();
		ESportType type;
		Address adres = new Address();
		adres.setStreet("Teststraat");
		adres.setCity("Teststad");

		for (int i = 0; i < aantalTestTrainingen; i++) {
			switch (rand.nextInt(5)) {
			case 0:
				type = ESportType.fietsen;
				break;
			case 1:
				type = ESportType.lopen;
				break;
			case 2:
				type = ESportType.sprinten;
				break;
			case 3:
				type = ESportType.triathlon;
				break;
			case 4:
				type = ESportType.wandelen;
				break;
			default:
				type = ESportType.zwemmen;
				break;
			}
			adres.setNumber(Integer.toString(rand.nextInt(400)));
			adres.setZipCode(Integer.toString(rand.nextInt(600)*10));
			manager.addTraining(
					new Training(type, Calendar.getInstance(), rand.nextDouble(), rand.nextInt(3600), adres));
		}
		System.out.printf("%d test-trainigen aangemaakt!\n", aantalTestTrainingen);
		overzichtAlleTrainingenWeergeven();
		ioTool.bevestigingVragenOmVerderTeGaan();
	}

	public static void main(String[] args) {
		// TODO: gegevens ophalen van bestand of maken naargelang constructor?
		// TrainingBeheer beheerder = new TrainingBeheer(); // normaal
		TrainingBeheer beheerder = new TrainingBeheer(0); // test
		beheerder.startMenu(); // starten :p
	}

	public void startMenu() {
		boolean runningLoop = true;

		do {
			try {
				printMenu();
				switch (ioTool.vraagInteger("Uw menu keuze > ")) {
				case 1: // Nieuwe training invoeren
					optieNieuweTrainingInvoeren();
					break;

				case 2: // Overzicht trainingen
					optieOverzichtTrainingenWeergeven();
					break;

				case 3: // Overzicht trainingen
					optieOverzichtTrainingenPerSportWeergeven();
					break;

				case 4: // Training verwijderen
					optieTrainingVerwijderen();
					break;

				case 99: // Stoppen
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

	private void printMenu() {
		System.out.println("TrainingBeheerder:");
		System.out.println("------------------");
		System.out.println("\nMenu:");
		System.out.println(" 1.\tNieuwe training invoeren");
		System.out.println(" 2.\tOverzicht alle trainingen");
		System.out.println(" 3.\tOverzicht van trainingen per sport");
		System.out.println(" 4.\tLaatst ingegeven training verwijderen");
		System.out.println(" 99.\tStoppen\n");
	}

	private void optieNieuweTrainingInvoeren() {
		System.out.println("\nNieuwe training invoeren:");
		System.out.println("-------------------------\n");

		ESportType type = ioTool.vraagTypeSport_ESportType();
		System.out.println(" = " + type.toString());

		Calendar datum = ioTool.vraagDatumVoorVandaag_Calendar();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(" = " + format1.format(datum.getTime()));

		double afstand = ioTool.vraagDouble("Afstand in km > ");

		long tijdInSec = ioTool.vraagLong("Tijd in sec > ");

		Address adres = ioTool.vraagAdres_Address();

		Training training = new Training(type, datum, afstand, tijdInSec, adres);
		// System.out.println(" = " + training.toString());

		if (ioTool.akkoordVragenOmOpdrachtDoorTeVoeren("Training = " + training.toString() + "\nAdres:\n"
				+ training.getAdres().toString() + "\nZijn deze gegevens correct?") == true) {
			manager.addTraining(training);
			overzichtAlleTrainingenWeergeven();
			System.out.println("Training ingevoerd.\n");
			ioTool.bevestigingVragenOmVerderTeGaan();
		}
	}

	private void optieOverzichtTrainingenWeergeven() {
		System.out.println("\nOverzicht van all trainingen:");
		System.out.println("-----------------------------\n");
		overzichtAlleTrainingenWeergeven();
		ioTool.bevestigingVragenOmVerderTeGaan();
	}

	private void optieOverzichtTrainingenPerSportWeergeven() {
		System.out.println("\nOverzicht van trainingen per sport:");
		System.out.println("-----------------------------------\n");
		ESportType type = ioTool.vraagTypeSport_ESportType();
		ArrayList<Training> list = manager.getAlleTrainingen();
		if (list.size() > 0) {
			int index = 1;
			for (Iterator<Training> i = list.iterator(); i.hasNext();) {
				Training t = i.next();
				if (t.getType() == type) {
					System.out.println(" " + index + ".\t" + t);
					index++;
				}
			}
		}
		System.out.println("<LIST_END>\n");
		ioTool.bevestigingVragenOmVerderTeGaan();
	}

	private void overzichtAlleTrainingenWeergeven() {
		ArrayList<Training> list = manager.getAlleTrainingen();
		if (list.size() > 0) {
			int index = 1;
			for (Iterator<Training> i = list.iterator(); i.hasNext();) {
				Training t = i.next();
				System.out.println(" " + index + ".\t" + t);
				index++;
			}
		}
		System.out.println("<LIST_END>\n");
	}

	private void optieTrainingVerwijderen() {
		System.out.println("\nLaatst ingegeven training verwijderen:");
		System.out.println("--------------------------------------\n");
		Training laatsteTraining = manager.getLastTraining();
		if (laatsteTraining == null) {
			System.out.println("Er is niets om te verwijderen.\n");
		} else {
			if (ioTool.akkoordVragenOmOpdrachtDoorTeVoeren(
					"De laatst-ingevoerde Training:\n" + laatsteTraining.toString() + "\nAdres:\n"
							+ laatsteTraining.getAdres().toString()+ "\nVerwijderen?") == true) {
				manager.removeLastTraining();
				overzichtAlleTrainingenWeergeven();
				System.out.println("Training verwijderd.\n");
			}
		}
		ioTool.bevestigingVragenOmVerderTeGaan();
	}
}
