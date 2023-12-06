package dev.swapi.helpers;

public final class Utils {

    public static Integer tryParseOrZero(String input){
        try {
            return Integer.valueOf(input);
        }
        catch (NumberFormatException e) {
            return 0;
        }
    }

}
