/** 
 * Barbarians: Nathan Kangas, Jonathan Franco, Douglas Shaffer
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2014
 *
 * Redistribution and use with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - the use is for academic purpose only
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Neither the name of Brahma Dathan or Sarnath Ramnath
 *     may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * The authors do not make any claims regarding the correctness of the code in this module
 * and are not responsible for any loss or damage resulting from its use.
 * 
 * This class serves as a generic collection class for use in the theater.
 * 
 * @param <T> type of item
 * @param <K> key of item
 */

package theater;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class TheaterPatronList <T extends Matchable<K>, K> implements Serializable {
	//change to cause push
	private List<T> elements = new ArrayList<T>();
	
	/**
	 * Adds an item to the list.
	 * @param item - the item to be added
	 * @return true iff the item could be added
	 */
	public boolean add(T type){
		return elements.add(type);
	}
	
	/**
	 * Removes the item from the list
	 * @param item - the item to be removed
	 * @return true iff the item could be removed
	 */
	public boolean remove(K value){
		T element = search(value);
		if (element == null){
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * Returns an iterator for the collection
	 * @return iterator for the collection
	 */
	public List<T> getList(){
		return elements;
	}
	
	/**
	 * Checks whether an item with a given id exists. 
	 * @param key - the id of the item
	 * @return the item iff the item exists	 * 
	 */
	public T search(K value){
		for (T element: elements){
			if (element.matches(value)){
				return element;
			}
		}
		return null;
	}

}
