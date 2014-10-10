package edu.uprm.ece.icom4035.list;

public class LinkedListFactory<E> implements ListFactory<E> {

	@Override
	public List<E> newInstance(int capacity) {
		return new LinkedList<E>();
	}

	@Override
	public List<E> newInstance() {
		return new LinkedList<E>();
	}

}
