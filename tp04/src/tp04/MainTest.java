package tp04;

//import maze.Maze;
//import maze.MazeReadingException;

import ui.*;

public class MainTest {

	public static void main(String[] args) {
		// // TODO Auto-generated method stub
		// Maze testMaze = new Maze(10, 10);
		// try{
		// testMaze.initFromTextFile("data/labyrinthe.txt");
		// }
		// catch(MazeReadingException e) {
		// System.out.println(e.getMessage());
		// e.printStackTrace();
		// }
		// testMaze.saveToTextFile("labyrinthe2");
		new DrawingApp();
	}

}
