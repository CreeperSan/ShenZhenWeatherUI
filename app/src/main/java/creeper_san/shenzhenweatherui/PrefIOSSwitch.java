package creeper_san.shenzhenweatherui;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//开关控件地址 https://github.com/iielse/SwitchButton

public class PrefIOSSwitch extends Preference{
    private String title;

    public PrefIOSSwitch(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.PrefSwitch);
        title = array.getString(R.styleable.PrefSwitch_switchTitle);
        array.recycle();
    }

    public PrefIOSSwitch(Context context, AttributeSet attrs) {
        this(context, attrs,0,0);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        view.setEnabled(false);
        ((TextView)view.findViewById(R.id.prefSwitchTitle)).setText(title);
    }

    @Override
    protected View onCreateView(ViewGroup parent) {
        super.onCreateView(parent);
        return LayoutInflater.from(getContext()).inflate(R.layout.pref_ios_switch,parent,false);
    }
}
