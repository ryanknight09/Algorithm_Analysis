import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Ryan knight
 * 
 * EXTRA CREDIT IS ATTEMPTED
 * 
 * Please see the CSVWritter.java class for the source code on CSV writing, and the TXTWritter.java for TXT writing. 
 * 
 * Upon reflection of my program, it runs a little inefficient being the way the lists are created, populated and sorted. 
 * With further time I would re-factor the code. 
 * 
 *  Accessing the specific data fields for each list type is not the easiest here...
 *  
 *  lists are structured as follows:
 *  
 *  -Main array list contains an array list of lists (Ascending, Descending, Random)
 *  
 *  -Inside each Ascending, Descending, and Random contains: (List500, list1000, list5000, list10000)
 *  
 *  -Inside each list500, list1000, list5000, list10000 contains: (BubbleSort, SelectionSort, InsertionSort, InsertionSortCut)
 *  
 *  -In total the Array List "ADR" contains 48 Doubly LinkedLists
 *  
 *  Data for timings are another main list called "ADRTiming and is structured the same as "ADR"
 *  
 *  That should provide some insight as to how the CSVWritter and TXTWritter are accessing the need data fields.
 *  
 *  
 *  JAVA OPTOMIZATION WILL SHOW DIFFERENT TIMES IN THE CSV AND THE TXT FILES, if you make the CSV first it shows slower times, 
 *  or if you make the TXT first it shows slower
 *  
 *  
 *  The TXT file will append to an existing file of the same name, So it you need re run this tester, delete the TXT file first.
 *  CSV file will automatically update, no need to delete first. 
 *  
 */

