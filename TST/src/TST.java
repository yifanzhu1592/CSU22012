import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class TST {

	private HashMap<String, String> map;
	private int n; // size
	private Node<String> root; // root of TST

	private static class Node<String> {
		private char c; // character
		private Node<String> left, mid, right; // left, middle, and right subtries
		private String val; // String associated with string
	}

	/**
	 * Does this symbol table contain the given key?
	 * 
	 * @param key the key
	 * @return {@code true} if this symbol table contains {@code key} and
	 *         {@code false} otherwise
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public boolean contains(String key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to contains() is null");
		}
		return get(key) != null;
	}

	/**
	 * Returns the String associated with the given key.
	 * 
	 * @param key the key
	 * @return the String associated with the given key if the key is in the symbol
	 *         table and {@code null} if the key is not in the symbol table
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public String get(String key) {
		if (key == null) {
			throw new IllegalArgumentException("calls get() with null argument");
		}
		if (key.length() == 0)
			throw new IllegalArgumentException("key must have length >= 1");
		Node<String> x = get(root, key, 0);
		if (x == null)
			return null;
		return x.val;
	}

	// return subtrie corresponding to given key
	private Node<String> get(Node<String> x, String key, int d) {
		if (x == null)
			return null;
		if (key.length() == 0)
			throw new IllegalArgumentException("key must have length >= 1");
		char c = key.charAt(d);
		if (c < x.c)
			return get(x.left, key, d);
		else if (c > x.c)
			return get(x.right, key, d);
		else if (d < key.length() - 1)
			return get(x.mid, key, d + 1);
		else
			return x;
	}

	/**
	 * Inserts the key-String pair into the symbol table, overwriting the old String
	 * with the new String if the key is already in the symbol table. If the String
	 * is {@code null}, this effectively deletes the key from the symbol table.
	 * 
	 * @param key the key
	 * @param val the String
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public void put(String key, String val) {
		if (key == null) {
			throw new IllegalArgumentException("calls put() with null key");
		}
		if (!contains(key))
			n++;
		else if (val == null)
			n--; // delete existing key
		root = put(root, key, val, 0);
	}

	private Node<String> put(Node<String> x, String key, String val, int d) {
		char c = key.charAt(d);
		if (x == null) {
			x = new Node<String>();
			x.c = c;
		}
		if (c < x.c)
			x.left = put(x.left, key, val, d);
		else if (c > x.c)
			x.right = put(x.right, key, val, d);
		else if (d < key.length() - 1)
			x.mid = put(x.mid, key, val, d + 1);
		else
			x.val = val;
		return x;
	}

	/**
	 * Returns all of the keys in the set that start with {@code prefix}.
	 * 
	 * @param prefix the prefix
	 * @return all of the keys in the set that start with {@code prefix}, as an
	 *         iterable
	 * @throws IllegalArgumentException if {@code prefix} is {@code null}
	 */
	public Iterable<String> keysWithPrefix(String prefix) {
		if (prefix == null) {
			throw new IllegalArgumentException("calls keysWithPrefix() with null argument");
		}
		Queue<String> queue = new LinkedList<String>();
		Node<String> x = get(root, prefix, 0);
		if (x == null)
			return queue;
		if (x.val != null)
			queue.add(prefix);
		collect(x.mid, new StringBuilder(prefix), queue);
		return queue;
	}

	// all keys in subtrie rooted at x with given prefix
	private void collect(Node<String> x, StringBuilder prefix, Queue<String> queue) {
		if (x == null)
			return;
		collect(x.left, prefix, queue);
		if (x.val != null)
			queue.add(prefix.toString() + x.c);
		collect(x.mid, prefix.append(x.c), queue);
		prefix.deleteCharAt(prefix.length() - 1);
		collect(x.right, prefix, queue);
	}

	public TST(String fileName) {
		File file = new File(fileName);
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		sc.nextLine(); // remove the first line
		map = new HashMap<String, String>();

		while (sc.hasNextLine()) {
			String wholeLine = sc.nextLine();
			String[] lineArray = wholeLine.split(",");
			String stopId = lineArray[0];
			StringBuilder sb = new StringBuilder();
			sb.append(lineArray[2]);
			if (sb.substring(0, 8).equals("FLAGSTOP")) {
				String dir = sb.substring(0, 11);
				sb.delete(0, 12);
				sb.append(" " + dir);
			} else if (sb.substring(0, 2).equals("NB") || sb.substring(0, 2).equals("SB")
					|| sb.substring(0, 2).equals("WB") || sb.substring(0, 2).equals("EB")) {
				String dir = sb.substring(0, 2);
				sb.delete(0, 3);
				sb.append(" " + dir);
			}
			String stopName = sb.toString();
			this.put(stopName, stopId);

			StringBuilder sbInfo = new StringBuilder();
			sbInfo.append("stop_id: " + stopId + "\n");
			sbInfo.append("stop_code: " + lineArray[1] + "\n");
			sbInfo.append("stop_name: " + stopName + "\n");
			sbInfo.append("stop_desc: " + lineArray[3] + "\n");
			sbInfo.append("stop_lat: " + lineArray[4] + "\n");
			sbInfo.append("stop_lon: " + lineArray[5] + "\n");
			sbInfo.append("zone_id: " + lineArray[6] + "\n");
			sbInfo.append("location_type: " + lineArray[8] + "\n");
			String stopInfo = sbInfo.toString();
			map.put(stopId, stopInfo);

		}
	}

	public List<String> getStopInformation(String input) {
		List<String> list = new LinkedList<>();
		this.keysWithPrefix(input).forEach((info) -> {
			list.add(map.get(this.get(info)));
		});
		return list;
	}

	public static void main(String[] args) {

		TST tst = new TST("stops.txt");
		tst.getStopInformation("HASTINGS ST FS").forEach((info) -> {
			System.out.println(info);
		});
	}

}
