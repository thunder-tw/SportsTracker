/**
 * @Autor: Ward Truyen, Ben Vanden Dries
 * @Team: Team08
 * @Date: 10/26/2015
 * @Project: SportsTracker
 * @Purpose: Een lijst van trainingen beheren 
 */
package model;

import java.util.ArrayList;

public class TrainingManager {
	ArrayList<Training> list;

	public TrainingManager() {
		list = new ArrayList<Training>();
	}
	
	public void addTraining( Training training )
	{
		list.add(training);
	}

	// TODO: functies die een gefilterde lijst van trainingen terug geven (type,
	// datum)
	public ArrayList<Training> getAlleTrainingen() {
		@SuppressWarnings("unchecked")
		ArrayList<Training> clone = (ArrayList<Training>) list.clone();
		return clone;
	}
	
	public Training getLastTraining() {
		if(list.size()== 0) return null;
		return list.get(list.size()-1).clone();
	}
	
	public void removeLastTraining() {
		list.remove(list.size()-1);
	}
}
