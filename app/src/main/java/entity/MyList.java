package entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wei on 2017/12/26.
 */

public class MyList implements Serializable {
    public List<Response> getList() {
        return list;
    }

    public void setList(List<Response> list) {
        this.list = list;
    }

    private  List<Response> list;
}
