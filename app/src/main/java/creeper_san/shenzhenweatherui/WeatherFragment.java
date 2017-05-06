package creeper_san.shenzhenweatherui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class WeatherFragment extends Fragment {
    private RecyclerView recyclerView;
    private ImageView backgroundImg;
    private ImageView drawerBtnImage;
    private ViewPager viewPager;
    private List<ForecastItem> weatherList;
    private WeatherDetailAdapter pagerAdapter;
    private List<WeatherDetailFragment> weatherDetailFragmentList;
    private OnDrawerBtnClickListener onDrawerBtnClickListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.fragmentWeatherWeatherList);
        viewPager = (ViewPager) view.findViewById(R.id.fragmentWeatherWeatherPager);
        backgroundImg = (ImageView) view.findViewById(R.id.fragmentWeatherBackground);
        drawerBtnImage = (ImageView) view.findViewById(R.id.fragmentForecastDrawerBtn);
        drawerBtnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDrawerBtnClickListener!=null){
                    onDrawerBtnClickListener.onClick();
                }
            }
        });
        initData();
        initRecyclerView();
        initViewPager();
        return view;
    }

    public void setCurrentPage(int num){
        if (num>weatherList.size()){
            viewPager.setCurrentItem(weatherList.size()-1);
        }else {
            viewPager.setCurrentItem(num);
        }
    }

    private void initViewPager() {
        weatherDetailFragmentList = new ArrayList<>();
        for (int i=0;i<5;i++){
            WeatherDetailFragment fragment = new WeatherDetailFragment();
            fragment.setImgID((int) (Math.random()*5));
            weatherDetailFragmentList.add(fragment);
        }
        pagerAdapter = new WeatherDetailAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        setBackground(weatherDetailFragmentList.get(0).getImgID());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            @Override
            public void onPageSelected(int position) {
                setBackground(weatherDetailFragmentList.get(position).getImgID());
            }
            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    private void setBackground(int id){
        switch (id){
            case 0:
                Glide.with(getContext()).load(R.drawable.bg1).into(backgroundImg);
                break;
            case 1:
                Glide.with(getContext()).load(R.drawable.bg2).into(backgroundImg);
                break;
            case 2:
                Glide.with(getContext()).load(R.drawable.bg3).into(backgroundImg);
                break;
            case 3:
                Glide.with(getContext()).load(R.drawable.bg4).into(backgroundImg);
                break;
            default:
                Glide.with(getContext()).load(R.drawable.bg5).into(backgroundImg);
                break;
        }
    }

    private void initData() {
        weatherList = new ArrayList<>();
        for (int i=0;i<14;i++){
            ForecastItem item = new ForecastItem();
            item.setImgID((int) (Math.random()*5));
            item.setTxtBottom("25/29");
            item.setTxtTop("5/7");
            item.setTxtMiddle("周一");
            weatherList.add(item);
        }
    }

    public OnDrawerBtnClickListener getOnDrawerBtnClickListener() {
        return onDrawerBtnClickListener;
    }

    public void setOnDrawerBtnClickListener(OnDrawerBtnClickListener onDrawerBtnClickListener) {
        this.onDrawerBtnClickListener = onDrawerBtnClickListener;
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        ForecastAdapter adapter = new ForecastAdapter();
        recyclerView.setAdapter(adapter);
    }

    class ForecastItem{
        private String txtTop;
        private String txtMiddle;
        private String txtBottom;
        private int imgID;

        public String getTxtTop() {
            return txtTop;
        }

        public void setTxtTop(String txtTop) {
            this.txtTop = txtTop;
        }

        public String getTxtMiddle() {
            return txtMiddle;
        }

        public void setTxtMiddle(String txtMiddle) {
            this.txtMiddle = txtMiddle;
        }

        public String getTxtBottom() {
            return txtBottom;
        }

        public void setTxtBottom(String txtBottom) {
            this.txtBottom = txtBottom;
        }

        public int getImgID() {
            return imgID;
        }

        public void setImgID(int imgID) {
            this.imgID = imgID;
        }
    }
    class ForecastAdapter extends RecyclerView.Adapter<ForecastViewHolder>{

        @Override
        public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ForecastViewHolder(getActivity().getLayoutInflater().inflate(R.layout.item_fragment_weather_list,parent,false));
        }

        @Override
        public void onBindViewHolder(ForecastViewHolder holder, int position) {
            ForecastItem item = weatherList.get(position);
            holder.textBottom.setText(item.getTxtBottom());
            holder.textMiddle.setText(item.getTxtMiddle());
            holder.textTop.setText(item.getTxtTop());
            switch (item.getImgID()){
                case 0:
                    holder.weatherImg.setImageResource(R.drawable.report_pressed_01);
                    break;
                case 1:
                    holder.weatherImg.setImageResource(R.drawable.report_pressed_02);
                    break;
                case 2:
                    holder.weatherImg.setImageResource(R.drawable.report_pressed_03);
                    break;
                case 3:
                    holder.weatherImg.setImageResource(R.drawable.report_pressed_04);
                    break;
                default:
                    holder.weatherImg.setImageResource(R.drawable.report_pressed_05);
                    break;

            }
        }

        @Override
        public int getItemCount() {
            return weatherList.size();
        }
    }
    class ForecastViewHolder extends RecyclerView.ViewHolder{
        TextView textTop;
        TextView textMiddle;
        TextView textBottom;
        ImageView weatherImg;

        public ForecastViewHolder(View itemView) {
            super(itemView);
            textTop = (TextView) itemView.findViewById(R.id.itemWeatherForecastDay);
            textMiddle = (TextView) itemView.findViewById(R.id.itemWeatherForecastWeekday);
            textBottom = (TextView) itemView.findViewById(R.id.itemWeatherForecastTemp);
            weatherImg = (ImageView) itemView.findViewById(R.id.itemWeatherForecastWeather);
        }
    }
    public interface OnDrawerBtnClickListener{
        void onClick();
    }
    public static class WeatherDetailFragment extends Fragment{
        private int imgID;

        public int getImgID() {
            return imgID;
        }

        public void setImgID(int imgID) {
            this.imgID = imgID;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_weather_forecast,container,false);
            return view;
        }
    }
    class WeatherDetailAdapter extends FragmentStatePagerAdapter{


        public WeatherDetailAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return weatherDetailFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return weatherDetailFragmentList.size();
        }
    }
}
