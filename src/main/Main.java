package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws IOException {

		// Load a file with our input.
		File f = new File("C:\\Users\\Dalton\\Desktop\\input.txt");
		BufferedReader br = new BufferedReader(new FileReader(f));
		
		ArrayList<String> input = new ArrayList<String>();
		String tempInput;
		
		while ((tempInput = br.readLine()) != null) {
			input.add(tempInput);
		}
		
		br.close();
		
		System.out.println("Checksum: " + partOne(input));
		System.out.println("Letters common betwen two correct box IDs: " + partTwo(input));

	}
	
	public static int partOne(ArrayList<String> input) {
		
		// Declare our variables.
		HashMap<Character, Integer> map;
		char[] id;
		int twos = 0;
		int threes = 0;
		
		for (String s : input) {
			
			boolean incrementedTwo = false;
			boolean incrementedThree = false;
			
			// Initialize the HashMap
			map = new HashMap<Character, Integer>();
			
			// Split the input ID into a character array.
			id = s.toCharArray();
			
			// For each item in the array, increment the letter in the map.
			for (char c : id) {
				if (map.get(c) != null) {
					map.put(c, map.get(c) + 1);
				} else {
					map.put(c, 1);
				}
			}
			
			// For each character in the map, increment our "twos" and "threes" values if the ID has duplicate characters.
			// Each can only be incremented once per ID.
			for (Map.Entry<Character, Integer> entry : map.entrySet()) {
				if (!incrementedTwo && entry.getValue() == 2) {
					twos++;
					incrementedTwo = true;
				} else if (!incrementedThree && entry.getValue() == 3) {
					threes++;
					incrementedThree = true;
				}
			}
			
		}
		
		// Return our checksum.
		return twos * threes;
	}
	
	public static String partTwo(ArrayList<String> input) {
		
		char[] currentValue;
		char[] nextValue;
		boolean oneDifference = false;
		
		// For each item in input
		for (int i = 0; i < input.size() - 1; i++) {
			currentValue = input.get(i).toCharArray();
			
			// Compare item with the items after it
			for (int x = 0; x < input.size() - i; x++) {
				nextValue = input.get(x + i).toCharArray();
				
				// Compare letters in currentValue with letters in nextValue.
				for (int y = 0; y < currentValue.length; y++) {
					if (currentValue[y] != nextValue[y] && !oneDifference) {
						oneDifference = true;
					} else if (currentValue[y] != nextValue[y] && oneDifference) {
						oneDifference = false;
						break;
					}
				}
				
				if (oneDifference) {
					StringBuilder sb = new StringBuilder();
					for (int y = 0; y < currentValue.length; y++) {
						if (currentValue[y] == nextValue[y]) {
							sb.append(currentValue[y]);
						}
					}
					return sb.toString();
				}
			}
		}
		return "Not found.";
	}

}
