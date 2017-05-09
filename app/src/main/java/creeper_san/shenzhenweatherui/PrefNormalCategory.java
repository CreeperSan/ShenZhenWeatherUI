package creeper_san.shenzhenweatherui;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.PreferenceGroup;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by huang on 2017/5/6.
 */

public class PrefNormalCategory extends PreferenceGroup {
    private String title;


    public PrefNormalCategory(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.PrefGroup);
        title = array.getString(R.styleable.PrefGroup_groupTitle);
        array.recycle();
        setSelectable(false);
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        ((TextView)view.findViewById(R.id.prefGroupTitle)).setText(title);
    }

    @Override
    protected View onCreateView(ViewGroup parent) {
        return LayoutInflater.from(getContext()).inflate(R.layout.pref_group,parent,false);
    }

    public PrefNormalCategory(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }
    public PrefNormalCategory(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
}
