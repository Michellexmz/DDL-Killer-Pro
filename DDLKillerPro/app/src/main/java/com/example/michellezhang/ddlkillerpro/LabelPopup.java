package com.example.michellezhang.ddlkillerpro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.goodiebag.horizontalpicker.HorizontalPicker;

import java.util.ArrayList;
import java.util.List;

public class LabelPopup extends AppCompatActivity {
    HorizontalPicker hpImage ;
    HorizontalPicker hpImage2;
    HorizontalPicker hpImage3;
    HorizontalPicker.PickerItem selected;
    Integer logoIndex = 0;
    final private int RESULT_POPUP = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label_popup);
        hpImage = (HorizontalPicker) findViewById(R.id.hpImage1);
        hpImage2 = (HorizontalPicker) findViewById(R.id.hpImage2);
        hpImage3 = (HorizontalPicker) findViewById(R.id.hpImage3);

        Button btnCancel = (Button) findViewById(R.id.button2);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initPicker();

        HorizontalPicker.OnSelectionChangeListener listener = new HorizontalPicker.OnSelectionChangeListener() {
            @Override
            public void onItemSelect(HorizontalPicker picker, int index) {
                selected = picker.getSelectedItem();
                logoIndex = picker.getSelectedIndex();
                Toast.makeText(LabelPopup.this, selected.getDrawable() + "\n" + logoIndex, Toast.LENGTH_SHORT).show();
            }
        };
        hpImage.setChangeListener(listener);

        Button btnConfirm = (Button) findViewById(R.id.button);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To add a label
                EditText labelName = (EditText) findViewById(R.id.labelName);
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("labelID", logoIndex.toString());
                bundle.putString("labelName", labelName.getText().toString());
                intent.putExtras(bundle);
                setResult(RESULT_POPUP, intent);
                finish();
            }
        });
    }

    private void initPicker(){

        List<HorizontalPicker.PickerItem> imageItems = new ArrayList<>();
        imageItems.add(new HorizontalPicker.DrawableItem(R.drawable.calendar));
        imageItems.add(new HorizontalPicker.DrawableItem(R.drawable.groupflag));
        imageItems.add(new HorizontalPicker.DrawableItem(R.drawable.item));
        imageItems.add(new HorizontalPicker.DrawableItem(R.drawable.battery));
        imageItems.add(new HorizontalPicker.DrawableItem(R.drawable.chicken));
        hpImage.setItems(imageItems);

        List<HorizontalPicker.PickerItem> imageItems2 = new ArrayList<>();
        imageItems2.add(new HorizontalPicker.DrawableItem(R.drawable.candy));
        imageItems2.add(new HorizontalPicker.DrawableItem(R.drawable.clock));
        imageItems2.add(new HorizontalPicker.DrawableItem(R.drawable.lollipol));
        imageItems2.add(new HorizontalPicker.DrawableItem(R.drawable.star));
        imageItems2.add(new HorizontalPicker.DrawableItem(R.drawable.talk));
        hpImage2.setItems(imageItems2);

        List<HorizontalPicker.PickerItem> imageItems3 = new ArrayList<>();
        imageItems3.add(new HorizontalPicker.DrawableItem(R.drawable.football));
        imageItems3.add(new HorizontalPicker.DrawableItem(R.drawable.umbre));
        imageItems3.add(new HorizontalPicker.DrawableItem(R.drawable.library));
        imageItems3.add(new HorizontalPicker.DrawableItem(R.drawable.life));
        imageItems3.add(new HorizontalPicker.DrawableItem(R.drawable.school));
        hpImage3.setItems(imageItems3);

    }
}
