package com.bernabeborrero.gestiopersones.models;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Custom Set to store Person objects keeping the original order
 * @author Bernabé Borrero
 * 
 */
public class CSVData extends AbstractSet<Person> {
	
	ArrayList<Person> persons = new ArrayList<Person>();

	@Override
	public Iterator<Person> iterator() {
		return new WildListIterator<Person>() {
			int pos = 0;

			@Override
			public boolean hasNext() {
				return pos < persons.size();
			}

			@Override
			public Person next() {
				return persons.get(pos++);
			}

			@Override
			public boolean hasPrevious() {
				return (pos - 1) > 0;
			}

			@Override
			public Person previous() {
				pos--;
				return persons.get(pos-1);
			}

			@Override
			public int nextIndex() {
				return pos+1;
			}

			@Override
			public int previousIndex() {
				return pos-1;
			}
			
			@Override
			public Person first() {
				pos = 0;
				return persons.get(pos++);
			}
			
			@Override
			public Person last() {
				pos = persons.size() - 1;
				return persons.get(pos++);
			}

			@Override
			public void remove() {
				// Empty method body for optional implementation
			}

			@Override
			public void set(Person e) {
				// Empty method body for optional implementation
			}

			@Override
			public void add(Person e) {
				// Empty method body for optional implementation
			}
		};
	}
	
	@Override
	public boolean add(Person p) {
		for(Person person : persons) {
			if(person.equals(p)) {
				return false;
			}
		}
		
		persons.add(p);
		return true;
	}
	
	/**
	 * Replace the person in the specified index
	 * @param index
	 * @param p
	 */
	public void replace(int index, Person p) {
		persons.set(index, p);
	}
	
	@Override
	public boolean remove(Object p) {
		if(p instanceof Person) {
			for(int i = 0; i < persons.size(); i++) {
				if(persons.get(i).equals(p)) {
					persons.remove(i);
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Remove the person in the specified index
	 * @param index
	 */
	public void remove(int index) {
		persons.remove(index);
	}

	@Override
	public int size() {
		return persons.size();
	}

}
