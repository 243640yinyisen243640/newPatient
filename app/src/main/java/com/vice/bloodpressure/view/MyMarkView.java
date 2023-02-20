package com.vice.bloodpressure.view;

import android.content.Context;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.vice.bloodpressure.R;


public class MyMarkView extends MarkerView {


    public MyMarkView(Context context) {
        super(context, R.layout.my_marker_view);

    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        super.refreshContent(e, highlight);
    }
}
