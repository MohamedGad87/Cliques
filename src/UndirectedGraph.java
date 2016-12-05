import java.util.*;

public class UndirectedGraph {
	public ArrayList<Integer> ListOfNodes = new ArrayList<Integer>();
	private ArrayList<edge> ListOfEdges = new ArrayList<edge> () ;
	//private ArrayList<ArrayList<Integer>>  PowerGraph = new ArrayList<ArrayList<Integer>>();
	public ArrayList<ArrayList<Integer>>  Cliques = new ArrayList<ArrayList<Integer>> () ;
	public ArrayList<ArrayList<Integer>>  Cliques1 = new ArrayList<ArrayList<Integer>> () ;
	public int numberOfNodes;
	
	public class edge{
		private int x;
		private int y;
		edge(int a, int b){
			this.x=a;
			this.y=b;
			}	
		public int getX() {return this.x;}
		public int getY() {return this.y;}
		public void displayEdge(){
			System.out.print("["+this.x+"---"+this.y+"]");
		}
		}
	
	public void copyEdgeList(ArrayList<edge> Source, ArrayList<edge> Dest ){
		for(edge anEdge : Source){
			edge newEdge= new edge(anEdge.getX(),anEdge.getY());
			Dest.add(newEdge);
			}
		}
	public static void displayListOfEdges(ArrayList<edge> list){
		System.out.println("________________________________");
		for(edge anEdge: list){
			anEdge.displayEdge();
			System.out.println();
			}
		System.out.println("________________________________");
		}

	// constructor initialize an undirected graph, n is the number of nodes
	public UndirectedGraph(int n){
		numberOfNodes = n;
		for( int i =0; i<n; i++){
			ListOfNodes.add(i);
			}
	}

	// check if the given node id is out of bounds
	private boolean outOfBounds(int nidx){
		if(0>nidx || nidx>this.numberOfNodes-1)
			return true;
		return false;
	}

	// set an edge (n1,n2).
	// Since this is an undirected graph, (n2,n1) is also set to one
	public void setEdge(int n1, int n2){
		if(n1 == n2)
			return;
		edge newEdge;
		if(n1<n2)
			 newEdge  = new edge(n1, n2);
		else
			 newEdge = new edge(n2, n1); 
		for( edge anEdge : ListOfEdges){
				if(newEdge.getX() == anEdge.getX() && newEdge.getY() == anEdge.getY())
					return;
			}
		ListOfEdges.add(newEdge);

	}
	
	public boolean edgeExists(int n1, int n2){

		edge newEdge;
		if(n1<n2)
			 newEdge  = new edge(n1, n2);
		else
			 newEdge = new edge(n2, n1); 
		for( edge anEdge : ListOfEdges){
				if(newEdge.getX() == anEdge.getX() && newEdge.getY() == anEdge.getY())
					return true;
			}
		return false;
		
		}

	// print an output soft clique in one line
	 public void printClique(ArrayList<Integer> nlist){
	   for(int i = 0; i < nlist.size(); ++i)
	     System.out.print(nlist.get(i) + " ");
	   System.out.println("");
	 }

