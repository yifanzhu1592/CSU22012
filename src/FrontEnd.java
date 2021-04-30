import java.util.ArrayList;
import java.util.Scanner;

public class FrontEnd {

	public static void main(String[] args) {
		java.io.File file = new java.io.File("stop_times.txt");
		ArrivalTime time = new ArrivalTime();

		boolean quit = false;
		do {
			TST tst = new TST("stops.txt");
			Scanner input = new Scanner(System.in);
			System.out.println("Please select one of the following options by number(1,2,3) or 0 to exit");
			System.out.println("1. Finding shortest paths between 2 bus stops ");
			System.out.println("2. Search for a bus stop");
			System.out.println("3. Search for an arrival time");
			System.out.println("0. To exit");
			if (input.hasNextInt()) {
				int choice = input.nextInt();
				input.nextLine();
				if (choice >= 0 && choice <= 3) {
					try {

						if (choice == 1) {
							
							
							String inputSource = "";
							String inputDestination = "";
							 Dijkstra algorithm = new Dijkstra("stops.txt","transfers.txt");
								
								ArrayList<Vertex> vertexes = algorithm.vertexes;   /* vertex objects for the Algorithm */
								ArrayList<String> startStops = algorithm.startStops; /* Start point of the edge */
								ArrayList<String> endStops = algorithm.endStops; /* End point of the edge */
							    ArrayList<Edge> edges = algorithm.edges;
								ArrayList<Double> weight = algorithm.weight; /* weight of the edge defined in startStops and End Stops */
							
								Double cost = algorithm.cost; /* for storing the cost of the path taken, initially 0 */ 
							 
							   /////********* To be realised that the vertexes have been initalised but the vertexes still have no  ******\\\\\\\\\\
					 ////////********  edges, hence I have provided the above variables to create the edges and then initalise the vertexes  *******\\\\\\\
							 
								
								
							
								boolean lock = true;
							
							while(lock!=false){
							
							System.out.println("Enter Stop ID of starting stop: ");
							String userInputSource = input.nextLine();
							
						    inputSource = userInputSource; //User input holder for source stop
							 
							 
							 System.out.println("Enter Stop ID of destination stop");
							 String userInputDestination = input.nextLine();
						    inputDestination = userInputDestination; // User Input holder for destination stop
							 

							 
							 Vertex source = new Vertex(inputSource); //creates a vertex object of the source stop
							 Vertex destination = new Vertex(inputDestination);//creates a vertex object of the destination stop
							 
							 if((vertexes.contains(source) && vertexes.contains(destination)) == false) {
								 
								 System.out.println("Sorry, the bus stops entered do not exist. Please try again with a different stop ID");
								 
								 continue;
								 
							 }
							 
							 else {
								 lock = false;
							 }
							 
							}
							  
							 Vertex source = new Vertex(inputSource); //creates a vertex object of the source stop
							 Vertex destination = new Vertex(inputDestination);//creates a vertex object of the destination stop
							 
							 algorithm.computePath(source); //compute shortest path from source to all vertexes
							 // Printing the result- Display in the Display area of the UI
							  cost = algorithm.cost; 
							 
							 System.out.println(algorithm.getShortestPathTo(destination) + "\n" + cost);
							//getShortesPathTo(Destination), returns the path of the shortest path between the source and the destination
							 // algorithm.cost returns the cost of the path by the shortest path algorithm.
							
							
							
							

						} else if (choice == 2) {
							System.out.print(
									"Please enter the bus stop's full name or by the first few characters in the name: ");
							String userInput = input.nextLine();
							tst.getStopInformation(userInput).forEach((info) -> {
								System.out.println(info);
							});
						} else if (choice == 3) {
							time.printArrival(file);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			} else {
				System.out.println("Not a valid input!");
			}

		} while (!quit);
	}

}
