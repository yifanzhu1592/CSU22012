import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ArrivalTime {

	public static void printArrival(File file) throws FileNotFoundException, IOException {

		try {
			ArrayList<String> stopTimes = readTextFileByLines(file);
			ArrayList<String> results = new ArrayList();

			Scanner input = new Scanner(System.in);
			System.out.println("Enter an arrival time in the format HH:MM:SS: ");
			boolean validTime = false;
			try {
				String time = input.next();
				if (time.matches("(([0-1]?[0-9])|(2[0-3])):[0-5][0-9]:[0-5][0-9]")) {
					if(time.length()== 7)
						time += " ";
					for (int i = 0; i < stopTimes.size(); i++) {
						if (stopTimes.get(i).contains(time)) {
							results.add(stopTimes.get(i));
						}
						validTime = true;
					}
				}
				else {
					System.out.println("Your input is not in the format HH:MM:SS where it does not exceed 23:59:59");
					
				}
			} catch (Exception e) {
				System.out.println("Something went wrong");
			}
			if (results.size() > 0) {
				printArrivalTimes(results);
			} else if(validTime) {
				System.out.println("The arrival time could not be found.");
			}
			else {
				System.out.println("Improper input");
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");			
		}
	}

	public static ArrayList<String> readTextFileByLines(File file) throws IOException {
		ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(Paths.get("stop_times.txt"));
		Files.lines(file.toPath()).map(s -> s.trim())
				.filter(s -> !s.matches("(([0-1]?[0-9])|(2[0-3])):[0-5][0-9]:[0-5][0-9]"));
		return lines;
	}

	public static void printArrivalTimes(ArrayList<String> list) throws IOException {

		Collections.sort(list, (a, b) -> parseId(a) - parseId(b));
		System.out.println(
				"Trip ID, Arrival Time, Departure Time, Stop ID, Stop sequence, Stop Headsign, Pickup Type, Drop Off Type, Shape, Distance Traveled  ");
		
		for (var l : list)
			System.out.println(l);
	}

	static int parseId(String line) {
		return Integer.parseInt(line.split(",")[0]);
	}

}
