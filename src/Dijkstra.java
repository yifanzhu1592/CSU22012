
			/*
			 * Vertex v1 = new Vertex("A"); Vertex v2 = new Vertex("B"); Vertex v3 = new
			 * Vertex("C");
			 * 
			 * v1.addNeighbour(new Edge(12, v1, v2)); v1.addNeighbour(new Edge(10, v1, v2));
			 * 
			 * v2.addNeighbour(new Edge(14, v2, v3));
			 * 
			 * Dijkstra dijkstra = new Dijkstra(); dijkstra.computePath(v1);
			 * 
			 * System.out.println(dijkstra.getShortestPathTo(v3) + " " + dijkstra.cost);
			 */
		 



import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra {
	
	public ArrayList<Vertex> vertexes = new ArrayList<Vertex>();
	   public ArrayList<String> startStops = new ArrayList<String>();
	   public ArrayList<String> endStops = new ArrayList<String>();
	   
	   public ArrayList<Double> weight = new ArrayList<Double>();
	   public Double cost;
	
		
	   public Dijkstra(String stopsFileName, String transfersFileName) throws FileNotFoundException 
	   { 
		   File stopsfile = new File(stopsFileName);
		   Scanner sc1 = new Scanner(stopsfile);

	   ArrayList<Vertex> vertexesAL = new ArrayList<Vertex>();

	   while(sc1.hasNextLine())
	   {
		   String stop = sc1.nextLine();
		   String stopar[] = stop.split(",");
		   Vertex v = new Vertex(stopar[0]);
		   vertexesAL.add(v);
	   }
	   
	   sc1.close();
	   vertexes = vertexesAL;

	   File transfersFile = new File(transfersFileName);
	   Scanner sc2 = new Scanner(transfersFile);

	   ArrayList<String> startStopsAL = new ArrayList<String>();
	   ArrayList<String> endStopsAL = new ArrayList<String>();

	   ArrayList<Double> weightAL = new ArrayList<Double>();

	   while(sc2.hasNextLine())
	   {
		   String edge = sc2.nextLine();
		   String edgear[] = edge.split(",");

	   
		   startStopsAL.add(edgear[0]);
	   
		   endStopsAL.add(edgear[1]);


	   if(edgear[2]=="0") { weightAL.add(2.0); }

	   else if(edgear[2]=="2") { weightAL.add(Double.parseDouble(edgear[3])/100); }

	   else { weightAL.add(1.0); } 
	   
	   }
	   
	   startStops = startStopsAL;
	   endStops = endStopsAL;
	   
	   weight = weightAL;

	   }
		 
	
   
  
    public void computePath(Vertex sourceVertex) {
        sourceVertex.setMinDistance(0);
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(sourceVertex);

        while (!priorityQueue.isEmpty()) {
            Vertex vertex = priorityQueue.poll();

            for (Edge edge : vertex.getEdges()) {
                Vertex v = edge.getTargetVertex();
//                Vertex u = edge.getStartVertex();
                double weight = edge.getWeight();
                double minDistance = vertex.getMinDistance() + weight;

                if (minDistance < v.getMinDistance()) {
                    priorityQueue.remove(vertex);
                    v.setPreviosVertex(vertex);
                    v.setMinDistance(minDistance);
                    priorityQueue.add(v);
                }
            }
        }
    }

    public List<Vertex> getShortestPathTo(Vertex targetVerte) {
        List<Vertex> path = new ArrayList<>();
        
        cost = targetVerte.getMinDistance();
        
        for (Vertex vertex = targetVerte; vertex != null; vertex = vertex.getPreviosVertex()) {
            path.add(vertex);
        }

        Collections.reverse(path);
        return path;
    }

 

  
		 
    	
		

    

}

