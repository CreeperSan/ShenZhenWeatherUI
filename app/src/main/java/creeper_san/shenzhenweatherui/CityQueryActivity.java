package creeper_san.shenzhenweatherui;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CityQueryActivity extends BaseActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.item_query_result,R.id.itemQueryResult);
        for (int i=0;i<60;i++){
            arrayAdapter.add("城市"+i);
        }
        listView.setAdapter(arrayAdapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_city_query;
    }

    @Override
    protected void onFindViews() {
        listView = (ListView) findViewById(R.id.queryList);

    }

}
