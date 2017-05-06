package creeper_san.shenzhenweatherui;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class PrefValueText extends Preference{
    private String title;
    private String value;

    public PrefValueText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.PrefValue);
        title = array.getString(R.styleable.PrefValue_valueTitle);
        value = array.getString(R.styleable.PrefValue_valueValue);
        array.recycle();
    }

    public PrefValueText(Context context, AttributeSet attrs) {
        this(context, attrs,0,0);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        view.setEnabled(false);
        ((TextView)view.findViewById(R.id.prefValueTitle)).setText(title);
        ((TextView)view.findViewById(R.id.prefValueValue)).setText(value);
    }

    @Override
    protected View onCreateView(ViewGroup parent) {
        super.onCreateView(parent);
        return LayoutInflater.from(getContext()).inflate(R.layout.pref_value,parent,false);
    }
}
