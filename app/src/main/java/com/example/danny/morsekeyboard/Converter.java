package com.example.danny.morsekeyboard;
import java.util.*;
public class Converter {
    private MorseStrings stringList[] = new MorseStrings[49];
    private HashMap<String,MorseStrings> mappedStrings;

    public Converter(){
        this.mappedStrings = buildMap();
    }

    public void buildStrings(){
        stringList[0] = new MorseStrings("a", ".-");
        stringList[1] = new MorseStrings("b", "-...");
        stringList[2] = new MorseStrings("c", "-.-.");
        stringList[3] = new MorseStrings("d", "-..");
        stringList[4] = new MorseStrings("e", ".");
        stringList[5] = new MorseStrings("f", "..-.");
        stringList[6] = new MorseStrings("g", "--.");
        stringList[7] = new MorseStrings("h", "....");
        stringList[8] = new MorseStrings("i", "..");
        stringList[9] = new MorseStrings("j", ".---");
        stringList[10] = new MorseStrings("k", "-.-");
        stringList[11] = new MorseStrings("l", ".-..");
        stringList[12] = new MorseStrings("m", "--");
        stringList[13] = new MorseStrings("n", "-.");
        stringList[14] = new MorseStrings("o", "---");
        stringList[15] = new MorseStrings("p", ".--.");
        stringList[16] = new MorseStrings("q", "--.-");
        stringList[17] = new MorseStrings("r", ".-.");
        stringList[18] = new MorseStrings("s", "...");
        stringList[19] = new MorseStrings("t", "-");
        stringList[20] = new MorseStrings("u", "..-");
        stringList[21] = new MorseStrings("v", "...-");
        stringList[22] = new MorseStrings("w", ".--");
        stringList[23] = new MorseStrings("x", "-..-");
        stringList[24] = new MorseStrings("y", "-.--");
        stringList[25] = new MorseStrings("z", "--..");
        stringList[26] = new MorseStrings("1", ".----");
        stringList[27] = new MorseStrings("2", "..---");
        stringList[28] = new MorseStrings("3", "...--");
        stringList[29] = new MorseStrings("4", "....-");
        stringList[30] = new MorseStrings("5", ".....");
        stringList[31] = new MorseStrings("6", "-....");
        stringList[32] = new MorseStrings("7", "--...");
        stringList[33] = new MorseStrings("8", "---..");
        stringList[34] = new MorseStrings("9", "----.");
        stringList[35] = new MorseStrings("0", "-----");
        stringList[36] = new MorseStrings(".", ".-.-.-");
        stringList[37] = new MorseStrings(",", "--..--");
        stringList[38] = new MorseStrings(":", "---...");
        stringList[39] = new MorseStrings(";", "-.-.-.");
        stringList[40] = new MorseStrings("(", "-.--.");
        stringList[41] = new MorseStrings(")", "-.--.-");
        stringList[42] = new MorseStrings("", ".-..-.");
        stringList[43] = new MorseStrings("'", ".----.");
        stringList[44] = new MorseStrings("-", "-....-");
        stringList[45] = new MorseStrings("/", "-..-.");
        stringList[46] = new MorseStrings("?", "..--..");
        stringList[47] = new MorseStrings("!", "-.-.--");
        stringList[48] = new MorseStrings("@", ".--.-.");

    }

    public HashMap<String,MorseStrings> buildMap(){
        buildStrings();
        HashMap<String, MorseStrings> map = new HashMap<String,MorseStrings>();
        for(MorseStrings letterCode: stringList){
            map.put(letterCode.getCode(), letterCode);
        }
        return map;
    }

    public String getLetter(String key){
        return mappedStrings.get(key).getLetter();
    }
}

/**
 * Created by Danny on 1/6/2016.
 */