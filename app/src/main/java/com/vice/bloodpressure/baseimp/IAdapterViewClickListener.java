package com.vice.bloodpressure.baseimp;

import android.view.View;

public interface IAdapterViewClickListener {
    void adapterClickListener(int position, View view);
    void adapterClickListener(int position, int index, View view);
}
