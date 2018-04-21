package com.sofn.model.qry;

import com.sofn.model.generator.AlesProduceAdminPunish;

/**
 * author gene
 *
 * @date 2017/5/3
 */
public class AlesProduceAdminPunishDto extends AlesProduceAdminPunish {
    private String  entityIndustryName; //所属行业
    private String  entityTypeName;//主体类型

    @Override
    public String toString() {
        return "AlesProduceAdminPunishDto{" +
                "entityIndustryName='" + entityIndustryName + '\'' +
                ", entityTypeName='" + entityTypeName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AlesProduceAdminPunishDto that = (AlesProduceAdminPunishDto) o;

        if (!entityIndustryName.equals(that.entityIndustryName)) return false;
        return entityTypeName.equals(that.entityTypeName);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + entityIndustryName.hashCode();
        result = 31 * result + entityTypeName.hashCode();
        return result;
    }

    public String getEntityIndustryName() {

        return entityIndustryName;
    }

    public void setEntityIndustryName(String entityIndustryName) {
        this.entityIndustryName = entityIndustryName;
    }

    public String getEntityTypeName() {
        return entityTypeName;
    }

    public void setEntityTypeName(String entityTypeName) {
        this.entityTypeName = entityTypeName;
    }
}