	 // compute maximal soft clique
	 // cliquesize_lower_bd: k
	 // num_missing_edges: l
	 public void findMaxSoftClique(int cliquesize_lower_bd, int num_missing_edges){
		 if(num_missing_edges ==0)
			 calculateMaxClick(cliquesize_lower_bd);
		 else
		 	{
			ArrayList<edge> copyOfEdges = new ArrayList();
			copyEdgeList(this.ListOfEdges, copyOfEdges); // Source, Destination
			//this.displayListOfEdges(copyOfEdges);
			
			for(int i=0; i<this.numberOfNodes; ++i){
				for(int j=i+1; j<this.numberOfNodes; ++j){
					if(edgeExists(i, j))
						continue;
					this.setEdge(i, j);	
					// Add the first additional edge
					int numEdgesAdded = 1;
					boolean addedMore = false;
					
					ArrayList<edge> copy2 = new ArrayList();
					copyEdgeList(this.ListOfEdges, copy2);
					/*
					for(int l=1; l<num_missing_edges; ++l){
						for(int a = i; a<this.numberOfNodes; ++a){
							for(int b = j; j<this.numberOfNodes; ++b){
								if( !(edgeExists(a, b)) ){
									this.setEdge(a, b);
									numEdgesAdded++;
									if(numEdgesAdded == num_missing_edges){
										calculateMaxClick(cliquesize_lower_bd);
										this.displayListOfEdges(this.ListOfEdges);
										copyEdgeList(this.ListOfEdges, copy2);
										numEdgesAdded = 1;
										addedMore = true;
										}
								}
								}
							}
						}
					*/
					//this.displayListOfEdges(this.ListOfEdges);

					if(  numEdgesAdded == num_missing_edges ){
						calculateMaxClick(cliquesize_lower_bd);
						}
					this.ListOfEdges.clear();
					for (ArrayList<Integer> subList : this.Cliques) {

							Cliques1.add(subList);
						//System.out.println();
						
						}
					//findmaxq(Cliques);////////
					this.Cliques.clear();
					copyEdgeList(copyOfEdges, this.ListOfEdges);	
					//this.displayListOfEdges(this.ListOfEdges);
					}
				}
			findmaxq(Cliques1);/////////////////////////
			for(int j=0; j<Cliques1.size(); ++j){
			for(int i=j+1;i<Cliques1.size();i++) {

							if(isSubset(Cliques1.get(j), Cliques1.get(i) )){
								Cliques1.remove(i);
								i--;
								//break;
								}
							}
						}

					}
					
				for (ArrayList<Integer> subList : Cliques1) {
					for (int z=0; z<subList.size(); z++) {
						System.out.print(subList.get(z)+", ");
						//Cliques1.add(subList);
						}
					System.out.println();
					
					}
				
				
			for(int i = 0; i<this.Cliques.size(); ++i){			// Eliminate "Cliques" that are subgraphs of a bigger "Clique"
				//System.out.println("i = "+i+", Size: "+this.Cliques.size());
				for(int j = 0; j<this.Cliques.size(); ++j){
					//System.out.println("j = "+j+", Size: "+this.Cliques.size());
					if(Cliques.get(j).size()>Cliques.get(i).size()){
						if(isSubset(Cliques.get(j), Cliques.get(i)) || (isEqual(Cliques.get(j), Cliques.get(i)) )){
							Cliques.remove(i);
							i--;
							break;
							}
						}
					}
				
				}
			
			}
		 
	 
	 
	public void findmaxq(ArrayList<ArrayList<Integer>> big ){
		ArrayList<Integer> max;
		max=big.get(0);
		for(int i=0;i<big.size()-1;i++){
			if(big.get(i).size()< big.get(i+1).size())
		     max=big.get(i+1);	
		}
		//System.out.println(big.indexOf(max));
		if(big.indexOf(max)!= 0)

		    Collections.swap(big,0,big.indexOf(max));
		//System.out.println(big.indexOf(max));
		
	}
	
	 
	 

