package com.bernabeborrero.gestiopersones.models;

import java.util.ListIterator;

/**
 * Interface to extend the functionality of ListIterator
 * to add "wild" jumps
 * @author Bernabé Borrero
 *
 * @param <E>
 */
public interface WildListIterator<E> extends ListIterator<E> {
	
	/**
	 * Returns the first element of the iterator
	 * and sets the iterator to the first position
	 * @return the first element
	 */
	public E first();
	
	/**
	 * Returns the last element of the iterator
	 * and sets the iterator to the last position
	 * @return the last element
	 */
	public E last();

}
