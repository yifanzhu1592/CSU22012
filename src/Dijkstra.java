
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra {
	
	
	   public ArrayList<String> startStops = new ArrayList<String>();
	   public ArrayList<String> endStops = new ArrayList<String>();
	   public ArrayList<Edge> edges = new ArrayList<Edge>();
	   public ArrayList<Double> weight = new ArrayList<Double>();
	   public Double cost;
	   public HashMap<String,Vertex> VertexesMap = new HashMap<String,Vertex>();
	   public ArrayList<String[]> tripIdStops = new ArrayList<String[]>();	   
	
		
	   public Dijkstra(String stopsFileName, String transfersFileName, String stop_timesFileName) throws FileNotFoundException 
	   { 
		   File stopsfile = new File(stopsFileName);
		   Scanner sc1 = new Scanner(stopsfile);

	  

	   while(sc1.hasNextLine())
	   {
		   String stop = sc1.nextLine();
		   String stopar[] = stop.split(",");
		   Vertex v = new Vertex(stopar[0]);
		   VertexesMap.put(stopar[0], v);
	   }
	   
	   sc1.close();
	  

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
	   
	   sc2.close();
	   
	   startStops = startStopsAL;
	   endStops = endStopsAL;
	   
	   weight = weightAL;
	   
	   
	    
	    
	    for(int i = 1;i < startStopsAL.size();i++) {
	    	Vertex V1 = VertexesMap.get(startStops.get(i));
	    	Vertex V2 = VertexesMap.get(endStops.get(i));
	    	Double edgeWeight = weight.get(i);
	    	
	    	
	    	V1.addNeighbour(new Edge(edgeWeight,V1,V2));
	    	VertexesMap.put(startStops.get(i), V1);
	    	
	    }
	    
	  File stops_times_file = new File(stop_timesFileName);
	  Scanner sc3 = new Scanner(stops_times_file);
	  
	  while(sc3.hasNextLine()) {
		  
		 String a = sc3.nextLine();
		 String stop_times_ar[] = a.split(",");
		 
		 String tripID = stop_times_ar[0];
		 
		String stopID = stop_times_ar[3];
		String TripStopAr[] = {tripID,stopID};
		
		tripIdStops.add(TripStopAr);
		  
		

	  }
	  
		  
		  String TripID = tripIdStops.get(1)[0];
		  String StopID = tripIdStops.get(1)[1];
		  Vertex v1 = VertexesMap.get(StopID);
		  for(int j = 2; j < tripIdStops.size(); j++) {
			  
			  String TripID2 = tripIdStops.get(j)[0];
			  String StopID2 = tripIdStops.get(j)[1];
			  
			  
			  if(TripID.equals(TripID2)) {
				  Vertex v2 = VertexesMap.get(StopID2);
				  v1.addNeighbour(new Edge(1.0,v1,v2));
				  
				  VertexesMap.put(v1.name, v1);
				  v1 = v2;
				  
			  }
			  
			  else {
				  TripID =TripID2;
				  Vertex v2 = VertexesMap.get(StopID2);
				  v1 = v2;
			  }
			  
		  }
		  
	  
	   
	    
	    
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

