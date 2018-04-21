package com.sofn.model.sys;

import com.sofn.model.generator.SysDictType;

import java.io.Serializable;
import java.util.List;

/**
 * Created by: dong4j.
 * Date: 2016-10-19.
 * Time: 22:33.
 * Description:
 */

public class SysDictTypeDto implements Serializable{
    private static final long serialVersionUID = -7438352897270531177L;
    private String            id;
    private String            name;
    private String enable;
    private List<SysDictType> sysDictType;
    // 是否有更多子类
    private boolean           hasMore;

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SysDictType> getSysDictType() {
        return sysDictType;
    }

    public void setSysDictType(List<SysDictType> sysDictType) {
        this.sysDictType = sysDictType;
    }

    @Override
    public String toString() {
        return "SysDictTypeDto{" +
                "enable='" + enable + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sysDictType=" + sysDictType +
                ", hasMore=" + hasMore +
                '}';
    }
}
