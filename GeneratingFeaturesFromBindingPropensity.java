import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GeneratingFeaturesFromBindingPropensity {

	public static void main(String[] args) {
		ArrayList<ProteinBindingObject> results = parseFile("bindingPropensity0-8999.txt");
		writeToFile(results, "binding features.csv");
	}	
	private static ProteinBindingObject createObjects(ArrayList<String>input){
		String name;
		String rnaBindingResidues;
		ArrayList<Double> rnaBindingPropensity=new ArrayList<Double>();
		String dnaBindingResidues;
		ArrayList<Double> dnaBindingPropensity=new ArrayList<Double>();
		String proteinBindingResidues;
		ArrayList<Double> proteinBindingPropensity=new ArrayList<Double>();

		
		String line1=input.get(0);
		String line3=input.get(2);
		String line4=input.get(3);
		line4=line4.substring(23,line4.length());
		String line5=input.get(4);
		String line6=input.get(5);
		line6=line6.substring(23,line6.length());
		String line7=input.get(6);
		String line8=input.get(7);
		line8=line8.substring(27,line8.length());
		
		name = line1.substring(1, line1.length());
		rnaBindingResidues=line3.substring(21, line3.length());
		String[] tempRNAPropensity=line4.split(",");
		for(int i=0;i<tempRNAPropensity.length;i++){
			Double tempCurrentPropensity=Double.parseDouble(tempRNAPropensity[i]);
			rnaBindingPropensity.add(tempCurrentPropensity);
		}
		dnaBindingResidues=line5.substring(21, line5.length());
		String[] tempDNAPropensity=line6.split(",");
		for(int i=0;i<tempDNAPropensity.length;i++){
			Double tempCurrentPropensity=Double.parseDouble(tempDNAPropensity[i]);
			dnaBindingPropensity.add(tempCurrentPropensity);
		}
		proteinBindingResidues=line7.substring(25,line7.length());
		String[] tempProteinPropensity=line8.split(",");
		for(int i=0;i<tempProteinPropensity.length;i++){
			Double tempCurrentPropensity=Double.parseDouble(tempProteinPropensity[i]);
			proteinBindingPropensity.add(tempCurrentPropensity);
		}
		
		return new ProteinBindingObject(rnaBindingResidues, rnaBindingPropensity, dnaBindingResidues, dnaBindingPropensity, proteinBindingResidues, proteinBindingPropensity, name);
	}
	 /**
    get files
    */
   private static ArrayList<ProteinBindingObject> parseFile(String fileName) {
       ArrayList<ProteinBindingObject> resultArrayList = new ArrayList<ProteinBindingObject>();

       try {
    	   File file = new File(fileName);
    	   BufferedReader br = new BufferedReader(new FileReader(file));
    	   BufferedReader br2 = new BufferedReader(new FileReader(file));
    	   String line;
    	   String line2 = "";
    	   
    	   br2.readLine();
    	   
    	   while((line = br.readLine()) != null){
    		   ArrayList<String> tempInput=new ArrayList<String>();
    		   if (line.startsWith(">")){
    			   tempInput.add(line);
    			   while (((line2 = br2.readLine())!=null)&&!(line2.startsWith(">"))) {
    				   br.readLine();
    				   tempInput.add(line2);
    			   }
    			   
    			resultArrayList.add(createObjects(tempInput));   
    		   }
    	   }
		br.close();
       }
       catch (Exception e) {
    	   e.printStackTrace();
       }
       return resultArrayList;
   }
   private static Boolean writeToFile(ArrayList<ProteinBindingObject> objects, String filename) {
       BufferedWriter bw;
       try {
           bw = new BufferedWriter(new FileWriter(filename));
           bw.write("Name,numBindingResiduesRNA,numResidueGrpsRNA,avgPropensityRNA,numBindingResiduesDNA,numResidueGrpsDNA,avgPropensityDNA,numBindingResiduesPrt,numResidueGrpsPrt,avgPropensityPrt,whichResidueHighest,whichPropensityHighest");
           bw.newLine();
           for(int i = 0; i < objects.size(); i++) {
               bw.write(objects.get(i).features());
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
