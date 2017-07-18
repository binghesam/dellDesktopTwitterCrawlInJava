package twitter4JBing;

//import java.util.*;
/*
 * this file is written in ascii coding not utf-8 coding files for the processing.
 */
import java.io.*;
public class writeStringToFile {
	 public static void writeStringToFileProcess(String filePathAndName, String stringToBeWritten){    
	        try
	        {
	            String filename= filePathAndName;
	            boolean append = true;
	            FileWriter fw = new FileWriter(filename,append);
	            fw.write(stringToBeWritten);//appends the string to the file
	            fw.write("\n");
	            fw.close();
	        }
	        catch(IOException ioe)
	        {
	            System.err.println("IOException: " + ioe.getMessage());
	            System.out.println("Stop in write files");
	            //System.exit(-1);//for stop the processing
	        }
	    }
}
