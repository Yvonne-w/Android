package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class HelpIntroViewPagerAdapter extends PagerAdapter {
    Context context;
    List<ScreenItem> screenItemList;
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View layoutScreen = LayoutInflater.from(context).inflate(R.layout.layout_screen, parent, false);
//        return new MyViewHolder(layoutScreen);
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        holder.textViewName.setText();
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return screenItemList.size();
//    }
//
//    public HelpIntroViewPagerAdapter(Context context, List<ScreenItem> screenItemList) {
//        this.context = context;
//        this.screenItemList = screenItemList;
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView textViewName;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            textViewName = itemView.findViewById(R.id.textViewName);
//        }
//    }

    //    Context context;
//    List<ScreenItem> screenItemList;
//
    public HelpIntroViewPagerAdapter(Context mContext, List<ScreenItem> screenItemList) {
        this.context = mContext;
        this.screenItemList = screenItemList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.layout_screen, null);

        ImageView imgSlide = layoutScreen.findViewById(R.id.intro_img);
        TextView title = layoutScreen.findViewById(R.id.intro_title);
        TextView description = layoutScreen.findViewById(R.id.intro_description);

        imgSlide.setImageResource(screenItemList.get(position).getScreenImg());
        title.setText(screenItemList.get(position).getTitle());
        description.setText(screenItemList.get(position).getDescription());

        container.addView(layoutScreen);
        return layoutScreen;

    }

    @Override
    public int getCount() {
        return screenItemList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
