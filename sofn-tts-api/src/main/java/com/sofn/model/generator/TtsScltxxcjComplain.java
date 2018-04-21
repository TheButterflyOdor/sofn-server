package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 投诉举报模型对象
 * @author moon.l
 *
 */
 @SuppressWarnings("serial")
public class TtsScltxxcjComplain extends BaseModel {
	
	
	/*
	* 对应字段
	*/	
	//public String id;//主键

    private String entityIdcode; //投诉主体身份码
    private String userIdcode; //投诉主体用户码
    private String beEntityIdcode; //被投诉主体身份码
    private String beUserIdcode; //被投诉主体用户码
    private String accEntityIdcode; //受理主体身份码
    private String accUserIdcode; //受理主体用户码
    private Date complaintTime; //投诉时间
    private String complaintTitle; //投诉标题
    private String content; //投诉内容
    private String type; //问题类型
    private String status; //投诉信息状态
    private String acceptance; //受理意见
    private String areaId; //所属区域ID
    private Date accTime; //受理时间
    private String attachmentName; //附件名称
    private String complaintEntName; //被投诉主体名称
    private String attachmentPath; //附件路径
    private String complaintCopName; //投诉主体名称
    private String typeName; //问题类型显示名

    private String reservedField2;

    private String reservedField3;

    private String reservedField4;

    private String reservedField5;

    private String reservedField6;

    private String reservedField7;

    private String reservedField8;

    private String reservedField9;

    private String reservedField10;

    private String reservedField11;

    private String reservedField12;

    private String reservedField13;

    private String reservedField14;

    private String reservedField15;

    private String reservedField16;

    private String reservedField17;

    private String reservedField18;

    private String reservedField19;

    private String reservedField20;

    public String getEntityIdcode() {
        return entityIdcode;
    }

    public void setEntityIdcode(String entityIdcode) {
        this.entityIdcode = entityIdcode == null ? null : entityIdcode.trim();
    }

    public String getUserIdcode() {
        return userIdcode;
    }

    public void setUserIdcode(String userIdcode) {
        this.userIdcode = userIdcode == null ? null : userIdcode.trim();
    }

    public String getBeEntityIdcode() {
        return beEntityIdcode;
    }

    public void setBeEntityIdcode(String beEntityIdcode) {
        this.beEntityIdcode = beEntityIdcode == null ? null : beEntityIdcode.trim();
    }

    public String getBeUserIdcode() {
        return beUserIdcode;
    }

    public void setBeUserIdcode(String beUserIdcode) {
        this.beUserIdcode = beUserIdcode == null ? null : beUserIdcode.trim();
    }

    public String getAccEntityIdcode() {
        return accEntityIdcode;
    }

    public void setAccEntityIdcode(String accEntityIdcode) {
        this.accEntityIdcode = accEntityIdcode == null ? null : accEntityIdcode.trim();
    }

    public String getAccUserIdcode() {
        return accUserIdcode;
    }

    public void setAccUserIdcode(String accUserIdcode) {
        this.accUserIdcode = accUserIdcode == null ? null : accUserIdcode.trim();
    }

    public Date getComplaintTime() {
        return complaintTime;
    }

    public void setComplaintTime(Date complaintTime) {
        this.complaintTime = complaintTime;
    }

    public String getComplaintTitle() {
        return complaintTitle;
    }

