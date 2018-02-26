package dijkstra;


public class Dijkstra {
	
	/**A value high enough which can be compared to another one*/
	private static final int infinite=1000;
	
	/**
	 * Dijkstra algorithm implementation
	 * @param g         graph
	 * @param r         root
	 * @param a         A set (examined vertices)
	 * @param pi        PI function (return the shortest distance from the root)
	 * @param previous  function which return previous vertex
	 * @return previous
	 */
	private static PreviousInterface dijkstra(GraphInterface g, VertexInterface r, ASetInterface a, PiInterface pi,
			PreviousInterface previous) {
		a.add(r);
		VertexInterface pivot = r;
		pi.setValue(r, 0);

		for (VertexInterface t : g.getAllVertices()) {
			if (t != r)
				//Those values can be subsequently used in comparisons.
				pi.setValue(t, infinite); 
		}
		int n = g.getAllVertices().size();
		for (int j = 1; j < n; j++) {
			int minPiValue = 0;

			for (VertexInterface y : g.getSuccessors(pivot)) {

				if (!a.contains(y)) {

					if (pi.getValue(pivot) + g.getWeight(pivot, y) < pi.getValue(y)) {
						pi.setValue(y, pi.getValue(pivot) + g.getWeight(pivot, y));
						previous.setValue(y, pivot);

					}
				}
			}

			// Initialization of the value
			for (VertexInterface t : g.getAllVertices()) {

				if (t.canGo() && !a.contains(t)) {
					minPiValue = pi.getValue(t);
					pivot = t;
					break;
				}
			}
			// find the minimum
			for (VertexInterface t : g.getAllVertices()) {

				if (t.canGo() && !a.contains(t) && pi.getValue(t) < minPiValue) {
					minPiValue = pi.getValue(t);
					pivot = t;
				}
			}
			a.add(pivot);
		}
		return previous;
	}
	

	/**
	 * Dijkstra algorithm launcher
	 * @param g  graph
	 * @param r  root
	 * @return previous function
	 */
	public static PreviousInterface dijkstra(GraphInterface g, VertexInterface r) {
		ASet a = new ASet();
		Pi pi = new Pi(g.getAllVertices().size());
		Previous previous = new Previous(g.getAllVertices().size());
		return dijkstra(g, r, a, pi, previous);
	}

}
