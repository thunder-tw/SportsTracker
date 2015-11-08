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
import java.util.ArrayList;
import java.util.Date;
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

	public static void main(String[] args) {
		// TODO: gegevens ophalen van bestand of factory naargelang gebruikte
		// constructor?
		TrainingBeheer beheerder = new TrainingBeheer();
		beheerder.startMenu();
	}

	public void startMenu() {
		boolean runningLoop = true;

		do {
			try {
				printMenu();
				switch (vraagInteger("Uw menu keuze> ")) {
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
				// name = reader.readLine();
				// Scanner sc = new Scanner(System.in);
				int getal = Integer.parseInt(reader.readLine());// sc.nextInt();
				// sc.close();
				return getal;

			} catch (IOException ex) {
				System.out.println("General I/O exception: " + ex.getMessage());
				ex.printStackTrace();
			} catch (NumberFormatException ex) {
				System.out.println("Gelieve een geheel getal in te geven.");
			}
		} while (true);

	}

	private void printMenu() {
		System.out.println("TrainingBeheerder:");
		System.out.println("------------------");
		System.out.println("\nMenu:");
		System.out.println(" 1.\tNieuwe training invoeren");
		System.out.println(" 2.\tOverzicht trainingen");
		System.out.println(" 3.\tTraining verwijderen");
		System.out.println(" 9.\tStoppen");
	}

	private void optieNieuweTrainingInvoeren() {
		System.out.println("\nNieuwe training:");

		System.out.println("Type (w,l,s,z,f,t,?)> ");
		System.out.println("Afstand in km> ");
		System.out.println("Tijd in sec> ");
		System.out.println("datum (xx-xx-xxxx)> ");
		System.out.println("adres pff> ");
		manager.addTraining(new Training(ESportType.fietsen, 10, 20, new Date(2015,12,30), null));

		System.out.println("Training ingevoerd.\n");
	}

	private void optieOverzichtTrainingenWeergeven() {
		System.out.println("\nOverzicht trainingen:");
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
	}
}