	 public void calculateMaxClick(int k){
			ArrayList<ArrayList<Integer>>  ThePowerSet = powerset(ListOfNodes, k);
			for(ArrayList subList: ThePowerSet){			// Filter all subsets that are a "clique"
				boolean stillValid = true;
				for(int i=0; i<subList.size() && stillValid==true; i++){
					for(int j=i+1; j<subList.size() && stillValid==true; j++){
							int a = (Integer)subList.get(i);
							int b = (Integer)subList.get(j);
							if( !(Connected(a, b)) )
								stillValid = false;
						}
					}
				if(stillValid==true)	// The given subset is a "clique"
					Cliques.add(subList);
				}	
			
			Collections.reverse(this.Cliques);
			
			for(int i = 0; i<this.Cliques.size(); ++i){			// Eliminate "Cliques" that are subgraphs of a bigger "Clique"
				//System.out.println("i = "+i+", Size: "+this.Cliques.size());
				for(int j = i+1; j<this.Cliques.size(); ++j){
					//System.out.println("j = "+j+", Size: "+this.Cliques.size());
					if(Cliques.get(j).size()>Cliques.get(i).size()){
						if(isSubset(Cliques.get(j), Cliques.get(i))){
							Cliques.remove(i);
							i--;
							break;
							}
						}
					}
				
				}
			
		 
	 	}
	 
	 
	
	 
	 public boolean isSubset(ArrayList<Integer> big, ArrayList<Integer> small){
		 for(int i = 0; i < small.size(); ++i){
			 boolean found = false;
			 for(int j = 0; j<big.size(); ++j){
				if( (small.get(i) ==big.get(j) ) ){
					found = true;
					break;
					}
			 	}
			 if(found==false){
				return false; 
			 	}
		 }
		 return true;
		 
	 }
	 
	 public boolean isEqual(ArrayList<Integer> listA, ArrayList<Integer> listB){
		 for(int i = 0; i < listA.size(); ++i){
			 boolean found = false;
			 for(int j = 0; j<listB.size(); ++j){
				if( (listA.get(i) ==listB.get(j) ) ){
					found = true;
					break;
					}
			 	}
			 if(found==false){
				return false; 
			 	}
		 }
		 return true;
		 
	 }	 
	 
	 
	 
	 
	 
	 
	 
	 public boolean Connected(int a, int b){
		 edge tEdge = new edge(a, b);
		 for(edge anEdge: this.ListOfEdges){
			 if(anEdge.getX()==tEdge.getX() && anEdge.getY()==tEdge.getY())
				 return true;
		 	}
		 return false;
	 	}
	 
	 
	 

	 // compute maximal soft clique by using recursion 
	 // to compute all (k,l) soft cliques using recursion
	 // you should check the partial subset during generation 
	 // rather than checking the whole subset
	 // cliquesize_lower_bd: k
	 // num_missing_edges: l
	 public void findMaxSoftCliqueAdvanced(int cliquesize_lower_bd, int num_missing_edges){
		 
		 
	 }
	 
	public static ArrayList<ArrayList<Integer>> powerset(ArrayList<Integer>list, int k) {
		ArrayList<ArrayList<Integer>> ps = new ArrayList<ArrayList<Integer>>();
		  ps.add(new ArrayList<Integer>());   // add the empty set
		 
		  // for every item in the original list
		  for (Integer item : list) {
			  ArrayList<ArrayList<Integer>> newPs = new ArrayList<ArrayList<Integer>>();
		 
		    for (ArrayList<Integer> subset : ps) {
		      // copy all of the current powerset's subsets
		      newPs.add(subset);
		 
		      // plus the subsets appended with the current item
		      ArrayList<Integer> newSubset = new ArrayList<Integer>(subset);
		      newSubset.add(item);
		      newPs.add(newSubset);
		    }
		 
		    // powerset is now powerset of list.subList(0, list.indexOf(item)+1)
		    ps = newPs;
		  }
		Collections.reverse(ps);
		int size = list.size();
		ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
		for(int i = size; i>=k; i--){
			for(int j = 0; j<ps.size(); ++j){
				if(ps.get(j).size()==i)
				temp.add(ps.get(j));
				}
			}
		  return temp;
		}

	
	public static void main(String[] args) {
		UndirectedGraph ug1 = new UndirectedGraph(5);		
		ug1.setEdge(0, 1);
		ug1.setEdge(0, 3);
		ug1.setEdge(0, 4);
		ug1.setEdge(1, 4);
		ug1.setEdge(2, 3);
		ug1.setEdge(3, 4);
		
		ug1.findMaxSoftClique(2, 1);
		
		for (ArrayList<Integer> subList : ug1.Cliques) {
			for (int i=0; i<subList.size(); i++) {
				System.out.print(subList.get(i)+", ");
				}
			System.out.println();
			}
			
	}
	 

}
