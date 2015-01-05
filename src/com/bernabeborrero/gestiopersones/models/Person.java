package com.bernabeborrero.gestiopersones.models;

/**
 * Class to store persons
 * @author Bernabé Borrero
 * 
 */
public class Person {
	
	private String dni, name, firstSurname, secondSurname;
	private int age;
	
	/**
	 * Initialize a person with a DNI
	 * @param dni the unique identifier of that person
	 */
	public Person(String dni) {
		this.dni = dni;
	}
	
	/**
	 * Initialize a person with all of its information
	 * @param dni
	 * @param name
	 * @param firstSurname
	 * @param secondSurname
	 * @param age
	 */
	public Person(String dni, String name, String firstSurname, String secondSurname, int age) {
		this.dni = dni;
		this.name = name;
		this.firstSurname = firstSurname;
		this.secondSurname = secondSurname;
		this.age = age;
	}

	public String getDni() {
		return dni;
	}

	public String getName() {
		return name;
	}

	public String getFirstSurname() {
		return firstSurname;
	}

	public String getSecondSurname() {
		return secondSurname;
	}

	public int getAge() {
		return age;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFirstSurname(String firstSurname) {
		this.firstSurname = firstSurname;
	}

	public void setSecondSurname(String secondSurname) {
		this.secondSurname = secondSurname;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public boolean equals(Object person) {
		if(person instanceof Person) {
			if(((Person) person).getDni().equals(this.getDni())) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return this.getDni() + ";" + this.getName() + ";" + this.getFirstSurname() +
				";" + this.getSecondSurname() + ";" + this.getAge();
	}

}
