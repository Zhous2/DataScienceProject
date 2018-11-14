import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CombineFastaFiles {

	public static void main(String[] args) {
		ArrayList<String> files=new ArrayList<String>();
		files.add("bindingPropensity0-4999.txt");
		files.add("bindingPropensity5000-8999.txt");
		
		ArrayList<String> fileInfo = parseFile(files);
		writeToFile(fileInfo, "bindingPropensity0-8999.txt");

		
		
		
	}
	 /**
    get files
    */
   private static ArrayList<String> parseFile(ArrayList<String> fileNames) {
       ArrayList<String> resultArrayList = new ArrayList<String>();
      
       for (String currentFile: fileNames){
           try {
               File file = new File(currentFile);
               BufferedReader br = new BufferedReader(new FileReader(file));
               String line;
               while ((line = br.readLine()) != null) {
                   resultArrayList.add(line);
               }
               br.close();
           }
           catch (Exception e) {
               e.printStackTrace();
           }
       }
       return resultArrayList;
   }
   private static Boolean writeToFile(ArrayList<String> stringArrayList, String filename) {
       BufferedWriter bw;
       try {
           bw = new BufferedWriter(new FileWriter(filename));
           for(int i = 0; i < stringArrayList.size(); i++) {
               bw.write(stringArrayList.get(i));
               bw.newLine();
           }
           bw.close();
       }
       catch (IOException e) {
           System.out.println("there was an IO exception: " + e);
           return false;
       }
       return true;
   }

}
