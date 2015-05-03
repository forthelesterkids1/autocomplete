package com.openeco.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by christopherlester on 4/24/15.
 */
public class RandomStringGenerator {

    static final String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static Random random = new Random();
    private Context mContext;

    public RandomStringGenerator(Context context){
        this.mContext = context;
    }
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

        //for(int i = 0; i < randomStringListSize; i++){
          //  alphaNumericStringList.add(randomString(stringLength));
        //}

        ArrayList<PackageInfo> res = new ArrayList<PackageInfo>();
        PackageManager pm = mContext.getPackageManager();
        List<PackageInfo> packs = pm.getInstalledPackages(0);

        for(PackageInfo info:packs){
            alphaNumericStringList.add(info.packageName);
        }

        return alphaNumericStringList;
    }

}
