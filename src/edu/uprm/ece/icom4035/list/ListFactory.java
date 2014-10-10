package edu.uprm.ece.icom4035.list;

public interface ListFactory<E> {
		
	
	public List<E> newInstance(int capacity);
	
	public List<E> newInstance();
	

}
