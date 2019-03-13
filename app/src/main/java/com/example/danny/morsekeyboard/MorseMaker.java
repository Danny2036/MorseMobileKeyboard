package com.example.danny.morsekeyboard;

/**
 * Created by Danny on 1/6/2016.
 */
class MorseMaker {
    private static final String dot = ".";
    private static final String dash = "-";
    private static final int dashMultiplier = 3;
    private static Converter converter = new Converter();

    /**
     * Gets character based off of code entered
     * @param currentCode The Morse Code representation
     * @return A String that is the equivalent of the Morse Code
     */
    static String getCharacter(String currentCode){
        String convertedLetter = converter.getLetter(currentCode);
        String returnValue = "";
        if(convertedLetter != null){
            returnValue = convertedLetter;
        }
        return returnValue;
    }

    /**
     * Returns either a dot or a dash based on elapsed time
     * @param elapsedTime The amount of time the user pressed a key
     * @return A dot or dash
     */
    static String getDotOrDash(long dotTime, long elapsedTime){
        if(elapsedTime < dashMultiplier * dotTime){
            return dot;
        } else {
            return dash;
        }
    }
}
