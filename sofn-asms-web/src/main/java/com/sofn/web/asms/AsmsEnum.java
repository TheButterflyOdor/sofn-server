package com.sofn.web.asms;

/**
 * 监管枚举
 *
 * @author LiBing
 */
public enum AsmsEnum {

    //任务状态
    UN_PUBLISHED("未发布", "0"),
    PUBLISHED("已发布", "1"),
    ABOLISH("已废止", "2"),

    //检测结果
    QUALIFIED("合格", "1"),
    UNQUALIFIED("不合格", "0"),
    NOTDETECTED("未检测", "-1"),

    //任务状态根据时间自动判断
    TASK_BEFORE_DATE("未开始", "1"),
    TASK_IN_DATE("执行中", "2"),
    TASK_OUT_DATE("已结束", "3"),

    //考核类型
    YEAR_TYPE("年度", "1"),
    MONTHS_TYPE("月度", "2"),
    QURTER_TYPE("季度", "3"),


    //巡查人员任务完成状态
    TASK_OVER("完成", "1"),
    TASK_NON_OVER("未完成", "2"),

    //任务分级
    MINISTRY("部级任务", "1"),
    PROVINCE("省级任务", "2"),
    CITY("市级任务", "3"),
    COUNTY("区县级任务", "4"),

    //抽样单提交状态
    NON_SAMPLE("未提交抽样单", "0"),
    IS_SAMPLE("已提交抽样单", "1"),
    PART_SAMPLE("部分提交抽样单", "2"),

    //监督抽查任务等级
    MINISTERIAL("部级", "0"),
    NON_MINISTERIAL("非部级", "1"),

    //监督抽查-是否分离
    NON_SEPARATE("否", "0"),
    IS_SEPARATE("是", "1"),

    //批次
    ONE_BATCH("第一批次", "1"),
    TWO_BATCH("第二批次", "2"),
    THREE_BATCH("第三批次", "3"),
    FOUR_BATCH("第四批次", "4"),

    //考核任务 —季度
    ONE_QUARTER("第一季度", "1"),
    TWO_QUARTER("第二季度", "2"),
    THREE_QUARTER("第三季度", "3"),
    FOUR_QUARTER("第四季度", "4"),

    //任务类型
    ROUTINE_MONITOR_TASK("例行监测", "1"),
    SPECIAL_MONITOR_TASK("专项监测", "2"),
    CHECK_TASK("监督抽查", "3"),
    RECHECK_TASK("复检任务", "4"),

    //操作返回提示
    NO_SON_OBJECT("无对象!", "NoObj"),

    //投诉受理
    NON_HANDLE("未受理", "0"),
    HANDLE("已受理", "1");

    private String name;
    private String code;

    AsmsEnum(String name, String code) {
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
