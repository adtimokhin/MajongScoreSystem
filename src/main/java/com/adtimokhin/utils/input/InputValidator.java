package com.adtimokhin.utils.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputValidator {

    private static BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));


    public static int untilGotInt(String msg) {
        String input;

        try {
            System.out.println(msg);
            input = reader.readLine();
            try {
                return Integer.parseInt(input);
            } catch (Exception ee) {
                System.out.println("Please, enter an integer!");
                return untilGotInt(msg);
            }
        } catch (IOException e) {
            System.out.println("Please, enter an integer!");
            return untilGotInt(msg);
        }
    }

    public static int untilGotIntInRange(String msg, int min, int max){
        int number = untilGotInt(msg);
        if(number < min || number > max){
            System.out.println("Please, check that a number that you have entered is in the range between " + min + " and " + max);
            return untilGotIntInRange(msg , min , max);
        }
        return number;
    }

    public static String untilGetString (String msg){

        System.out.println(msg);
        try {
            String input = reader.readLine();
            if (input == null){
                System.out.println("Please, enter a valid string!");
                return untilGetString(msg);
            }else if (input.isEmpty()){
                System.out.println("Please, enter a valid string!");
                return untilGetString(msg);
            }else {
                return input;
            }
        } catch (IOException e) {
            System.out.println("Please, enter a valid string!");
            return untilGetString(msg);
        }
    }

    public static boolean yesOrNo(String msg){
        String input;
        System.out.println(msg);

        try {
            input = reader.readLine();
            if (input.toLowerCase().equals("yes") || input.toLowerCase().equals("y") || input.equals("+")){
                return true;
            }else if (input.toLowerCase().equals("no") || input.toLowerCase().equals("n") || input.equals("-")){
                return false;
            }
        } catch (IOException e) {
            return yesOrNo(msg);
        }
        System.out.println("Please, enter yes or no");
        return yesOrNo(msg);
    }

}
