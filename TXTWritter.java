import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.IOException;

/*
 * 
 * The way I am using this class will not Overwrite the TXT file created it will append!!!
 * So, if you need to rerun my main tester class you should delete the TXT file that it created the first time you ran it. 
 * 
 * 
 * 
 */

public class TXTWritter {
	
	private String path;
	private boolean addToFile = false;
	
	
	public TXTWritter(String filePath) {
		path = filePath;
	}
	
	public TXTWritter(String filePath, boolean addTo) {
		path = filePath;
		addToFile = addTo;
	}
	
	public void writeToFile(String textLine) throws IOException {
		
		FileWriter write = new FileWriter(path, addToFile);
		PrintWriter printLine = new PrintWriter(write);
		printLine.printf("%s" + "%n", textLine);
		printLine.close();
	}
	
	
	public static void WriteRawData(String fileName, ArrayList<DblyCrcLinkedList> res, ArrayList<DblyCrcLinkedList> resTime) throws IOException {
		
		try {
			TXTWritter data = new TXTWritter(fileName, true);
			data.writeToFile("Algorithm_RAW_DATA \n");
			data.writeToFile("The data is in the following order: \n");
			data.writeToFile("Sort_Name, Size, Data_Assignments, Data_Compasisons, Loop_Control_Assignments, Loop_Control_Comparisons, Other, Total_Oporations \n\n\n");
			
		
			// Ascending
			data.writeToFile("ASCENDING ORDER LISTS \n");
			loopRawData(0, res, data);
			loopRawData(1, res, data);
			loopRawData(2, res, data);
			loopRawData(3, res, data);
			data.writeToFile("\n\n");
		
			// Descending
			data.writeToFile("DESCENDING ORDER LISTS \n");
			loopRawData(16, res, data);
			loopRawData(17, res, data);
			loopRawData(18, res, data);
			loopRawData(19, res, data);
			data.writeToFile("\n\n");

			// Random
			data.writeToFile("RANDOM ORDER LISTS \n");
			loopRawData(32, res, data);
			loopRawData(33, res, data);
			loopRawData(34, res, data);
			loopRawData(35, res, data);
			data.writeToFile("\n\n");
			
			data.writeToFile("TIMING DATA IN SECONDS: \n\n");
			data.writeToFile("The data is in the following order: \n");
			data.writeToFile("Name, Size, Time \n\n\n");
			
			// Ascending
						data.writeToFile("ASCENDING ORDER LISTS \n");
						loopRawDataTime(0, resTime, data);
						loopRawDataTime(1, resTime, data);
						loopRawDataTime(2, resTime, data);
						loopRawDataTime(3, resTime, data);
						data.writeToFile("\n\n");
					
						// Descending
						data.writeToFile("DESCENDING ORDER LISTS \n");
						loopRawDataTime(16, resTime, data);
						loopRawDataTime(17, resTime, data);
						loopRawDataTime(18, resTime, data);
						loopRawDataTime(19, resTime, data);
						data.writeToFile("\n\n");

						// Random
						data.writeToFile("RANDOM ORDER LISTS \n");
						loopRawDataTime(32, resTime, data);
						loopRawDataTime(33, resTime, data);
						loopRawDataTime(34, resTime, data);
						loopRawDataTime(35, resTime, data);
						data.writeToFile("\n\n");
			
			
			
			
			
			System.out.println("txt file created succefully!");
		} catch (Exception e) {
			System.out.println("error making txt file");
		}
	}
	
	
	public static void loopRawData(int iStartVal, ArrayList<DblyCrcLinkedList> res, TXTWritter data) {
		int i; 
		
		for (i = iStartVal; i <= iStartVal + 12; i += 4) {
			try {
				data.writeToFile(res.get(i).toStringRaw() + "\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void loopRawDataTime(int iStartVal, ArrayList<DblyCrcLinkedList> res, TXTWritter data) {
		int i; 
		
		for (i = iStartVal; i <= iStartVal + 12; i += 4) {
			try {
				data.writeToFile(res.get(i).toStringRawTime());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
