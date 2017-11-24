package dijkstra;

public class Dijkstra {

	private PreviousInterface dijkstra(GraphInterface g, VertexInterface r,
			ASetInterface a, PiInterface pi, PreviousInterface previous) {
		a.add(r);
		VertexInterface pivot = r;
		pi.setValue(r, 0);
		
		for(VertexInterface t : g.getAllVertices()){
			//infinity = 1000
			if(t!=r) pi.setValue(t, 1000);
		}
		int n=g.getAllVertices().size();
		for(int j=1;j<n;j++){
			int minPiValue=0;
			
			for(VertexInterface y : g.getSuccessors(pivot)){
				
				if(!a.contains(y)){
					
					if(pi.getValue(pivot)+g.getWeight(pivot, y)<pi.getValue(y)){
						pi.setValue(y, pi.getValue(pivot)+g.getWeight(pivot, y));
						previous.setValue(y, pivot);
						
					}
				}
			}
			
			//Initialisation of the value
			for(VertexInterface t : g.getAllVertices()){
				
				if(!a.contains(t)) {
					minPiValue=pi.getValue(t);
					break;
				}
			}
			//find the minimum
			for(VertexInterface t : g.getAllVertices()){
				
				if(!a.contains(t) && pi.getValue(t)<minPiValue) {
					minPiValue=pi.getValue(t);
					pivot=t;
				}
			}
			a.add(pivot);
		}
		return previous;
	}
/*
	public static PreviousInterface dijkstra(GraphInterface g, VertexInterface r){

	}*/
}
