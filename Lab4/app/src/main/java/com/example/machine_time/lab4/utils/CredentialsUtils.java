package com.example.machine_time.lab4.utils;

import android.content.Intent;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CredentialsUtils {

    public final static String ts = "1";
    public final static String public_key = "fc950f903aa4590cac6dac063fb3c2bf";
    public final static String private_key = "ca066887d7226a497d77affe06a09684d453cc6d";

    public static String getHash(){
        String hash = ts + private_key + public_key;
        return md5(hash);
    }

    private static String md5(String s){

        try{
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            }
            return hexString.toString();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return "";
    }

}
