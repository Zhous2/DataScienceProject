package com.company;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Object> testCompleteArrayList = parseFile("test_complete_sample_set.csv");
        ArrayList<Object> testArrayList = parseFile("test_sample_set.csv");
        ArrayList<Object>[] classArrays = getClassesArrayLists(testArrayList);
        ArrayList<Object> yesTestArrayList = classArrays[0];
        ArrayList<Object> noTestArrayList = classArrays[1];

        ArrayList<Object> obectArrayList = parseFile("dataset_complete.csv");

        ArrayList<Object> obect01ArrayList = parseFile("dataset_missing01.csv");
        ArrayList<Object>[] class01Arrays = getClassesArrayLists(obect01ArrayList);
        ArrayList<Object> yes01ArrayList = class01Arrays[0];
        ArrayList<Object> no01ArrayList = class01Arrays[1];

        ArrayList<Object> obect10ArrayList = parseFile("dataset_missing10.csv");
        ArrayList<Object>[] class10Arrays = getClassesArrayLists(obect10ArrayList);
        ArrayList<Object> yes10ArrayList = class10Arrays[0];
        ArrayList<Object> no10ArrayList = class10Arrays[1];

        //run4Methods(testCompleteArrayList, testArrayList, yesTestArrayList, noTestArrayList, "532");
        run4Methods(obectArrayList, obect01ArrayList, yes01ArrayList, no01ArrayList, "01");
        run4Methods(obectArrayList, obect10ArrayList, yes10ArrayList, no10ArrayList, "10");
    }

    private static void run4Methods(ArrayList<Object> completeObjectArrayList, ArrayList<Object> missingObjectArrayList, ArrayList<Object> yesObjectArrayList, ArrayList<Object> noObjectArrayList, String percenteMissing) {//TODO write results to the correct filename
        String output ="";
        //complete mean
        double[] completeMeans = findMeans(missingObjectArrayList);
        ArrayList<Object> completeMeansCopy = new ArrayList<>();
        copyArrayList(completeMeansCopy, missingObjectArrayList);
        ArrayList<Object> completeMeanList = fillInCompleteMean(completeMeansCopy, completeMeans);
        if("01".equals(percenteMissing)) {
            writeToFile(completeMeanList, "V00788156_missing01_imputed_mean.csv");
            output = "MAE_01_mean";
        }
        else if("10".equals(percenteMissing)) {
            writeToFile(completeMeanList, "V00788156_missing10_imputed_mean.csv");
            output = "MAE_10_mean";
        }
        calculateMAE(completeObjectArrayList, completeMeanList, missingObjectArrayList, output);

        //conditional mean
        double[] conditionalYesMeans = findMeans(yesObjectArrayList);
        double[] conditionalNoMeans = findMeans(noObjectArrayList);
        double[] conditionalMeans = combineYesAndNoMeans(conditionalYesMeans, conditionalNoMeans);
        ArrayList<Object> conditionalMeanCopy = new ArrayList<>();
        copyArrayList(conditionalMeanCopy, missingObjectArrayList);
        ArrayList<Object> conditionalMeanList = fillInConditionalMean(conditionalMeanCopy, conditionalMeans);
        if("01".equals(percenteMissing)) {
            writeToFile(conditionalMeanList, "V00788156_missing01_imputed_mean_conditional.csv");
            output = "MAE_01_mean_conditional";
        }
        else if("10".equals(percenteMissing)) {
            writeToFile(conditionalMeanList, "V00788156_missing10_imputed_mean_conditional.csv");
            output = "MAE_10_mean_conditional";
        }
        calculateMAE(completeObjectArrayList, conditionalMeanList, missingObjectArrayList, output);

        //complete hotdeck2
        try {
            ArrayList<Object> completeHotDeckList = hotDeck(missingObjectArrayList);
            if("01".equals(percenteMissing)) {
                writeToFile(completeHotDeckList, "V00788156_missing01_imputed_hd.csv");
                output = "MAE_01_hd";
            }
            else if("10".equals(percenteMissing)) {
                writeToFile(completeHotDeckList, "V00788156_missing10_imputed_hd.csv");
                output = "MAE_10_hd";
            }
            calculateMAE(completeObjectArrayList, completeHotDeckList, missingObjectArrayList, output);
        }
        catch (IllegalAccessException e) {
            System.out.println("error: "+ e);
        }

        //conditional hotdeck
        try {
            ArrayList<Object> conditionalHotDeckList = conditionalHotDeck(missingObjectArrayList);
            if("01".equals(percenteMissing)) {
                writeToFile(conditionalHotDeckList, "V00788156_missing01_imputed_hd_conditional.csv");
                output = "MAE_01_hd_conditional";
            }
            else if("10".equals(percenteMissing)) {
                writeToFile(conditionalHotDeckList, "V00788156_missing10_imputed_hd_conditional.csv");
                output = "MAE_10_hd_conditional";
            }
            calculateMAE(completeObjectArrayList, conditionalHotDeckList, missingObjectArrayList, output);
        }
        catch (IllegalAccessException e) {
            System.out.println("error: "+ e);
        }
    }

    private static void calculateMAE(ArrayList<Object> correctObjectArrayList, ArrayList<Object> imputedObjectArrayList, ArrayList<Object> missingObjectArrayList, String output) {
        double mae = 0;
        int totalMissingValues = 0;
        for(int i = 0; i < missingObjectArrayList.size(); i++) {
            if(missingObjectArrayList.get(i).getF1() == -1) {
                mae += Math.abs(imputedObjectArrayList.get(i).getF1() - correctObjectArrayList.get(i).getF1());
                totalMissingValues++;
            }
            if(missingObjectArrayList.get(i).getF2() == -1) {
                mae += Math.abs(imputedObjectArrayList.get(i).getF2() - correctObjectArrayList.get(i).getF2());
                totalMissingValues++;
            }
            if(missingObjectArrayList.get(i).getF3() == -1) {
                mae += Math.abs(imputedObjectArrayList.get(i).getF3() - correctObjectArrayList.get(i).getF3());
                totalMissingValues++;
            }
            if(missingObjectArrayList.get(i).getF4() == -1) {
                mae += Math.abs(imputedObjectArrayList.get(i).getF4() - correctObjectArrayList.get(i).getF4());
                totalMissingValues++;
            }
            if(missingObjectArrayList.get(i).getF5() == -1) {
                mae += Math.abs(imputedObjectArrayList.get(i).getF5() - correctObjectArrayList.get(i).getF5());
                totalMissingValues++;
            }
            if(missingObjectArrayList.get(i).getF6() == -1) {
                mae += Math.abs(imputedObjectArrayList.get(i).getF6() - correctObjectArrayList.get(i).getF6());
                totalMissingValues++;
            }
            if(missingObjectArrayList.get(i).getF7() == -1) {
                mae += Math.abs(imputedObjectArrayList.get(i).getF7() - correctObjectArrayList.get(i).getF7());
                totalMissingValues++;
            }
            if(missingObjectArrayList.get(i).getF8() == -1) {
                mae += Math.abs(imputedObjectArrayList.get(i).getF8() - correctObjectArrayList.get(i).getF8());
                totalMissingValues++;
            }
            if(missingObjectArrayList.get(i).getF9() == -1) {
                mae += Math.abs(imputedObjectArrayList.get(i).getF9() - correctObjectArrayList.get(i).getF9());
                totalMissingValues++;
            }
            if(missingObjectArrayList.get(i).getF10() == -1) {
                mae += Math.abs(imputedObjectArrayList.get(i).getF10() - correctObjectArrayList.get(i).getF10());
                totalMissingValues++;
            }
            if(missingObjectArrayList.get(i).getF11() == -1) {
                mae += Math.abs(imputedObjectArrayList.get(i).getF11() - correctObjectArrayList.get(i).getF11());
                totalMissingValues++;
            }
            if(missingObjectArrayList.get(i).getF12() == -1) {
                mae += Math.abs(imputedObjectArrayList.get(i).getF12() - correctObjectArrayList.get(i).getF12());
                totalMissingValues++;
            }
            if(missingObjectArrayList.get(i).getF13() == -1) {
                mae += Math.abs(imputedObjectArrayList.get(i).getF13() - correctObjectArrayList.get(i).getF13());
                totalMissingValues++;
            }
        }
        mae /= totalMissingValues;
        mae = (double)Math.round(mae * 100000d) / 100000d;
        System.out.println(output + " = " + mae);
    }

    private static  void copyArrayList(ArrayList<Object> copy, ArrayList<Object> source) {
        for(Object object : source) {
            copy.add(object.clone());
        }
    }

    /**
     * mean stuff
     */

    private static double[]  combineYesAndNoMeans(double[] yesMeans, double[] noMeans) {
        double[] result = new double[yesMeans.length + noMeans.length];
        for(int i = 0; i < yesMeans.length;  i++) {
            result[i] = yesMeans[i];
        }
        for(int i = yesMeans.length; i < result.length; i++){
            result[i] = noMeans[i - yesMeans.length];
        }
        return result;
    }

    private static double[] findMeans(ArrayList<Object> objectArrayList) {
        double f1Mean = 0, f1Count = 0;
        double f2Mean = 0, f2Count = 0;
        double f3Mean = 0, f3Count = 0;
        double f4Mean = 0, f4Count = 0;
        double f5Mean = 0, f5Count = 0;
        double f6Mean = 0, f6Count = 0;
        double f7Mean = 0, f7Count = 0;
        double f8Mean = 0, f8Count = 0;
        double f9Mean = 0, f9Count = 0;
        double f10Mean = 0, f10Count = 0;
        double f11Mean = 0, f11Count = 0;
        double f12Mean = 0, f12Count = 0;
        double f13Mean = 0, f13Count = 0;
        for(int i = 0; i < objectArrayList.size(); i++){
            if(objectArrayList.get(i).getF1() >= 0) {
                f1Count ++;
                f1Mean += objectArrayList.get(i).getF1();
            }

            if(objectArrayList.get(i).getF2() >= 0) {
                f2Count ++;
                f2Mean += objectArrayList.get(i).getF2();
            }

            if(objectArrayList.get(i).getF3() >= 0) {
                f3Count ++;
                f3Mean += objectArrayList.get(i).getF3();
            }

            if(objectArrayList.get(i).getF4() >= 0) {
                f4Count ++;
                f4Mean += objectArrayList.get(i).getF4();
            }

            if(objectArrayList.get(i).getF5() >= 0) {
                f5Count ++;
                f5Mean += objectArrayList.get(i).getF5();
            }

            if(objectArrayList.get(i).getF6() >= 0) {
                f6Count ++;
                f6Mean += objectArrayList.get(i).getF6();
            }

            if(objectArrayList.get(i).getF7() >= 0) {
                f7Count ++;
                f7Mean += objectArrayList.get(i).getF7();
            }

            if(objectArrayList.get(i).getF8() >= 0) {
                f8Count ++;
                f8Mean += objectArrayList.get(i).getF8();
            }

            if(objectArrayList.get(i).getF9() >= 0) {
                f9Count ++;
                f9Mean += objectArrayList.get(i).getF9();
            }

            if(objectArrayList.get(i).getF10() >= 0) {
                f10Count ++;
                f10Mean += objectArrayList.get(i).getF10();
            }

            if(objectArrayList.get(i).getF11() >= 0) {
                f11Count ++;
                f11Mean += objectArrayList.get(i).getF11();
            }

            if(objectArrayList.get(i).getF12() >= 0) {
                f12Count ++;
                f12Mean += objectArrayList.get(i).getF12();
            }

            if(objectArrayList.get(i).getF13() >= 0) {
                f13Count ++;
                f13Mean += objectArrayList.get(i).getF13();
            }
        }
        f1Mean /= f1Count;
        f1Mean = (double)Math.round(f1Mean * 100000d) / 100000d;
        f2Mean /= f2Count;
        f2Mean = (double)Math.round(f2Mean * 100000d) / 100000d;
        f3Mean /= f3Count;
        f3Mean = (double)Math.round(f3Mean * 100000d) / 100000d;
        f4Mean /= f4Count;
        f4Mean = (double)Math.round(f4Mean * 100000d) / 100000d;
        f5Mean /= f5Count;
        f5Mean = (double)Math.round(f5Mean * 100000d) / 100000d;
        f6Mean /= f6Count;
        f6Mean = (double)Math.round(f6Mean * 100000d) / 100000d;
        f7Mean /= f7Count;
        f7Mean = (double)Math.round(f7Mean * 100000d) / 100000d;
        f8Mean /= f8Count;
        f8Mean = (double)Math.round(f8Mean * 100000d) / 100000d;
        f9Mean /= f9Count;
        f9Mean = (double)Math.round(f9Mean * 100000d) / 100000d;
        f10Mean /= f10Count;
        f10Mean = (double)Math.round(f10Mean * 100000d) / 100000d;
        f11Mean /= f11Count;
        f11Mean = (double)Math.round(f11Mean * 100000d) / 100000d;
        f12Mean /= f12Count;
        f12Mean = (double)Math.round(f12Mean * 100000d) / 100000d;
        f13Mean /= f13Count;
        f13Mean = (double)Math.round(f13Mean * 100000d) / 100000d;
        double[] result = {-1.0, f1Mean, f2Mean, f3Mean, f4Mean, f5Mean, f6Mean, f7Mean, f8Mean, f9Mean, f10Mean, f11Mean, f12Mean, f13Mean};//the -1 is there to make the index line up with the feature number
        return result;
    }

    private static ArrayList<Object>  fillInConditionalMean(ArrayList<Object> objectArrayList, double[] means) {
        ArrayList<Object> resultArrayList = new ArrayList<>(objectArrayList);
        for(int i = 0; i < resultArrayList.size(); i++) {
            ArrayList<Integer> missingFieldFromIObject = getMissingField(objectArrayList.get(i));
            Boolean objectClass = resultArrayList.get(i).getfClass();
            //System.out.println("missing field:" + missingFieldFromIObject);
            for( int j = 0; j < missingFieldFromIObject.size(); j++) {
                if (missingFieldFromIObject.get(j) == 1) {
                    if (objectClass)
                        resultArrayList.get(i).setF1(means[1]);//yes class value
                    else
                        resultArrayList.get(i).setF1(means[1 + 14]);//no class value// 14 is the length of YesMeans
                } else if (missingFieldFromIObject.get(j) == 2) {
                    if (objectClass)
                        resultArrayList.get(i).setF2(means[2]);//yes class value
                    else
                        resultArrayList.get(i).setF2(means[2 + 14]);//no class value
                } else if (missingFieldFromIObject.get(j) == 3) {
                    if (objectClass)
                        resultArrayList.get(i).setF3(means[3]);//yes class value
                    else
                        resultArrayList.get(i).setF3(means[3 + 14]);//no class value
                } else if (missingFieldFromIObject.get(j) == 4) {
                    if (objectClass)
                        resultArrayList.get(i).setF4(means[4]);//yes class value
                    else
                        resultArrayList.get(i).setF4(means[4 + 14]);//no class value
                } else if (missingFieldFromIObject.get(j) == 5) {
                    if (objectClass)
                        resultArrayList.get(i).setF5(means[5]);//yes class value
                    else
                        resultArrayList.get(i).setF5(means[5 + 14]);//no class value
                } else if (missingFieldFromIObject.get(j) == 6) {
                    if (objectClass)
                        resultArrayList.get(i).setF6(means[6]);//yes class value
                    else
                        resultArrayList.get(i).setF6(means[6 + 14]);//no class value
                } else if (missingFieldFromIObject.get(j) == 7) {
                    if (objectClass)
                        resultArrayList.get(i).setF7(means[7]);//yes class value
                    else
                        resultArrayList.get(i).setF7(means[7 + 14]);//no class value
                } else if (missingFieldFromIObject.get(j) == 8) {
                    if (objectClass)
                        resultArrayList.get(i).setF8(means[8]);//yes class value
                    else
                        resultArrayList.get(i).setF8(means[8 + 14]);//no class value
                } else if (missingFieldFromIObject.get(j) == 9) {
                    if (objectClass)
                        resultArrayList.get(i).setF9(means[9]);//yes class value
                    else
                        resultArrayList.get(i).setF9(means[9 + 14]);//no class value
                } else if (missingFieldFromIObject.get(j) == 10) {
                    if (objectClass)
                        resultArrayList.get(i).setF10(means[10]);//yes class value
                    else
                        resultArrayList.get(i).setF10(means[10 + 14]);//no class value
                } else if (missingFieldFromIObject.get(j) == 11) {
                    if (objectClass)
                        resultArrayList.get(i).setF11(means[11]);//yes class value
                    else
                        resultArrayList.get(i).setF11(means[11 + 14]);//no class value
                } else if (missingFieldFromIObject.get(j) == 12) {
                    if (objectClass)
                        resultArrayList.get(i).setF12(means[12]);//yes class value
                    else
                        resultArrayList.get(i).setF12(means[12 + 14]);//no class value
                } else if (missingFieldFromIObject.get(j) == 13) {
                    if (objectClass)
                        resultArrayList.get(i).setF13(means[13]);//yes class value
                    else
                        resultArrayList.get(i).setF13(means[13 + 14]);//no class value
                }
            }
        }
        return resultArrayList;
    }

    private static ArrayList<Object>  fillInCompleteMean(ArrayList<Object> objectArrayList, double[] means) {
        ArrayList<Object> resultArrayList = new ArrayList<>(objectArrayList);
        for(int i = 0; i < resultArrayList.size(); i++) {
            ArrayList<Integer> missingFieldFromIObject = getMissingField(objectArrayList.get(i));
            //System.out.println("missing field:");
            //printArrayList(missingFieldFromIObject);
            for(int j = 0; j < missingFieldFromIObject.size(); j++) {
                if (missingFieldFromIObject.get(j) == 1)
                    resultArrayList.get(i).setF1(means[1]);
                else if (missingFieldFromIObject.get(j) == 2)
                    resultArrayList.get(i).setF2(means[2]);
                else if (missingFieldFromIObject.get(j) == 3)
                    resultArrayList.get(i).setF3(means[3]);
                else if (missingFieldFromIObject.get(j) == 4)
                    resultArrayList.get(i).setF4(means[4]);
                else if (missingFieldFromIObject.get(j) == 5)
                    resultArrayList.get(i).setF5(means[5]);
                else if (missingFieldFromIObject.get(j) == 6)
                    resultArrayList.get(i).setF6(means[6]);
                else if (missingFieldFromIObject.get(j) == 7)
                    resultArrayList.get(i).setF7(means[7]);
                else if (missingFieldFromIObject.get(j) == 8)
                    resultArrayList.get(i).setF8(means[8]);
                else if (missingFieldFromIObject.get(j) == 9)
                    resultArrayList.get(i).setF9(means[9]);
                else if (missingFieldFromIObject.get(j) == 10)
                    resultArrayList.get(i).setF10(means[10]);
                else if (missingFieldFromIObject.get(j) == 11)
                    resultArrayList.get(i).setF11(means[11]);
                else if (missingFieldFromIObject.get(j) == 12)
                    resultArrayList.get(i).setF12(means[12]);
                else if (missingFieldFromIObject.get(j) == 13)
                    resultArrayList.get(i).setF13(means[13]);
            }
        }
        return resultArrayList;
    }

    private static ArrayList<Integer> getMissingField(Object object){
        ArrayList<Integer> missingFields = new ArrayList<>();
        if(object.getF1() == -1)
            missingFields.add(1);
        if(object.getF2() == -1)
            missingFields.add(2);
        if(object.getF3() == -1)
            missingFields.add(3);
        if(object.getF4() == -1)
            missingFields.add(4);
        if(object.getF5() == -1)
            missingFields.add(5);
        if(object.getF6() == -1)
            missingFields.add(6);
        if(object.getF7() == -1)
            missingFields.add(7);
        if(object.getF8() == -1)
            missingFields.add(8);
        if(object.getF9() == -1)
            missingFields.add(9);
        if(object.getF10() == -1)
            missingFields.add(10);
        if(object.getF11() == -1)
            missingFields.add(11);
        if(object.getF12() == -1)
            missingFields.add(12);
        if(object.getF13() == -1)
            missingFields.add(13);
        return missingFields;
    }

    private static void printMeans(double[] means) {
        System.out.println("mean f1: " + means[1]);
        System.out.println("mean f2: " + means[2]);
        System.out.println("mean f3: " + means[3]);
        System.out.println("mean f4: " + means[4]);
        System.out.println("mean f5: " + means[5]);
        System.out.println("mean f6: " + means[6]);
        System.out.println("mean f7: " + means[7]);
        System.out.println("mean f8: " + means[8]);
        System.out.println("mean f9: " + means[9]);
        System.out.println("mean f10: " + means[10]);
        System.out.println("mean f11: " + means[11]);
        System.out.println("mean f12: " + means[12]);
        System.out.println("mean f13: " + means[13]);
    }

    /**
        hot deck stuff
    */

    private static ArrayList<Object> conditionalHotDeck(ArrayList<Object> objectArrayList) throws IllegalAccessException{
        ArrayList<Object> resultArrayList = new ArrayList<>();
        copyArrayList(resultArrayList, objectArrayList);
        for(int i = 0; i < objectArrayList.size(); i++) {
            Boolean objectClass = objectArrayList.get(i).getfClass();
            ArrayList<Integer> missingFieldFromOriginalObject = getMissingField(objectArrayList.get(i));
            if(missingFieldFromOriginalObject.size() == 0)
                continue;
            ArrayList<Object> tempCopy = new ArrayList<>();
            copyArrayList(tempCopy, objectArrayList);
            for(int x = 0; x < missingFieldFromOriginalObject.size(); x++) {
                ArrayList<Object> objectsWithMissingFieldValue = new ArrayList<>();
                for(int j = 0; j < tempCopy.size(); j++) {
                    if(i == j) {//skip itself
                        continue;
                    }
                    addIfValueExists(tempCopy.get(j), objectsWithMissingFieldValue, missingFieldFromOriginalObject.get(x));
                }
                for(int j = 0; j < objectsWithMissingFieldValue.size(); j++) {
                    if(objectClass == objectArrayList.get(j).getfClass())
                        calculateEuclideanDistance(tempCopy.get(i), objectsWithMissingFieldValue.get(j));
                }
                Object closestObject = findMinEuclideanDistance(objectsWithMissingFieldValue);
                if(closestObject == null)
                    System.out.println("there was no closest object");
                fillInMissingValue(resultArrayList.get(i), closestObject, missingFieldFromOriginalObject.get(x));
            }
        }
        return resultArrayList;
    }

    private static ArrayList<Object> hotDeck(ArrayList<Object> objectArrayList) throws IllegalAccessException{//O(n^3)
        ArrayList<Object> resultArrayList = new ArrayList<>();
        copyArrayList(resultArrayList, objectArrayList);
        for(int i = 0; i < objectArrayList.size(); i++) {//O(n)
            ArrayList<Integer> missingFieldFromOriginalObject = getMissingField(objectArrayList.get(i));
            if(missingFieldFromOriginalObject.size() == 0)
                continue;
            ArrayList<Object> tempCopy = new ArrayList<>();
            copyArrayList(tempCopy, objectArrayList);
            for(int x = 0; x < missingFieldFromOriginalObject.size(); x++) {//O(n)
                ArrayList<Object> objectsWithMissingFieldValue = new ArrayList<>();
                for(int j = 0; j < tempCopy.size(); j++) {//O(n)
                    if(i == j) {//skip itself
                        continue;
                    }
                    addIfValueExists(tempCopy.get(j), objectsWithMissingFieldValue, missingFieldFromOriginalObject.get(x));
                }
                for(int j = 0; j < objectsWithMissingFieldValue.size(); j++) {//O(n)
                    calculateEuclideanDistance(tempCopy.get(i), objectsWithMissingFieldValue.get(j));
                }
                Object closestObject = findMinEuclideanDistance(objectsWithMissingFieldValue);//O(n)
                if(closestObject == null)
                    System.out.println("there was no closest object");
                fillInMissingValue(resultArrayList.get(i), closestObject, missingFieldFromOriginalObject.get(x));//O(1)
            }
        }
        return resultArrayList;
    }

    private static Object findMinEuclideanDistance(ArrayList<Object> objectArrayList) {
        if(objectArrayList.size() == 0)
            return null;
        Object min = objectArrayList.get(0);
        for(int i = 0; i < objectArrayList.size(); i++) {
            Object currentObject = objectArrayList.get(i);
            if(currentObject.getEuclideanDistance() < min.getEuclideanDistance())
                min = currentObject;
        }
        return min;
    }

    private static void addIfValueExists(Object tempCopy, ArrayList<Object> objectsWithMissingFieldValue, int missingValue) {
        ArrayList<Integer> missingFieldFromSortedObject = getMissingField(tempCopy);
        if (missingFieldFromSortedObject.contains(missingValue))
            return;//If both objects share the missing field, skip it.
        if (missingValue == 1)
            objectsWithMissingFieldValue.add(tempCopy);
        else if (missingValue == 2)
            objectsWithMissingFieldValue.add(tempCopy);
        else if (missingValue == 3)
            objectsWithMissingFieldValue.add(tempCopy);
        else if (missingValue == 4)
            objectsWithMissingFieldValue.add(tempCopy);
        else if (missingValue == 5)
            objectsWithMissingFieldValue.add(tempCopy);
        else if (missingValue == 6 )
            objectsWithMissingFieldValue.add(tempCopy);
        else if (missingValue == 7)
            objectsWithMissingFieldValue.add(tempCopy);
        else if (missingValue == 8)
            objectsWithMissingFieldValue.add(tempCopy);
        else if (missingValue == 9)
            objectsWithMissingFieldValue.add(tempCopy);
        else if (missingValue == 10)
            objectsWithMissingFieldValue.add(tempCopy);
        else if (missingValue == 11)
            objectsWithMissingFieldValue.add(tempCopy);
        else if (missingValue == 12)
            objectsWithMissingFieldValue.add(tempCopy);
        else if (missingValue == 13)
            objectsWithMissingFieldValue.add(tempCopy);
}

    //calculate the EuclideanDistance for each object
    private static void calculateEuclideanDistance(Object originalObject, Object compareObject) throws IllegalAccessException {
        if(originalObject.equals(compareObject)){
            compareObject.setEuclideanDistance(999);
            return;
        }
        double f1Diff = -2, f2Diff = -2, f3Diff = -2, f4Diff = -2, f5Diff = -2, f6Diff = -2, f7Diff = -2, f8Diff = -2, f9Diff = -2, f10Diff = -2, f11Diff = -2, f12Diff = -2, f13Diff = -2;
        try {
            f1Diff = getFieldDiff(1, originalObject, compareObject);
            f2Diff = getFieldDiff(2, originalObject, compareObject);
            f3Diff = getFieldDiff(3, originalObject, compareObject);
            f4Diff = getFieldDiff(4, originalObject, compareObject);
            f5Diff = getFieldDiff(5, originalObject, compareObject);
            f6Diff = getFieldDiff(6, originalObject, compareObject);
            f7Diff = getFieldDiff(7, originalObject, compareObject);
            f8Diff = getFieldDiff(8, originalObject, compareObject);
            f9Diff = getFieldDiff(9, originalObject, compareObject);
            f10Diff = getFieldDiff(10, originalObject, compareObject);
            f11Diff = getFieldDiff(11, originalObject, compareObject);
            f12Diff = getFieldDiff(12, originalObject, compareObject);
            f13Diff = getFieldDiff(13, originalObject, compareObject);
        }
        catch (IllegalAccessException e) {
            throw new IllegalAccessException("error:" + e);
        }
        if (f1Diff == -2 || f2Diff == -2 || f3Diff == -2 || f4Diff == -2 || f5Diff == -2 || f6Diff == -2 || f7Diff == -2 || f8Diff == -2 || f9Diff == -2 || f10Diff == -2 || f11Diff == -2 || f12Diff == -2 || f13Diff == -2) {
            throw new IllegalAccessException("could not retrieve a filed value from objectArrayList. originalObject = " + originalObject + ". compareObject = " + compareObject);
        }
        double totalDiff = f1Diff + f2Diff + f3Diff + f4Diff + f5Diff + f6Diff + f7Diff + f8Diff + f9Diff + f10Diff + f11Diff + f12Diff + f13Diff;
        totalDiff = Math.sqrt(totalDiff);
        compareObject.setEuclideanDistance(totalDiff);
    }

    //set the correct field from the most similar object that has the desired field
    private static void fillInMissingValue(Object originalObject, Object closestObject, int missingField) {
        if (missingField == 1)
            originalObject.setF1(closestObject.getF1());
        else if (missingField == 2)
            originalObject.setF2(closestObject.getF2());
        else if (missingField == 3)
            originalObject.setF3(closestObject.getF3());
        else if (missingField == 4)
            originalObject.setF4(closestObject.getF4());
        else if (missingField == 5)
            originalObject.setF5(closestObject.getF5());
        else if (missingField == 6)
            originalObject.setF6(closestObject.getF6());
        else if (missingField == 7)
            originalObject.setF7(closestObject.getF7());
        else if (missingField == 8)
            originalObject.setF8(closestObject.getF8());
        else if (missingField == 9)
            originalObject.setF9(closestObject.getF9());
        else if (missingField == 10)
            originalObject.setF10(closestObject.getF10());
        else if (missingField == 11)
            originalObject.setF11(closestObject.getF11());
        else if (missingField == 12)
            originalObject.setF12(closestObject.getF12());
        else if (missingField == 13)
            originalObject.setF13(closestObject.getF13());
    }

    private static double getFieldDiff(int f, Object originalObject, Object compareObject) throws IllegalAccessException{
        double originalfield = -2;//-2 means something went wrong
        double comparefield = -2;
        double fieldDiff;
        if (f == 1) {
            originalfield = originalObject.getF1();
            comparefield = compareObject.getF1();
        }
        else if (f == 2) {
            originalfield = originalObject.getF2();
            comparefield = compareObject.getF2();
        }
        else if (f == 3) {
            originalfield = originalObject.getF3();
            comparefield = compareObject.getF3();
        }
        else if (f == 4) {
            originalfield = originalObject.getF4();
            comparefield = compareObject.getF4();
        }
        else if (f == 5) {
            originalfield = originalObject.getF5();
            comparefield = compareObject.getF5();
        }
        else if (f == 6) {
            originalfield = originalObject.getF6();
            comparefield = compareObject.getF6();
        }
        else if (f == 7) {
            originalfield = originalObject.getF7();
            comparefield = compareObject.getF7();
        }
        else if (f == 8) {
            originalfield = originalObject.getF8();
            comparefield = compareObject.getF8();
        }
        else if (f == 9) {
            originalfield = originalObject.getF9();
            comparefield = compareObject.getF9();
        }
        else if (f == 10) {
            originalfield = originalObject.getF10();
            comparefield = compareObject.getF10();
        }
        else if (f == 11) {
            originalfield = originalObject.getF11();
            comparefield = compareObject.getF11();
        }
        else if (f == 12) {
            originalfield = originalObject.getF12();
            comparefield = compareObject.getF12();
        }
        else if (f == 13) {
            originalfield = originalObject.getF13();
            comparefield = compareObject.getF13();
        }

        if (originalfield == -2 || comparefield == -2) {
            throw new IllegalAccessException("could not retrieve a filed value from objectArrayList. original = " + originalObject + ". compare = " + compareObject);
        }
        else if(originalfield == -1 || comparefield == -1) {
            fieldDiff = 1;
        }
        else {
            fieldDiff = originalfield - comparefield;
            fieldDiff *= fieldDiff;
        }
        return fieldDiff;
    }

    /**
        get files
    */

    private static ArrayList<Object> parseFile(String fileName) {
        ArrayList<Object> objectArrayList = new ArrayList<>();
        try {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            br.readLine();//to skip the header line
            while ((line = br.readLine()) != null) {
                String[] lineInfo = line.split(",");
                Object object = new Object();
                object.setF1(getNextLineDoubleValue(lineInfo[0]));
                object.setF2(getNextLineDoubleValue(lineInfo[1]));
                object.setF3(getNextLineDoubleValue(lineInfo[2]));
                object.setF4(getNextLineDoubleValue(lineInfo[3]));
                object.setF5(getNextLineDoubleValue(lineInfo[4]));
                object.setF6(getNextLineDoubleValue(lineInfo[5]));
                object.setF7(getNextLineDoubleValue(lineInfo[6]));
                object.setF8(getNextLineDoubleValue(lineInfo[7]));
                object.setF9(getNextLineDoubleValue(lineInfo[8]));
                object.setF10(getNextLineDoubleValue(lineInfo[9]));
                object.setF11(getNextLineDoubleValue(lineInfo[10]));
                object.setF12(getNextLineDoubleValue(lineInfo[11]));
                object.setF13(getNextLineDoubleValue(lineInfo[12]));
                object.setfClass(getNextLineBooleanValue(lineInfo[13]));
                objectArrayList.add(object);
            }
            br.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return objectArrayList;
    }

    private static Double getNextLineDoubleValue(String value) {
        if("?".equals(value))
            return -1.0;
        else
            return Double.parseDouble(value);
    }

    private static Boolean getNextLineBooleanValue(String value) {
        if("Y".equals(value))
            return true;
        else
            return false;
    }

    /**
     * set up stuff
     */

    private static ArrayList<Object>[] getClassesArrayLists(ArrayList<Object> objectArrayList) {
        ArrayList<Object>[] classArrays = new ArrayList[2];
        ArrayList<Object> yesClass = new ArrayList<>();
        ArrayList<Object> noClass = new ArrayList<>();
        for(int i = 0; i < objectArrayList.size(); i++){
            if(objectArrayList.get(i).getfClass()) {
                yesClass.add(objectArrayList.get(i));
            }
            else {
                noClass.add(objectArrayList.get(i));
            }
        }
        classArrays[0] = yesClass;
        classArrays[1] = noClass;
        return classArrays;
    }

    private static void printArrayList(ArrayList list) {
        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
    }

    /**
     * write files
     */
    private static Boolean writeToFile(ArrayList<Object> objectArrayList, String filename) {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(filename));
            bw.write("F1" + ", " + "F2" + ", " + "F3" + ", " + "F4" + ", " + "F5" + ", " + "F6" + ", " + "F7" + ", " + "F8" + ", " + "F9" + ", " + "F10" + ", " + "F11" + ", " + "F12" + ", " + "F13" + ", " + "Class");
            for(int i = 0; i < objectArrayList.size(); i++) {
                bw.newLine();
                bw.write(objectArrayList.get(i).getF1() + ", " + objectArrayList.get(i).getF2() + ", " + objectArrayList.get(i).getF3() + ", " + objectArrayList.get(i).getF4() + ", " + objectArrayList.get(i).getF5() + ", " +
                        objectArrayList.get(i).getF6() + ", " + objectArrayList.get(i).getF7() + ", " + objectArrayList.get(i).getF8() + ", " + objectArrayList.get(i).getF9() + ", " + objectArrayList.get(i).getF10() + ", " +
                        objectArrayList.get(i).getF11() + ", " + objectArrayList.get(i).getF12() + ", " + objectArrayList.get(i).getF13() + ", " + objectArrayList.get(i).getfClass());
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
