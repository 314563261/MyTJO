package fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import adapter.ResponseAdapter;
import entity.MyList;
import entity.Response;
import tianjw.com.cn.mytjo.R;

/**
 * Created by wei on 2017/12/26.
 */

public class CityGuideActivity extends Fragment {
    private View main_view;
    private ListView viewById;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        main_view = inflater.inflate(R.layout.fragment_cityguide, null);

        initView();
        initData();
        initEvent();

        return main_view;
    }

    private void initView() {
        HomeFragmentActivity activity = (HomeFragmentActivity) getActivity();
        MyList list = activity.getList();
        List<Response> list1 = list.getList();
        viewById = (ListView)main_view.findViewById(R.id.listview_city);
        ResponseAdapter adapter = new ResponseAdapter(getActivity(),list1);
        viewById.setAdapter(adapter);
    }

    private void initData() {


    }

    private void initEvent() {

    }
}
