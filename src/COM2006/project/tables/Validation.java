package COM2006.project.tables;

import javax.swing.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Validation {

    //Validation for email
    public static boolean checkEmail(String str) {

        if (!str.contains("@") || !str.contains(".")) return false;

        String[] parts = str.split("@|\\.");

        int len = parts[0].length();
        int len2 = parts[1].length();

        if (parts.length != 3 || len2 == 0 || !parts[2].contains("com")) return false;

        //Validates the first part of mail
        for (int i = 0; i < len; i++) {
            if (!Character.isLetterOrDigit(parts[0].charAt(i))) {
                return false;
            }
        }

        //Validates the second part of mail
        for (int i = 0; i < len2; i++) {
            if (!Character.isLetter(parts[1].charAt(i))) {
                return false;
            }
        }

        //Validates the last part of mail
        return true;
    }

    //Validation for surname, forename, street name,city, country
    public static boolean checkName(String str) {
        String string = str.replaceAll("\\s+","");
        for (int i = 0; i < string.length(); i++) {
            if (!Character.isLetter(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    //Validation for inputs that can have letters or numbers
    public static boolean checkHasLetterOrDigit(String str) {
        String string = str.replaceAll("\\s+","");
        for (int i = 0; i < string.length(); i++) {
            if (!Character.isLetterOrDigit(string.charAt(i))) {
                return false;
            }

        }
        return true;
    }

    //Validation for inputs that can only be numbers
    public static boolean checkIsNumbers(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }

        }
        return true;
    }

    //Checks for strings that can be converted to type double
    public static boolean checkIsDouble(String str) {
        boolean isDouble = true;
        try{
            double num = Double.parseDouble(str);
        }catch (NumberFormatException ex){
            isDouble = false;
        }
        return isDouble;
    }

    //checks for empty inputs in textfields
    public static boolean isEmpty(String str){
        return str.equals("");
    }

    //used in register panel to check all the text fields for empty fields
    public static boolean checkEmptyRegister(String email,String password, String username, String forename,
                                             String surname, String houseNumber,String streetName, String postCode,
                                             String city,String country,String phoneNumber){
        return (isEmpty(email) || isEmpty(password) || isEmpty(username) || isEmpty(forename) || isEmpty(surname)
                || isEmpty(houseNumber) || isEmpty(streetName) || isEmpty(postCode) || isEmpty(city) ||
                isEmpty(country) || isEmpty(phoneNumber));
    }
    /*
     * @return - The type of error in the registration panel.
     */
    //checking the length of each text field in registration panel
    public static String checkLengthRegister(String email,String password, String username, String forename,
                                      String surname, String houseNumber,String streetName, String postCode,
                                      String city,String country,String phoneNumber){
        int emailLen = email.length();
        int passLen = password.length();
        int usernameLen = username.length();
        int forenameLen = forename.length();
        int surnameLen = surname.length();
        int houseNoLen = houseNumber.length();
        int streetNameLen = streetName.length();
        int postCodeLen = postCode.length();
        int cityLen = city.length();
        int countryLen = country.length();
        int phoneNumberLen = phoneNumber.length();
        String errorMsg;
        if (emailLen > 30){
            errorMsg = "Email is too long, please make it shorter than 30 Characters";
        }
        else if (passLen > 128){
            errorMsg = "Password is too long, please make it shorter than 128 Characters";
        }
        else if (usernameLen > 30){
            errorMsg = "Username is too long, please make it shorter than 30 Characters";
        }
        else if (forenameLen > 20){
            errorMsg = "Forename is too long, please make it shorter than 20 Characters";
        }
        else if (surnameLen > 20){
            errorMsg = "Surname is too long, please make it shorter than 20 Characters";
        }
        else if (phoneNumberLen > 20){
            errorMsg = "Phone Number is invalid!";
        }
        else if (houseNoLen > 6){
            errorMsg = "House number is invalid!";
        }
        else if (streetNameLen > 30){
            errorMsg = "Street name is too long, keep below 30 characters!";
        }
        else if (postCodeLen > 10){
            errorMsg = "Post Code is too long, keep below 10 characters!";
        }
        else if (cityLen > 30){
            errorMsg = "City name is too long, keep below 30 characters!";
        }
        else if (countryLen > 30){
            errorMsg = "Country name is too long, keep below 30 characters!";
        }
        else {
            errorMsg = "";
        }

        return errorMsg;
    }

    /* Used in date text fields
     * @return -- False if the current date is beyond the date provided, true otherwise
     */
    public static boolean checkDateStart(java.sql.Date date) {
        long millis=System.currentTimeMillis();
        java.sql.Date currentDate=new java.sql.Date(millis);

        if(currentDate.compareTo(date)>0) return false;

        return true;
    }
    /* Used in date text fields
     * @return -- False if the endDate is beyond 2022, true otherwise
     */
    public static boolean checkDateEnd(java.sql.Date endDate) {
        java.sql.Date dec2022 = java.sql.Date.valueOf("2022-12-31");

        if(endDate.compareTo(dec2022)>0) return false;

        return true;
    }

    /* Used in date text fields
     * @return -- True if date format is in YYYY-MM-DD, false otherwise
     */
    public static boolean checkDateFormat(String str) {

        if (!str.contains("-")) return false;

        String[] parts = str.split("-");

        int len = parts[0].length();
        int len2 = parts[1].length();
        int len3 = parts[2].length();

        if (parts.length != 3 || len != 4 || len2 != 2 || len3 != 2 ) return false;

        //Validates the first part of date
        for (int i = 0; i < len; i++) {
            if (!Character.isDigit(parts[0].charAt(i))) {
                return false;
            }
        }

        //Validates the second part of date
        for (int i = 0; i < len2; i++) {
            if (!Character.isDigit(parts[1].charAt(i))) {
                return false;
            }
        }

        //Validates the last part of date
        for (int i = 0; i < len3; i++) {
            if (!Character.isDigit(parts[2].charAt(i))) {
                return false;
            }
        }


        return true;
    }

    /* Based on Chandra Prakash's work on Baeldung.com (https://www.baeldung.com/java-string-valid-date)
     * Last edited on May 21, 2021.
     * Accessed on December 1 2021.
     * Checks if a date has valid numbers (2022-02-30 would return false for instance)
     */
    public static boolean checkValidDate (String str) {
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setLenient(false);
        try {
            simpleDateFormat.parse(str);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /*
     * Checks the length of all the text fields in addProperty panel so that they match the length specified in DB
     * if one doesn't match it will return a string message related to the variable with incorrect length
     */
    public static String checkLengthAddProperty(String propertyName,String description, String houseNumber,
                                                String streetName,String postCode,String city,String country) {
        int propertyNameLen = propertyName.length();
        int descriptionLen = description.length();
        int houseNumberLen = houseNumber.length();
        int streetNameLen = streetName.length();
        int postCodeLen = postCode.length();
        int cityLen = city.length();
        int countryLen =country.length();
        String errorMsg;
        if (propertyNameLen > 30){
            errorMsg = "Property Name is too long, please make it shorter than 30 Characters";
        }
        else if (descriptionLen > 45){
            errorMsg = "description is too long, please make it shorter than 45 Characters";
        }
        else if (houseNumberLen > 6){
            errorMsg = "House number is invalid!";
        }
        else if (streetNameLen > 30){
            errorMsg = "Street name is too long, keep below 30 characters!";
        }
        else if (postCodeLen > 10){
            errorMsg = "Post Code is too long, keep below 10 characters!";
        }
        else if (cityLen > 30){
            errorMsg = "City name is too long, keep below 30 characters!";
        }
        else if (countryLen > 30){
            errorMsg = "Country name is too long, keep below 30 characters!";
        }
        else {
            errorMsg = "";
        }

        return errorMsg;
    }

    //Checks if any of the passed in arguments are empty strings or not, returns true or false.
    public static Boolean checkEmptyAddProperty(String propertyName,String description, String octDec2021PPN,
                                               String janMar2022PPN, String arpJun2022PPN, String julSept2022PPN,
                                               String octDec2022PPN,String serviceCharge1,String serviceCharge2,
                                               String serviceCharge3,String serviceCharge4,String serviceCharge5,
                                               String cleaningCharge1,String cleaningCharge2,String cleaningCharge3,
                                               String cleaningCharge4,String cleaningCharge5,String houseNumber,
                                               String streetName,String postCode,String city,String country){
        return (isEmpty(propertyName) || isEmpty(description) || isEmpty(octDec2021PPN) || isEmpty(janMar2022PPN) ||
                isEmpty(arpJun2022PPN) || isEmpty(julSept2022PPN) || isEmpty(octDec2022PPN) || isEmpty(serviceCharge1)
                || isEmpty(serviceCharge2) || isEmpty(serviceCharge3) || isEmpty(serviceCharge4) ||
                isEmpty(serviceCharge5) || isEmpty(cleaningCharge1) || isEmpty(cleaningCharge2) ||
                isEmpty(cleaningCharge3) || isEmpty(cleaningCharge4)|| isEmpty(cleaningCharge5)
                || isEmpty(houseNumber) || isEmpty(streetName) ||isEmpty(postCode) ||
                isEmpty(city) || isEmpty(country)  );

    }
}