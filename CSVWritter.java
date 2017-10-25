import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVWritter {
	
	private static final String COMMA_DELIMETER = ",";
	private static final String NEW_LINE_SEPERATOR = "\n";
	
	//CSV Header
	private static final String FILE_HEADER = " ,List500,List1000,List5000,List10000";
	
	public static void writeCsvFile(String fileName, ArrayList<DblyCrcLinkedList> res, ArrayList<DblyCrcLinkedList> resTime) {
		
		FileWriter fileWriter = null;
		
		try {
			fileWriter = new FileWriter(fileName);
			
			//Write header
			fileWriter.append(FILE_HEADER.toString());
			
			//Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPERATOR.toString());
	
			/* write appended lines for Ascending Sorts
			 * 
			 * The Array list (res) has 48 indexes that contain other array lists which carry the sorting routines data. 
			 * In order to access them I had to loop through the list in a specific manner for the way that I wanted the 
			 * CSV file to print out. 
			 * 
			 */
			appendLines(0, fileWriter, res );
			appendLines(1, fileWriter, res );
			appendLines(2, fileWriter, res );
			appendLines(3, fileWriter, res );
			fileWriter.append(NEW_LINE_SEPERATOR.toString());
			fileWriter.append(FILE_HEADER.toString());
			fileWriter.append(NEW_LINE_SEPERATOR.toString());
			appendLines(16, fileWriter, res );
			appendLines(17, fileWriter, res );
			appendLines(18, fileWriter, res );
			appendLines(19, fileWriter, res );
			fileWriter.append(NEW_LINE_SEPERATOR.toString());
			fileWriter.append(FILE_HEADER.toString());
			fileWriter.append(NEW_LINE_SEPERATOR.toString());
			appendLines(32, fileWriter, res );
			appendLines(33, fileWriter, res );
			appendLines(34, fileWriter, res );
			appendLines(35, fileWriter, res );
			
			
			fileWriter.append(NEW_LINE_SEPERATOR.toString());
			fileWriter.append(FILE_HEADER.toString());
			fileWriter.append(NEW_LINE_SEPERATOR.toString());
			
		
			appendLinesTotalOps(0, fileWriter, res );
			appendLinesTotalOps(1, fileWriter, res );
			appendLinesTotalOps(2, fileWriter, res );
			appendLinesTotalOps(3, fileWriter, res );
			fileWriter.append(NEW_LINE_SEPERATOR.toString());
			fileWriter.append(FILE_HEADER.toString());
			fileWriter.append(NEW_LINE_SEPERATOR.toString());
			appendLinesTotalOps(16, fileWriter, res );
			appendLinesTotalOps(17, fileWriter, res );
			appendLinesTotalOps(18, fileWriter, res );
			appendLinesTotalOps(19, fileWriter, res );
			fileWriter.append(NEW_LINE_SEPERATOR.toString());
			fileWriter.append(FILE_HEADER.toString());
			fileWriter.append(NEW_LINE_SEPERATOR.toString());
			appendLinesTotalOps(32, fileWriter, res );
			appendLinesTotalOps(33, fileWriter, res );
			appendLinesTotalOps(34, fileWriter, res );
			appendLinesTotalOps(35, fileWriter, res );
			
			
			fileWriter.append(NEW_LINE_SEPERATOR.toString());
			fileWriter.append(FILE_HEADER.toString());
			fileWriter.append(NEW_LINE_SEPERATOR.toString());
			
		
			appendLinesTime(0, fileWriter, resTime );
			appendLinesTime(1, fileWriter, resTime );
			appendLinesTime(2, fileWriter, resTime );
			appendLinesTime(3, fileWriter, resTime );
			fileWriter.append(NEW_LINE_SEPERATOR.toString());
			fileWriter.append(FILE_HEADER.toString());
			fileWriter.append(NEW_LINE_SEPERATOR.toString());
			appendLinesTime(16, fileWriter, resTime );
			appendLinesTime(17, fileWriter, resTime );
			appendLinesTime(18, fileWriter, resTime );
			appendLinesTime(19, fileWriter, resTime );
			fileWriter.append(NEW_LINE_SEPERATOR.toString());
			fileWriter.append(FILE_HEADER.toString());
			fileWriter.append(NEW_LINE_SEPERATOR.toString());
			appendLinesTime(32, fileWriter, resTime );
			appendLinesTime(33, fileWriter, resTime );
			appendLinesTime(34, fileWriter, resTime );
			appendLinesTime(35, fileWriter, resTime );
			
			System.out.println("CSV file was created successfully!");
			
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/ closing fileWriter !!!");
				e.printStackTrace();
			}
		}
		
	}
	
	
	/*
	 * This method is used to loop through the indexes each time adding 4 to the index to 
	 * increment in such a way that prints data for each type of sort routine. For example, at index 0 it will print
	 * BubbleSort, SelectionSort, InsertionSort, and InsertionCutSort all on ascending lists, and so forth.  
	 */
	public static void appendLines(int iStartVal, FileWriter fileWriter, ArrayList<DblyCrcLinkedList> res ) throws IOException {
		int i; 
	
		fileWriter.append(String.valueOf(res.get(iStartVal).getName()));
		
		for (i = iStartVal; i <= iStartVal + 12; i += 4) {
			fileWriter.append(COMMA_DELIMETER);
			fileWriter.append(String.valueOf(res.get(i).getDataAssign()));
		}
			fileWriter.append(NEW_LINE_SEPERATOR);
	}
	
	public static void appendLinesTotalOps(int iStartVal, FileWriter fileWriter, ArrayList<DblyCrcLinkedList> res ) throws IOException {
		int i; 
	
		fileWriter.append(String.valueOf(res.get(iStartVal).getName()));
		
		for (i = iStartVal; i <= iStartVal + 12; i += 4) {
			fileWriter.append(COMMA_DELIMETER);
			fileWriter.append(String.valueOf(res.get(i).getTotalOps()));
		}
			fileWriter.append(NEW_LINE_SEPERATOR);
	}
	
	public static void appendLinesTime(int iStartVal, FileWriter fileWriter, ArrayList<DblyCrcLinkedList> res ) throws IOException {
		int i; 
		
		fileWriter.append(String.valueOf(res.get(iStartVal).getName()));
		
		for (i = iStartVal; i <= iStartVal + 12; i += 4) {
			fileWriter.append(COMMA_DELIMETER);
			fileWriter.append(String.valueOf(res.get(i).getTiming()));
		}
			fileWriter.append(NEW_LINE_SEPERATOR);
	}
	
}