import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

//Author Michael Sweeney
public class FrontEnd {

	public static void main(String[] args) throws FileNotFoundException {
		java.io.File file = new java.io.File("stop_times.txt");
		Dijkstra algorithm = new Dijkstra("stops.txt", "transfers.txt", "stop_times.txt");
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
						if (choice == 0) {
							System.out.println("Have a nice day!");
							System.exit(0);
						}
						if (choice == 1) {

							String inputSource = "";
							String inputDestination = "";
							/*
							  vertex objects for the
							  Algorithm
							 */
							HashMap<String, Vertex> vertexes = algorithm.VertexesMap; 
							Double cost = algorithm.cost; /* for storing the cost of the path taken, initially 0 */

							boolean lock = true;

							while (lock != false) {

								String regex = "[0-9]+";
								System.out.println("Enter Stop ID of starting stop: ");
								String userInputSource = input.nextLine();
								inputSource = userInputSource; //User input holder for source stop
								
								System.out.println("Enter Stop ID of destination stop");
								String userInputDestination = input.nextLine();
								inputDestination = userInputDestination; // User Input holder for destination stop

								
								if (!(inputSource.matches(regex)) || !(inputDestination.matches(regex)) ) {
									if (!(inputSource.matches(regex)))
										System.out.println("Your starting stop ID should only contain digits.");
									if (!(inputDestination.matches(regex)))
										System.out.println("Your destination stop ID should only contain digits.");
								} else {

									Vertex source = vertexes.get(inputSource); // creates a vertex object of the source
																				// stop
									Vertex destination = vertexes.get(inputDestination);// creates a vertex object of
																						// the
																						// destination stop
									if (vertexes.get(inputSource) == null && vertexes.get(inputDestination) == null) {

										System.out.println(
												"Sorry, the bus stops entered do not exist. Please try again with a different stop ID");
									}
									else {
										lock = false;
									}
								}

							}

							Vertex source = vertexes.get(inputSource); // creates a vertex object of the source stop
							Vertex destination = vertexes.get(inputDestination);// creates a vertex object of the
																				// destination stop

							algorithm.computePath(source); // compute shortest path from source to all vertexes
							// Printing the result- Display in the Display area of the UI
							cost = algorithm.cost;

							System.out.println("The Path from " + source.name + " to " + destination.name + " is "
									+ algorithm.getShortestPathTo(destination));
							// getShortesPathTo(Destination), returns the path of the shortest path between
							// the source and the destination
							// algorithm.cost returns the cost of the path by the shortest path algorithm.
							System.out.println("Cost of the Path: " + algorithm.cost + "\n");

						} else if (choice == 2) {
							System.out.print(
									"Please enter the bus stop's full name or by the first few characters in the name: ");
							String userInput = input.nextLine();
							tst.getStopInformation(userInput).forEach((info) -> {
								System.out.println(info);
							});
						} else if (choice == 3) {
							ArrivalTime.printArrival(file);
						}
					} catch (Exception e) {
						System.out.println("");
						e.printStackTrace();
					}
				}

			} else {
				System.out.println("Not a valid input!");
			}

		} while (!quit);
	}

}
