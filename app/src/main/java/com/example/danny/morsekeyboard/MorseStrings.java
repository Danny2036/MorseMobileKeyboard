package com.example.danny.morsekeyboard;

/**
 * Created by Danny on 1/6/2016.
 */
class MorseString {
    private String letter;
    private String code;
    private final String undefinedText = "undefined";

    /**
     * Constructor
     * Creates object if inputs are not specified
     */
    public MorseString(){
        this.letter = undefinedText;
        this.code = undefinedText;
    }

    /**
     * Constructor
     * @param key: reference number
     * @param letter: the letter this object represents
     * @param code: the morse code that maps to the letter
     */
    MorseString(String letter, String code){
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
