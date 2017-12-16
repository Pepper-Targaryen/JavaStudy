package tp04;

import java.util.ArrayList;

import dijkstra.*;
import maze.Maze;
import maze.MazeReadingException;

import ui.*;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Maze testMaze = new Maze(10, 10);
		try {
			testMaze.initFromTextFile("data/labyrinthe.txt");
		} catch (MazeReadingException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		ArrayList<VertexInterface> shortestPath = Dijkstra.dijkstra(testMaze, testMaze.getDBox()).getShortestPathTo(testMaze.getABox());
		for(VertexInterface vertex : shortestPath)
			System.out.println(vertex.getCoordonnate());
		//testMaze.saveToTextFile("labyrinthe2");
		//new DrawingApp();
	}

}
