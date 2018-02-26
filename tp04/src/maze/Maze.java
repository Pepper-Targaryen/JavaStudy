package maze;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;


import dijkstra.GraphInterface;
import dijkstra.VertexInterface;
import exceptions.MazeReadingException;

/**A class for the maze considered as a graph.*/
public class Maze implements GraphInterface {
	
	/**1000 as infinite for the comparisons in Dijkstra algorithm*/
	public static final int infinite =1000;
	
	private int length;
	private int width;
	private Box[][] labyrinth;
	private ArrayList<DBox> src = new ArrayList<DBox>();
	private ArrayList<ABox> dst = new ArrayList<ABox>();
	
	
	/**maze for Open item */
	public Maze(){
		
	}
	
	/**Builds a solvable maze with random boxes using Kruskal's algorithm.  
	 * @param a  The number of rows.
	 * @param b  The number of columns.
	 */
	public Maze(int a, int b) {
		length    = a;
		width     = b;
		labyrinth = new Box[length][width];
		int	idx   = 0;
		//Cell is associated to a number of connected component. 
		Hashtable<VertexInterface, Integer> sets = new Hashtable<VertexInterface, Integer>();
		//Creates a maze where two sells are split by a wall.
		for(int y = 0; y < width; y++){
			for(int x = 0; x < length; x++){
				if(x%2 == 0 && y%2 == 0) //... and EBox 1 on two
				{
					labyrinth[x][y]=new EBox(this, x, y);
					//Each cells has its own connected component at the beginning.
					sets.put(labyrinth[x][y], new Integer(idx++));
				}
				else{
					labyrinth[x][y]=new WBox(this, x, y);
				}
			}
		}
		kruskal(sets);
		randomDABoxes(a,b);
		
	}
	
	public void randomDABoxes(int a,int b){
		int srcX = 0, srcY = 0, dstX = 0, dstY = 0;
		Random random = new Random();
		do {
			srcX = random.nextInt(a);
			srcY = random.nextInt(b);
			dstX = random.nextInt(a);
			dstY = random.nextInt(b);
		} while (srcX == dstX && srcY == dstY);

		// Generate a random destination and a random source
		dst.add(new ABox(this, dstX, dstY));
		labyrinth[dstX][dstY] = dst.get(0);

		src.add(new DBox(this, srcX, srcY));
		labyrinth[srcX][srcY] = src.get(0);

	}
	
	/** Get the WBox connected to at least one EBox
	 * Used into Kruskal's algorithm (for the constructor)
	 * @return an array list of WBox possibly removable
	 */
	public ArrayList<VertexInterface> getRemovableWalls(){
		ArrayList<VertexInterface> removableWalls = new ArrayList<VertexInterface>();
		
		for(int x = 0; x < length; x++) {
			for(int y = 0; y < width; y++) {
				if(!labyrinth[x][y].canGo()) {
					ArrayList<VertexInterface> successors = getSuccessors(labyrinth[x][y]);
					if (successors.size()>0){
						removableWalls.add(labyrinth[x][y]);
					}
						
				}
			}
		}
		return(removableWalls);
	}
	
	/** Get vertex successors one on two
	 * @param v source vertex
	 * @return  the successors list
	 */
	public ArrayList<VertexInterface> getMazeCreatorSuccessors(VertexInterface v)
	{
		Box box = (Box)v;
		int x   = box.getX();
		int y   = box.getY();
		ArrayList<VertexInterface> successors = new ArrayList<VertexInterface>();
		
		if (x < length - 2)
			successors.add((VertexInterface)labyrinth[x+2][y]);
		if (x > 1)
			successors.add((VertexInterface)labyrinth[x-2][y]);
		if (y < width - 2)
			successors.add((VertexInterface)labyrinth[x][y+2]);
		if (y > 1)
			successors.add((VertexInterface)labyrinth[x][y-2]);
		return(successors);
	}
	
