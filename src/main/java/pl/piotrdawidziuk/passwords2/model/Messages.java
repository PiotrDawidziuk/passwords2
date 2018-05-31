package pl.piotrdawidziuk.passwords2.model;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Messages {

    public Messages() {
    }

    public Messages(Password password) {

        this.password = password;
    }

    List<String> list = new ArrayList<>();
    Password password;

    public List<String> createList(String [] words){

        String s = password.getValue();
        User user = password.getUser();
        String username = user.getUsername();
        String email = user.getEmail();
        List <String> list = new ArrayList<>();
        list.add(is123(s));
        list.add(is321(s));
        list.add(isShort(s));
        list.add(isUser(s,username));
        //list.add(containsDupa(s));
        list.add(containsProfanity(s));
        list.add(containsUpperAndLowerCaseNOT(s));
        list.add(containsNumbersNOT(s));
        list.add(isEmail(s,email));
        list.add(containsRepeatedChars(s));
        list.add(containsWordInEnglish(s, words));
        list.add(containsPopularPass(s));
        return list;
    }

    public List<String> createListPositive () {

        String s = password.getValue();

        List <String> list = new ArrayList<>();
        list.add(isLong(s));
        list.add(containsSpecial(s));
        list.add(containsUpperAndLowerCase(s));
        list.add(containsNumbers(s));
        return list;
    }

    public List<String> getList() {

        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String is123(String password){
        if (password.equals("123")){
            return "Your password is 123!";
        }
        else if (password.equals("1234")){
            return "Your password to 1234!";
        }
        else if (password.equals("12345")){
            return "Your password is 12345!";
        }
        else if (password.equals("123456")){
            return "Your password is 123456!";
        }
        else if (password.equals("1234567")){
            return "Your password is 1234567!";
        }
        else if (password.equals("12345678")){
            return "Your password is 12345678!";
        }
        else if (password.equals("123456789")){
            return "Your password is 123456789!";
        }
        else if (password.equals("qwerty")){
            return "Your password is qwerty!";
        }
        else {return null;}
    }

    public String is321(String password){
        if (password.equals("321")){
            return "Your password is 321!";
        }
        else {return null;}
    }
    public String isShort(String password){
        if (password.length()<=5) {
            return "Your password has 5 characters or less!";
        }
        else if (password.length()<= 10 && password.length()>3){
            return "Your password has 10 characters or less.";
        }

        else {
            return null;
        }
    }

    public String isLong (String password) {
        if (password.length() > 10 ){
            return "Your password has more than 10 characters!";
        }
        else {
            return null;
        }
    }

    public String isUser (String password, String user) {
        if (password.toLowerCase().equals(user.toLowerCase())){
            return "Your password is the same as your user name!";
        }
        else if (password.toLowerCase().contains(user.toLowerCase())){
            return "Your password includes the user name!";
        }
        else {
            return null;
        }
    }

    public String isEmail (String password, String email) {

        String result = email.split("@")[0];

        if (password.toLowerCase().equals(email.toLowerCase())){
            return "Your password is your e-mail address!";
        }
        else if (password.toLowerCase().contains(email.toLowerCase())){
            return "Your password includes your e-mail address!";
        }
        else if (password.toLowerCase().contains(result)) {
            return "Your password includes a part of your e-mail address!";
        }
        else {
            return null;
        }
    }

    public String containsDupa (String password) {
        String dupa = "dupa";
        if (password.toLowerCase().contains(dupa)) {
            return "Your password includes the word DUPA!";
        }
        return  null;
    }

    public String containsSpecial (String password){

        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(password);
        boolean b = m.find();
        if (b) {
            return "Your password includes special characters.";}
        return null;
    }

    public String containsUpperAndLowerCase (String password) {
        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasLowercase = !password.equals(password.toUpperCase());
        if (hasLowercase == true && hasUppercase ==true) {
            return "Your password includes upper and lower case.";
        }
        return null;
    }
    public String containsNumbers (String password){


        Pattern p = Pattern.compile(".*\\d+.*", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(password);
        boolean b = m.find();
        if (b) {
            return "Your password includes numbers.";}
        return null;
    }
    public String containsProfanity (String password) {

        WordLists wulgaryzmy = new WordLists();

        String[] DIRTY_WORDS = wulgaryzmy.getDirtyWordsArray();

        boolean vulg = stringContainsItemFromList(password.toLowerCase(), DIRTY_WORDS);

        if (vulg== true) {

            return "Your password includes profanities in Polish!";
        }

        return null;
    }

    public String containsPopularPass (String password) {

        WordLists wordLists = new WordLists();
        String[] popular = wordLists.getPopularPasswordsArray();

        boolean vulg = stringEqualsItemFromList(password.toLowerCase(), popular);

        if (vulg== true) {

            return "Your password is on the list of the most popular passwords of 2017!";
        }

        return null;
    }

    public String containsWordInEnglish (String password, String[] words) {

        //EnglishWords englishWords = new EnglishWords();

        //String[] words = englishWords.getWords();

        boolean word = stringContainsItemFromList(password.toLowerCase(), words);
        boolean wordIs = stringEqualsItemFromList(password.toLowerCase(), words);


        if (word == true && wordIs == false) {

            return "Your password includes an existing word in English.";
        }

        else if (wordIs== true) {
            return "Your password is an existing word in English!";
        }

        return null;
    }


    public static boolean stringContainsItemFromList(String inputStr, String[] items) {
        return Arrays.stream(items).parallel().anyMatch(inputStr::contains);
    }
    public static boolean stringEqualsItemFromList(String inputStr, String[] items) {
        return Arrays.stream(items).parallel().anyMatch(inputStr::equals);
    }

    public String containsUpperAndLowerCaseNOT (String password) {

        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasLowercase = !password.equals(password.toUpperCase());

        if (hasLowercase == false || hasUppercase ==false){
            return "Your password should include upper and lower case!";
        }
        return null;
    }

    public String containsNumbersNOT (String password){

        Pattern p = Pattern.compile(".*\\d+.*", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(password);
        boolean b = m.find();
        if (b) {
            return null;}
        return "Your password doesn't contain any numbers.";
    }

    public String containsRepeatedChars (String password) {
        Pattern p = Pattern.compile("(.)\\1{2,}", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(password);
        boolean b = m.find();
        if (b) {
            return "Your password includes a row or 3 or more repeated characters.";}
        return null;

    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }
}
