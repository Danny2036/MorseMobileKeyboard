package com.example.danny.morsekeyboard;
import java.util.*;
class Converter {
    private MorseString stringList[] = new MorseString[49];
    private HashMap<String,MorseString> mappedStrings;

    Converter(){
        this.mappedStrings = buildMap();
    }

    String getLetter(String key){
        try{
            return mappedStrings.get(key).getLetter();
        } catch (Exception e){
            return null;
        }
    }

    private HashMap<String,MorseString> buildMap(){
        buildStrings();
        HashMap<String, MorseString> map = new HashMap<String,MorseString>();
        for(MorseString letterCode: stringList){
            map.put(letterCode.getCode(), letterCode);
        }
        return map;
    }

    private void buildStrings(){
        stringList[0] = new MorseString("a", ".-");
        stringList[1] = new MorseString("b", "-...");
        stringList[2] = new MorseString("c", "-.-.");
        stringList[3] = new MorseString("d", "-..");
        stringList[4] = new MorseString("e", ".");
        stringList[5] = new MorseString("f", "..-.");
        stringList[6] = new MorseString("g", "--.");
        stringList[7] = new MorseString("h", "....");
        stringList[8] = new MorseString("i", "..");
        stringList[9] = new MorseString("j", ".---");
        stringList[10] = new MorseString("k", "-.-");
        stringList[11] = new MorseString("l", ".-..");
        stringList[12] = new MorseString("m", "--");
        stringList[13] = new MorseString("n", "-.");
        stringList[14] = new MorseString("o", "---");
        stringList[15] = new MorseString("p", ".--.");
        stringList[16] = new MorseString("q", "--.-");
        stringList[17] = new MorseString("r", ".-.");
        stringList[18] = new MorseString("s", "...");
        stringList[19] = new MorseString("t", "-");
        stringList[20] = new MorseString("u", "..-");
        stringList[21] = new MorseString("v", "...-");
        stringList[22] = new MorseString("w", ".--");
        stringList[23] = new MorseString("x", "-..-");
        stringList[24] = new MorseString("y", "-.--");
        stringList[25] = new MorseString("z", "--..");
        stringList[26] = new MorseString("1", ".----");
        stringList[27] = new MorseString("2", "..---");
        stringList[28] = new MorseString("3", "...--");
        stringList[29] = new MorseString("4", "....-");
        stringList[30] = new MorseString("5", ".....");
        stringList[31] = new MorseString("6", "-....");
        stringList[32] = new MorseString("7", "--...");
        stringList[33] = new MorseString("8", "---..");
        stringList[34] = new MorseString("9", "----.");
        stringList[35] = new MorseString("0", "-----");
        stringList[36] = new MorseString(".", ".-.-.-");
        stringList[37] = new MorseString(",", "--..--");
        stringList[38] = new MorseString(":", "---...");
        stringList[39] = new MorseString(";", "-.-.-.");
        stringList[40] = new MorseString("(", "-.--.");
        stringList[41] = new MorseString(")", "-.--.-");
        stringList[42] = new MorseString("", ".-..-.");
        stringList[43] = new MorseString("'", ".----.");
        stringList[44] = new MorseString("-", "-....-");
        stringList[45] = new MorseString("/", "-..-.");
        stringList[46] = new MorseString("?", "..--..");
        stringList[47] = new MorseString("!", "-.-.--");
        stringList[48] = new MorseString("@", ".--.-.");

    }
}