	/** Checks if a Box is removable for the constructor
	 * i.e. if its successors have different values
	 * @param vertex VertexInterface  potentially removable vertex
	 * @return boolean  is removable ?
	 */
	private boolean  isRemovable(VertexInterface vertex, Hashtable<VertexInterface, Integer> sets)
	{
		ArrayList<VertexInterface> successors = this.getSuccessors(vertex);
		
		if(successors.size() < 2)
			return(true);
		return(sets.get(successors.get(0))!= sets.get(successors.get(1)));
		
	}
	
	/** Chooses a wall to remove randomly into a removable walls list(for the constructor)
	 * @param removableWalls contains removable walls list 
	 * @param sets an hash table that divides cells into sets of cells 
	 * @return VertexInterface  vertex to remove
	 */
	private VertexInterface getAleaRemovableWall(ArrayList<VertexInterface> removableWalls,Hashtable<VertexInterface, Integer> sets)
	{
		VertexInterface 	vertex = null;
		int 				nbAlea = 0;
		
		do{
			if(vertex != null)
				removableWalls.remove(vertex);
			if(removableWalls.size() == 0)
				return(null);
			Random random = new Random();
			nbAlea = random.nextInt(removableWalls.size());
			vertex = removableWalls.get(nbAlea);
		}
		while(!isRemovable(vertex,sets));
		removableWalls.remove(vertex);
		return(vertex);
	}
	
	
	/** Merges two sets, one contains v1 and the other v2(for the constructor)
	 * @param v1 VertexInterface : belongs to first set
	 * @param v2 VertexInterface : belongs to second set
	 * @param sets the hash table representing the different sets
	 */
	private void 	merge(VertexInterface v1, VertexInterface v2,Hashtable<VertexInterface, Integer> sets)
	{
		Integer value      = sets.get(v1);
		Integer falseValue = sets.get(v2);
		ArrayList<VertexInterface> successors = getMazeCreatorSuccessors(v2);
		sets.replace(v2, value);
		for(VertexInterface successor : successors)
			if(sets.get(successor) == falseValue)
				merge(v1, successor,sets);
	}
	
	/**Randomized Kruskal's algorithm for the constructor
	 * @param sets the hash table representing the different sets
	 */
	public void kruskal(Hashtable<VertexInterface,Integer> sets){
		//Gives a first list of walls removable.
				ArrayList<VertexInterface> removableWalls = getRemovableWalls();
				ArrayList<VertexInterface> nearReachableBoxes;
				VertexInterface 	wall = null;
				
				while(removableWalls.size() > 0){
					//Get a random removable wall  
					wall = getAleaRemovableWall(removableWalls,sets);
					if(wall == null)
						continue;
					labyrinth[((Box)wall).getX()][((Box)wall).getY()]=new EBox(this, ((Box)wall).getX(), ((Box)wall).getY());
					nearReachableBoxes = this.getSuccessors(wall);
					if(nearReachableBoxes.size() == 2){ // no situate on border
						merge(nearReachableBoxes.get(0), nearReachableBoxes.get(1),sets);
						}
					
				}
		
	}
	/** Gets the source boxes edited in the frame.
	 * @return an array list of DBox 
	 */
	public ArrayList<DBox> getSrc() {
		return src;
	}
	
	/** Sets the source boxes in the maze.
	 * @param src - an array list of DBox
	 */
	public void setSrc(ArrayList<DBox> src) {
		this.src = src;
	}

	/** Gets the destination boxes edited in the frame.
	 * @return an array list of the destination boxes
	 */
	public ArrayList<ABox> getDst() {
		return dst;
	}

	/** Sets the destination boxes in the maze.
	 * @param dst  an array list of ABox
	 */
	public void setDst(ArrayList<ABox> dst) {
		this.dst = dst;
	}

	/**Gets the width of the maze
	 * @return the width of the maze
	 */
	public int getWidth() {
		return width;
	}
	
	/**Gets the length of the maze.
	 * @return the length of the maze
	  */
	public int getLength() {
		return length;
	}
	
