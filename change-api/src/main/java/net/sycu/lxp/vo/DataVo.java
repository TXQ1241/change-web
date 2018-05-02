package net.sycu.lxp.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author lxp
 * @Description: 前台数据封装
 **/
public class DataVo implements Serializable {

    private String code;//状态码

    private String msg;//返回信息

    private Integer count;//数据条数

    private List<? extends Object> datalist;//数据

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<? extends Object> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<? extends Object> datalist) {
        this.datalist = datalist;
    }

}
