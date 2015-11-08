/**
 * @Autor: Ward Truyen, Ben Vanden Dries
 * @Team: Team08
 * @Date: 10/26/2015
 * @Project: SportsTracker
 * @Purpose: Training-gegevens beheren 
 */

package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Training implements Cloneable {
	private ESportType type; // Type Training/sport
	private Calendar  datum; // wanneer de training afspeelde
	private double afstand; // afgelegde afstand in km, tijdens de training
	private long tijdInSec; // gebruikte tijd voor de training in sec
	private Address adres; // waar de training afspeelde

	public Training(ESportType type, Calendar  datum, double afstand, long tijdInSec, Address adres) {
		this.type = type;
		this.datum = datum;
		this.afstand = afstand;
		this.tijdInSec = tijdInSec;
		this.adres = adres;
	}

	public ESportType getType() {
		return type;
	}

	public double getAfstand() {
		return afstand;
	}

	public long getTijdInSec() {
		return tijdInSec;
	}
	
	public Calendar getDatum()
	{ return datum; }
	
	public Address getAdres(){
		return adres;
	}

	public String toString() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		return String.format("%-12s\t%s\t%.2fkm.\t%dsec.", type.toString(), format1.format(datum.getTime()), afstand, tijdInSec);
	}

	public Training clone() {
		return new Training(type, datum, afstand, tijdInSec, adres);
	}
}
