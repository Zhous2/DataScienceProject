
import java.io.*;
import java.util.ArrayList;

public class HydrophobicityCSV {

	 public static void main(String[] args) {
	        ArrayList<String> hydrophobicity = parseFile("grandAverageOfHydrophobicity0-999.txt");
	        ArrayList<String> hydrophobicity1 = parseFile("grandAverageOfHydrophobicity1000-1999.txt");
	        ArrayList<String> hydrophobicity2 = parseFile("grandAverageOfHydrophobicity2000-2999.txt");
	        ArrayList<String> hydrophobicity3 = parseFile("grandAverageOfHydrophobicity3000-3999.txt");
	        ArrayList<String> hydrophobicity4 = parseFile("grandAverageOfHydrophobicity4000-4999.txt");
	        ArrayList<String> hydrophobicity5 = parseFile("grandAverageOfHydrophobicity5000-5999.txt");
	        ArrayList<String> hydrophobicity6 = parseFile("grandAverageOfHydrophobicity6000-6999.txt");
	        ArrayList<String> hydrophobicity7 = parseFile("grandAverageOfHydrophobicity7000-7999.txt");
	        ArrayList<String> hydrophobicity8 = parseFile("grandAverageOfHydrophobicity8000-8999.txt");
	        
	        hydrophobicity.addAll(hydrophobicity1);
	        hydrophobicity.addAll(hydrophobicity2);
	        hydrophobicity.addAll(hydrophobicity3);
	        hydrophobicity.addAll(hydrophobicity4);
	        hydrophobicity.addAll(hydrophobicity5);
	        hydrophobicity.addAll(hydrophobicity6);
	        hydrophobicity.addAll(hydrophobicity7);
	        hydrophobicity.addAll(hydrophobicity8);
	        
	        writeToCSVFile(hydrophobicity, "hydrophobicity.csv");
	    }

	    /**
	     get files
	     */
	    private static ArrayList<String> parseFile(String fileName) {
	        ArrayList<String> resultArrayList = new ArrayList<>();

	        try {
	            File file = new File(fileName);
	            BufferedReader br = new BufferedReader(new FileReader(file));
	            String line;
	            line = br.readLine();
	            line = br.readLine();
	            while ((line = br.readLine()) != null) {
	            	System.out.println(line);
	            	resultArrayList.add(line);
	            	br.readLine();
	            }
	            br.close();
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        return resultArrayList;
	    }

	    /**
	     * write files
	     * stringArrayList.size() should equal classArrayList.size() if file was parsed correctly
	     */
	    private static Boolean writeToCSVFile(ArrayList<String> hydrophobicity, String filename) {
	        BufferedWriter bw;
	        try {
	            bw = new BufferedWriter(new FileWriter(filename));
	            bw.write("hydrophobicity");
	            for(int i = 0; i < hydrophobicity.size(); i++) {
	                bw.newLine();
	                String temp = hydrophobicity.get(i).toString();
	                bw.write(temp);
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