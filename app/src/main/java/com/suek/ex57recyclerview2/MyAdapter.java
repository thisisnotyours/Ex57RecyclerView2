package com.suek.ex57recyclerview2;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<Item> items;



    public MyAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {  // 1) 보여줄 뷰 만들기

        LayoutInflater inflater= LayoutInflater.from(context);    //view 를 Main 에 붙여주려면 Context 에 허락받기
        View itemView= inflater.inflate(R.layout.recycler_item, parent, false); //parent= activity_main.xml  //recycler_item 을 parent 에 붙여라.
        //뷰홀더(아이템뷰 안에있는 뷰들: tv, tv_name..etc) 객체 생성 및 리턴
        VH holder= new VH(itemView);   // itemView == cardView


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {     // 3) 값을 연결하는 곳

        VH vh= (VH)holder;
        //현재번째(position 번째) 데이터를 가진 Item 객체 얻어오기
        Item item= items.get(position);

        vh.civ.setImageResource(item.profileImg);
        vh.tvName.setText(item.name);
        vh.tvMsg.setText(item.msg);
        //네트워크 이미지를 불러오는 작업을 쉽게 해주는 외부 라이브러리 사용: Glide
        Glide.with(context).load(item.imgURL).into(vh.iv);
        Glide.with(context).load(item.profileImg).into(vh.civ);   //이미지도 glide 또는 piccaso library 사용

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //아이템뷰(뷰홀더)의 안에있는 뷰들의 참조변수를 멤버로 가진 클래스
    class VH extends RecyclerView.ViewHolder{

        CircleImageView civ;
        TextView tvName;
        TextView tvMsg;
        ImageView iv;

        public VH(@NonNull View itemView) {
            super(itemView);

            civ= itemView.findViewById(R.id.iv_profile);              // 2) 뷰 연결해주기
            tvName= itemView.findViewById(R.id.tv_name);
            tvMsg= itemView.findViewById(R.id.tv_msg);
            iv= itemView.findViewById(R.id.iv);


            //아이템 클릭 리스너 생성 및 설정
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Item item= items.get(getLayoutPosition());  //getLayoutPosition()->  현재클릭한 번째의 데이터 참조

                    Intent intent= new Intent(context, DetailActivity.class);

                    //전달할 데이터 추가
                    intent.putExtra("Name",item.name);
                    intent.putExtra("img", item.profileImg);

                    //전환효과 (api 21버전 이상에서만 가능함)  --->  Detail.java로 가기
                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                        ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation((Activity)context, new Pair<View, String>(civ,"IMG"));  //civ- 프로필사진에 별명(IMG)주기
                        context.startActivity(intent, options.toBundle());
                    }else{
                        context.startActivity(intent);
                    }


                }
            });

        }
    }

}
