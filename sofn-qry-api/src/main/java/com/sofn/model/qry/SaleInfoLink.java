package com.sofn.model.qry;

import java.io.Serializable;

/**
 * 销售-采购导向线
 * Created by sofn on 2017/4/10.
 */
public class SaleInfoLink implements Serializable {
    private static final long serialVersionUID = -7308653631859230450L;

    private String source;
    private String target;

    public SaleInfoLink() {
    }

    public SaleInfoLink(String source, String target) {
        this.source = source;
        this.target = target;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
