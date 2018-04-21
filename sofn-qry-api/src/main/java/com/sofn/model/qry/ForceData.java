package com.sofn.model.qry;

import java.io.Serializable;
import java.util.List;

/**
 * 力向图数据对象
 * Created by sofn on 2017/4/24.
 */
public class ForceData implements Serializable {
    private static final long serialVersionUID = -5672641410255780669L;

    private List<SaleInfoData> data;
    private List<SaleInfoLink> links;

    public ForceData() {
    }

    public ForceData(List<SaleInfoData> data, List<SaleInfoLink> links) {
        this.data = data;
        this.links = links;
    }

    public List<SaleInfoData> getData() {
        return data;
    }

    public void setData(List<SaleInfoData> data) {
        this.data = data;
    }

    public List<SaleInfoLink> getLinks() {
        return links;
    }

    public void setLinks(List<SaleInfoLink> links) {
        this.links = links;
    }
}
