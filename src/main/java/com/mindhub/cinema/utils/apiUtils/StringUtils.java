package com.mindhub.cinema.utils.apiUtils;

public class StringUtils {

    public static String firstLetterUppercase(String word){

        // Unir la primera letra mayúscula con el resto de la cadena en minúscula
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }
}
