package fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tianjw.com.cn.mytjo.R;

/**
 * Created by wei on 2017/12/26.
 * 商店
 */

public class DeliciousFoodActivity extends Fragment {
    private View main_view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        main_view = inflater.inflate(R.layout.fragment_deliciousfood, null);

        initView();
        initData();
        initEvent();

        return main_view;
    }

    private void initView() {

    }

    private void initData() {


    }

    private void initEvent() {

    }
}
