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
