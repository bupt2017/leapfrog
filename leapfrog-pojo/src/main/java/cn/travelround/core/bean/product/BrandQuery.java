package cn.travelround.core.bean.product;

import java.io.Serializable;

/**
 * Created by jiyuan on 2019/3/11.
 */
public class BrandQuery implements Serializable{
    private static final long SerialVersionUID = 1L;

    private long id;
    private  String name;
    private String description;
    private String imgUrl;
    private Integer sort;
    private Integer isDisplay;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Integer isDisplay) {
        this.isDisplay = isDisplay;
    }

    //额外支持
    private Integer pageNo = 1;//默认页
    private Integer pageSize = 10;//每页显示条目数
    private Integer startRow;//开始行

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.startRow = (pageNo - 1) * pageSize;
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.startRow = (pageNo - 1) * pageSize;
        this.pageSize = pageSize;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }
}
