/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Tifani
 */
public class SpamFilterConfig {
    private static String DATASET_SPAM_PATH;
    private static String DATASET_NOT_SPAM_PATH;
    private static String PREPROCESSED_FILE_PATH;
    private static String PREPROCESSED_FILE_NAME_SPAM;
    private static String PREPROCESSED_FILE_NAME_NOT_SPAM;
    private static String ARFF_FILE_PATH;
    private static String ARFF_FILE_NAME;
    
    public static void initializeConfig(String datasetSpamPath,
            String datasetNotSpamPath,
            String preprocessedFilePath,
            String preprocessedFileNameSpam,
            String preprocessedFileNameNotSpam,
            String arffFilePath) {
        DATASET_SPAM_PATH = datasetSpamPath;
        DATASET_NOT_SPAM_PATH = datasetNotSpamPath;
        PREPROCESSED_FILE_PATH = preprocessedFilePath;
        PREPROCESSED_FILE_NAME_SPAM = preprocessedFileNameSpam;
        PREPROCESSED_FILE_NAME_NOT_SPAM = preprocessedFileNameNotSpam; 
        ARFF_FILE_PATH = arffFilePath;
    }

    public static String getDatasetSpamPath() {
        return DATASET_SPAM_PATH;
    }

    public static String getDatasetNotSpamPath() {
        return DATASET_NOT_SPAM_PATH;
    }

    public static String getPreprocessedFilePath() {
        return PREPROCESSED_FILE_PATH;
    }

    public static String getPreprocessedFileNameSpam() {
        return PREPROCESSED_FILE_NAME_SPAM;
    }

    public static String getPreprocessedFileNameNotSpam() {
        return PREPROCESSED_FILE_NAME_NOT_SPAM;
    }

    public static String getArffFilePath() {
        return ARFF_FILE_PATH;
    }
    
}
