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
    private Keyboard keyboard;
    private boolean caps = false;
    private boolean capsLock = false;
    private String currentCode = "";

    private long elementTimer = 0l;
    private final long dotTime = 200l;
    private static final int charMultiplier = 3;
    private static final int spaceMultiplier = 7;
    private static final String spaceText = " ";
    private static Timer spaceTimer = new Timer();
    private static Timer charTimer = new Timer();

    //Add tone

    /**
     * Creates view of keyboard
     * @return Returns keyboard view
     */
    @SuppressLint("InflateParams")
    @Override
    public View onCreateInputView(){
        KeyboardView keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboard = new Keyboard(this, R.xml.morsekey);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(this);
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
        Long elapsedTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - this.elementTimer);
        currentCode += MorseMaker.getDotOrDash(dotTime, elapsedTime);
        setKeyboardLabelText(currentCode);
        createCharTimer(connection);
        createSpaceTimer(connection);
    }

    /**
     * Operates shifting and capsLock
     */
    @Override
    public void swipeUp() {
        if(capsLock){
            caps = false;
            capsLock = false;
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

    private void setKeyboardLabelText(String currentCode){
        Keyboard.Key key = keyboard.getKeys().get(0);
        StringBuilder builder = new StringBuilder(MorseMaker.getCharacter(currentCode));
        builder.append("\n");
        builder.append(currentCode);
        key.label = builder.toString();
    }

    /**
     * Creates a timer that waits 7*dotTime before adding a space
     * @param connection The connection the space is added too
     * @return A timer that waits to add a space
     */
    private void createSpaceTimer(final InputConnection connection){
        MorseIME.spaceTimer = new Timer();
        MorseIME.spaceTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                addSpace(connection);
            }
        }, spaceMultiplier * dotTime);
    }

    /**
     * Creates a timer based on 3*dotTime to add a char
     * @param connection The connection attached to a textfield
     * @return A timer that waits to add a space
     */
    private void createCharTimer(final InputConnection connection){
        MorseIME.charTimer = new Timer();
        MorseIME.charTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                addChar(connection);
            }
        }, charMultiplier * dotTime);
    }

    /**
     * Adds spaces to textfield
     */
    private void addSpace(InputConnection connection){
        connection.commitText(String.valueOf(spaceText), 1);
    }

    /**
     * Adds char represented by currentCode to textfield
     * @param connection The connection that is attached to the textfield
     */
    private void addChar(InputConnection connection){
        String conversion = MorseMaker.getCharacter(currentCode);
        if (caps){
            conversion = conversion.toUpperCase();
        }
        connection.commitText(String.valueOf(conversion), 1);
        currentCode = "";
    }

    /**
     * Converts nano seconds to milliseconds
     * @param timeInNano Time in nanoseconds
     * @return Equivalent time in milliseconds
     */
    private long nanoToMilli(Long timeInNano){
        return timeInNano/(10^6);
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
