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
import util.PreProcess;

/**
 *
 * @author ASUS X202E
 */
public class SpamFilter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String sentence = "Selamat SIM CARD anda Tr,pilih menjadi pemenang Dari pesta ISI ULANG PT.M-TRONIK 2016 PIN HADIAH 25e477r untk info KLICK: www.hadiahresmimtronik.blogspot.com";
        PreProcess p = new PreProcess();
        System.out.println(Arrays.toString(p.run(sentence).toArray()));
    }
    
}
