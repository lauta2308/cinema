package com.mindhub.cinema.utils.apiUtils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {

    // true if string contains numbers and symbols

    public static Matcher checkNumbersAndSymbols(String name){
        // Definir la expresión regular que busca números y símbolos
        String regex = ".*[0-9\\p{Punct}].*";

        // Crear un objeto Pattern con la expresión regular
        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(name);
    }


    // true if string length is =< 2

    public static boolean notValidNameLength(String name) {
        return name.length() <= 2;
    }

    // true if name and last name are same

    public static boolean nameAndLastAreSame(String name, String lastName) {
        return name.equals(lastName);
    }


    // true if password dont contain at least 8 characters, 1 lowercase, 1 uppercase, 1 number, 1 simbol
    public static boolean checkInvalidPassword(String password) {
        // Verificar que el password cumpla con los requisitos (al menos una mayúscula, una minúscula, un número, un símbolo y una longitud mínima de 8 caracteres)
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=.*[a-zA-Z\\d@#$%^&+=!]).{8,}$";
        return !password.matches(regex);
    }


    // true if email has text@domain.com

    public static Matcher checkValidEmail(String email) {
        // Definir la expresión regular para validar el formato de correo electrónico
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        // Crear un objeto Pattern con la expresión regular
        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(email);
    }





    public static String checkUserRole(Authentication authentication){
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).findFirst().get();


    }

    public static boolean compareStrings(String string1, String string2){

        return string1.equals(string2);
    }



}
