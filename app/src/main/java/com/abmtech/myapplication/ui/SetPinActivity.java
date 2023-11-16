package com.abmtech.myapplication.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.abmtech.myapplication.R;
import com.abmtech.myapplication.databinding.ActivitySetPinBinding;
import com.abmtech.myapplication.room.AppDatabase;
import com.abmtech.myapplication.room.BusinessDao;
import com.abmtech.myapplication.room.UserDao;
import com.abmtech.myapplication.utils.CustomEditText;
import com.abmtech.myapplication.utils.Session;

public class SetPinActivity extends AppCompatActivity implements CustomEditText.handleDismissingKeyboard{
    private Activity activity;
    private ActivitySetPinBinding binding;
    private EditText[] editTexts;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetPinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;

        Session session = new Session(activity);

        userDao = AppDatabase.getInstance(activity).userDao();

        editTexts = new EditText[]{binding.edtOne, binding.edtTwo, binding.edtThree, binding.edtFour};

        binding.edtOne.requestFocus();

        binding.edtOne.setHandleDismissingKeyboard(this);
        binding.edtTwo.setHandleDismissingKeyboard(this);
        binding.edtThree.setHandleDismissingKeyboard(this);
        binding.edtFour.setHandleDismissingKeyboard(this);

        binding.edtOne.addTextChangedListener(new PinTextWatcher(0));
        binding.edtTwo.addTextChangedListener(new PinTextWatcher(1));
        binding.edtThree.addTextChangedListener(new PinTextWatcher(2));
        binding.edtFour.addTextChangedListener(new PinTextWatcher(3));

        binding.edtOne.setOnKeyListener(new PinOnKeyListener(0));
        binding.edtTwo.setOnKeyListener(new PinOnKeyListener(1));
        binding.edtThree.setOnKeyListener(new PinOnKeyListener(2));
        binding.edtFour.setOnKeyListener(new PinOnKeyListener(3));

        binding.cardSubmit.setOnClickListener(v -> {
            if (valid()) {
                String one = binding.edtOne.getText().toString();
                String two = binding.edtTwo.getText().toString();
                String three = binding.edtThree.getText().toString();
                String four = binding.edtFour.getText().toString();
                final String pin = one + two + three + four;

                session.setMyPin(pin);
                session.setLogin(true);

                new Thread(() -> userDao.UpdateUserPin(Integer.parseInt(session.getUserId()), pin)).start();

                startActivity(new Intent(activity, DashboardActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
    }


    private boolean valid() {
        boolean flag = true;
        if (binding.edtOne.getText().toString().isEmpty()) {
            binding.edtOne.setError("Fill Pin");
            flag = false;
        } else if (binding.edtTwo.getText().length() <= 0) {
            binding.edtTwo.setError("Fill Pin");
            flag = false;
        } else if (binding.edtThree.getText().length() <= 0) {
            binding.edtThree.setError("Fill Pin");
            flag = false;
        } else if (binding.edtFour.getText().length() <= 0) {
            binding.edtFour.setError("Fill Pin");
            flag = false;
        }
        return flag;
    }

    @Override
    public void dismissKeyboard() {

    }

    public class PinTextWatcher implements TextWatcher {
        private final int currentIndex;
        private boolean isFirst = false, isLast = false;
        private String newTypedString = "";

        PinTextWatcher(int currentIndex) {
            this.currentIndex = currentIndex;

            if (currentIndex == 0)
                this.isFirst = true;
            else if (currentIndex == editTexts.length - 1)
                this.isLast = true;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            newTypedString = s.subSequence(start, start + count).toString().trim();
        }


        @Override
        public void afterTextChanged(Editable s) {
            String text = newTypedString;

            /* Detect paste event and set first char */
            if (text.length() > 1)
                text = String.valueOf(text.charAt(0));

            editTexts[currentIndex].removeTextChangedListener(this);
            editTexts[currentIndex].setText(text);
            editTexts[currentIndex].setSelection(text.length());
            editTexts[currentIndex].addTextChangedListener(this);
            editTexts[currentIndex].setBackground(ContextCompat.getDrawable(activity, R.drawable.custom_bg_edt_pin_full));
            if (text.length() == 1)
                moveToNext();
            else if (text.length() == 0) {
                editTexts[currentIndex].setBackground(ContextCompat.getDrawable(activity, R.drawable.custom_bg_edt_pin));
                moveToPrevious();
            }

            String one = binding.edtOne.getText().toString();
            String two = binding.edtTwo.getText().toString();
            String three = binding.edtThree.getText().toString();
            String four = binding.edtFour.getText().toString();
            final String pin = one + two + three + four;

            if (pin.length() == 4) {
                binding.cardSubmit.setCardBackgroundColor(getColor(R.color.color_primary));
                binding.textSubmit.setTextColor(getColor(R.color.white));
            } else {
                binding.cardSubmit.setCardBackgroundColor(getColor(R.color.background));
                binding.textSubmit.setTextColor(getColor(R.color.black));
            }
        }

        private void moveToNext() {
            if (!isLast)
                editTexts[currentIndex + 1].requestFocus();

            if (isAllEditTextsFilled() && isLast) { // isLast is optional
                editTexts[currentIndex].clearFocus();
                // hideKeyboard();
            }
        }

        private void moveToPrevious() {
            if (!isFirst)
                editTexts[currentIndex - 1].requestFocus();
        }

        private boolean isAllEditTextsFilled() {
            for (EditText editText : editTexts)
                if (editText.getText().toString().trim().length() == 0)
                    return false;
            return true;
        }
    }

    public class PinOnKeyListener implements View.OnKeyListener {
        private final int currentIndex;

        PinOnKeyListener(int currentIndex) {
            this.currentIndex = currentIndex;
        }

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
                if (editTexts[currentIndex].getText().toString().isEmpty() && currentIndex != 0)
                    editTexts[currentIndex - 1].requestFocus();
            }
            return false;
        }
    }

}