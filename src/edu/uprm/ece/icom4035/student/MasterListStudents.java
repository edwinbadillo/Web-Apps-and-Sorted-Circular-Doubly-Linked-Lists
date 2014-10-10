package edu.uprm.ece.icom4035.student;

import edu.uprm.ece.icom4035.list.SortedCircularDoublyLinkedList;
import edu.uprm.ece.icom4035.list.SortedList;

public class MasterListStudents {
	
	private static SortedList<Student> masterListStudents;
	
	public static SortedList<Student> getMasterListStudents(){
		
		if (masterListStudents == null){
			initialize();
		}
		return masterListStudents;
	}

	private static void initialize() {
		masterListStudents = new SortedCircularDoublyLinkedList<Student>();
		masterListStudents.add(new Student("123","Tom", "Li", "7874658900", 3.92, 21));
		masterListStudents.add(new Student("464","Ned", "Lee", "7878512323", 3.00, 21));
		masterListStudents.add(new Student("132","Amy", "Nell", "7877477777", 2.92, 23));
		masterListStudents.add(new Student("121","Apu", "Pol", "7878888888", 1.02, 18));
		masterListStudents.add(new Student("782","Li", "Li", "7879000011", 4.00, 18));
		masterListStudents.add(new Student("423","Xi", "Ral", "9393012020", 3.20, 25));
		masterListStudents.add(new Student("411","Bob", "Son", "7876001010", 2.48, 22));
		masterListStudents.add(new Student("923","Jon", "Rin", "7879515555", 0.43, 19));
	
	}
	
	

}
