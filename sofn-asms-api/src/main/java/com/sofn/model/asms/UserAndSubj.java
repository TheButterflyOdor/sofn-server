package com.sofn.model.asms;

/**
 * @author sofn
 * @version 2017年01月04日 下午 3:58
 */
public class UserAndSubj {
    private String account;//账号

    private String name;//机构名称

    private String type;//机构类型

    private String code;//机构代码

    private String level;//机构级别

    private String areaId;//所属区域

    private String leader;//负责人

    private String pwd;//密码

    private String superiorLevel;//上级机构级别

    private String superiorAreaId;//上级所属区域

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSuperiorLevel() {
        return superiorLevel;
    }

    public void setSuperiorLevel(String superiorLevel) {
        this.superiorLevel = superiorLevel;
    }

    public String getSuperiorAreaId() {
        return superiorAreaId;
    }

    public void setSuperiorAreaId(String superiorAreaId) {
        this.superiorAreaId = superiorAreaId;
    }
}
