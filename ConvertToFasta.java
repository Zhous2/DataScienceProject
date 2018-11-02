import java.io.*;
import java.util.ArrayList;

public class ConvertToFasta {
    public static void main(String[] args) {
        ArrayList<ArrayList<String>> temp = parseFile("acidData.csv");
        ArrayList<String> acidSequenceArrayList = temp.get(0);
        ArrayList<String> fastaFormat = createFasta(acidSequenceArrayList);
        writeToFile(fastaFormat,"projectFastaFormat.fasta");
    }

    private static ArrayList<String> createFasta(ArrayList<String> acidSequenceArrayList) {
        ArrayList<String> fastaFormat = new ArrayList<>();
        for(int i = 0; i < acidSequenceArrayList.size(); i++) {
            String seqName = ">seq" + i;
            fastaFormat.add(seqName);
            fastaFormat.add(acidSequenceArrayList.get(i));
        }
        return fastaFormat;
    }

    /**
     get files
     */
    private static ArrayList<ArrayList<String>> parseFile(String fileName) {
        ArrayList<ArrayList<String>> resultArrayList = new ArrayList<>();
        ArrayList<String> acidArrayList = new ArrayList<>();
        ArrayList<String> classArrayList = new ArrayList<>();

        try {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineInfo = line.split(",");
                acidArrayList.add(lineInfo[0]);
                classArrayList.add(lineInfo[1]);
            }
            br.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        resultArrayList.add(acidArrayList);
        resultArrayList.add(classArrayList);
        return resultArrayList;
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
