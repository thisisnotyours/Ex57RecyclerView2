package com.suek.ex57recyclerview2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    //대량의 데이터  <Item> 내 java 패키지명
    ArrayList<Item> items= new ArrayList<>();
    MyAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //대량의 데이터 추가 [실무에서는 DB나 서버에서 데이터를 읽어옴]
        items.add(new Item("루피","해적단 선장", R.drawable.img01, "https://www.mhns.co.kr/news/photo/201905/209915_311713_5353.png"));
        items.add(new Item("조로","해적단 검사", R.drawable.img02, "https://www.kyeonggi.com/news/photo/202001/2233115_1020889_3432.jpg"));
        items.add(new Item("나미","해적단 항해사", R.drawable.img03, "https://image.aladin.co.kr/product/19316/13/cover500/k142635408_1.jpg"));
        items.add(new Item("우솝","해적단 저격수", R.drawable.img04, "https://cds.ggilbo.com/news/photo/201903/626133_474182_135.jpg"));
        items.add(new Item("상디","해적단 요리사", R.drawable.img05, "https://photo.jtbc.joins.com/news/2019/07/18/20190718172008598.jpg"));
        items.add(new Item("초파","해적단 의사", R.drawable.img06, "https://image.yes24.com/momo/TopCate1748/MidCate009/174781379.jpg"));
        items.add(new Item("니코로빈","해적단 한량", R.drawable.img07, "https://image.yes24.com/momo/TopCate1293/MidCate003/129223560.jpg"));
        items.add(new Item("프랑크","해적단 목수", R.drawable.img08, "https://image.aladin.co.kr/product/23590/14/cover500/k212638327_1.jpg"));

        adapter= new MyAdapter(this, items);
        recyclerView= findViewById(R.id.recycler);
        recyclerView.setAdapter(adapter);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_aa:   //Linear
                RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
                break;

            case R.id.menu_bb:   //Grid
                RecyclerView.LayoutManager layoutManager1= new GridLayoutManager(this, 2);
                recyclerView.setLayoutManager(layoutManager1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
