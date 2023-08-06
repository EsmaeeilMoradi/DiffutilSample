package com.esm.diffutilsample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.esm.diffutilsample.utils.MyAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    Button btn_insert, btn_update;
    RecyclerView recyclerView;
    List<String> dataSource = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btn_insert = findViewById(R.id.btn_insert);
        btn_update = findViewById(R.id.btn_update);

        initData();

        MyAdapter adapter = new MyAdapter(dataSource);
        recyclerView.setAdapter(adapter);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> insertList = new ArrayList<>();//Assign old data

                for (int i = 0; i < 3; i++)
                    insertList.add(UUID.randomUUID().toString()); //Add new data

                adapter.insertData(insertList);
                recyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);//Auto scroll to last item
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> updateList = new ArrayList<>();//Assign old data

                for (int i = 0; i < 3; i++)
                    updateList.add(UUID.randomUUID().toString()); //Add new data

                adapter.updateData(updateList);
            }
        });

    }

    private void initData() {
        for (int i = 0; i < 3; i++) {
            dataSource.add(UUID.randomUUID().toString());
        }


    }
}