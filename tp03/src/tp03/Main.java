package tp03;
import structures.Heap;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Heap wordsHeap = new Heap(args);
		String[] temp= wordsHeap.sort();
		for(String a:temp) System.out.println(a);
	}

}
