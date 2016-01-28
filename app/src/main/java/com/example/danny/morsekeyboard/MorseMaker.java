package com.example.danny.morsekeyboard;

/**
 * Created by Danny on 1/6/2016.
 */
public class MorseMaker {
    private String currentLetter = "";
    private static final String dot = ".";
    private static final String dash = "-";
    private static Converter converter = new Converter();

    /**
     * Gets character based off of code entered
     * @param curretCode The Morse Code representation
     * @return A String that is the equivalent of the Morse Code
     */
    public static String getCharacter(String curretCode){
        String convertedLetter = converter.getLetter(curretCode);
        String returnValue = "";
        if(convertedLetter == null){
            //Do nothing
         } else {
            returnValue = convertedLetter;
        }
        return returnValue;
    }

    /**
     * Returns either a dot or a dash based on elapsed time
     * @param elapsedTime The amount of time the user pressed a key
     * @return A dot or dash
     */
    public static String getDotOrDash(long dotTime, long elapsedTime){
        if(elapsedTime<3*dotTime){
            return dot;
        } else {
            return dash;
        }
    }
}
