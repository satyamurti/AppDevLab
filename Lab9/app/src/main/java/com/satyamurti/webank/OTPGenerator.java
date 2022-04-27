package com.satyamurti.webank;

import java.util.Random;

public class OTPGenerator {

    static String currentOtp = "";

    static String OTP(int len)  {
        System.out.println("Generating OTP using random() : ");
        System.out.print("You OTP is : ");

        String numbers = "0123456789";

        Random random = new Random();

        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < len; i++) {
            otp.append(numbers.charAt(random.nextInt(numbers.length())));
        }

        currentOtp = otp.toString();

        return otp.toString();
    }
}
