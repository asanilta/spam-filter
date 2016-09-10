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
        String sentence = "Maaf.Untuk Pembayaran Angsuran Klu Mau di Bayar Harap Tlp Dulu Ke Atasan Saya Bpk BAHRI/IBU RIKA 081806005667 Soalnya Sudah di Kuasakan Kepada Beliau.Thanks";
        PreProcess p = new PreProcess();
        System.out.println(Arrays.toString(p.run(sentence).toArray()));
    }
    
}
