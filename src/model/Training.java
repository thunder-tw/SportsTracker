/**
 * @Autor: Ward Truyen, Ben Vanden Dries
 * @Team: Team08
 * @Date: 10/26/2015
 * @Project: SportsTracker
 * @Purpose: Training-gegevens beheren 
 */

package model;

import java.util.GregorianCalendar;

public class Training {
	private
		Address adres; // waar de training afspeelde
		GregorianCalendar datum; // wanneer de training afspeelde
		double afstand; // afgelegde afstand in km, tijdens de training
		long tijdInSec; // gebruikte tijd voor de training in sec
		ESportType type;
}
