import java.io.*;
import java.util.ArrayList;

public class ParseFastaTo1000SeqFiles {

    public static void main(String[] args) {
        ArrayList<ArrayList<String>> temp = parseFile("projectFastaFormat.fasta");

        ArrayList<String> fastaFormatFirst = temp.get(0);
        ArrayList<String> fastaFormatSecond = temp.get(1);
        ArrayList<String> fastaFormatThird = temp.get(2);
        ArrayList<String> fastaFormatFourth = temp.get(3);
        ArrayList<String> fastaFormatFifth = temp.get(4);
        ArrayList<String> fastaFormatSixth = temp.get(5);
        ArrayList<String> fastaFormatSeventh = temp.get(6);
        ArrayList<String> fastaFormatEighth = temp.get(7);
        ArrayList<String> fastaFormatNinth = temp.get(8);
        ArrayList<String> fastaFormatTenth = temp.get(9);

        System.out.println("Sizes:");//2 because each entry is 2 lines. the ex: 1} >seq1 2) asfhsfdcsdnjfsaj
        System.out.println("First:" + fastaFormatFirst.size()/2);
        System.out.println("Second:" + fastaFormatSecond.size()/2);
        System.out.println("Third:" + fastaFormatThird.size()/2);
        System.out.println("Fourth:" + fastaFormatFourth.size()/2);
        System.out.println("Fifth:" + fastaFormatFifth.size()/2);
        System.out.println("Sixth:" + fastaFormatSixth.size()/2);
        System.out.println("Seventh:" + fastaFormatSeventh.size()/2);
        System.out.println("Eighth:" + fastaFormatEighth.size()/2);
        System.out.println("Ninth:" + fastaFormatNinth.size()/2);
        System.out.println("Tenth:" + fastaFormatTenth.size()/2);


        writeToFile(fastaFormatFirst,"projectFastaFormat0-999.fasta");
        writeToFile(fastaFormatSecond,"projectFastaFormat1000-1999.fasta");
        writeToFile(fastaFormatThird,"projectFastaFormat2000-2999.fasta");
        writeToFile(fastaFormatFourth,"projectFastaFormat3000-3999.fasta");
        writeToFile(fastaFormatFifth,"projectFastaFormat4000-4999.fasta");
        writeToFile(fastaFormatSixth,"projectFastaFormat5000-5999.fasta");
        writeToFile(fastaFormatSeventh,"projectFastaFormat6000-6999.fasta");
        writeToFile(fastaFormatEighth,"projectFastaFormat7000-7999.fasta");
        writeToFile(fastaFormatNinth,"projectFastaFormat8000-8999.fasta");
        writeToFile(fastaFormatTenth,"projectFastaFormat9000-9999.fasta");
    }

    /**
     get files
     */
    private static ArrayList<ArrayList<String>> parseFile(String fileName) {
        ArrayList<ArrayList<String>> resultArrayList = new ArrayList<>();
        ArrayList<String> fastaFormatFirst = new ArrayList<>();
        ArrayList<String> fastaFormatSecond = new ArrayList<>();
        ArrayList<String> fastaFormatThird = new ArrayList<>();
        ArrayList<String> fastaFormatFourth = new ArrayList<>();
        ArrayList<String> fastaFormatFifth = new ArrayList<>();
        ArrayList<String> fastaFormatSixth = new ArrayList<>();
        ArrayList<String> fastaFormatSeventh = new ArrayList<>();
        ArrayList<String> fastaFormatEighth = new ArrayList<>();
        ArrayList<String> fastaFormatNinth = new ArrayList<>();
        ArrayList<String> fastaFormatTenth = new ArrayList<>();

        try {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String numberLine = line;
                String sequence = br.readLine();
                if(sequence == null)
                    throw  new IllegalArgumentException("The file did not have a sequence appear after a \"seqI\"");
                String temp = numberLine.substring(4);//>seqXXXX
                int seqNumber = Integer.parseInt(temp);

                if(seqNumber < 1000) {
                    fastaFormatFirst.add(numberLine);
                    fastaFormatFirst.add(sequence);
                }
                else if(seqNumber < 2000) {
                    fastaFormatSecond.add(numberLine);
                    fastaFormatSecond.add(sequence);
                }
                else if(seqNumber < 3000) {
                    fastaFormatThird.add(numberLine);
                    fastaFormatThird.add(sequence);
                }
                else if(seqNumber < 4000) {
                    fastaFormatFourth.add(numberLine);
                    fastaFormatFourth.add(sequence);
                }
                else if(seqNumber < 5000) {
                    fastaFormatFifth.add(numberLine);
                    fastaFormatFifth.add(sequence);
                }
                else if(seqNumber < 6000) {
                    fastaFormatSixth.add(numberLine);
                    fastaFormatSixth.add(sequence);
                }
                else if(seqNumber < 7000) {
                    fastaFormatSeventh.add(numberLine);
                    fastaFormatSeventh.add(sequence);
                }
                else if(seqNumber < 8000) {
                    fastaFormatEighth.add(numberLine);
                    fastaFormatEighth.add(sequence);
                }
                else if(seqNumber < 9000) {
                    fastaFormatNinth.add(numberLine);
                    fastaFormatNinth.add(sequence);
                }
                else if(seqNumber < 10000) {
                    fastaFormatTenth.add(numberLine);
                    fastaFormatTenth.add(sequence);
                }
            }
            br.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        resultArrayList.add(fastaFormatFirst);
        resultArrayList.add(fastaFormatSecond);
        resultArrayList.add(fastaFormatThird);
        resultArrayList.add(fastaFormatFourth);
        resultArrayList.add(fastaFormatFifth);
        resultArrayList.add(fastaFormatSixth);
        resultArrayList.add(fastaFormatSeventh);
        resultArrayList.add(fastaFormatEighth);
        resultArrayList.add(fastaFormatNinth);
        resultArrayList.add(fastaFormatTenth);

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
