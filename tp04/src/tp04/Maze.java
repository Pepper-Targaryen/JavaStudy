package tp04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tp04.MazeReadingException;
//import java.util.ArrayList;

public class Maze implements GraphInterface {
	private int length;
	private int width;
	private Box[][] labyrinthe;

	public Maze(int a, int b) {
		length = a;
		width = b;
		labyrinthe = new Box[a][b];
		// to be continued
	}

	/*public ArrayList<VertexInterface> getAllVertices() {
		// return all the boxes

		ArrayList<VertexInterface> temp = new ArrayList<VertexInterface>();

		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++)
				temp.add(labyrinthe.get(i).get(j));
		}

		return temp;
	}*/

	/*public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex) {
		// return all the boxes that this box has access to
		ArrayList<VertexInterface> temp = new ArrayList<VertexInterface>();

		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++)
				if (matrixAjascente[i][j] == 1)
					temp.add(labyrinthe.get(i).get(j));
		}
		return temp;
	}*/

	public final void initFromTextFile(String fileName) throws MazeReadingException{
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(new File(fileName)));
			//success of reading the file
			
			int line = 0;
			for(String x = in.readLine(); x != null; x = in.readLine()) {
				//Check whether there is too many lines
				if(line>length) {
					try {
				        in.close();
				    } catch (IOException e) {
				        e.printStackTrace();
				    }
					throw new MazeReadingException(fileName, line, "This line has overpassed the length of the maze.");
				}
				
				
				int lengthOfLine = x.length();
				
				//Check whether there is too many letters in each line
				if(lengthOfLine>width) {
					try {
				        in.close();
				    } catch (IOException e) {
				        e.printStackTrace();
				    }
					throw new MazeReadingException(fileName, line, "Line " + line +" has overpassed the width of the maze.");
				}
				
				for(int i=0;i<lengthOfLine;i++) {
					switch(x.charAt(i)) {
					case 'E': labyrinthe[line][i]= new EBox(this, line, i);
								break;
					case 'D': labyrinthe[line][i]= new DBox(this, line, i);
								break;
					case 'W': labyrinthe[line][i]= new WBox(this, line, i);
								break;
					case 'A': labyrinthe[line][i]= new ABox(this, line, i);
								break;
					}
				}
				line++;
			}
			
		}
		catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        in.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
	}
	public final void saveToTextFile(String fileName) {
		File newFile = new File("data/"+fileName+".txt");
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(newFile);
			for(int i = 0;i<length;i++) {
				for(int j=0;j<width;j++) {
					pw.print(labyrinthe[i][j].getLabel());
				}
				pw.println();
			}
				
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				pw.close();
			}catch(Exception e) {}
		}	
	}
	/*public String TT() {
		return labyrinthe[1][0].getLabel();
	}*/
	/*public int getWeight(VertexInterface src,VertexInterface dst){
		if(matrixAjascente[])
		return 1;
	}*/
}