public class SortTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Random rnd = new Random();
		int temp;
		BigInteger bigIn = new BigInteger("1");

		DblyCrcLinkedList list500Ascending = new DblyCrcLinkedList("Ascending"); // Sorted
		DblyCrcLinkedList list1000Ascending = new DblyCrcLinkedList("Ascending");
		DblyCrcLinkedList list5000Ascending = new DblyCrcLinkedList("Ascending");
		DblyCrcLinkedList list10000Ascending = new DblyCrcLinkedList("Ascending");

		DblyCrcLinkedList list500random = new DblyCrcLinkedList("Descending500"); // Randomized
		DblyCrcLinkedList list1000random = new DblyCrcLinkedList("Descending500");
		DblyCrcLinkedList list5000random = new DblyCrcLinkedList("Descending500");
		DblyCrcLinkedList list10000random = new DblyCrcLinkedList("Descending500");

		DblyCrcLinkedList list500Descending = new DblyCrcLinkedList("Descending500"); // Un-Sorted
		DblyCrcLinkedList list1000Descending = new DblyCrcLinkedList("Descending1000");
		DblyCrcLinkedList list5000Descending = new DblyCrcLinkedList("Descending5000");
		DblyCrcLinkedList list10000Descending = new DblyCrcLinkedList("Descending10000");
		
		/*
		 * Populates all of the lists with BigIntegers
		 * 
		 * 
		 */

		// Ascending order on 500 list
		for (int i = 0; i < 500; i++) {
			bigIn = BigInteger.valueOf(i);
			list500Ascending.addLast(bigIn);
		}

		// Ascending order on 1000 list
		for (int i = 0; i < 1000; i++) {
			bigIn = BigInteger.valueOf(i);
			list1000Ascending.addLast(bigIn);
		}

		// Ascending order on 5000 list
		for (int i = 0; i < 5000; i++) {
			bigIn = BigInteger.valueOf(i);
			list5000Ascending.addLast(bigIn);
		}

		// Ascending order on 10000 list
		for (int i = 0; i < 10000; i++) {
			bigIn = BigInteger.valueOf(i);
			list10000Ascending.addLast(bigIn);
		}

		// Ascending order on 500 list
		for (int i = 500; i > 0; i--) {
			bigIn = BigInteger.valueOf(i);
			list500Descending.addLast(bigIn);
		}

		// Ascending order on 1000 list
		for (int i = 1000; i > 0; i--) {
			bigIn = BigInteger.valueOf(i);
			list1000Descending.addLast(bigIn);
		}

		// Ascending order on 5000 list
		for (int i = 5000; i > 0; i--) {
			bigIn = BigInteger.valueOf(i);
			list5000Descending.addLast(bigIn);
		}

		// Ascending order on 10000 list
		for (int i = 10000; i > 0; i--) {
			bigIn = BigInteger.valueOf(i);
			list10000Descending.addLast(bigIn);
		}

		// Ascending order on 500 list (Randomized data)
		for (int i = 0; i < 500; i++) {
			temp = rnd.nextInt(501);
			bigIn = BigInteger.valueOf(temp);
			list500random.addLast(bigIn);
		}

		// Ascending order on 1000 list (Randomized data)
		for (int i = 0; i < 1000; i++) {
			temp = rnd.nextInt(1001);
			bigIn = BigInteger.valueOf(temp);
			list1000random.addLast(bigIn);
		}

		// Ascending order on 5000 list (Randomized data)
		for (int i = 0; i < 5000; i++) {
			temp = rnd.nextInt(5001);
			bigIn = BigInteger.valueOf(temp);
			list5000random.addLast(bigIn);
		}

		// Ascending order on 10000 list (Randomized data)
		for (int i = 0; i < 10000; i++) {
			temp = rnd.nextInt(10001);
			bigIn = BigInteger.valueOf(temp);
			list10000random.addLast(bigIn);
		}
		
		/* Sorting Operations lists
		 * 
		 * 
		 * 
		 */

		List<DblyCrcLinkedList> ascendingLists = new ArrayList<DblyCrcLinkedList>(); // lists of ascending lists sent to ADR
		SortsCloneGen(list500Ascending, ascendingLists);
		SortsCloneGen(list1000Ascending, ascendingLists);
		SortsCloneGen(list5000Ascending, ascendingLists);			// each call to SortsCloneGen makes 4 lists
		SortsCloneGen(list10000Ascending, ascendingLists);

		List<DblyCrcLinkedList> descendingLists = new ArrayList<DblyCrcLinkedList>(); // lists of descending lists sent to ADR
																						
		SortsCloneGen(list500Descending, descendingLists);
		SortsCloneGen(list1000Descending, descendingLists);
		SortsCloneGen(list5000Descending, descendingLists);
		SortsCloneGen(list10000Descending, descendingLists);

		List<DblyCrcLinkedList> randomLists = new ArrayList<DblyCrcLinkedList>(); // lists of random lists sent to ADR
		SortsCloneGen(list500random, randomLists);
		SortsCloneGen(list1000random, randomLists);
		SortsCloneGen(list5000random, randomLists);
		SortsCloneGen(list10000random, randomLists);

		List<DblyCrcLinkedList> ADR = new ArrayList<DblyCrcLinkedList>(); // List send to CSV Generator
		ADR.addAll(ascendingLists);
		ADR.addAll(descendingLists);
		ADR.addAll(randomLists);
		
		/* timing algorithms lists
		 * 
		 *  
		 * 
		 */
		
		List<DblyCrcLinkedList> ascendingTimeLists = new ArrayList<DblyCrcLinkedList>(); // lists of ascending lists sent to ADR
		SortsCloneGenTiming(list500Ascending, ascendingTimeLists);
		SortsCloneGenTiming(list1000Ascending, ascendingTimeLists);
		SortsCloneGenTiming(list5000Ascending, ascendingTimeLists);			// each call to SortsCloneGen makes 4 lists
		SortsCloneGenTiming(list10000Ascending, ascendingTimeLists);

		List<DblyCrcLinkedList> descendingTimeLists = new ArrayList<DblyCrcLinkedList>(); // lists of descending lists sent to ADR
																						
		SortsCloneGenTiming(list500Descending, descendingTimeLists);
		SortsCloneGenTiming(list1000Descending, descendingTimeLists);
		SortsCloneGenTiming(list5000Descending, descendingTimeLists);
		SortsCloneGenTiming(list10000Descending, descendingTimeLists);

		List<DblyCrcLinkedList> randomTimeLists = new ArrayList<DblyCrcLinkedList>(); // lists of random lists sent to ADR
		SortsCloneGenTiming(list500random, randomTimeLists);
		SortsCloneGenTiming(list1000random, randomTimeLists);
		SortsCloneGenTiming(list5000random, randomTimeLists);
		SortsCloneGenTiming(list10000random, randomTimeLists);

		List<DblyCrcLinkedList> ADRTime = new ArrayList<DblyCrcLinkedList>(); // List send to CSV Generator
		ADRTime.addAll(ascendingTimeLists);
		ADRTime.addAll(descendingTimeLists);
		ADRTime.addAll(randomTimeLists);
		
		
		
		try {
			TXTWritter.WriteRawData("RawData.txt", (ArrayList<DblyCrcLinkedList>) ADR, (ArrayList<DblyCrcLinkedList>) ADRTime);	// Makes TXT file
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		CSVWritter.writeCsvFile("CSVStats.csv", (ArrayList<DblyCrcLinkedList>) ADR, (ArrayList<DblyCrcLinkedList>) ADRTime);		// Makes CSV FILE

	}
	
	
	/* Methods for creating the many assorted lists
	 * 
	 * 
	 * 
	 */

	
	// THIS IS FOR OPPORTATIONS, NO TIMING
	public static void SortsCloneGen(DblyCrcLinkedList itemToAdd, List<DblyCrcLinkedList> AddList) {		// makes 4 lists, one for each sort routine
		String sortName = "";
		DblyCrcLinkedList temp;

		for (int i = 0; i < 4; i++) {

			if (i == 0) {							// This if block gives each list the type of sort routine name
				sortName = "BubbleSort";
			} else if (i == 1) {
				sortName = "SelectionSort";
			} else if (i == 2) {
				sortName = "InsertionSort";
			} else {
				sortName = "InsertionCutSort";
			}

			if (i == 0) {							// this if block does the actual sort routine assigned in the above if block	
				temp = itemToAdd.clone(sortName);
				temp.smartBubbleCounter();
			} else if (i == 1) {
				temp = itemToAdd.clone(sortName);
				temp.selectionSortCount();
			} else if (i == 2) {
				temp = itemToAdd.clone(sortName);
				temp.insertionSortCounter();
			} else {
				temp = itemToAdd.clone(sortName);
				temp.insertionSortCutCounter();
			}

			AddList.add(temp);
		}
	}
	
	// THIS IS FOR TIMING
	public static void SortsCloneGenTiming(DblyCrcLinkedList itemToAdd, List<DblyCrcLinkedList> AddList) {		// makes 4 lists, one for each sort routine
		String sortName = "";
		DblyCrcLinkedList temp;

		for (int i = 0; i < 4; i++) {

			if (i == 0) {							// This if block gives each list the type of sort routine name
				sortName = "BubbleSort_Time";
			} else if (i == 1) {
				sortName = "SelectionSort_Time";
			} else if (i == 2) {
				sortName = "InsertionSort_Time";
			} else {
				sortName = "InsertionCutSort_Time";
			}

			if (i == 0) {							// this if block does the actual sort routine assigned in the above if block	
				temp = itemToAdd.clone(sortName);
				temp.smartBubble();
			} else if (i == 1) {
				temp = itemToAdd.clone(sortName);
				temp.selectionSort();
			} else if (i == 2) {
				temp = itemToAdd.clone(sortName);
				temp.insertionSort();
			} else {
				temp = itemToAdd.clone(sortName);
				temp.insertionSortCut();
			}

			AddList.add(temp);
		}
	}

}