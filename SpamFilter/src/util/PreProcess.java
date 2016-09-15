/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import IndonesianNLP.IndonesianNETagger;
import IndonesianNLP.IndonesianSentenceDetector;
import IndonesianNLP.IndonesianSentenceFormalization;
import IndonesianNLP.IndonesianSentenceTokenizer;
import com.opencsv.CSVReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author feryandi
 */
public class PreProcess {
    
    public PreProcess() {  
    }
    
    public static void processCSV(String type, String filepath) {
        try {
            CSVReader reader = new CSVReader(new FileReader(filepath));
            String [] nextLine;
            try {
                int i = 1;
                while ((nextLine = reader.readNext()) != null) {
                    String sentence = nextLine[0];
                    writeToFile(type + "/" + type + "-" + String.valueOf(i), run(sentence));
                    i++;
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            
        } catch (FileNotFoundException notFoundException) {
            notFoundException.printStackTrace();
        }
    }
    
    public static ArrayList<String> run (String message) {      
        message = message.toLowerCase();
        
        IndonesianSentenceDetector detector = new IndonesianSentenceDetector();
        ArrayList<String> sentenceList = detector.splitSentence(message);
        
        IndonesianSentenceTokenizer tokenizer = new IndonesianSentenceTokenizer();
        ArrayList<String> token = new ArrayList<String>();
        
        for (String s: sentenceList) {
            token.addAll(tokenizer.tokenizeSentence(s));
        }
                
        IndonesianSentenceFormalization formalizer = new IndonesianSentenceFormalization();
        formalizer.initStopword(); 
        ArrayList<String> result = new ArrayList<String>();
            
        for (int i = 0; i < token.size(); i++) {
            String word = token.get(i);
            
            if (!isLink(word) && word.contains(".")) {
                String[] words = word.split("\\.");
                if (words.length>1) {
                    word = words[0];
                    token.add(i+1,words[1]);
                }
            }
            String r = formalizer.deleteStopword(formalizer.formalizeWord(word)).trim();            
            for (String l : r.split("[,/.()+-=]")) {
                result.add(formalizer.formalizeWord(l).trim());                
            }            
        }        
                
        result.removeAll(Arrays.asList("", null, "-", ".", ":", ";", "+", "(", ")", "*", "!", "?", ",", "/", "\\", "[", "]", "<", ">", "=", "_", "\""));
                              
        return result;    
    }
    
    private static boolean isLink (String token) {
        String regex = "((https?://)|(www\\.)|(https?://)|(www\\.))[\\w\\-\\.~]+\\.[a-z]{2,6}(/[\\w\\-\\.~]*)*";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(token);
        return (m.matches());
    }
    
    private static void writeToFile(String filename, ArrayList<String> terms) {
        try {
            File file = new File(System.getProperty("user.dir") + "/corpus/" + filename + ".txt");
            file.getParentFile().mkdirs();
            System.out.println("Writing to " + file.getCanonicalPath());

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for(String term : terms) { 
                bw.write(term);
                bw.write(" ");
            }
            bw.close();
        } catch (IOException e) {
                e.printStackTrace();
        }
    }
        
}
