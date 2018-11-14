import java.util.ArrayList;
import java.util.Arrays;

//type 0 is rnaBinding, type 1 is dnaBinding, type 2 is proteinBinding

public class ProteinBindingObject {

	private String rnaBindingResidues;
	private ArrayList<Double> rnaBindingPropensity;
	private String dnaBindingResidues;
	private ArrayList<Double> dnaBindingPropensity;
	private String proteinBindingResidues;
	private ArrayList<Double> proteinBindingPropensity;
	private String name;
	
	public ProteinBindingObject(String rNABindingResidues, ArrayList<Double> rNABindingPropensity, String dNABindingResidues,
			ArrayList<Double> dNABindingPropensity, String pRoteinBindingResidues, ArrayList<Double> pRoteinBindingPropensity, String givenName) {
		super();
		this.rnaBindingResidues = rNABindingResidues;
		this.rnaBindingPropensity = rNABindingPropensity;
		this.dnaBindingResidues = dNABindingResidues;
		this.dnaBindingPropensity = dNABindingPropensity;
		this.proteinBindingResidues = pRoteinBindingResidues;
		this.proteinBindingPropensity = pRoteinBindingPropensity;
		this.name=givenName;
	}
	
	public String conversionOfTypeString(int type){
		String thingToCount = "";

		switch(type){
		case 0: thingToCount =rnaBindingResidues;
			break;
		case 1: thingToCount = dnaBindingResidues;
			break;
		default: thingToCount = proteinBindingResidues;
			break;
		}
		return thingToCount;
	}
	
	public ArrayList<Double> conversionOfTypeArray(int type){
		ArrayList<Double> thingToCount = new ArrayList<Double>(); 

		switch(type){
		case 0: thingToCount =rnaBindingPropensity;
			break;
		case 1: thingToCount = dnaBindingPropensity;
			break;
		default: thingToCount = proteinBindingPropensity;
			break;
		}
		return thingToCount;
	}
	
	public int numBindingResidues(int type){
		String thingToCount = conversionOfTypeString(type);
		int count = 0;
		for(int i=0; i<thingToCount.length();i++){
			if(thingToCount.charAt(i)=='1'){
				count++;
			}
		}
		return count;
	}
	public int numResidueGrps(int type){
		String thingToCount = conversionOfTypeString(type);
		int count = 0;
		for(int i =0; i<thingToCount.length();i++){
			if(thingToCount.charAt(i)=='1'){
				count++;
				while(((i+1)<thingToCount.length())&&(thingToCount.charAt(i+1)=='1')){
					i++;
				}
			}
		}
		return count;
	}
	public int whichResidueHighest(){
		int countZero=numBindingResidues(0);
		int countOne=numBindingResidues(1);
		int countTwo=numBindingResidues(2);
		int[] tempArray={countZero,countOne,countTwo};
		Arrays.sort(tempArray);
		int highestResidues = tempArray[2];
		int typeHighest = -1;
		if(highestResidues==countZero){
			typeHighest=0;
		}else if(highestResidues==countOne){
			typeHighest=1;
		}else if(highestResidues==countTwo){
			typeHighest=2;
		}
		return typeHighest;
	}
	public double avgPropensity(int type){
		ArrayList<Double> thingToCount = conversionOfTypeArray(type);
		double total=0;
		for(double currentNum:thingToCount){
			total+=currentNum;
		}
		total=total/thingToCount.size();
		return total;
	}
	public int whichPropensityHighest(){
		double countZero=avgPropensity(0);
		double countOne=avgPropensity(1);
		double countTwo=avgPropensity(2);
		double[] tempArray={countZero,countOne,countTwo};
		Arrays.sort(tempArray);
		double highestResidues = tempArray[2];
		int typeHighest = -1;
		if(highestResidues==countZero){
			typeHighest=0;
		}else if(highestResidues==countOne){
			typeHighest=1;
		}else if(highestResidues==countTwo){
			typeHighest=2;
		}
		return typeHighest;
	}

	/**
	 * @return the rnaBindingResidues
	 */
	public String getRnaBindingResidues() {
		return rnaBindingResidues;
	}
	/**
	 * @param rnaBindingResidues the rnaBindingResidues to set
	 */
	public void setRnaBindingResidues(String rnaBindingResidues) {
		this.rnaBindingResidues = rnaBindingResidues;
	}
	/**
	 * @return the rnaBindingPropensity
	 */
	public ArrayList<Double> getRnaBindingPropensity() {
		return rnaBindingPropensity;
	}
	/**
	 * @param rnaBindingPropensity the rnaBindingPropensity to set
	 */
	public void setRnaBindingPropensity(ArrayList<Double> rnaBindingPropensity) {
		this.rnaBindingPropensity = rnaBindingPropensity;
	}
	/**
	 * @return the dnaBindingResidues
	 */
	public String getDnaBindingResidues() {
		return dnaBindingResidues;
	}
	/**
	 * @param dnaBindingResidues the dnaBindingResidues to set
	 */
	public void setDnaBindingResidues(String dnaBindingResidues) {
		this.dnaBindingResidues = dnaBindingResidues;
	}
	/**
	 * @return the dnaBindingPropensity
	 */
	public ArrayList<Double> getDnaBindingPropensity() {
		return dnaBindingPropensity;
	}
	/**
	 * @param dnaBindingPropensity the dnaBindingPropensity to set
	 */
	public void setDnaBindingPropensity(ArrayList<Double> dnaBindingPropensity) {
		this.dnaBindingPropensity = dnaBindingPropensity;
	}
	/**
	 * @return the proteinBindingResidues
	 */
	public String getProteinBindingResidues() {
		return proteinBindingResidues;
	}
	/**
	 * @param proteinBindingResidues the proteinBindingResidues to set
	 */
	public void setProteinBindingResidues(String proteinBindingResidues) {
		this.proteinBindingResidues = proteinBindingResidues;
	}
	/**
	 * @return the proteinBindingPropensity
	 */
	public ArrayList<Double> getProteinBindingPropensity() {
		return proteinBindingPropensity;
	}
	/**
	 * @param proteinBindingPropensity the proteinBindingPropensity to set
	 */
	public void setProteinBindingPropensity(ArrayList<Double> proteinBindingPropensity) {
		this.proteinBindingPropensity = proteinBindingPropensity;
	}
	
	public String features(){
		return name+","+numBindingResidues(0)+ ","+ numResidueGrps(0)+ ","+avgPropensity(0)+ ","+
				numBindingResidues(1)+ ","+ numResidueGrps(1)+ ","+avgPropensity(1)+ ","+
				numBindingResidues(2)+ ","+ numResidueGrps(2)+ ","+avgPropensity(2)+ ","+
				whichResidueHighest() + "," + whichPropensityHighest();
		
	}
	
	
	
}
