/**
 * @Autor: Ward Truyen, Ben Vanden Dries
 * @Team: Team08
 * @Date: 10/26/2015
 * @Project: SportsTracker
 * @Purpose: Tool voor het vereenvoudigen van console-verwerkingen 
 */package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import model.Address;
import model.ESportType;

public class IOTool {

	public int vraagInteger(String vraag) {
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
	
	public long vraagLong(String vraag) {
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

	public double vraagDouble(String vraag) {
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
	
	public Address vraagAdres_Address()
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

	public Calendar vraagDatumVoorVandaag_Calendar()
	{
		Calendar datumVandaag = Calendar.getInstance();
		do
		{
			Calendar nieuweDatum = vraagDatum_Calendar();
			if( !nieuweDatum.after(datumVandaag))
				return nieuweDatum;
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(" = " + format1.format(nieuweDatum.getTime()));
			System.out.println(String.format("Gelieve een datum vroeger dan %s in te geven.", format1.format(datumVandaag.getTime())));
		}while(true);
	}

	public Calendar vraagDatum_Calendar()
	{
		Calendar datumVandaag = Calendar.getInstance();
		
		// Jaar
		int jaar = vraagInteger("Jaar van training(0=" + datumVandaag.get(Calendar.YEAR) + ") > ");
		if( jaar == 0 ) 
		{
		jaar = datumVandaag.get(Calendar.YEAR);
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
	
	public ESportType vraagTypeSport_ESportType()
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

	public boolean akkoordVragenOmOpdrachtDoorTeVoeren(String vraag)
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

	public void bevestigingVragenOmVerderTeGaan()
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

}
