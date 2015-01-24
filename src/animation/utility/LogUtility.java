package animation.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class LogUtility {
	
	public static void printLog(final String logString)
	{
		BufferedWriter bw = null;
		try {
			File f = new File ("E://Log/LogFile.txt");
			bw = new BufferedWriter(new FileWriter(f, true));
			bw.write(logString);
			bw.newLine();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				bw.close();
			}catch(Exception e1) {
				
			}
		}
		
	}
	
	public static void printConsole(String consoleString) {
		System.out.println(consoleString);
	}
	
}
