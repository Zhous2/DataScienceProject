import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MainTwo {
    public static void main(String[] args) {
        ArrayList<ProteinObjectTwo> temp = parseFile("comboTestData.csv");
        writeToCSVFile(temp, "testDataPrediction1.csv");
    }
//    public ProteinObjectTwo( String currentClassNaive,  String currentClassKNN, double[] currentNaiveConfidence, double[] currentKNNConfidence) {

    /**
     get files
     */
    private static ArrayList<ProteinObjectTwo> parseFile(String fileName) {
        ArrayList<ProteinObjectTwo> resultArrayList = new ArrayList<ProteinObjectTwo>();

        try {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {            	
                String[] lineInfo = line.split(",");
                System.out.println(line);
                String currentClassNaive=lineInfo[2];
                String currentClassKNN=lineInfo[3];
            	double[] currentNaiveConfidence=new double[4];
            	currentNaiveConfidence[0]=Double.parseDouble(lineInfo[4]);
            	currentNaiveConfidence[1]=Double.parseDouble(lineInfo[5]);
            	currentNaiveConfidence[2]=Double.parseDouble(lineInfo[6]);
            	currentNaiveConfidence[3]=Double.parseDouble(lineInfo[7]);
            	double[] currentKNNConfidence = new double[4];
            	currentKNNConfidence[0]=Double.parseDouble(lineInfo[8]);
            	currentKNNConfidence[1]=Double.parseDouble(lineInfo[9]);
            	currentKNNConfidence[2]=Double.parseDouble(lineInfo[10]);
            	currentKNNConfidence[3]=Double.parseDouble(lineInfo[11]);
            	ProteinObjectTwo currentProtein = new ProteinObjectTwo(currentClassNaive,currentClassKNN,currentNaiveConfidence,currentKNNConfidence);
            	currentProtein.calculateProteinClass();
            	resultArrayList.add(currentProtein);
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
    private static Boolean writeToCSVFile(ArrayList<ProteinObjectTwo> proteinArrayList, String filename) {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(filename));
            bw.write("PredictedClass");
            for(int i = 0; i < proteinArrayList.size(); i++) {
                bw.newLine();
                bw.write(""+proteinArrayList.get(i).toString());
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