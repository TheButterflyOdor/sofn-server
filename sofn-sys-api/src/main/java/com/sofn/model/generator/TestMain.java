package com.sofn.model.generator;

/**
 * Created by Administrator on 2017/11/13/013.
 */
public class TestMain {
    public static void main(String[] args) {
        SysTestStandardModel stsm = new SysTestStandardModel();
        stsm.setStandardCode("GB212182");
        stsm.setStandardName("国家检测标准");
        System.out.println(stsm);
    }
}
