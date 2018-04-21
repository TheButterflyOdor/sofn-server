package com.sofn.model.sys;

import com.sofn.model.generator.TtsScltxxcjProposal;

/**
 * Created by Administrator on 2018/1/22/022.
 */
public final class SysSuggestionQueryResultDTO extends TtsScltxxcjProposal {
    private String enterpriseName; // 企业名称
    private String area; // 行政区域

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SysSuggestionQueryResultDTO{");
        sb.append("enterpriseName='").append(enterpriseName).append('\'');
        sb.append(", area='").append(area).append('\'');
        sb.append(", delFlag='").append(getDelFlag()).append('\'');
        sb.append(", id='").append(getId()).append('\'');
        sb.append(", typeDescribe='").append(getTypeDescribe()).append('\'');
        sb.append(", remark='").append(getRemark()).append('\'');
        sb.append(", createBy='").append(getCreateBy()).append('\'');
        sb.append(", title='").append(getTitle()).append('\'');
        sb.append(", createTime=").append(getCreateTime());
        sb.append(", content='").append(getContent()).append('\'');
        sb.append(", updateBy='").append(getUpdateBy()).append('\'');
        sb.append(", time=").append(getTime());
        sb.append(", attachName='").append(getAttachName()).append('\'');
        sb.append(", updateTime=").append(getUpdateTime());
        sb.append(", attachPath='").append(getAttachPath()).append('\'');
        sb.append(", entityIdcode='").append(getEntityIdcode()).append('\'');
        sb.append(", userIdcode='").append(getUserIdcode()).append('\'');
        sb.append(", ip='").append(getIp()).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
