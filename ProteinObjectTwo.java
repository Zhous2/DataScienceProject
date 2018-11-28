import java.util.ArrayList;
import java.util.Arrays;

public class ProteinObjectTwo {

    private String proteinClass;
    private String predictedClassNaive;
    private String predictedClassKNN;
    private double[] confidenceValuesNaive; //nondrna, drna, rna, dna
    private double[] confidenceValuesKNN;//nondrna, drna, rna, dna
    private double threshold;

    public ProteinObjectTwo() {
        this.proteinClass = "";
        this.predictedClassNaive="";
        this.predictedClassKNN="";
        this.confidenceValuesNaive = new double[4];
        this.confidenceValuesKNN = new double[4];
        this.threshold=0.600;
    }

    public ProteinObjectTwo( String currentClassNaive,  String currentClassKNN, double[] currentNaiveConfidence, double[] currentKNNConfidence) {
        this.proteinClass = "";
        this.predictedClassNaive=currentClassNaive;
        this.predictedClassKNN=currentClassKNN;
        this.confidenceValuesNaive = currentNaiveConfidence;
        this.confidenceValuesKNN = currentKNNConfidence;
        this.threshold=0.600;
    }

    public void setProteinClass(String newProteinClass){
    	this.proteinClass=newProteinClass;
    }

    public String getPredictedClassNaive() {
        return predictedClassNaive;
    }
    
    public String getPredictedClassKNN() {
        return predictedClassKNN;
    }
    
    public String getProteinClass() {
        return proteinClass;
    }

    public void calculateProteinClass(){
    	if(confidenceValuesKNN[0]>=0.95){
    		proteinClass="nonDRNA";
    	}else if((predictedClassNaive.equalsIgnoreCase("drna"))&&(confidenceValuesNaive[1]>threshold)){
    		proteinClass="DRNA";
    	}else if(predictedClassKNN.equalsIgnoreCase("dna")||predictedClassKNN.equalsIgnoreCase("rna")||predictedClassKNN.equalsIgnoreCase("nondrna")){
    		proteinClass=predictedClassKNN;
    	}else{
    		double max=confidenceValuesNaive[0];
    		String currentPredictedClass=predictedClassNaive;
    		for(int i = 0; i<confidenceValuesNaive.length; i++){
    			if(confidenceValuesNaive[i]>max){
    				max = confidenceValuesNaive[i];
    			}
    		}
    		for(int i = 0; i<confidenceValuesKNN.length; i++){
    			if(confidenceValuesKNN[i]>max){
    				max = confidenceValuesKNN[i];
    				currentPredictedClass=predictedClassKNN;
    			}
    		}
    		proteinClass=currentPredictedClass;
    	}
    }

    @Override
    public String toString() {
        return ""+proteinClass;
    }
}
