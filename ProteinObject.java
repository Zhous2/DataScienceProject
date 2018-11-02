import java.util.ArrayList;
import java.util.Arrays;

public class ProteinObject {

    private int[] numberOfAminoAcidsForEachProtein;
    private String proteinClass;

    public ProteinObject() {
        this.numberOfAminoAcidsForEachProtein = new int[26];
        this.proteinClass = "DNA";
    }

    public ProteinObject(int[] numberOfAminoAcidsForEachProtein, String proteinClass) {
        this.numberOfAminoAcidsForEachProtein = numberOfAminoAcidsForEachProtein;
        this.proteinClass = proteinClass;
    }

    public int[] getNumberOfAminoAcidsForEachProtein() {
        return numberOfAminoAcidsForEachProtein;
    }

    public void setNumberOfAminoAcidsForEachProtein(int[] numberOfAminoAcidsForEachProtein) {
        this.numberOfAminoAcidsForEachProtein = numberOfAminoAcidsForEachProtein;
    }

    public String getProteinClass() {
        return proteinClass;
    }

    public void setProteinClass(String proteinClass) {
        this.proteinClass = proteinClass;
    }

    @Override
    public String toString() {
        return "ProteinObject{" +
                "numberOfAminoAcidsForEachProtein=" + Arrays.toString(numberOfAminoAcidsForEachProtein) +
                ", proteinClass='" + proteinClass + '\'' +
                '}';
    }
}
