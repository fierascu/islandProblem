package org.example;

import java.util.regex.Pattern;

public class Constants {
    public static final char CHAR_FOR_LAND = '1';

    public static final char CHAR_FOR_WATTER = '0';

    public static final char CHAR_FOUND_TOKEN = '¹';

    public static final char CHAR_NOT_FOUND_TOKEN = '°';

    public static final int HARD_STOP_VALUE = 100;

    public static final int DELAY = 200;

    public static String ISLAND_DEMO_VALUE =
                    "11110" + "\n" +
                    "11010" + "\n" +
                    "11000" + "\n" +
                    "00000";

    public static String ISLAND_DEMO_VALUE_SIMPLE_ROW = "1101101";

    public static Pattern PATTERN_FOR_LETTERS_AND_NUMBERS = Pattern.compile("^[a-zA-Z0-9]+$");
}
