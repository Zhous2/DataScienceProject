import java.io.*;
import java.util.ArrayList;

public class AddClass {

    public static void main(String[] args) {
//        String featureGenerationFileName = "autoGearyFull.csv";
//        String outputFileName = "autoGearyComplete.csv";

        ArrayList<String> featureGenerationResults = parseFile("0-8999.csv"); //add csv file here
        ArrayList<String> classList = parseFileGetClass("acidData.csv");

        ArrayList<String> featureGenerationWithClass = combineFeatureGenerationWithClassList(featureGenerationResults, classList);

        writeToFile(featureGenerationWithClass,"CTDwithClass.csv");  //output name you want
    }

    private static ArrayList<String> combineFeatureGenerationWithClassList(ArrayList<String> featureGenerationResults, ArrayList<String> classList) {
        ArrayList<String> result = new ArrayList<>();
        if(featureGenerationResults.size() != classList.size() + 1)
            throw new IllegalArgumentException("featureGenerationResults.size != classList.sizze + 1. The +1 is to account for the header in featureGenerationResults");
        result.add(featureGenerationResults.get(0) + ",class");
        for(int i = 1; i < featureGenerationResults.size(); i++) {
            result.add(featureGenerationResults.get(i) + "," + classList.get(i - 1));
        }
        return result;
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
            while ((line = br.readLine()) != null) {
                resultArrayList.add(line);
            }
            br.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return resultArrayList;
    }

    /**
     get files
     */
    private static ArrayList<String> parseFileGetClass(String fileName) {
        ArrayList<String> classArrayList = new ArrayList<>();

        try {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineInfo = line.split(",");
                classArrayList.add(lineInfo[1]);
            }
            br.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return classArrayList;
    }

    /**
     * write files
     * stringArrayList.size() should equal classArrayList.size() if file was parsed correctly
     */
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
