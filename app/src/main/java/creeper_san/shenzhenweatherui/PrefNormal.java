package creeper_san.shenzhenweatherui;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class PrefNormal extends Preference{
    private String title;

    public PrefNormal(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.PrefNormal);
        title = array.getString(R.styleable.PrefNormal_title);
        array.recycle();
    }

    public PrefNormal(Context context, AttributeSet attrs) {
        this(context, attrs,0,0);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        view.setEnabled(false);
        ((TextView)view.findViewById(R.id.prefNormalTitle)).setText(title);
    }

    @Override
    protected View onCreateView(ViewGroup parent) {
        super.onCreateView(parent);
        return LayoutInflater.from(getContext()).inflate(R.layout.pref_normal,parent,false);
    }
}