    public void setComplaintTitle(String complaintTitle) {
        this.complaintTitle = complaintTitle == null ? null : complaintTitle.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getAcceptance() {
        return acceptance;
    }

    public void setAcceptance(String acceptance) {
        this.acceptance = acceptance == null ? null : acceptance.trim();
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    public Date getAccTime() {
        return accTime;
    }

    public void setAccTime(Date accTime) {
        this.accTime = accTime;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName == null ? null : attachmentName.trim();
    }

    public String getComplaintEntName() {
        return complaintEntName;
    }

    public void setComplaintEntName(String complaintEntName) {
        this.complaintEntName = complaintEntName == null ? null : complaintEntName.trim();
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath == null ? null : attachmentPath.trim();
    }

    public String getComplaintCopName() {
        return complaintCopName;
    }

    public void setComplaintCopName(String complaintCopName) {
        this.complaintCopName = complaintCopName == null ? null : complaintCopName.trim();
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getReservedField2() {
        return reservedField2;
    }

    public void setReservedField2(String reservedField2) {
        this.reservedField2 = reservedField2 == null ? null : reservedField2.trim();
    }

    public String getReservedField3() {
        return reservedField3;
    }

    public void setReservedField3(String reservedField3) {
        this.reservedField3 = reservedField3 == null ? null : reservedField3.trim();
    }

    public String getReservedField4() {
        return reservedField4;
    }

    public void setReservedField4(String reservedField4) {
        this.reservedField4 = reservedField4 == null ? null : reservedField4.trim();
    }

    public String getReservedField5() {
        return reservedField5;
    }

    public void setReservedField5(String reservedField5) {
        this.reservedField5 = reservedField5 == null ? null : reservedField5.trim();
    }

    public String getReservedField6() {
        return reservedField6;
    }

    public void setReservedField6(String reservedField6) {
        this.reservedField6 = reservedField6 == null ? null : reservedField6.trim();
    }

    public String getReservedField7() {
        return reservedField7;
    }

    public void setReservedField7(String reservedField7) {
        this.reservedField7 = reservedField7 == null ? null : reservedField7.trim();
    }

    public String getReservedField8() {
        return reservedField8;
    }

    public void setReservedField8(String reservedField8) {
        this.reservedField8 = reservedField8 == null ? null : reservedField8.trim();
    }

    public String getReservedField9() {
        return reservedField9;
    }

    public void setReservedField9(String reservedField9) {
        this.reservedField9 = reservedField9 == null ? null : reservedField9.trim();
    }

    public String getReservedField10() {
        return reservedField10;
    }

    public void setReservedField10(String reservedField10) {
        this.reservedField10 = reservedField10 == null ? null : reservedField10.trim();
    }

    public String getReservedField11() {
        return reservedField11;
    }

    public void setReservedField11(String reservedField11) {
        this.reservedField11 = reservedField11 == null ? null : reservedField11.trim();
    }

    public String getReservedField12() {
        return reservedField12;
    }

    public void setReservedField12(String reservedField12) {
        this.reservedField12 = reservedField12 == null ? null : reservedField12.trim();
    }

    public String getReservedField13() {
        return reservedField13;
    }

    public void setReservedField13(String reservedField13) {
        this.reservedField13 = reservedField13 == null ? null : reservedField13.trim();
    }

    public String getReservedField14() {
        return reservedField14;
    }

    public void setReservedField14(String reservedField14) {
        this.reservedField14 = reservedField14 == null ? null : reservedField14.trim();
    }

    public String getReservedField15() {
        return reservedField15;
    }

    public void setReservedField15(String reservedField15) {
        this.reservedField15 = reservedField15 == null ? null : reservedField15.trim();
    }

    public String getReservedField16() {
        return reservedField16;
    }

    public void setReservedField16(String reservedField16) {
        this.reservedField16 = reservedField16 == null ? null : reservedField16.trim();
    }

    public String getReservedField17() {
        return reservedField17;
    }

    public void setReservedField17(String reservedField17) {
        this.reservedField17 = reservedField17 == null ? null : reservedField17.trim();
    }

    public String getReservedField18() {
        return reservedField18;
    }

    public void setReservedField18(String reservedField18) {
        this.reservedField18 = reservedField18 == null ? null : reservedField18.trim();
    }

    public String getReservedField19() {
        return reservedField19;
    }

    public void setReservedField19(String reservedField19) {
        this.reservedField19 = reservedField19 == null ? null : reservedField19.trim();
    }

    public String getReservedField20() {
        return reservedField20;
    }

    public void setReservedField20(String reservedField20) {
        this.reservedField20 = reservedField20 == null ? null : reservedField20.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", entityIdcode=").append(entityIdcode);
        sb.append(", userIdcode=").append(userIdcode);
        sb.append(", beEntityIdcode=").append(beEntityIdcode);
        sb.append(", beUserIdcode=").append(beUserIdcode);
        sb.append(", accEntityIdcode=").append(accEntityIdcode);
        sb.append(", accUserIdcode=").append(accUserIdcode);
        sb.append(", complaintTime=").append(complaintTime);
        sb.append(", complaintTitle=").append(complaintTitle);
        sb.append(", content=").append(content);
        sb.append(", type=").append(type);
        sb.append(", status=").append(status);
        sb.append(", acceptance=").append(acceptance);
        sb.append(", areaId=").append(areaId);
        sb.append(", accTime=").append(accTime);
        sb.append(", attachmentName=").append(attachmentName);
        sb.append(", complaintEntName=").append(complaintEntName);
        sb.append(", attachmentPath=").append(attachmentPath);
        sb.append(", complaintCopName=").append(complaintCopName);
        sb.append(", typeName=").append(typeName);
        sb.append(", reservedField2=").append(reservedField2);
        sb.append(", reservedField3=").append(reservedField3);
        sb.append(", reservedField4=").append(reservedField4);
        sb.append(", reservedField5=").append(reservedField5);
        sb.append(", reservedField6=").append(reservedField6);
        sb.append(", reservedField7=").append(reservedField7);
        sb.append(", reservedField8=").append(reservedField8);
        sb.append(", reservedField9=").append(reservedField9);
        sb.append(", reservedField10=").append(reservedField10);
        sb.append(", reservedField11=").append(reservedField11);
        sb.append(", reservedField12=").append(reservedField12);
        sb.append(", reservedField13=").append(reservedField13);
        sb.append(", reservedField14=").append(reservedField14);
        sb.append(", reservedField15=").append(reservedField15);
        sb.append(", reservedField16=").append(reservedField16);
        sb.append(", reservedField17=").append(reservedField17);
        sb.append(", reservedField18=").append(reservedField18);
        sb.append(", reservedField19=").append(reservedField19);
        sb.append(", reservedField20=").append(reservedField20);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TtsScltxxcjComplain other = (TtsScltxxcjComplain) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getEntityIdcode() == null ? other.getEntityIdcode() == null : this.getEntityIdcode().equals(other.getEntityIdcode()))
                && (this.getUserIdcode() == null ? other.getUserIdcode() == null : this.getUserIdcode().equals(other.getUserIdcode()))
                && (this.getBeEntityIdcode() == null ? other.getBeEntityIdcode() == null : this.getBeEntityIdcode().equals(other.getBeEntityIdcode()))
                && (this.getBeUserIdcode() == null ? other.getBeUserIdcode() == null : this.getBeUserIdcode().equals(other.getBeUserIdcode()))
                && (this.getAccEntityIdcode() == null ? other.getAccEntityIdcode() == null : this.getAccEntityIdcode().equals(other.getAccEntityIdcode()))
                && (this.getAccUserIdcode() == null ? other.getAccUserIdcode() == null : this.getAccUserIdcode().equals(other.getAccUserIdcode()))
                && (this.getComplaintTime() == null ? other.getComplaintTime() == null : this.getComplaintTime().equals(other.getComplaintTime()))
                && (this.getComplaintTitle() == null ? other.getComplaintTitle() == null : this.getComplaintTitle().equals(other.getComplaintTitle()))
                && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
                && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getAcceptance() == null ? other.getAcceptance() == null : this.getAcceptance().equals(other.getAcceptance()))
                && (this.getAreaId() == null ? other.getAreaId() == null : this.getAreaId().equals(other.getAreaId()))
                && (this.getAccTime() == null ? other.getAccTime() == null : this.getAccTime().equals(other.getAccTime()))
                && (this.getAttachmentName() == null ? other.getAttachmentName() == null : this.getAttachmentName().equals(other.getAttachmentName()))
                && (this.getComplaintEntName() == null ? other.getComplaintEntName() == null : this.getComplaintEntName().equals(other.getComplaintEntName()))
                && (this.getAttachmentPath() == null ? other.getAttachmentPath() == null : this.getAttachmentPath().equals(other.getAttachmentPath()))
                && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
                && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
                && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
                && (this.getComplaintCopName() == null ? other.getComplaintCopName() == null : this.getComplaintCopName().equals(other.getComplaintCopName()))
                && (this.getTypeName() == null ? other.getTypeName() == null : this.getTypeName().equals(other.getTypeName()))
                && (this.getReservedField2() == null ? other.getReservedField2() == null : this.getReservedField2().equals(other.getReservedField2()))
                && (this.getReservedField3() == null ? other.getReservedField3() == null : this.getReservedField3().equals(other.getReservedField3()))
                && (this.getReservedField4() == null ? other.getReservedField4() == null : this.getReservedField4().equals(other.getReservedField4()))
                && (this.getReservedField5() == null ? other.getReservedField5() == null : this.getReservedField5().equals(other.getReservedField5()))
                && (this.getReservedField6() == null ? other.getReservedField6() == null : this.getReservedField6().equals(other.getReservedField6()))
                && (this.getReservedField7() == null ? other.getReservedField7() == null : this.getReservedField7().equals(other.getReservedField7()))
                && (this.getReservedField8() == null ? other.getReservedField8() == null : this.getReservedField8().equals(other.getReservedField8()))
                && (this.getReservedField9() == null ? other.getReservedField9() == null : this.getReservedField9().equals(other.getReservedField9()))
                && (this.getReservedField10() == null ? other.getReservedField10() == null : this.getReservedField10().equals(other.getReservedField10()))
                && (this.getReservedField11() == null ? other.getReservedField11() == null : this.getReservedField11().equals(other.getReservedField11()))
                && (this.getReservedField12() == null ? other.getReservedField12() == null : this.getReservedField12().equals(other.getReservedField12()))
                && (this.getReservedField13() == null ? other.getReservedField13() == null : this.getReservedField13().equals(other.getReservedField13()))
                && (this.getReservedField14() == null ? other.getReservedField14() == null : this.getReservedField14().equals(other.getReservedField14()))
                && (this.getReservedField15() == null ? other.getReservedField15() == null : this.getReservedField15().equals(other.getReservedField15()))
                && (this.getReservedField16() == null ? other.getReservedField16() == null : this.getReservedField16().equals(other.getReservedField16()))
                && (this.getReservedField17() == null ? other.getReservedField17() == null : this.getReservedField17().equals(other.getReservedField17()))
                && (this.getReservedField18() == null ? other.getReservedField18() == null : this.getReservedField18().equals(other.getReservedField18()))
                && (this.getReservedField19() == null ? other.getReservedField19() == null : this.getReservedField19().equals(other.getReservedField19()))
                && (this.getReservedField20() == null ? other.getReservedField20() == null : this.getReservedField20().equals(other.getReservedField20()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getEntityIdcode() == null) ? 0 : getEntityIdcode().hashCode());
        result = prime * result + ((getUserIdcode() == null) ? 0 : getUserIdcode().hashCode());
        result = prime * result + ((getBeEntityIdcode() == null) ? 0 : getBeEntityIdcode().hashCode());
        result = prime * result + ((getBeUserIdcode() == null) ? 0 : getBeUserIdcode().hashCode());
        result = prime * result + ((getAccEntityIdcode() == null) ? 0 : getAccEntityIdcode().hashCode());
        result = prime * result + ((getAccUserIdcode() == null) ? 0 : getAccUserIdcode().hashCode());
        result = prime * result + ((getComplaintTime() == null) ? 0 : getComplaintTime().hashCode());
        result = prime * result + ((getComplaintTitle() == null) ? 0 : getComplaintTitle().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getAcceptance() == null) ? 0 : getAcceptance().hashCode());
        result = prime * result + ((getAreaId() == null) ? 0 : getAreaId().hashCode());
        result = prime * result + ((getAccTime() == null) ? 0 : getAccTime().hashCode());
        result = prime * result + ((getAttachmentName() == null) ? 0 : getAttachmentName().hashCode());
        result = prime * result + ((getComplaintEntName() == null) ? 0 : getComplaintEntName().hashCode());
        result = prime * result + ((getAttachmentPath() == null) ? 0 : getAttachmentPath().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getComplaintCopName() == null) ? 0 : getComplaintCopName().hashCode());
        result = prime * result + ((getTypeName() == null) ? 0 : getTypeName().hashCode());
        result = prime * result + ((getReservedField2() == null) ? 0 : getReservedField2().hashCode());
        result = prime * result + ((getReservedField3() == null) ? 0 : getReservedField3().hashCode());
        result = prime * result + ((getReservedField4() == null) ? 0 : getReservedField4().hashCode());
        result = prime * result + ((getReservedField5() == null) ? 0 : getReservedField5().hashCode());
        result = prime * result + ((getReservedField6() == null) ? 0 : getReservedField6().hashCode());
        result = prime * result + ((getReservedField7() == null) ? 0 : getReservedField7().hashCode());
        result = prime * result + ((getReservedField8() == null) ? 0 : getReservedField8().hashCode());
        result = prime * result + ((getReservedField9() == null) ? 0 : getReservedField9().hashCode());
        result = prime * result + ((getReservedField10() == null) ? 0 : getReservedField10().hashCode());
        result = prime * result + ((getReservedField11() == null) ? 0 : getReservedField11().hashCode());
        result = prime * result + ((getReservedField12() == null) ? 0 : getReservedField12().hashCode());
        result = prime * result + ((getReservedField13() == null) ? 0 : getReservedField13().hashCode());
        result = prime * result + ((getReservedField14() == null) ? 0 : getReservedField14().hashCode());
        result = prime * result + ((getReservedField15() == null) ? 0 : getReservedField15().hashCode());
        result = prime * result + ((getReservedField16() == null) ? 0 : getReservedField16().hashCode());
        result = prime * result + ((getReservedField17() == null) ? 0 : getReservedField17().hashCode());
        result = prime * result + ((getReservedField18() == null) ? 0 : getReservedField18().hashCode());
        result = prime * result + ((getReservedField19() == null) ? 0 : getReservedField19().hashCode());
        result = prime * result + ((getReservedField20() == null) ? 0 : getReservedField20().hashCode());
        return result;
    }
}