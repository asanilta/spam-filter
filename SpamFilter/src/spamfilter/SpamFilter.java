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
//        String sentence1 = "Maaf.Untuk Pembayaran Angsuran Klu Mau di Bayar Harap Tlp Dulu Ke Atasan Saya Bpk BAHRI/IBU RIKA 081806005667 Soalnya Sudah di Kuasakan Kepada Beliau.Thanks";
//        String sentence2 = "Maaf aku tes tes tes dulu ya hihihi http://www.facebook.com";
//        ArrayList<String> smsList = new ArrayList<>();
//        smsList.add(sentence1);
//        smsList.add(sentence2);
//        PreProcess p = new PreProcess();
//        ArrayList<ArrayList<String>> processedSmsList = new ArrayList<>();
//        int i = 1;
//        for (String sentence : smsList) {
//            ArrayList<String> processedSms = p.run(sentence);
//            System.out.println(Arrays.toString(processedSms.toArray()));
//            processedSmsList.add(processedSms);
//            p.writeToFile(String.valueOf(i), processedSms);
//            i++;
//        }
        String currentDir = System.getProperty("user.dir");
        SpamFilterConfig.initializeConfig(
                currentDir + "\\dataset\\Text Dataset - SPAM.csv",
                currentDir + "\\dataset\\Text Dataset - NOT SPAM.csv",
                currentDir + "\\dataset\\preprocessed\\",
                "spam",
                "not spam",
                currentDir + "\\dataset\\data.arff");
        
//        PreProcess.processCSV(
//                SpamFilterConfig.getPreprocessedFileNameSpam(),
//                SpamFilterConfig.getDatasetSpamPath());
//        
//        PreProcess.processCSV(
//                SpamFilterConfig.getPreprocessedFileNameNotSpam(),
//                SpamFilterConfig.getDatasetNotSpamPath());
        System.out.println(SpamFilterConfig.getPreprocessedFilePath());
        FeatureExtract.createArff(SpamFilterConfig.getPreprocessedFilePath());
        // PreProcess.run("Maaf.Untuk Pembayaran Angsuran Klu Mau di Bayar Harap Tlp Dulu Ke Atasan Saya Bpk BAHRI/IBU RIKA 081806005667 Soalnya Sudah di Kuasakan Kepada Beliau.Thanks");

//        FeatureExtract f = new FeatureExtract();
//        HashMap<String,Double> map = f.tfIdfMap(processedSmsList);
//        
//        for (Map.Entry<String, Double> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }
    }

}
