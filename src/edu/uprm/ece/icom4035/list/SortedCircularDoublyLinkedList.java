package edu.uprm.ece.icom4035.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Edwin O. Badillo Mendez
 * @version 1.0
 */
public class SortedCircularDoublyLinkedList<E extends Comparable<E>> implements
		SortedList<E> {

	// Private Inner Classes

	/**
	 * Inner Class for Nodes
	 * 
	 * @author Edwin O. Badillo
	 * 
	 */
	private class Node {
		private E value;
		private Node next;
		private Node prev;

		public E getValue() {
			return value;
		}

		public void setValue(E value) {
			this.value = value;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public Node getPrev() {
			return prev;
		}

		public void setPrev(Node prev) {
			this.prev = prev;
		}

	}

	/**
	 * Inner Class for iterating through List
	 * 
	 * @author Edwin O. Badillo Mendez
	 */
	private class ListIterator implements Iterator<E> {

		// Private Fields
		private Node nextNode;

		/**
		 * Default Constructor for the List Iterator
		 */
		public ListIterator() {
			this.nextNode = header.getNext();
		}

		/**
		 * Constructor for the List Iterator when given an Index where to start
		 * iterating from
		 * 
		 * @param index
		 *            Index in which to start iterating
		 */
		public ListIterator(int index) {
			if ((index < 0) || (index > currentSize)) {
				throw new IndexOutOfBoundsException();
			}

			int counter = 0;
			// Making sure that we start iterating at the given index
			for (this.nextNode = header.getNext(); counter < index; this.nextNode = this.nextNode
					.getNext(), counter++)
				;

		}

		/**
		 * Checks if there is a Next Node available
		 */
		@Override
		public boolean hasNext() {
			return nextNode.getValue() != null;
		}

		/**
		 * Returns the next value if there is one
		 * 
		 * @return result
		 */
		@Override
		public E next() {
			if (hasNext()) {
				E result = this.nextNode.getValue();
				this.nextNode = this.nextNode.getNext();
				return result;
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();

		}

	}

	/**
	 * Inner Class for iterating backwards through List
	 * 
	 * @author Edwin O. Badillo Mendez
	 */
	private class ReverseListIterator implements ReverseIterator<E> {

		// Private Fields
		private Node prevNode;

		/**
		 * Default Constructor for the Reverse Iterator
		 */
		public ReverseListIterator() {
			this.prevNode = header.getPrev();
		}

		/**
		 * Constructor for the Reverse List Iterator when given an Index where
		 * to start iterating from
		 * 
		 * @param index
		 *            Index in which to start iterating
		 */
		public ReverseListIterator(int index) {

			int counter = currentSize;
			for (this.prevNode = header.getPrev(); counter > currentSize
					- index; this.prevNode = this.prevNode.getPrev(), counter--)
				;

		}

		/**
		 * Determines if there is a previous node available and returns a
		 * boolean
		 * 
		 */
		@Override
		public boolean hasPrevious() {
			return prevNode != header;
		}

		/**
		 * Returns the previous value if there is one
		 * 
		 * @return result
		 */
		@Override
		public E previous() {
			if (hasPrevious()) {
				E result = prevNode.getValue();
				prevNode = prevNode.getPrev();
				return result;
			} else {
				throw new NoSuchElementException();

			}
		}
	}

	// Private Fields
	Node header;
	int currentSize;

	/**
	 * Default Sorted Circular Doubly Linked List Constructor
	 * 
	 */
	public SortedCircularDoublyLinkedList() {
		this.header = new Node();
		this.header.setNext(header);
		this.header.setPrev(header);
		this.header.setValue(null);

		this.currentSize = 0;
	}

	/**
	 * Iterator Method
	 * 
	 * @return An instance of the list iterator
	 */
	@Override
	public Iterator<E> iterator() {
		return new ListIterator();
	}

	/**
	 * Add Method: Adds a new element to the list
	 * 
	 * @return Returns a boolean true if an element was added
	 */

	@Override
	public boolean add(E obj) {
		if (obj == null) {
			throw new IllegalArgumentException("Argument cannot be null");
		}
		Node newNode = new Node();
		for (Node temp = this.header.getNext(); temp.getValue() != null; temp = temp
				.getNext()) {

			if (obj.compareTo(temp.getValue()) < 0) {
				if (temp.getPrev().getValue() == (this.header.getValue())) {
					newNode.setValue(obj);
					newNode.setNext(temp);
					newNode.setPrev(this.header);
					this.header.setNext(newNode);
					temp.setPrev(newNode);
					this.currentSize++;
					return true;
				} else {

					newNode.setValue(obj);
					newNode.setNext(temp);
					newNode.setPrev(temp.getPrev());
					temp.getPrev().setNext(newNode);
					temp.setPrev(newNode);
					this.currentSize++;
					return true;
				}
			}
		}

		newNode.setValue(obj);
		newNode.setNext(this.header);
		newNode.setPrev(this.header.getPrev());
		this.header.getPrev().setNext(newNode);
		this.header.setPrev(newNode);
		this.currentSize++;
		return true;

	}

	/**
	 * Size Method
	 * 
	 * @return Returns the size of the list
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.currentSize;
	}

	/**
	 * Remove Method: Removes an element from the list
	 * 
	 * @param obj
	 *            Element to be removed
	 * @return Returns True if element was found and erased and false otherwise
	 */
	@Override
	public boolean remove(E obj) {
		if (obj == null) {
			throw new IllegalArgumentException("Parameter cannot be null");
		}

		for (Node temp = this.header.getNext(); temp.getValue() != null; temp = temp
				.getNext()) {
			if (obj.equals(temp.getValue())) {
				temp.getPrev().setNext(temp.getNext());
				temp.getNext().setPrev(temp.getPrev());
				temp.setNext(null);
				temp.setPrev(null);
				temp.setValue(null);
				currentSize--;
				return true;
			}
		}
		return false;
	}

	/**
	 * Remove Method: Removes an element at a given index from the list
	 * 
	 * @param index
	 *            Index where the element to be removed is found
	 * @return Returns True if element was found and erased and false otherwise
	 */
	@Override
	public boolean remove(int index) {
		if ((index < 0) || (index > this.currentSize)) {
			throw new IndexOutOfBoundsException();
		}
		Node temp = null;
		int counter = 0;
		for (temp = this.header.getNext(); counter < index; temp = temp
				.getNext(), counter++)
			;

		temp.getPrev().setNext(temp.getNext());
		temp.getNext().setPrev(temp.getPrev());
		temp.setNext(null);
		temp.setPrev(null);
		temp.setValue(null);
		this.currentSize--;
		return true;

	}

	/**
	 * Remove All Method: Removes all copies of a given element
	 * 
	 * @param Element
	 *            to be removed
	 * @return Number of copies removed
	 */
	@Override
	public int removeAll(E obj) {
		int counter = 0;
		while (this.remove(obj)) {
			counter++;
		}
		return counter;
	}

	/**
	 * First Method: Finds the value of the first element in the list
	 * 
	 * @return The value of the first element
	 */
	@Override
	public E first() {
		if (isEmpty()) {
			return null;
		}
		return this.header.getNext().getValue();
	}

	/**
	 * Last Method: Finds the value of the last element in the list
	 * 
	 * @return The value of the last element
	 */
	@Override
	public E last() {
		if (isEmpty()) {
			return null;
		}
		return this.header.getPrev().getValue();
	}

	/**
	 * Get Method: Used for getting the value of a given index in the list
	 * 
	 * @param index
	 *            Index to of element to retrieve
	 * @return The element at the given index
	 */
	@Override
	public E get(int index) {
		if ((index < 0) || (index > this.currentSize)) {
			throw new IndexOutOfBoundsException();
		}
		Node temp = null;
		int counter = 0;
		for (temp = this.header.getNext(); counter < index; temp = temp.getNext(), counter++)
			;
		return temp.getValue();
	}

	/**
	 * Clear Method: Clears the entire list
	 */
	@Override
	public void clear() {
		while (!this.isEmpty()) {
			this.remove(0);
		}
	}

	/**
	 * Contains Method: Verifies that a given element is in the list
	 * 
	 * @param e
	 *            Element to check
	 * @return Returns True if it is in the list and false otherwise
	 */
	@Override
	public boolean contains(E e) {
		for (Node temp = this.header.getNext(); temp.getValue() != null; temp = temp
				.getNext()) {
			if (temp.getValue().equals(e)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Is Empty Method: Verifies if the list is empty
	 * 
	 * @return Returns True if the list is empty and false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return this.currentSize == 0;
	}

	/**
	 * Iterator Method
	 * 
	 * @param index
	 *            Element to begin iterating at
	 * @return A new instance of the list iterator at the given element
	 */
	@Override
	public Iterator<E> iterator(int index) {
		return new ListIterator(index);
	}

	/**
	 * First Index Method: Used to determine the index of the first copy of an
	 * element
	 * 
	 * @param e
	 *            Element to check
	 * @return Returns the Index of the first copy of a given element
	 */
	@Override
	public int firstIndex(E e) {
		int counter = 0;
		for (Node temp = this.header.getNext(); temp.getValue() != null; temp = temp
				.getNext(), counter++) {
			if (e.equals(temp.getValue())) {
				return counter;
			}
		}

		return -1;
	}

	/**
	 * Last Index Method: Used to determine the index of the last copy of an
	 * element
	 * 
	 * @param e
	 *            Element to check
	 * @return Returns the Index of the last copy of a given element
	 */
	@Override
	public int lastIndex(E e) {
		int counter = this.currentSize - 1;
		for (Node temp = this.header.getPrev(); temp.getValue() != null; temp = temp
				.getPrev(), counter--) {
			if (e.equals(temp.getValue())) {
				return counter;
			}
		}

		return -1;
	}

	/**
	 * Reverse Iterator Method: Goes through the elements of the list backwards
	 * 
	 * @return A new instance of the reverse list iterator
	 */
	@Override
	public ReverseIterator<E> reverseIterator() {
		return new ReverseListIterator();
	}

	/**
	 * Reverse Iterator Method: Goes through the elements of the list backwards
	 * 
	 * @param index
	 *            Element to begin iterating at
	 * @return A new instance of the reverse list iterator begining at the given
	 *         element
	 */
	@Override
	public ReverseIterator<E> reverseIterator(int index) {
		return new ReverseListIterator(index);
	}

}