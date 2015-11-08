/**
 * @Autor: Ward Truyen, Ben Vanden Dries
 * @Team: Team08
 * @Date: 10/26/2015
 * @Project: SportsTracker
 * @Purpose: Training-gegevens beheren 
 */

package model;

import java.util.Date;
import java.util.GregorianCalendar;

public class Training implements Cloneable {
	private ESportType type; // Type Training/sport
	private double afstand; // afgelegde afstand in km, tijdens de training
	private long tijdInSec; // gebruikte tijd voor de training in sec
	private Date datum; // wanneer de training afspeelde
	private Address adres; // waar de training afspeelde

	public Training(ESportType type, double afstand, long tijdInSec, Date datum, Address adres) {
		this.type = type;
		this.afstand = afstand;
		this.tijdInSec = tijdInSec;
		this.datum = datum;
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
	
	public Date getDatum()
	{ return datum; }
	
	public Address getAdres(){
		return adres;
	}

	public String toString() {
		return "testje " + type.toString() + " " + afstand;
	}

	public Training clone() {
		return new Training(type, afstand, tijdInSec, datum, adres);
	}
}
