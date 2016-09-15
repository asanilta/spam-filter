/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spamfilter;

import IndonesianNLP.IndonesianSentenceDetector;
import IndonesianNLP.IndonesianSentenceFormalization;
import IndonesianNLP.IndonesianSentenceTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import util.PreProcess;
import util.FeatureExtract;
import util.SpamFilterConfig;

/**
 *
 * @author ASUS X202E
 */
public class SpamFilter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String currentDir = System.getProperty("user.dir");
        SpamFilterConfig.initializeConfig(
                currentDir + "\\dataset\\Text Dataset - SPAM.csv",
                currentDir + "\\dataset\\Text Dataset - NOT SPAM.csv",
                currentDir + "\\dataset\\preprocessed\\",
                "spam",
                "not spam",
                currentDir + "\\dataset\\data.arff");
        
        PreProcess.processCSV(
                SpamFilterConfig.getPreprocessedFileNameSpam(),
                SpamFilterConfig.getDatasetSpamPath());
        
        PreProcess.processCSV(
                SpamFilterConfig.getPreprocessedFileNameNotSpam(),
                SpamFilterConfig.getDatasetNotSpamPath());

        FeatureExtract.createArff(SpamFilterConfig.getPreprocessedFilePath());
    }

}
