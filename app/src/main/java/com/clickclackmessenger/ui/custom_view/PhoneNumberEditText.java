package com.clickclackmessenger.ui.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.clickclackmessenger.R;

import java.util.ArrayList;
import java.util.List;

public class PhoneNumberEditText extends LinearLayout {
    public static final String TAG = "PHONE.NUMBER.EDIT.TEXT";

    private static final int MAX_NUMBER_COUNT = 12;

    private EditText plusEditText;

    private final List<EditText> numbers;

    private int numberCounter;

    private OnFieldsFilled onFieldsFilled;

    private boolean isCellEmpty;
    private boolean isDelliting;

    public PhoneNumberEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
        numberCounter = 0;
        isCellEmpty = true;
        numbers = new ArrayList<>();
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.number_form_layout, this);

        initComponents();
    }

    public void attachListener(OnFieldsFilled onFieldsFilled) {
        this.onFieldsFilled = onFieldsFilled;
    }

    private void initComponents() {
        plusEditText = findViewById(R.id.cellphone__first_char);
        plusEditText.setEnabled(false);

        EditText firstCharEditText = findViewById(R.id.cellphone__second_char);
        numbers.add(firstCharEditText);

        EditText secondCharEditText = findViewById(R.id.cellphone__third_char);
        numbers.add(secondCharEditText);

        EditText thirdCharEditText = findViewById(R.id.cellphone__fourth_char);
        numbers.add(thirdCharEditText);

        EditText fourthCharEditText = findViewById(R.id.cellphone__fifth_char);
        numbers.add(fourthCharEditText);

        EditText fifthCharEditText = findViewById(R.id.cellphone__sixth_char);
        numbers.add(fifthCharEditText);

        EditText sixthCharEditText = findViewById(R.id.cellphone__seventh_char);
        numbers.add(sixthCharEditText);

        EditText seventhCharEditText = findViewById(R.id.cellphone__eighth_char);
        numbers.add(seventhCharEditText);

        EditText eighthCharEditText = findViewById(R.id.cellphone__ninth_char);
        numbers.add(eighthCharEditText);

        EditText ninthCharEditText = findViewById(R.id.cellphone__tenth_char);
        numbers.add(ninthCharEditText);

        EditText tenthCharEditText = findViewById(R.id.cellphone__eleventh_char);
        numbers.add(tenthCharEditText);

        EditText eleventhCharEditText = findViewById(R.id.cellphone__twelfth_char);
        numbers.add(eleventhCharEditText);

        EditText twelvthCharEditText = findViewById(R.id.cellphone__thirteenth_char);
        numbers.add(twelvthCharEditText);

        setNumberListeners();
    }

    private void setNumberListeners() {
        for (EditText editText : numbers) {
            editText.addTextChangedListener(new EditTextWatcher());
            editText.setOnFocusChangeListener(new OnFocusListener());
            editText.setOnKeyListener(new DeleteText());
        }
    }

    public String getNumber() {
        StringBuilder number = new StringBuilder(13);
        for (EditText numeral : numbers) {
            number.append(numeral.getText().toString());
        }
        return number.toString();
    }

    private class OnFocusListener implements OnFocusChangeListener {

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            EditText editText = numbers.get(numberCounter);
            if (v.equals(editText)){
                return;
            }
            editText.requestFocus();
        }
    }

    private class EditTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if (s.toString().equals("")) {
                return;
            } else {
                numberCounter++;
                if (numberCounter >= MAX_NUMBER_COUNT) {
                    sendCode();
                    return;
                }
                numbers.get(numberCounter).requestFocus();
            }
            Log.d(TAG, "onTextChanged: number counts == " + numberCounter);
        }

        @Override
        public void afterTextChanged(Editable s) {
            System.out.println(12);
        }
    }

    private void sendCode() {
        if (onFieldsFilled != null) {
            String number = getNumber();
            onFieldsFilled.numberFilled(number);
        }
    }

    private class DeleteText implements OnKeyListener {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {

            if (event.getAction()!= KeyEvent.ACTION_DOWN || numberCounter < 1)
                return true;

            if (keyCode == KeyEvent.KEYCODE_DEL) {
                Log.d(TAG, "onKey: keyCode == KeyEvent.KEYCODE_DEL -> TRUE, COUNT NUMBERS == " + numberCounter);
                numberCounter--;
                if (numberCounter == MAX_NUMBER_COUNT) {
                    EditText editText = numbers.get(numberCounter);
                    editText.setText("");
                    return true;
                }
                EditText editText = numbers.get(numberCounter);
                editText.setText("");
                editText.requestFocus();
                return true;
            }
            return false;
        }
    }

    public interface OnFieldsFilled {

        void numberFilled(String number);
    }
}
