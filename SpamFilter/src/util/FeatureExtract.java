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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.SimpleCart;
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
            // convert the directory into a dataset
            loader.setDirectory(new File(directory));
            Instances dataRaw = loader.getDataSet();
            
            // apply the StringToWordVector and tf-idf weighting
            StringToWordVector filter = new StringToWordVector();
            filter.setIDFTransform(true);
            filter.setInputFormat(dataRaw);
            Instances dataFiltered = Filter.useFilter(dataRaw, filter);
            
            // output the arff file
            ArffSaver saver = new ArffSaver();
            saver.setInstances(dataFiltered);
            saver.setFile(new File(SpamFilterConfig.getArffFilePath()));
            saver.writeBatch();
            
            // train with simple cart
            SimpleCart classifier = new SimpleCart();
            classifier.buildClassifier(dataFiltered);
            System.out.println("\n\nClassifier model:\n\n" + classifier.toString());
            
            // using 10 cross validation
            Evaluation eval = new Evaluation(dataFiltered);
            eval.crossValidateModel(classifier, dataFiltered, 10, new Random(1));
            
            System.out.println("\n\nCross fold:\n\n" + eval.toSummaryString());
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
