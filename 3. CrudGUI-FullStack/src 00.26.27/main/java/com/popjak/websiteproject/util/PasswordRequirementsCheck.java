package com.popjak.websiteproject.util;

public class PasswordRequirementsCheck {
    public static boolean passwordRequirementsCheck(String password) {

        /*
        This method performs a password check to determine its validity.
        If the password meets the specified criteria, it will
        return "VALID." Otherwise, it will return "INVALID."
         */

        if (password == null) return false;

        boolean conditionIsDigit = password.chars().anyMatch(Character::isDigit);
        boolean conditionIsUpperCase = password.chars().anyMatch(Character::isUpperCase);
        boolean conditionIsLowerCase = password.chars().anyMatch(Character::isLowerCase);
        boolean conditionLength = password.length() > 7;

        return conditionIsDigit && conditionLength && conditionIsLowerCase && conditionIsUpperCase;
    }
}
