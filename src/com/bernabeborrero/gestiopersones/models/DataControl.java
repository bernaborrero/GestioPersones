package com.bernabeborrero.gestiopersones.models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class to manage the model of the application
 * @author Bernabé Borrero
 * 
 */
public class DataControl {
	
	String dbFileName;
	CSVData data;
	WildListIterator<Person> iterator;
	
	/**
	 * Constructor to initialize the model with a given database
	 * @param dbFileName the name of the database
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public DataControl(String dbFileName) throws FileNotFoundException, IOException {
		this.dbFileName = dbFileName;
		
		String line;
		data = new CSVData();
		
		// Read database
		BufferedReader br = new BufferedReader(new FileReader(dbFileName));
		while((line = br.readLine()) != null) {
			data.add(parsePerson(line));
		}
		
		br.close();
		iterator = (WildListIterator<Person>) data.iterator();
	}
	
	/**
	 * Transform CSV row in Person object
	 * @param line the CSV row
	 * @return the Person object
	 */
	private Person parsePerson(String line) {
		String[] parts = line.split(";");
		return new Person(parts[0], parts[1], parts[2], parts[3], Integer.parseInt(parts[4]));
	}

	/**
	 * Get the current person number
	 * @return
	 */
	public int getCurrentPersonID() {
		return iterator.nextIndex();
	}
	
	/**
	 * Get the previous person number
	 * @return
	 */
	public int getPreviousPersonID() {
		return iterator.previousIndex()+1;
	}
	
	/**
	 * Get the total number of persons
	 * @return
	 */
	public int getTotalPersons() {
		return data.size();
	}
	
	/**
	 * Get the next person
	 * @return
	 */
	public Person getNextPerson() {
		return iterator.next();
	}
	
	/**
	 * Get the previous person
	 * @return
	 */
	public Person getPreviousPerson() {
		return iterator.previous();
	}
	
	/**
	 * Get the last person
	 * @return
	 */
	public Person getLastPerson() {
		return iterator.last();
	}
	
	/**
	 * Get the first person
	 * @return
	 */
	public Person getFirstPerson() {
		return iterator.first();
	}
	
	/**
	 * Check if the current element is the first one
	 * @return true if the current element is the first one, false if not
	 */
	public boolean isFirstElement() {
		return !iterator.hasPrevious();
	}
	
	/**
	 * Check if the current element is the last one
	 * @return true if the current element is the last one, false if not
	 */
	public boolean isLastElement() {
		return !iterator.hasNext();
	}
	
	/**
	 * Add a new Person to the list
	 * @param p person to store
	 * @return true if successful, false if not
	 */
	public boolean saveNewPerson(Person p) {
		return data.add(p);
	}
	
	/**
	 * Update current Person information
	 * @param p Person information to update
	 */
	public void updateCurrentPerson(Person p) {
		int position = iterator.previousIndex();
		data.replace(position, p);
	}
	
	/**
	 * Delete current Person
	 */
	public void deleteCurrentPerson() {
		int position = iterator.previousIndex();
		data.remove(position);
	}
	
	/**
	 * Store the virtual data to disk
	 * @throws IOException
	 */
	public void saveDataToDisk() throws IOException {
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(dbFileName));
		for(Person p : data) {
			bw.write(p.toString());
			bw.newLine();
		}
		
		bw.close();
	}
	
}