	/** Gets the box indicated by the coordinates.
	 * @param x the line of the box
	 * @param y the column of the box
	 * @return the box at the coordinates
	 */
	public Box getBox(int x, int y) {
		return labyrinth[x][y];
	}
	
	
	/** Sets the box indicated by the coordinates.
	 * @param x      : the line of the box
	 * @param y      : the column of the box
	 * @param newBox : the new box at this position
	 */
	public void setBox(int x, int y, Box newBox) {
		labyrinth[x][y] = newBox;
	}


	/**Return all the boxes.
	 * @return 	an array list of the boxes 
	 */
	public ArrayList<VertexInterface> getAllVertices() {
		ArrayList<VertexInterface> temp = new ArrayList<VertexInterface>();

		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++)
				temp.add(labyrinth[i][j]);
		}

		return temp;
	}

	
	/**Returns all the boxes that this box has access to.
	 * @param vertex 
	 * @return  an array list of its successors
	 */
	public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex) {
		Box t = (Box) vertex; // transform into type Box
		

		ArrayList<VertexInterface> temp = new ArrayList<VertexInterface>();

		int i = t.getX(), j = t.getY();

		// There is only four possibilities
		if (i - 1 >= 0 && labyrinth[i - 1][j].canGo())
			temp.add(labyrinth[i - 1][j]);
		if (i + 1 < length && labyrinth[i + 1][j].canGo())
			temp.add(labyrinth[i + 1][j]);
		if (j - 1 >= 0 && labyrinth[i][j - 1].canGo())
			temp.add(labyrinth[i][j - 1]);
		if (j + 1 < width && labyrinth[i][j + 1].canGo())
			temp.add(labyrinth[i][j + 1]);

		return temp;
	}
	
	/**Gets the weight between two vertex in a maze.
	 * @param src a vertex
	 * @param dst another vertex
	 * @return 1 if there are linked, otherwise infinite=1000
	 */
	public int getWeight(VertexInterface src, VertexInterface dst) {
		Box a = (Box) src, b = (Box) dst; // transform into type Box

		if (getSuccessors(a).contains(b))
			return 1;
		else
			return infinite; 

	}
	
	/**Initializes a maze from a text file. 
	 *@param  fileName               the file name of the text file
	 *@throws MazeReadingException   if the file is not equivalent to a maze 
	 */
	public final void initFromTextFile(String fileName) throws MazeReadingException {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(new File(fileName)));
			width = in.readLine().length();

			if (width == 0)
				throw new MazeReadingException(fileName, 0, "The file or the first line is empty");

			int line = 1;
			while (in.readLine() != null)
				line++;
			length = line;
			labyrinth = new Box[length][width];
			// initialize the length, width, Boxes

			in = new BufferedReader(new FileReader(new File(fileName)));
			// reset the BufferedReader and success of reading the file

			line = 0;
			for (String x = in.readLine(); x != null; x = in.readLine()) {

				if (width != x.length()) {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					throw new MazeReadingException(fileName, line,
							"The width of line " + (line + 1) + " is different of the first line.");
				}

				for (int i = 0; i < width; i++) {
					switch (x.charAt(i)) {
					case 'E':
						// Empty
						labyrinth[line][i] = new EBox(this, line, i);
						break;
					case 'D':
						// Depart == Source
						labyrinth[line][i] = new DBox(this, line, i);
						src.add((DBox) labyrinth[line][i]);
						break;
					case 'W':
						// Wall
						labyrinth[line][i] = new WBox(this, line, i);
						break;
					case 'A':
						// Arrive == Destination
						labyrinth[line][i] = new ABox(this, line, i);
						dst.add((ABox) labyrinth[line][i]);
						break;
					}
				}
				line++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**Writes a text file that describes the maze.
	 *@param filePath  the name of the file saved
	 */
	public final void saveToTextFile(String filePath) {
		File newFile = new File(filePath + "\\Customized_Labyrinth.txt");
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(newFile);
			for (int i = 0; i < length; i++) {
				for (int j = 0; j < width; j++) {
					pw.print(labyrinth[i][j].getLabel());
				}
				pw.println();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pw.close();
			} catch (Exception e) {
			}
		}
	}
	
	

}