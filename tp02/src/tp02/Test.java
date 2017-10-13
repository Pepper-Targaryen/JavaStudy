package tp02;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Promotion salleC130 = new Promotion();
		//System.out.print("Bellot".compareTo("Bellot"));
		salleC130.add("Patrick", "Bellot");
		salleC130.add("Cuong", "Bellot");
		salleC130.add("Leila", "Bellot");
		salleC130.add("Nam", "Bellot");
		salleC130.quickSort();
		salleC130.printToConsole();
	}

}
