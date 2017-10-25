import java.text.DecimalFormat;

/**
 * 
 */

/**
 * @author ryan knight
 *
 */
public class DblyCrcLinkedList {

	private class Node {

		private Node next;
		private Node prev;
		private Comparable data;

		private Node(Comparable data, Node prev, Node next) {
			this.data = data;
			this.prev = prev;
			this.next = next;

		}

		private Node() {
			this(null, null, null);
		}
	}

	private Node head;
	private int size;
	public int dataAssign;
	public int dataComp;
	public int loopContAssign;
	public int loopContComp;
	public int other;
	public String name;
	public int totalOps;
	public double timing;
	final DecimalFormat df = new DecimalFormat("#0.000000000");

	public DblyCrcLinkedList(String name) {
		this.head = new Node();
		this.head.prev = this.head; // Points back to head
		this.head.next = this.head;
		this.name = name;
	}

	public DblyCrcLinkedList() {
		this.head = new Node();
		this.head.prev = this.head; // Points back to head
		this.head.next = this.head;
	}

	/*-------------------------------------
	 * Methods 
	 *-------------------------------------*/

	public void ResetCounters() {
		this.dataAssign = 0;
		this.dataComp = 0;
		this.loopContAssign = 0;
		this.loopContComp = 0;
		this.other = 0;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public String getTiming() {
		
		return df.format(nanotimeToSeconds());
	}
	
	public int getTotalOps() {
		return totalOpsSum();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDataAssign() {
		return dataAssign;
	}

	public void setDataAssign(int dataAssign) {
		this.dataAssign = dataAssign;
	}

	public int getDataComp() {
		return dataComp;
	}

	public void setDataComp(int dataComp) {
		this.dataComp = dataComp;
	}

	public int getLoopContAssign() {
		return loopContAssign;
	}

	public void setLoopContAssign(int loopContAssign) {
		this.loopContAssign = loopContAssign;
	}

	public int getLoopContComp() {
		return loopContComp;
	}

	public void setLoopContComp(int loopContComp) {
		this.loopContComp = loopContComp;
	}

	public int getOther() {
		return other;
	}

	public void setOther(int other) {
		this.other = other;
	}
	
	public double nanotimeToSeconds() {
		timing = this.timing / 100000000;
		return timing;
	}

	public int totalOpsSum() {
		totalOps = this.dataAssign + this.dataComp + this.loopContAssign + this.loopContComp + this.other;
		return totalOps;
	}
	
	public boolean isEmpty() {

		return this.head == this.head.next; // if this.head points back to
											// itself its empty
	}

	public String toString() {

		String res = "";

		for (Node curr = this.head.next; curr != this.head; curr = curr.next) {

			res += curr.data + "\n";
		}

		return res;
	}
	
	public String toStringRaw() {
		
		String res = this.name + ", " + this.size + ", " + this.getDataAssign() + ", " + this.getDataComp()  + ", " + this.getLoopContAssign()  + ", " + this.getLoopContComp() + ", " + this.getOther() + ", " + this.getTotalOps();

		return res;
	}
	
public String toStringRawTime() {
		
		String res = this.name + ", " + this.size + ", " + this.getTiming() + " Seconds";

		return res;
	}

	public void addFirst(Comparable data) {

		Node temp = new Node(data, null, null);

		temp.next = this.head.next; // temp wired in
		temp.prev = this.head;

		this.head.next.prev = temp;
		this.head.next = temp; // update list
		size++;
	}

	public void addLast(Comparable data) {

		Node temp = new Node(data, this.head.prev, this.head);

		this.head.prev.next = temp;
		this.head.prev = temp;

		size++;
	}

	public void addIndex(int index, Comparable data) {

		if (index < 0 || index > this.size)
			throw new IndexOutOfBoundsException("Index was invalid, Size: " + this.size + " Index: " + index);

		int i = 0;
		Node curr = this.head.next;

		while (i < index) {
			curr = curr.next;
			i++;
		}

		Node temp = new Node(data, curr.prev, curr);

		curr.prev.next = temp;
		curr.prev = temp;
		size++;
	}

	public DblyCrcLinkedList clone(String sortName) {

		DblyCrcLinkedList twin = new DblyCrcLinkedList(sortName);
		Node curr = this.head.next;

		while (curr != this.head) {
			twin.addLast(curr.data);
			curr = curr.next;
		}
		return twin;
	}

	/*
	 * 
	 * This is the clean Sorts with System.nanoTime()
	 * 
	 * 
	 * 
	 */
	
	public void smartBubble() {
		this.timing = 0;
		double startTime = System.nanoTime();
		
		if (this.size < 2) {
			return;
		}

		Node offSet = this.head;
		boolean sorted;
		Node left, right;
		Comparable temp;

		do {

			sorted = true;

			for (left = this.head.next, right = left.next; right != offSet; left = left.next, right = right.next) {
				
				if (left.data.compareTo(right.data) > 0) {

					sorted = false;

					temp = left.data;
					left.data = right.data;
					right.data = temp;
				}

			} // end forLoop

			offSet = offSet.prev;

		} while (!sorted);
		
		this.timing = System.nanoTime() - startTime;
	}
	
	

	public void selectionSort() {
		this.timing = 0;
		double startTime = System.nanoTime();

		if (this.size < 2) {
			return;
		}

		Node start, smallest, curr;
		Comparable temp;

		for (start = this.head.next; start != this.head.prev; start = start.next) {

			smallest = start;

			for (curr = start.next; curr != this.head; curr = curr.next) {

				if (curr.data.compareTo(smallest.data) < 0) {

					smallest = curr;
				}
			}

			temp = start.data;
			start.data = smallest.data;
			smallest.data = temp;
		}
		
		this.timing = System.nanoTime() - startTime;
	}
	
	
	public void insertionSort() {
		this.timing = 0;
		double startTime = System.nanoTime();
		
		if(this.size < 2) {
			return;
		}
		
		Node lastSorted, firstUnsorted, sortedWalker;
		Comparable dataToInsert;
		
		for(lastSorted = this.head.next; lastSorted.next != this.head; lastSorted = lastSorted.next) {
			
			firstUnsorted = lastSorted.next;
			dataToInsert = firstUnsorted.data;
			
			for(sortedWalker = lastSorted; sortedWalker != this.head && sortedWalker.data.compareTo(dataToInsert) > 0; sortedWalker= sortedWalker.prev) {
				sortedWalker.next.data = sortedWalker.data;
			}
			
			sortedWalker.next.data = dataToInsert;
		}
		this.timing = System.nanoTime() - startTime;
	}
	
	
	public void insertionSortCut() {
		this.timing = 0;
		double startTime = System.nanoTime();
		
		if(this.size < 2) {
			return;
		}
		
		Node lastSorted, firstUnsorted, sortedWalker;
		
		for (lastSorted = this.head.next; lastSorted.next != this.head;) {
			
			firstUnsorted = lastSorted.next;
			sortedWalker = lastSorted;
			
			while(sortedWalker != this.head && sortedWalker.data.compareTo(firstUnsorted.data) > 0) {
			
				sortedWalker = sortedWalker.prev;
				
			}  // end while loop
				
				if (sortedWalker != lastSorted) {
				
					firstUnsorted.next.prev = lastSorted;			// Rewired list where firstUnsorted lives
					lastSorted.next = firstUnsorted.next;
					
					firstUnsorted.next = sortedWalker.next;			// making firstUnsorted point to the right spot
					firstUnsorted.prev = sortedWalker;
					
					sortedWalker.next.prev = firstUnsorted;			// wire unsorted node into sorted part of the list
					sortedWalker.next = firstUnsorted;	
					
				} else {
					
					lastSorted = lastSorted.next;
				}
			
		}		// end for loop
		
		this.timing = System.nanoTime() - startTime;	
	}
	
	
	

	/*
	 * 
	 * This section begins the Sorts with the counting routines written in.
	 * 
	 * 
	 * 
	 */

	public void smartBubbleCounter() {

		ResetCounters();

		this.other++; // for if block
		if (this.size < 2) {
			this.other++; // for return
			return;
		}

		this.other++;
		this.loopContAssign++; // for offset declaration, for offset assign
		Node offSet = this.head;

		this.other++; // for sorted declaration
		boolean sorted;

		this.other += 2; // for left/right declaration
		Node left, right;

		this.other++; // for comparable declaration
		Comparable temp;

		do {

			this.loopContAssign++; // for sorted assign (true)
			sorted = true;

			this.loopContAssign += 2; // for left/ right assign in loop
			for (left = this.head.next, right = left.next; right != offSet; left = left.next, right = right.next) {
				this.loopContComp++; // for right != offset (true)

				this.dataComp++; // for if block
				if (left.data.compareTo(right.data) > 0) {

					this.loopContAssign++; // for sorted assign (false)
					sorted = false;

					this.dataAssign += 3; // for data assign temp =, left.data = , right.data =
					temp = left.data;
					left.data = right.data;
					right.data = temp;
				}
				this.loopContAssign +=2; // for right = right.next, left = left.nexr in for loop
			} // end forLoop
			this.loopContComp++; // for right != offset (false)

			this.loopContAssign++; // for offSet assign
			offSet = offSet.prev;

			this.loopContComp++; // for while(conditional)
		} while (!sorted);
	}
	
	
	public void selectionSortCount() {
		
		ResetCounters();

		this.other ++;		// for if block
		if (this.size < 2) {
			this.other ++;		// for return
			return;
		}

		this.other +=4;		// for start, smallest, curr, temp declaration
		Node start, smallest, curr;
		Comparable temp;

		this.loopContAssign ++; // for start assignment
		for (start = this.head.next; start != this.head.prev; start = start.next) {
			this.loopContComp++; // for start != this.head.prev

			this.dataAssign ++; // for smallest = start data assign
			smallest = start;

			this.loopContAssign ++; //for cur = start.n
			for (curr = start.next; curr != this.head; curr = curr.next) {
				this.loopContComp++; // for cur != this.head

				this.dataComp ++;		// for comparing curr data to smallest data
				if (curr.data.compareTo(smallest.data) < 0) {

					smallest = curr;
				}
				this.loopContAssign ++; //for cur = cur.n in inner for loop
			}
			this.loopContAssign ++; // for start = start.next assignment in outer for loop

			this.dataAssign += 3; // swaps x3
			temp = start.data;
			start.data = smallest.data;
			smallest.data = temp;
		}

	}
	
	
	public void insertionSortCounter() {
			
			this.other++;		// for if block
			if(this.size < 2) {
				this.other++;	// for return
				return;
			}
			
			this.other += 4;		// for lastSorted, firstSorted, sortedWalker, and dataToInsert declarations 
			Node lastSorted, firstUnsorted, sortedWalker;
			Comparable dataToInsert;
			
			this.loopContAssign ++; //for lastSorted = this.h.n
			for(lastSorted = this.head.next; lastSorted.next != this.head; lastSorted = lastSorted.next) {
				this.loopContComp++; // for lastSoted != this.head
				
				this.other ++;		//for re-pointing nodes
				firstUnsorted = lastSorted.next;
				
				this.dataAssign ++;		////for re-pointing nodes
				dataToInsert = firstUnsorted.data;
				
				this.loopContAssign ++; //for sortedWalker = lastSorted
				for(sortedWalker = lastSorted; sortedWalker != this.head && sortedWalker.data.compareTo(dataToInsert) > 0; sortedWalker= sortedWalker.prev) {
					this.loopContComp += 2; 	// for sortedWalker != this.head && sortedWalker.data.compareTo(dataToInsert) > 0
					
					this.dataAssign ++; //for sortedWalker.next.data = sortedWalker.data;
					sortedWalker.next.data = sortedWalker.data;
					
					this.loopContAssign ++; // lastSorted = lastSorted.next
				}
				
				this.other ++; // for node re-pointing 
				sortedWalker.next.data = dataToInsert;
				
				this.loopContAssign ++; // lastSorted = lastSorted.next
			}
		}
	
	
	public void insertionSortCutCounter() {
			
			this.other ++;		// for if block
			if(this.size < 2) {
				this.other ++;		// for return
				return;
			}
			
			this.other += 3;		// for declaration of lastSorted, firstUnsorted, sortedWalker;
			Node lastSorted, firstUnsorted, sortedWalker;
			
			this.loopContAssign ++; //for lastSorted = this.h.n
			for (lastSorted = this.head.next; lastSorted.next != this.head;) {
				this.loopContComp++; // for lastSoted != this.head
				
				this.other += 2;		// for re-pointing nodes x2
				firstUnsorted = lastSorted.next;
				sortedWalker = lastSorted;
				
				while(sortedWalker != this.head && sortedWalker.data.compareTo(firstUnsorted.data) > 0) {
					this.loopContComp += 2;		// for (sortedWalker != this.head && sortedWalker.data.compareTo(firstUnsorted.data) > 0)
					
					this.other ++;	// for re-pointing nodes
					sortedWalker = sortedWalker.prev;
					
				}  // end while loop
					
				this.loopContComp ++;	// for sortedWalker != lastSorted
					if (sortedWalker != lastSorted) {
					
						this.other += 6;		// for cutting and moving nodes (NO DATA ASSIGNMENTS HERE)
						
						firstUnsorted.next.prev = lastSorted;			// Rewired list where firstUnsorted lives
						lastSorted.next = firstUnsorted.next;
						
						firstUnsorted.next = sortedWalker.next;			// making firstUnsorted point to the right spot
						firstUnsorted.prev = sortedWalker;
						
						sortedWalker.next.prev = firstUnsorted;			// wire unsorted node into sorted part of the list
						sortedWalker.next = firstUnsorted;	
						
					} else {
						
						this.loopContAssign ++;		// for lastSorted = lastSorted.next
						lastSorted = lastSorted.next;
					}
				
			}		// end for loop
				
		}

}