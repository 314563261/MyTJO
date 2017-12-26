package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import entity.Response;
import tianjw.com.cn.mytjo.R;
import utils.GetImageByUrl;


/**
 * 项目名称：jkjtapp
 * 类描述：显示所有路口列表的Adapter
 * 创建人：liuw
 * 创建时间：2015/11/27 0027 10:46
 * 修改人：Administrator
 * 修改时间：2015/11/27 0027 10:46
 * 修改备注：
 */


public class ResponseAdapter extends BaseAdapter {

    private Context context;
    private List<Response> responseModels;

    public ResponseAdapter(Context context, List<Response> corssParamModels) {
        this.context = context;
        this.responseModels = corssParamModels;
    }



    public void addCorssSignal(Response response){
        this.responseModels.add(response);
        notifyDataSetChanged();
    }

    public void deleteCrossSignal(Response response){
        this.responseModels.remove(response);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return responseModels != null ? responseModels.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return responseModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_fragment_city,null);
            holder = new ViewHolder();
            holder.tv_Name = (TextView)convertView.findViewById(R.id.tv_name);
            holder.tv_introduce = (TextView)convertView.findViewById(R.id.tv_introduce);
            holder.imageview_city_mini = (ImageView)convertView.findViewById(R.id.imageview_city_mini);
            holder.imageview_city_max = (ImageView)convertView.findViewById(R.id.imageview_city_max);
            holder.lin_city = (LinearLayout)convertView.findViewById(R.id.lin_city);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        if (responseModels.get(position).is_promote()){
            //显示大图
            holder.imageview_city_max.setVisibility(View.VISIBLE);
            holder.lin_city.setVisibility(View.GONE);
            GetImageByUrl getImageByUrl = new GetImageByUrl();
            getImageByUrl.setImage(holder.imageview_city_max, responseModels.get(position).getImage());
        }else{
            //小图 描述等
            holder.imageview_city_max.setVisibility(View.GONE);
            holder.imageview_city_mini.setVisibility(View.VISIBLE);
            GetImageByUrl getImageByUrl = new GetImageByUrl();
            getImageByUrl.setImage(holder.imageview_city_mini, responseModels.get(position).getImage());
            holder.tv_Name.setText(responseModels.get(position).getTitle());
            holder.tv_introduce.setText(responseModels.get(position).getDescription());
        }


            return convertView;
    }
    public class ViewHolder{
        TextView tv_Name;
        TextView tv_introduce;
        ImageView imageview_city_mini;
        ImageView imageview_city_max;
        LinearLayout lin_city;
    }
}