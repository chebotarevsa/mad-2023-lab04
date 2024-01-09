package com.romanov.labs.lab4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.romanov.labs.lab4.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_CARD = "extra_card";
    private ActivityMainBinding binding = null;
    private CardAdapter adapter = null;
    ArrayList<Card> cards = new ArrayList<>();
    private ItemTouchHelper itemTouchHelper;

    @SuppressLint("NotifyDataSetChanged")
    private void onAddCardResult(ActivityResult result) {
        if (result.getResultCode() == RESULT_OK) {
            Intent data = result.getData();
            if (data != null) {
                Card addedCard = (Card) data.getSerializableExtra(EXTRA_CARD);
                if (addedCard != null) {
                    cards.add(addedCard);
                    XmlHelper.saveToXml(MainActivity.this, cards);
                    notifyDataSetChanged();
                }
            }
        }
    }

    private final ActivityResultLauncher<Intent> addCardLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), this::onAddCardResult);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(manager); // setInitialData();
        cards.addAll(XmlHelper.readFromXml(MainActivity.this, "cards.xml"));
        notifyDataSetChanged();
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(
                0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                // Not implementing as we're not considering item movement
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // Handling swipe (deleting item)
                int position = viewHolder.getAdapterPosition();
                showDeleteConfirmationDialog(position);
            }
        };

        itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(binding.recyclerView);
    }

    private void notifyDataSetChanged() { // Обработка свайпа (удаление элемента)
        adapter = new CardAdapter(MainActivity.this, cards, null); // создаем адаптер
        binding.recyclerView.setAdapter(adapter); // устанавливаем для списка адаптер
    }

    private void showDeleteConfirmationDialog(int position) {
        binding.recyclerView.setAdapter(adapter); // устанавливаем для списка адаптер
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Подтверждение удаления");
        builder.setMessage("Вы уверены, что хотите удалить эту карточку?");
        builder.setPositiveButton("Да", (dialog, which) -> {
            adapter.removeItem(position);
            cards.remove(position);
            XmlHelper.saveToXml(MainActivity.this, cards);
            binding.recyclerView.setAdapter(adapter); // устанавливаем для списка адаптер
//            deleteListener.onItemDelete(position);
        });
        builder.setNegativeButton("Нет", (dialog, which) -> {
            dialog.dismiss();
        });
        builder.show();
    }

    public void addCard(View view) {
        Intent intent = new Intent(this, EditCardActivity.class);
        addCardLauncher.launch(intent);
    }
}
