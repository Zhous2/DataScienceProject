import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<ArrayList<String>> temp = parseFile("acidData.csv");
        ArrayList<String> acidSequenceArrayList = temp.get(0);
        ArrayList<String> classArrayList = temp.get(1);
        ArrayList<int[]> numberOfAminoAcidsForEachProtein = getNumberOfAminoAcidsForEachProtein(acidSequenceArrayList);
        ArrayList<ProteinObject> allProteins = getAllProteins(numberOfAminoAcidsForEachProtein, classArrayList);
        ArrayList<ProteinObject> DNAProteins = getProteinsFromClass(allProteins, "DNA");
        ArrayList<ProteinObject> RNAProteins = getProteinsFromClass(allProteins, "RNA");
        ArrayList<ProteinObject> DRNAProteins = getProteinsFromClass(allProteins, "DRNA");
        ArrayList<ProteinObject> nonDRNAProteins = getProteinsFromClass(allProteins, "nonDRNA");
        writeToCSVFile(allProteins, "projectAllClasses.csv");
        writeToCSVFile(DNAProteins,"projectDNAonly.csv");
        writeToCSVFile(RNAProteins,"projectRNAonly.csv");
        writeToCSVFile(DRNAProteins,"projectDRNAonly.csv");
        writeToCSVFile(nonDRNAProteins,"projectnonDRNAonly.csv");
    }

    private static ArrayList<ProteinObject> getAllProteins(ArrayList<int[]> numberOfAminoAcidsForEachProtein, ArrayList<String> classArrayList) {
        if(numberOfAminoAcidsForEachProtein.size() != classArrayList.size())
            throw new IllegalArgumentException("stringArrayList.size() should equal classArrayList.size() if file was parsed correctly. but they werent.");
        ArrayList<ProteinObject> allProteins = new ArrayList<>();
        for(int i = 0; i < numberOfAminoAcidsForEachProtein.size(); i++) {
            ProteinObject proteinObject = new ProteinObject(numberOfAminoAcidsForEachProtein.get(i), classArrayList.get(i));
            allProteins.add(proteinObject);
        }
        return allProteins;
    }

    private static ArrayList<ProteinObject> getProteinsFromClass(ArrayList<ProteinObject> allProteins, String proteinClass) {
        ArrayList<ProteinObject> proteinsFromClass = new ArrayList<>();
        for(int i = 0; i < allProteins.size(); i++) {
            if(allProteins.get(i).getProteinClass().equals(proteinClass)) {
                ProteinObject proteinObject = allProteins.get(i);
                proteinsFromClass.add(proteinObject);
            }
        }
        return proteinsFromClass;
    }

    private static ArrayList<int[]> getNumberOfAminoAcidsForEachProtein(ArrayList<String> stringArrayList) {
        ArrayList<int[]> numberOfAminoAcidsForEachProtein = new ArrayList<>();
        for(int i = 0; i < stringArrayList.size(); i++) {
            int[] letterCount = calcLetterCount(stringArrayList.get(i));
            System.out.println(Arrays.toString(letterCount));
            numberOfAminoAcidsForEachProtein.add(letterCount);
        }
        return numberOfAminoAcidsForEachProtein;
    }
    
    private static int[] calcLetterCount(String s) {
        int[] result = new int[26];
        for( int i =0; i < s.length(); i++) {
            if('A' == s.charAt(i)) {
                result[0]++;
            }
            else if('B' == s.charAt(i)) {
                result[1]++;
            }
            else if('C' == s.charAt(i)) {
                result[2]++;
            }
            else if('D' == s.charAt(i)) {
                result[3]++;
            }
            else if('E' == s.charAt(i)) {
                result[4]++;
            }
            else if('F' == s.charAt(i)) {
                result[5]++;
            }
            else if('G' == s.charAt(i)) {
                result[6]++;
            }
            else if('H' == s.charAt(i)) {
                result[7]++;
            }
            else if('I' == s.charAt(i)) {
                result[8]++;
            }
            else if('J' == s.charAt(i)) {
                result[9]++;
            }
            else if('K' == s.charAt(i)) {
                result[10]++;
            }
            else if('L' == s.charAt(i)) {
                result[11]++;
            }
            else if('M' == s.charAt(i)) {
                result[12]++;
            }
            else if('N' == s.charAt(i)) {
                result[13]++;
            }
            else if('O' == s.charAt(i)) {
                result[14]++;
            }
            else if('P' == s.charAt(i)) {
                result[15]++;
            }
            else if('Q' == s.charAt(i)) {
                result[16]++;
            }
            else if('R' == s.charAt(i)) {
                result[17]++;
            }
            else if('S' == s.charAt(i)) {
                result[18]++;
            }
            else if('T' == s.charAt(i)) {
                result[19]++;
            }
            else if('U' == s.charAt(i)) {
                result[20]++;
            }
            else if('V' == s.charAt(i)) {
                result[21]++;
            }
            else if('W' == s.charAt(i)) {
                result[22]++;
            }
            else if('X' == s.charAt(i)) {
                result[23]++;
            }
            else if('Y' == s.charAt(i)) {
                result[24]++;
            }
            else if('Z' == s.charAt(i)) {
                result[25]++;
            }
        }
        return result;
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
    private static Boolean writeToCSVFile(ArrayList<ProteinObject> proteinArrayList, String filename) {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(filename));
            bw.write("F1" + ", " + "F2" + ", " + "F3" + ", " + "F4" + ", " + "F5" + ", " + "F6" + ", " + "F7" + ", " + "F8" + ", " + "F9" + ", " + "F10" + ", " + "F11" + ", " + "F12" + ", " + "F13" + ", "
                    + "F14" + ", " + "F15" + ", " + "F16" + ", " + "F17" + ", " + "F18" + ", " + "F19" + ", " + "F20" + ", " + "F21" + ", " + "F22" + ", " + "F23" + ", " + "F24" + ", " + "F25" + ", " + "F26" + ", " +  "Class");
            for(int i = 0; i < proteinArrayList.size(); i++) {
                bw.newLine();
                int[] temp = proteinArrayList.get(i).getNumberOfAminoAcidsForEachProtein();
                bw.write(temp[0] + ", " + temp[1] + ", " + temp[2] + ", " + temp[3] + ", " + temp[4] + ", " + temp[5] + ", " + temp[6] + ", " + temp[7] + ", " + temp[8] + ", " + temp[9] + ", " + temp[10] + ", " + temp[11] + ", " +
                                temp[12] + ", " + temp[13] + ", " + temp[14] + ", " + temp[15] + ", " + temp[16] + ", " + temp[17] + ", " + temp[18] + ", " + temp[19] + ", " + temp[20] + ", " + temp[21] + ", " + temp[22] + ", " +
                                temp[23] + ", " + temp[24] + ", " + temp[25] + ", " + proteinArrayList.get(i).getProteinClass());
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