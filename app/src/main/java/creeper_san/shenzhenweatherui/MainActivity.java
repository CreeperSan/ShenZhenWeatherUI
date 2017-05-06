package creeper_san.shenzhenweatherui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements WeatherFragment.OnDrawerBtnClickListener {
    private DrawerLayout drawerLayout;
    private BottomNavigationView bottomNavigationView;
    private LinearLayout linearLayout;
    private FragmentManager fragmentManager;

    private int current = 1;
    private List<CityItem> cityItemList;

    private WeatherFragment weatherFragment;
    private ProductFragment productFragment;
    private MeFragment meFragment;
    private ImageView drawerBackground;
    private RecyclerView recyclerView;
    private ImageView settingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initBottomNavigationView();
        initDrawerList();
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(SettingActivity.class);
            }
        });
    }

    private void initDrawerList() {
        cityItemList = new ArrayList<>();
        for (int i=0;i<5;i++){
            CityItem item = new CityItem();
            item.setPlace("深圳");
            item.setTitle(String.valueOf((int)(Math.random()*10+20))+"°");
            item.setWeather((int) (Math.random()*5));
            cityItemList.add(item);
        }
        CityItem item = new CityItem();
        item.setWeather(-1);
        cityItemList.add(item);
        CityAdapter adapter = new CityAdapter();
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);
    }

    private void initBottomNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mainBottomNavigationWeather:
                        if (current == 1){
                            return false;
                        }
                        current = 1;
                        fragmentManager.beginTransaction()
                                .show(weatherFragment)
                                .hide(productFragment)
                                .hide(meFragment)
                                .commit();
                        drawerLayout.setEnabled(true);
                        break;
                    case R.id.mainBottomNavigationProduct:
                        if (current == 2){
                            return false;
                        }
                        current = 2;
                        fragmentManager.beginTransaction()
                                .hide(weatherFragment)
                                .show(productFragment)
                                .hide(meFragment)
                                .commit();
                        drawerLayout.setEnabled(false);
                        break;
                    case R.id.mainBottomNavigationMe:
                        if (current == 3){
                            return false;
                        }
                        current = 3;
                        fragmentManager.beginTransaction()
                                .hide(weatherFragment)
                                .hide(productFragment)
                                .show(meFragment)
                                .commit();
                        drawerLayout.setEnabled(false);
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void init() {
        weatherFragment = new WeatherFragment();
        weatherFragment.setOnDrawerBtnClickListener(this);
        productFragment = new ProductFragment();
        meFragment = new MeFragment();

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.mainFragmentLayout,weatherFragment)
                .add(R.id.mainFragmentLayout,productFragment)
                .add(R.id.mainFragmentLayout,meFragment)
                .hide(productFragment)
                .hide(meFragment)
                .commit();
    }


    @Override
    protected void onFindViews() {
        drawerLayout = (DrawerLayout) findViewById(R.id.mainDrawerLayout);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.mainBottomNavigation);
        linearLayout = (LinearLayout) findViewById(R.id.mainFragmentLayout);
        drawerBackground = (ImageView) findViewById(R.id.mainDrawerBackground);
        Glide.with(this).load(R.drawable.bg2).into(drawerBackground);
        recyclerView = (RecyclerView) findViewById(R.id.mainDrawerList);
        settingBtn = (ImageView) findViewById(R.id.mainDrawerSetting);
    }


    class CityItem{
        private String title;
        private String place;
        private int weather;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public int getWeather() {
            return weather;
        }

        public void setWeather(int weather) {
            this.weather = weather;
        }
    }
    class CityViewHolder extends RecyclerView.ViewHolder{
        ImageView background;
        ImageView weather;
        TextView temp;
        TextView place;

        public CityViewHolder(View itemView) {
            super(itemView);
            background = (ImageView) itemView.findViewById(R.id.itemDrawerBackground);
            weather = (ImageView) itemView.findViewById(R.id.itemDrawerWeather);
            place = (TextView) itemView.findViewById(R.id.itemDrawerPlace);
            temp = (TextView) itemView.findViewById(R.id.itemDrawerTemp);
        }
    }
    class CityAdapter extends RecyclerView.Adapter<CityViewHolder>{

        @Override
        public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new CityViewHolder(getLayoutInflater().inflate(R.layout.item_main_drawer,parent,false));
        }

        @Override
        public void onBindViewHolder(final CityViewHolder holder, int position) {
            CityItem cityItem = cityItemList.get(position);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.getAdapterPosition()==cityItemList.size()-1){
                        Intent intent = new Intent(MainActivity.this,CityQueryActivity.class);
                        startActivity(intent);
                    }else {
                        weatherFragment.setCurrentPage(holder.getAdapterPosition());
                    }
                }
            });

            if (cityItem.getWeather()==-1){
                holder.place.setVisibility(View.GONE);
                holder.temp.setVisibility(View.GONE);
                holder.weather.setVisibility(View.GONE);
                Glide.with(MainActivity.this).load(R.drawable.city_add).into(holder.background);
                return;
            }
            holder.place.setVisibility(View.VISIBLE);
            holder.temp.setVisibility(View.VISIBLE);
            holder.weather.setVisibility(View.VISIBLE);
            holder.place.setText(cityItem.getPlace());
            holder.temp.setText(cityItem.getTitle());
            switch (cityItem.getWeather()){
                case 0:
                    Glide.with(MainActivity.this).load(R.drawable.bg1).into(holder.background);
                    Glide.with(MainActivity.this).load(R.drawable.report_pressed_01).into(holder.weather);
                    break;
                case 1:
                    Glide.with(MainActivity.this).load(R.drawable.bg2).into(holder.background);
                    Glide.with(MainActivity.this).load(R.drawable.report_pressed_02).into(holder.weather);
                    break;
                case 2:
                    Glide.with(MainActivity.this).load(R.drawable.bg3).into(holder.background);
                    Glide.with(MainActivity.this).load(R.drawable.report_pressed_03).into(holder.weather);
                    break;
                case 3:
                    Glide.with(MainActivity.this).load(R.drawable.bg4).into(holder.background);
                    Glide.with(MainActivity.this).load(R.drawable.report_pressed_04).into(holder.weather);
                    break;
                default:
                    Glide.with(MainActivity.this).load(R.drawable.bg5).into(holder.background);
                    Glide.with(MainActivity.this).load(R.drawable.report_pressed_05).into(holder.weather);
                    break;
            }

        }

        @Override
        public int getItemCount() {
            return cityItemList.size();
        }
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick() {
        drawerLayout.openDrawer(Gravity.START);
    }
}
