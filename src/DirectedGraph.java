

import java.util.*;

//import UndirectedGraph.edge;
public class DirectedGraph {
	LinkedList<Integer> list1 = new LinkedList<Integer>();
	public  static LinkedList<Integer> ListOfNodes = new LinkedList<Integer>();
	public static ArrayList<edge> ListOfEdges = new ArrayList<edge> () ;


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
		public void printedge(edge e){
			System.out.println(e.getX()+"-----"+e.getY());
			
		}
		}


	
	// constructor initialize an undirected graph, n is the number of nodes
	public DirectedGraph(int n){
		numberOfNodes=n;
		for( int i =0; i<n; i++){
			ListOfNodes.add(i);
			
			}
		
	}
	public  static void intializeRank(LinkedList<Integer> list){
		for(int i=0; i<list.size(); i++)
			list.set(i, 1);
		//System.out.println(list.get(i));}
		
	//	printnodeRank(list);
	}
	public static void printnodeRanks(LinkedList<Integer> list){
		for(int i=0; i<list.size();i++)
			System.out.println("node "+(i)+ " rank is "+ list.get(i));
	}
public static void calcoutdegree(ArrayList<edge> list){
	for(int i=0; i<list.size(); i++){
		{
			int degree=0;
			if(list.get(i).getX()==i)
				degree++;
		}
	}
	
}

	// check if the given node id is out of bounds
	private boolean outOfBounds(int nidx){
		if(0>nidx || nidx>this.numberOfNodes-1)
			return true;
		return false;
	}

	// set an edge (n1,n2)
	// beware of repeatingly setting a same edge and out-of-bound node ids
	public void setEdge(int n1, int n2){
		if(n1 == n2)
			return;
		edge newEdge;
		
			 newEdge  = new edge(n1, n2);
			// printedge(newEdge);
		
		for( edge anEdge : ListOfEdges){
				if(newEdge.getX() == anEdge.getX() && newEdge.getY() == anEdge.getY())
					return;
			}
		ListOfEdges.add(newEdge);
	}
		/*{for(int i = 0; i < ListOfEdges.size(); ++i)
		printedge(ListOfEdges.get(i));

	}*/
	
	public void printedge(edge e){
		System.out.println(e.getX()+"-----"+e.getY());
		
	}

	public static void printlist(LinkedList<edge> nlist){
		   for(int i = 0; i < nlist.size(); ++i)
		     System.out.println(nlist.get(i));
		   System.out.println("");
		 }
	// compute page rank after num_iters iterations
	// then print them in a monotonically decreasing order
	 void computePageRank(int num_iters){
		 
	 }
	 public static void main(String[] args) {
		 DirectedGraph DG = new DirectedGraph(5);		
			DG.setEdge(0, 1);
			DG.setEdge(0, 4);
			DG.setEdge(1, 0);
			DG.setEdge(2, 3);
			DG.setEdge(2, 4);
			DG.setEdge(3, 0);
			DG.setEdge(3, 2);
			DG.setEdge(3, 1);
			DG.setEdge(4, 0);
			
		//printlist(ListOfEdges);
			intializeRank(ListOfNodes);
			printnodeRanks(ListOfNodes);
		}
	 } 

