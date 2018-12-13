package me.vincent.spring.tutorial.viewmodel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 加注释
 *
 * @description: 对外API结果包装类
 * @author: Vincent Huang
 * @version:
 */
@ApiModel(description = "REST API 返回结果包装")
@Data
public class ResultBean<T> {

    public static final int SUCCESS = 0;

    public static final int FAIL = 1;

    public static final int NO_PERMISSION = 2;

    @ApiModelProperty
    private int rtn = FAIL;

    @ApiModelProperty
    private String message = "";

    @ApiModelProperty
    private T data;

    public static <W> ResultBean<W> OK(W data, String message) {
        ResultBean<W> resultBean =  new ResultBean<W>();
        resultBean.setMessage(message);
        resultBean.setRtn(ResultBean.SUCCESS);
        resultBean.setData(data);
        return resultBean;
    }

    public static ResultBean OK() {
        ResultBean resultBean =  new ResultBean();
        resultBean.setRtn(ResultBean.SUCCESS);
        return resultBean;
    }

    public static <W> ResultBean<W> ERROR(W data, String message) {
        ResultBean<W> resultBean =  new ResultBean<W>();
        resultBean.setMessage(message);
        resultBean.setRtn(ResultBean.SUCCESS);
        resultBean.setData(data);
        return resultBean;
    }

    public static ResultBean ERROR(String message) {
        ResultBean resultBean =  new ResultBean();
        resultBean.setMessage(message);
        resultBean.setRtn(ResultBean.FAIL);
        resultBean.setData(null);
        return resultBean;
    }

}
