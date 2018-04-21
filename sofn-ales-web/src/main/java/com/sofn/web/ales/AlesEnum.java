package com.sofn.web.ales;

/**
 * 执法枚举
 *
 * @author LiBing
 */
public enum AlesEnum {

    //任务状态
    UN_PUBLISHED("未发布", "0"),
    PUBLISHED("已发布", "1"),
    ABOLISH("已废止", "2"),

    //检测结果
    QUALIFIED("合格", "1"),
    UNQUALIFIED("不合格", "0"),
    NOTDETECTED("未检测", "-1"),


    //抽样单提交状态
    ERRPR_TYPE_DATA_NON_COMPLETE("数据不完整", "-2"),
    ERRPR_TYPE_SYNC_ERROR("发布失败", "-3"),
    NON_TASK_ID("任务主键缺失", "-1"),
    NON_SAMPLE("未提交抽样单", "0"),
    IS_SAMPLE("已提交抽样单", "1"),
    PART_SAMPLE("部分提交抽样单", "2"),
    NO_SON_OBJECT("无检测对象!", "3"),
    NO_SAMPLE("无抽样单!", "4"),
    BLIVESUCCESS("数据完整", "bliveSuccess");


    private String name;
    private String code;

    AlesEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
