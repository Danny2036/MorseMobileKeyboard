package com.example.danny.morsekeyboard;

/**
 * Created by Danny on 1/6/2016.
 */
public class MorseStrings {
    private String letter;
    private String code;

    /*
     * Constructor
     * Creates object if inputs are not specified
     */
    public MorseStrings(){
        this.letter = "undefined";
        this.code = "undefinded";
    }

    /*
     * Constructor
     * @param key: reference number
     * @param letter: the letter this object represents
     * @param code: the morse code that maps to the letter
     */
    public MorseStrings(String letter, String code){
        this.letter = letter;
        this.code = code;
    }

    public String getLetter(){
        return this.letter;
    }

    public String getLetter(String code){
        return this.letter;
    }

    public String getCode(){
        return this.code;
    }

    public String getCode(String letter){
        return this.code;
    }
}
