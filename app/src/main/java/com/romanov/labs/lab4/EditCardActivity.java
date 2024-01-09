package com.romanov.labs.lab4;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.Serializable;

public class EditCardActivity extends AppCompatActivity {
    public static final String EXTRA_CARD = "extra_card";
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private EditText editText5;
    private ImageButton imageButton;
    private Uri selectedImageUri;

    // Создаем лаунчер для получения изображения из галереи
    private final ActivityResultLauncher<String> getContent =
            registerForActivityResult(new ActivityResultContracts.GetContent(),
                    new ActivityResultCallback<Uri>() {
                        @Override
                        public void onActivityResult(Uri result) {
                            // Обработка выбранного изображения
                            if (result != null) {
                                selectedImageUri = result;
                                // Здесь вы можете использовать selectedImageUri
                                // Например, установить его в качестве изображения в ImageButton
                                imageButton.setImageURI(selectedImageUri);
                            }
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_card);

        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        editText5 = findViewById(R.id.editText5);
        imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(v -> {
            // Запустите активность выбора изображения из галереи
            getContent.launch("image/*");
        });
    }

    private void saveCard() {
        Card editedCard = createCardFromFormData();
        Intent resultIntent = new Intent(this, MainActivity.class);
        resultIntent.putExtra(EXTRA_CARD, (Serializable) editedCard);
        setResult(RESULT_OK, resultIntent);
        finish();
    }


    private Card createCardFromFormData() {
        String question = editText2.getText().toString();
        String example = editText3.getText().toString();
        String answer = editText4.getText().toString();
        String translate = editText5.getText().toString();

        return new Card(0, question, example, answer, translate, selectedImageUri.toString()); // Передайте URI изображения при необходимости
    }

    public void doneCard(View view) {
        saveCard();
    }
}