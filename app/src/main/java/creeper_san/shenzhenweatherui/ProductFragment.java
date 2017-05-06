package creeper_san.shenzhenweatherui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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

public class ProductFragment extends Fragment {

    private ProductAdapter adapter1;

    private RecyclerView recyclerView1;

    private GridLayoutManager gridLayoutManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product,container,false);
        recyclerView1 = (RecyclerView) view.findViewById(R.id.fragmentProductList1);

        recyclerView1.setLayoutManager(gridLayoutManager);

        recyclerView1.setAdapter(adapter1);

        recyclerView1.setNestedScrollingEnabled(true);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final List<ListItem> itemList1 = new ArrayList<>();
        //生成数据
        for (int i=0;i<8;i++){
            ListItem item = new ListItem();
            item.setImgID(getList1ImgID(i));
            item.setTitle(getList1InfoText(i));
            itemList1.add(item);
        }
        ListItem itemTitle1 = new ListItem();
        itemTitle1.setTitle(true);
        itemTitle1.setImgID(0);
        itemTitle1.setTitle("深圳地区气象产品");
        itemList1.add(itemTitle1);
        for (int i=0;i<11;i++){
            ListItem item = new ListItem();
            item.setImgID(getList2ImgID(i));
            item.setTitle(getList2InfoText(i));
            itemList1.add(item);
        }
        ListItem itemTitle2 = new ListItem();
        itemTitle2.setTitle(true);
        itemTitle2.setImgID(0);
        itemTitle2.setTitle("深圳气象行政信息");
        itemList1.add(itemTitle2);
        for (int i=0;i<5;i++){
            ListItem item = new ListItem();
            item.setImgID(getList3ImgID(i));
            item.setTitle(getList3InfoText(i));
            itemList1.add(item);
        }
        //Adapter
        adapter1 = new ProductAdapter(itemList1);

        gridLayoutManager = new GridLayoutManager(getContext(),4);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {//Return这个View会占用多少格
                ListItem item = itemList1.get(position);
                return item.isTitle?4:1;
            }
        });
    }

    private class ListItem{
        private boolean isTitle = false;
        private String title;
        private int imgID;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isTitle() {
            return isTitle;
        }

        public void setTitle(boolean title) {
            isTitle = title;
        }

        public int getImgID() {
            return imgID;
        }

        public void setImgID(int imgID) {
            this.imgID = imgID;
        }
    }
    private class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        private List<ListItem> itemList;

        public ProductAdapter(List<ListItem> itemList) {
            this.itemList = itemList;
        }

        @Override
        public int getItemViewType(int position) {
            return itemList.get(position).isTitle?0:1;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType==0){
                return new ProductTitleHolder(getActivity().getLayoutInflater().inflate(R.layout.item_product_fragment_title,parent,false));
            }else {
                return new ProductHolder(getActivity().getLayoutInflater().inflate(R.layout.item_product_fragment,parent,false));
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder tempHolder, int position) {
            ListItem item = itemList.get(position);
            if (item.isTitle()){
                ProductTitleHolder holder = (ProductTitleHolder) tempHolder;
                holder.textView.setText(item.getTitle());
            }else {
                ProductHolder holder = (ProductHolder) tempHolder;
                holder.textView.setText(item.getTitle());
                Glide.with(getContext()).load(item.getImgID()).into(holder.imageView);
            }
        }

        @Override
        public int getItemCount() {
            return itemList.size();
        }
    }
    private class ProductHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;

        public ProductHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.itemProductListTitle);
            imageView = (ImageView) itemView.findViewById(R.id.itemProductListImg);
        }
    }
    private class ProductTitleHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public ProductTitleHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.itemProductListTitle);
        }
    }

    private static int getList1ImgID(int num){
        switch (num){
            case 0:
                return R.drawable.list11;
            case 1:
                return R.drawable.list12;
            case 2:
                return R.drawable.list13;
            case 3:
                return R.drawable.list14;
            case 4:
                return R.drawable.list15;
            case 5:
                return R.drawable.list16;
            case 6:
                return R.drawable.list17;
            default:
                return R.drawable.list18;
        }
    }
    private static String getList1InfoText(int num){
        switch (num){
            case 0:
                return "天气预报";
            case 1:
                return "台风路径";
            case 2:
                return "雷达图像";
            case 3:
                return "卫星云图";
            case 4:
                return "往日实况";
            case 5:
                return "日出日落";
            case 6:
                return "农历节气";
            default:
                return "交通服务";
        }
    }
    private static int getList2ImgID(int num){
        switch (num){
            case 0:
                return R.drawable.list201;
            case 1:
                return R.drawable.list202;
            case 2:
                return R.drawable.list203;
            case 3:
                return R.drawable.list204;
            case 4:
                return R.drawable.list205;
            case 5:
                return R.drawable.list206;
            case 6:
                return R.drawable.list207;
            case 7:
                return R.drawable.list208;
            case 8:
                return R.drawable.list209;
            case 9:
                return R.drawable.list210;
            default:
                return R.drawable.list211;
        }
    }
    private static String getList2InfoText(int num){
        switch (num){
            case 0:
                return "气象预报";
            case 1:
                return "定点降雨预报";
            case 2:
                return "分区预报";
            case 3:
                return "天气实况";
            case 4:
                return "闪电定位";
            case 5:
                return "气象提示";
            case 6:
                return "环境气候";
            case 7:
                return "阵风服务";
            case 9:
                return "地铁天气";
            case 10:
                return "气象视听";
            default:
                return "潮汐";
        }
    }
    private static int getList3ImgID(int num){
        switch (num){
            case 0:return R.drawable.list31;
            case 1:return R.drawable.list32;
            case 2:return R.drawable.list33;
            case 3:return R.drawable.list34;
            default:return R.drawable.list35;
        }
    }
    private static String getList3InfoText(int num){
        switch (num){
            case 0:return "常见问题";
            case 1:return "办公指南";
            case 2:return "工作动态";
            case 3:return "通知公告";
            default:return "互动交流";
        }
    }
}
