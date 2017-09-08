package com.autocomplete.util;

import java.util.ArrayList;
import java.util.Random;

public class RandomStringGenerator {

    static final String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static Random random = new Random();
    
    /**
     * Generate random string to be used as dummy data
     * @param stringLength
     * @return random string
     */
    public String randomString(int stringLength)
    {
        StringBuilder sb = new StringBuilder(stringLength);

        for( int i = 0; i < stringLength; i++ ){
            int randomPosition = random.nextInt(alphabet.length());
            sb.append(alphabet.charAt(randomPosition));
        }
        return sb.toString();
    }

    /**
     *
     * @param stringLength
     * @param randomStringListSize
     * @return ArrayList<String> list of random strings
     */
    public ArrayList<String> loadRandomAlphanumericStrings(int stringLength, int randomStringListSize) {
        ArrayList<String> alphaNumericStringList = new ArrayList<>();

        for(int i = 0; i < randomStringListSize; i++){
            alphaNumericStringList.add(randomString(stringLength));
        }

        return alphaNumericStringList;
    }
}
