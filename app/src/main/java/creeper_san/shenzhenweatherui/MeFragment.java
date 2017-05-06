package creeper_san.shenzhenweatherui;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MeFragment extends Fragment {
    private ImageView mapImage;
    private RecyclerView imageRecyclerView;
    private RadioGroup radioGroup;
    private ListAdapter adapter;
    private ImageView cameraImage;

    private List<ListItem> itemList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me,container,false);
        radioGroup = (RadioGroup) view.findViewById(R.id.fragmentMeRadioGroup);
        imageRecyclerView = (RecyclerView) view.findViewById(R.id.fragmentMeList);
        mapImage = (ImageView) view.findViewById(R.id.fragmentMeMap);
        cameraImage = (ImageView) view.findViewById(R.id.fragmentMeCamera);
        ObjectAnimator.ofFloat(cameraImage,"translationY",500f,0f).setDuration(300).start();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId==R.id.fragmentMeMode1){
                    cameraImage.setVisibility(View.VISIBLE);
                    ObjectAnimator.ofFloat(cameraImage,"translationY",500f,0f).setDuration(300).start();
                    imageRecyclerView.setVisibility(View.VISIBLE);
                    mapImage.setVisibility(View.GONE);
                }else {
                    cameraImage.setVisibility(View.GONE);
                    imageRecyclerView.setVisibility(View.GONE);
                    mapImage.setVisibility(View.VISIBLE);
                }
            }
        });
        imageRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
//        imageRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ListAdapter();
        imageRecyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        itemList = new ArrayList<>();
        for (int i=0;i<100;i++){
            ListItem item = new ListItem();
            item.setLike((int) (Math.random()*100));
            item.setPlaceName("地址"+i);
            item.setImgID(getImgByNum((int) (Math.random()*9)));
            item.setHeight((int) (400+Math.random()*300));
            itemList.add(item);
        }
    }


    class ListItem{
        private int height;
        private int like;
        private int imgID;
        private String placeName;

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getLike() {
            return like;
        }

        public void setLike(int like) {
            this.like = like;
        }

        public int getImgID() {
            return imgID;
        }

        public void setImgID(int imgID) {
            this.imgID = imgID;
        }

        public String getPlaceName() {
            return placeName;
        }

        public void setPlaceName(String placeName) {
            this.placeName = placeName;
        }
    }
    class ListAdapter extends RecyclerView.Adapter<ListViewHolder>{

        @Override
        public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ListViewHolder(getActivity().getLayoutInflater().inflate(R.layout.item_fragment_me_list,parent,false));
        }

        @Override
        public void onBindViewHolder(ListViewHolder holder, int position) {
            final ListItem item = itemList.get(position);

            holder.likeText.setText(String.valueOf(item.getLike()));
            holder.placeText.setText(item.getPlaceName());
            holder.placeImg.setImageResource(item.getImgID());
        }

        @Override
        public int getItemCount() {
            return itemList.size();
        }
    }
    class ListViewHolder extends RecyclerView.ViewHolder{
        private TextView likeText;
        private TextView placeText;
        private ImageView placeImg;

        public ListViewHolder(View itemView) {
            super(itemView);
            likeText = (TextView) itemView.findViewById(R.id.itemFragmentMeThumbsUp);
            placeText = (TextView) itemView.findViewById(R.id.itemFragmentMePlace);
            placeImg = (ImageView) itemView.findViewById(R.id.itemFragmentMeImage);
        }
    }

    private int getImgByNum(int num){
        switch (num){
            case 0:return R.drawable.s1;
            case 1:return R.drawable.s2;
            case 2:return R.drawable.s3;
            case 3:return R.drawable.s4;
            case 4:return R.drawable.s5;
            case 5:return R.drawable.s6;
            case 6:return R.drawable.s7;
            case 7:return R.drawable.s8;
            default:return R.drawable.s9;
        }
    }
    private int getViewHeight(int redid){
        switch (redid){
            case R.drawable.s1:return 600;
            case R.drawable.s2:return 450;
            case R.drawable.s3:return 700;
            case R.drawable.s4:return 500;
            case R.drawable.s5:return 480;
            case R.drawable.s6:return 620;
            case R.drawable.s7:return 550;
            case R.drawable.s8:return 620;
            default:return 650;
        }
    }
}
