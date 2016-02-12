package com.example.danny.morsekeyboard;

import android.annotation.SuppressLint;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MorseIME extends InputMethodService implements KeyboardView.OnKeyboardActionListener{
    private KeyboardView keyboardView;

    private boolean caps = false;
    private boolean capsLock = false;

    private long elementTimer = 0l;
    private long dotTime = 200l;
    private static Timer spaceTimer = new Timer();
    private static Timer charTimer = new Timer();
    private static String currentCode = "";


    //Add tone

    /**
     * Creates view of keyboard
     * @return Returns keyboard view
     */
    @SuppressLint("InflateParams")
    @Override
    public View onCreateInputView(){
        keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard, null);
        Keyboard keyboard = new Keyboard(this, R.xml.morsekey);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(this);
        //keyboardView.getKeyboard().getKeys().get(0).
        return keyboardView;
    }

    /**
     * Starts timer when a key is pressed
     * @param i Key pressed
     */
    @Override
    public void onPress(int i) {
        MorseIME.spaceTimer.cancel();
        MorseIME.charTimer.cancel();
        this.elementTimer = System.nanoTime();
    }

    /**
     * Adds a dot or dash to currentCode when the key pressed is released
     * @param i Key pressed
     */
    @Override
    public void onRelease(int i) {
        MorseIME.spaceTimer.cancel();
        MorseIME.charTimer.cancel();
        InputConnection connection = getCurrentInputConnection();
        //connection.commitText("apple", 1);
        Long elapsedTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - this.elementTimer);
        currentCode += MorseMaker.getDotOrDash(dotTime, elapsedTime);
        keyboardView.getKeyboard().getKeys().get(0).label = currentCode;
        /*List<Keyboard.Key> keyList = keyboardView.getKeyboard().getKeys();
        if(keyList.isEmpty()){
            connection.commitText("apple", 1);
        } else {
            connection.commitText("orange", 1);

        }
        key.label = (CharSequence)currentCode;
        */

        createCharTimer(connection);
        createSpaceTimer(connection);
    }

    /**
     * Operates shifting and capsLock
     */
    @Override
    public void swipeUp() {
        if(this.capsLock){
            this.caps = false;
            this.capsLock = false;
        } else if(caps){
            capsLock = true;
        } else {
            caps = true;
        }
    }

    /**
     * Erases most previous character entered
     */
    @Override
    public void swipeLeft() {
        //Try to erase all to till the last space on a double swipe
        InputConnection connection = getCurrentInputConnection();
        connection.deleteSurroundingText(1, 0);

    }

    /**
     * Send enter event to textfield
     */
    @Override
    public void swipeRight() {
        InputConnection connection = getCurrentInputConnection();
        connection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
    }

    /**
     * Creates a timer that waits 7*dotTime before adding a space
     * @param connection The connection the space is added too
     */
    private void createSpaceTimer(final InputConnection connection){
        MorseIME.spaceTimer = new Timer();
        MorseIME.spaceTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                addSpace(connection);
                //keyboardView.getKeyboard().getKeys().get(0).label = "";
            }
        }, 7 * dotTime);
    }

    /**
     * Creates a timer based on 3*dotTime to add a char
     * @param connection The connection attached to a textfield
     */
    private void createCharTimer(final InputConnection connection){
        MorseIME.charTimer = new Timer();
        MorseIME.charTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                addChar(connection);
            }
        }, 3 * dotTime);
    }

    /**
     * Adds spaces to textfield
     */
    private void addSpace(InputConnection connection){
        connection.commitText(String.valueOf(" "), 1);
    }

    /**
     * Adds char represented by currentCode to textfield
     * @param connection The connection that is attached to the textfield
     */
    private void addChar(InputConnection connection) {
        String conversion = MorseMaker.getCharacter(MorseIME.currentCode);
        if (caps) {
            conversion = conversion.toUpperCase();
        }
        connection.commitText(String.valueOf(conversion), 1);
        MorseIME.currentCode = "";
    }

    @Override
    public void onKey(int i, int[] ints) {

    }

    @Override
    public void onText(CharSequence charSequence) {

    }

    @Override
    public void swipeDown() {

    }
}
