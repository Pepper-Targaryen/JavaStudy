package tp02;

import java.util.ArrayList;

public class Promotion {
	private ArrayList<Student> studentlist;

	public Promotion() {
		this.studentlist = new ArrayList<Student>();
	}
	
	public int newId() {
		int studentSize = this.studentlist.size();
		if (studentSize == 0) return 0;
		
		int newId = this.studentlist.get(0).getId();
		
		for(int i = 1; i<studentSize; i++){
			
			if (this.studentlist.get(i).getId()>newId) newId = this.studentlist.get(i).getId();
		}
		
		return newId + 1;
	}
	
	public int add(String firstName, String lastName){
		int newId = this.newId();
		Student newStudent = new Student(newId, firstName, lastName);
		studentlist.add(newStudent);
		
		return newId;
	}
	
	public void printToConsole(){
		int studentSize = this.studentlist.size();
		for(int i=0; i<studentSize; i++) System.out.println(this.studentlist.get(i));
	}
	
	private void swap(int i, int j){
		Student t = studentlist.get(i);
		studentlist.set(i, studentlist.get(j));
		studentlist.set(j, t);
	}
	
	public void selectionSort(){
		int end = studentlist.size();
		for(int i=0; i<end-1; i++){
			int t = i;
			for(int j=i+1; j<end; j++){
				if(studentlist.get(t).compareTo(studentlist.get(j)) > 0) t = j;
			}
			if(t != i) this.swap(i, t);
		}
	}
	
	private int partition(int g, int d){
		Student key = studentlist.get(g);
		int i = g + 1;
		int j = d;
		while(i <= j){
			while(i<=j && key.compareTo(studentlist.get(i)) > 0) i++;
			while(j>=i && key.compareTo(studentlist.get(j)) < 0) j--;
			if(i<j){
				this.swap(i, j);
				i++;
				j--;
			}
		}
		this.swap(g, j);
		return j;
	}
	
	private void quickSort(int g, int d){
		if(g<d){
			int j = partition(g, d);
			quickSort(g, j - 1);
			quickSort(j + 1, d);
		}		
	}
	public void quickSort(){
		quickSort(0, studentlist.size()-1);
	}
}
