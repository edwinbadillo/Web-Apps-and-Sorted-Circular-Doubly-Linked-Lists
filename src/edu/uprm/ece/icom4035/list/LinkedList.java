package edu.uprm.ece.icom4035.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements List<E> {
	
	private class Node {
		// private field
		private E value;
		
		private Node next;

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
		
	}

	// private fields
	
	// number of elements
	private int currentSize;
	// header of list
	private Node header;
	
	public LinkedList(){
		this.currentSize = 0;
		this.header = new Node();
	}
	
	@Override
	public Iterator<E> iterator() {
		return new ListIterator<E>();
	}

	@Override
	public void add(E obj) {
		if (obj == null){
			throw new IllegalArgumentException("Inserted value cannot be null.");
		}
		Node temp;
		//for (temp = header; temp.getNext() != null; temp = temp.getNext());
		temp = header;
		while (temp.getNext() != null){
			temp = temp.getNext();
		}
		Node newNode = new Node();
		newNode.setValue(obj);
		temp.setNext(newNode);
		this.currentSize++;
	}

	@Override
	public void add(int index, E obj) {
		if (obj == null){
			throw new IllegalArgumentException("Inserted value cannot be null.");
		}

		if (index <0 || index > this.size()){
			throw new IllegalArgumentException("Index is out of bounds.");
		}
		else if (index == this.size()){
			this.add(obj);
		}
		else if (index == 0){
			Node newNode = new Node();
			newNode.setValue(obj);
			newNode.setNext(header.getNext());
			header.setNext(newNode);
			this.currentSize++;
		}
		else {
			int counter = 0;
			// Error Here
			 //Node temp = header;
			// Correct way
			Node temp = header.getNext();
			while(counter != (index -1)){
				temp = temp.getNext();
				++counter;
			}
			Node newNode = new Node(); // create space
			newNode.setValue(obj); // store value
			newNode.setNext(temp.getNext()); // Supplant old value as index
			temp.setNext(newNode); // let predecessor point to me
			++this.currentSize;
		}

	}

	@Override
	public boolean remove(E obj) {
		if (obj == null){
			throw new IllegalArgumentException("Inserted value cannot be null.");
		}
	
		Node temp1 = null, temp2 = null;
		for (temp1 = header.getNext(), temp2 = header; 
			temp1 != null; 
			temp2 = temp1, temp1 = temp1.getNext()){
			if (temp1.getValue().equals(obj)){
				temp2.setNext(temp1.getNext()); // take temp1 out of list
				temp1.setValue(null);
				temp1.setNext(null);
				temp1 = null;
				this.currentSize--;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean remove(int index) {
		if ((index < 0) || (index >= this.size())){
			throw new IllegalArgumentException("index is out of bounds.");
		}
		else {
			int counter = 0;
			Node temp1 = null, temp2 = null;
			
			for (temp1 = header.getNext(), temp2 = header; temp1 != null; 
				temp2 = temp1, temp1 = temp1.getNext(), counter++){
				if (counter == index){
					temp2.setNext(temp1.getNext());
					temp1.setValue(null);
					temp1.setNext(null);
					temp1 = null;
					--currentSize;
					// Error here
					break;
				}
			}
			return true;
		}
	}

	@Override
	public int removeAll(E obj) {
		int counter = 0;
		
		while (remove(obj)){
			++counter;
		}
		return counter;
	}

	@Override
	public E get(int index) {
		if ((index < 0 ) || (index >= this.size())){
			throw new IllegalArgumentException("index is out of bounds");
		}
		else {
			int counter = 0;
			E result = null;
			for (Node temp = header.getNext(); temp != null; temp = temp.getNext(), counter++){
				if (counter == index){
					result = temp.getValue();
				}
			}
			return result;
		}
	}

	@Override
	public E set(int index, E obj) {
		if (obj == null){
			throw new IllegalArgumentException("obj cannot be null.");
		}
		if ((index < 0 ) || (index >= this.size())){
			throw new IllegalArgumentException("index is out of bounds");
		}
		else {
			int counter = 0;
			E result = null;
			for (Node temp = header.getNext(); temp != null; temp = temp.getNext(), counter++){
				if (counter == index){
					result = temp.getValue();
					temp.setValue(obj);
				}
			}
			return result;
		}
	}

	@Override
	public E first() {
		if (this.isEmpty()){
			return null;
		}
		else {
			return this.get(0);
		}
	}

	@Override
	public E last() {
		if (this.isEmpty()){
			return null;
		}
		else {
			return this.get(this.currentSize - 1);
		}
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public boolean contains(E obj) {
		if (obj == null){
			throw new IllegalArgumentException("Argument cannot be null.");
		}
		else {
			for (Node temp = header.getNext(); temp!= null; temp = temp.getNext()){
				if (temp.getValue().equals(obj)){
					return true;
				}
			}
			return false;
		}
	}

	@Override
	public int firstIndex(E obj) {
		if (obj == null){
			throw new IllegalArgumentException("Argument cannot be null.");
		}
		else {
			int counter = 0;
			for (Node temp = header.getNext(); temp != null; temp = temp.getNext(), counter++){
				if (temp.getValue().equals(obj)){
					return counter;
				}
			}
			return -1;
		}
	}

	@Override
	public int lastIndex(E obj) {
		if (obj == null){
			throw new IllegalArgumentException("Argument cannot be null.");
		}
		else {
			int counter = 0;
			int lastSeen = -1;
			for (Node temp = header.getNext(); temp != null; temp = temp.getNext(), counter++){
				if (temp.getValue().equals(obj)){
					lastSeen = counter;
				}
			}
			return lastSeen;
		}
	}

	@Override
	public void clear() {
		while (!this.isEmpty()){
			remove(0);
		}
	}

	@Override
	public int size() {
		return this.currentSize;
	}

	private class ListIterator<E> implements Iterator<E>{
		private Node nextNode;
		
		public ListIterator(){
			this.nextNode = header.getNext();
		}

		@Override
		public boolean hasNext() {
			return this.nextNode != null;
		}

		@Override
		public E next() {
			if (hasNext()){
				E result = (E) this.nextNode.getValue();
				this.nextNode = this.nextNode.getNext();
				return result;
			}
			else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
