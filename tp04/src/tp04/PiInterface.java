package tp04;

public interface PiInterface {

	/** change the min distance between root and point i */
	public void setValue(VertexInterface i, int minDistance);
	
	/** get the min distance between root and point i */
	public void getValue(VertexInterface i);

}
