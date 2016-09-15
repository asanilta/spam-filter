/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.TextDirectoryLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

/**
 *
 * @author ASUS X202E
 */
public class FeatureExtract {
    
    public static void createArff(String directory) {
        TextDirectoryLoader loader = new TextDirectoryLoader();
        try {
            loader.setDirectory(new File(directory));
            Instances dataRaw = loader.getDataSet();
            StringToWordVector filter = new StringToWordVector();
            filter.setIDFTransform(true); // using tf-idf
            filter.setInputFormat(dataRaw);
            Instances dataFiltered = Filter.useFilter(dataRaw, filter);
            ArffSaver saver = new ArffSaver();
            saver.setInstances(dataFiltered);
            saver.setFile(new File(SpamFilterConfig.getArffFilePath()));
            saver.writeBatch();
        } catch (Exception ex) {
            Logger.getLogger(FeatureExtract.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public double tf(ArrayList<String> sms, String term) {
    double result = 0;
    for (String word : sms) {
       if (term.equals(word))
              result++;
       }
    return result / sms.size();
    }
    
    public double idf(ArrayList<ArrayList<String>> smsArrayList, String term) {
    double n = 0;
    for (ArrayList<String> sms : smsArrayList) {
        for (String word : sms) {
            if (term.equals(word)) {
                n++;
                break;
            }
        }
    }
    return Math.log(smsArrayList.size() / n);
    }
    
    public double tfIdf(ArrayList<String> sms, ArrayList<ArrayList<String>> smsArrayList, String term) {
        return tf(sms, term) * idf(smsArrayList, term);
    }
    
    public HashMap<String,Double> tfIdfMap(ArrayList<ArrayList<String>> smsArrayList) {
        HashMap<String,Double> tfIdfMap = new HashMap<>();
        for (ArrayList<String> sms : smsArrayList) {
            for (String word : sms) {
                if (!tfIdfMap.containsKey(word)) {
                    double tfIdf = tfIdf(sms,smsArrayList,word);
                    tfIdfMap.put(word,tfIdf);
                }
            }
        }
        return tfIdfMap;
    }
}