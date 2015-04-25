package com.sample.util;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by christopherlester on 4/24/15.
 */
public class RandomStringGenerator {

    static final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    static Random random = new Random();

    public String randomString(int stringLength)
    {
        StringBuilder sb = new StringBuilder(stringLength);
        String prefix = "Activity";

        sb.append(prefix);
        for( int i = 0; i < stringLength; i++ ){
            int randomPosition = random.nextInt(alphabet.length());
            sb.append(alphabet.charAt(randomPosition));
        }
        return sb.toString();
    }

    public ArrayList<String> loadRandomAlphanumericStrings(int stringLength, int randomStringListSize) {
        ArrayList<String> alphaNumericStringList = new ArrayList<>();

        for(int i = 0; i < randomStringListSize; i++){
            alphaNumericStringList.add(randomString(stringLength));
        }

        return alphaNumericStringList;
    }

}
