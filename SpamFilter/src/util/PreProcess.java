/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import IndonesianNLP.IndonesianSentenceDetector;
import IndonesianNLP.IndonesianSentenceFormalization;
import IndonesianNLP.IndonesianSentenceTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author feryandi
 */
public class PreProcess {
    
    public PreProcess() {  
    }
    
    public ArrayList<String> run (String message) {      
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
        
        for (String word : token) {
            result.add(formalizer.deleteStopword(formalizer.formalizeWord(word)).trim());            
        }
        
        result.removeAll(Arrays.asList("", null, "-", ".", ":", ";", "+", "(", ")", "*", "!", "?"));
                              
        return result;    
    }
        
}