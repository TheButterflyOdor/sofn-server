package com.sofn.core;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sofn
 * @version 2017年02月21日 上午 10:51
 */
public class Test {

    public static void main(String[] args) {
        int count = 0;
        for(int i = 2147483547;i<=2147483647;i++){
            count = count + 1;
        }
        System.out.println("#");
        System.out.println(count);
    }
    public void numOff(int m,int[] arr,String[] slogan){
        if(arr.length!=slogan.length){
            System.out.println("请输入的数字和口号要一一对应！");
        }
        if(m<1){
            System.out.println("请输入的人数要大于等于1！");
        }
        for(int n=1;n<=m;n++){
            if(String.valueOf(n).contains(String.valueOf(arr[0]))){
                System.out.println(slogan[0]);
                continue;
            }
            boolean isMutiple = false;
            for(int i=0;i<arr.length;i++) {
                if (n%arr[i]==0) {
                    isMutiple = true;
                    System.out.print(slogan[i]);
                }
                if(isMutiple&&(i==arr.length-1)){
                    System.out.println("");
                }
            }
            if(isMutiple){
                continue;
            }
            System.out.println(n);
        }
    }

    public String countYMD(int x,int y,int z){
        if(x<1900){
            return "请输入1900之后的年份！";
        }
        if(y<1||y>12){
            return "请输入1月到12月";
        }
        if(z<1||z>5){
            return "请输入1到5周";
        }
        int m = ((x-1900)+(x-1901)/4)%7;//用于计算x年第一月1号第0周已经过去了几天，实际第一周要重新计算
        int count = 0;
        for(int q=0;q<(x-1901)/100;q++){
            if((20+q)%4!=0){
                count++;
            }
        }
        m = m-count;
        boolean run = false;//当年是否为闰年
        if(x%4==0){
            run = true;
        }
        if((x%100==0)&&(x%400!=0)){
            run = false;
        }
        switch (y){
            case 1:
                if((7*z+1-m)>31){
                    return "输入周数大于该月范围！";
                }
                if((7*z+7-m)>31){
                    return x+"年"+y+"月"+"第"+z+"周，周一是"+x+"-"+y+"-"+(7*z+1-m)+"，周日是"+x+"-"+(y+1)+"-"+(7*z+7-m-31);
                }else {
                    return x+"年"+y+"月"+"第"+z+"周，周一是"+x+"-"+y+"-"+(7*z+1-m)+"，周日是"+x+"-"+y+"-"+(7*z+7-m);
                }
            case 2:
                m = (31+m)%7;
                if(run) {
                    if((7*z+1-m)>29){
                        return "输入周数大于该月范围！";
                    }
                    if((7*z+7-m)>29){
                        return x+"年"+y+"月"+"第"+z+"周，周一是"+x+"-"+y+"-"+(7*z+1-m)+"，周日是"+x+"-"+(y+1)+"-"+(7*z+7-m-29);
                    }else {
                        return x+"年"+y+"月"+"第"+z+"周，周一是"+x+"-"+y+"-"+(7*z+1-m)+"，周日是"+x+"-"+y+"-"+(7*z+7-m);
                    }
                }else{
                    if((7*z+1-m)>28){
                        return "输入周数大于该月范围！";
                    }
                    if((7*z+7-m)>28){
                        return x+"年"+y+"月"+"第"+z+"周，周一是"+x+"-"+y+"-"+(7*z+1-m)+"，周日是"+x+"-"+(y+1)+"-"+(7*z+7-m-28);
                    }else {
                        return x+"年"+y+"月"+"第"+z+"周，周一是"+x+"-"+y+"-"+(7*z+1-m)+"，周日是"+x+"-"+y+"-"+(7*z+7-m);
                    }
                }
            case 3:
                if(run) {
                    m = (60+m)%7;
                }else{
                    m = (59+m)%7;
                }
                if((7*z+1-m)>31){
                    return "输入周数大于该月范围！";
                }
                if((7*z+7-m)>31){
                    return x+"年"+y+"月"+"第"+z+"周，周一是"+x+"-"+y+"-"+(7*z+1-m)+"，周日是"+x+"-"+(y+1)+"-"+(7*z+7-m-31);
                }else {
                    return x+"年"+y+"月"+"第"+z+"周，周一是"+x+"-"+y+"-"+(7*z+1-m)+"，周日是"+x+"-"+y+"-"+(7*z+7-m);
                }
            case 4:
                if(run) {
                    m = (91+m)%7;
                }else{
                    m = (90+m)%7;
                }
                if((7*z+1-m)>30){
                    return "输入周数大于该月范围！";
                }
                if((7*z+7-m)>30){
                    return x+"年"+y+"月"+"第"+z+"周，周一是"+x+"-"+y+"-"+(7*z+1-m)+"，周日是"+x+"-"+(y+1)+"-"+(7*z+7-m-30);
                }else {
                    return x+"年"+y+"月"+"第"+z+"周，周一是"+x+"-"+y+"-"+(7*z+1-m)+"，周日是"+x+"-"+y+"-"+(7*z+7-m);
                }
            case 5:
                if(run) {
                    m = (121+m)%7;
                }else{
                    m = (120+m)%7;
                }
                if((7*z+1-m)>31){
                    return "输入周数大于该月范围！";
                }
                if((7*z+7-m)>31){
                    return x+"年"+y+"月"+"第"+z+"周，周一是"+x+"-"+y+"-"+(7*z+1-m)+"，周日是"+x+"-"+(y+1)+"-"+(7*z+7-m-31);
                }else {
                    return x+"年"+y+"月"+"第"+z+"周，周一是"+x+"-"+y+"-"+(7*z+1-m)+"，周日是"+x+"-"+y+"-"+(7*z+7-m);
                }
            case 6:
                if(run) {
                    m = (152+m)%7;
                }else{
                    m = (151+m)%7;
                }
                if((7*z+1-m)>30){
                    return "输入周数大于该月范围！";
                }
                if((7*z+7-m)>30){
                    return x+"年"+y+"月"+"第"+z+"周，周一是"+x+"-"+y+"-"+(7*z+1-m)+"，周日是"+x+"-"+(y+1)+"-"+(7*z+7-m-30);
                }else {
                    return x+"年"+y+"月"+"第"+z+"周，周一是"+x+"-"+y+"-"+(7*z+1-m)+"，周日是"+x+"-"+y+"-"+(7*z+7-m);
                }
            case 7:
                if(run) {
                    m = (182+m)%7;
                }else{
                    m = (181+m)%7;
                }
                if((7*z+1-m)>31){
                    return "输入周数大于该月范围！";
                }
                if((7*z+7-m)>31){
                    return x+"年"+y+"月"+"第"+z+"周，周一是"+x+"-"+y+"-"+(7*z+1-m)+"，周日是"+x+"-"+(y+1)+"-"+(7*z+7-m-31);
                }else {
                    return x+"年"+y+"月"+"第"+z+"周，周一是"+x+"-"+y+"-"+(7*z+1-m)+"，周日是"+x+"-"+y+"-"+(7*z+7-m);
                }
            case 8:
                if(run) {
                    m = (213+m)%7;
                }else{
                    m = (212+m)%7;
                }
                if((7*z+1-m)>31){
                    return "输入周数大于该月范围！";
                }
                if((7*z+7-m)>31){
                    return x+"年"+y+"月"+"第"+z+"周，周一是"+x+"-"+y+"-"+(7*z+1-m)+"，周日是"+x+"-"+(y+1)+"-"+(7*z+7-m-31);
                }else {
                    return x+"年"+y+"月"+"第"+z+"周，周一是"+x+"-"+y+"-"+(7*z+1-m)+"，周日是"+x+"-"+y+"-"+(7*z+7-m);
                }
            case 9:
                if(run) {
                    m = (244+m)%7;
                }else{
                    m = (243+m)%7;
                }
                if((7*z+1-m)>30){
                    return "输入周数大于该月范围！";
                }
                if((7*z+7-m)>30){
                    return x+"年"+y+"月"+"第"+z+"周，周一是"+x+"-"+y+"-"+(7*z+1-m)+"，周日是"+x+"-"+(y+1)+"-"+(7*z+7-m-30);
                }else {
                    return x+"年"+y+"月"+"第"+z+"周，周一是"+x+"-"+y+"-"+(7*z+1-m)+"，周日是"+x+"-"+y+"-"+(7*z+7-m);
                }
            case 10:
                if(run) {
                    m = (274+m)%7;
                }else{
                    m = (273+m)%7;
                }
                if((7*z+1-m)>31){
                    return "输入周数大于该月范围！";
                }
                if((7*z+7-m)>31){
                    return x+"年"+y+"月"+"第"+z+"周，周一是"+x+"-"+y+"-"+(7*z+1-m)+"，周日是"+x+"-"+(y+1)+"-"+(7*z+7-m-31);
                }else {
                    return x+"年"+y+"月"+"第"+z+"周，周一是"+x+"-"+y+"-"+(7*z+1-m)+"，周日是"+x+"-"+y+"-"+(7*z+7-m);
                }
            case 11:
                if(run) {
                    m = (305+m)%7;
                }else{
                    m = (304+m)%7;
                }
                if((7*z+1-m)>30){
                    return "输入周数大于该月范围！";
                }
                if((7*z+7-m)>30){
                    return x+"年"+y+"月"+"第"+z+"周，周一是"+x+"-"+y+"-"+(7*z+1-m)+"，周日是"+x+"-"+(y+1)+"-"+(7*z+7-m-30);
                }else {
                    return x+"年"+y+"月"+"第"+z+"周，周一是"+x+"-"+y+"-"+(7*z+1-m)+"，周日是"+x+"-"+y+"-"+(7*z+7-m);
                }
            case 12:
                if(run) {
                    m = (335+m)%7;
                }else{
                    m = (334+m)%7;
                }
                if((7*z+1-m)>31){
                    return "输入周数大于该月范围！";
                }
                if((7*z+7-m)>31){
                    return x+"年"+y+"月"+"第"+z+"周，周一是"+x+"-"+y+"-"+(7*z+1-m)+"，周日是"+(x+1)+"-"+"1"+"-"+(7*z+7-m-31);
                }else {
                    return x+"年"+y+"月"+"第"+z+"周，周一是"+x+"-"+y+"-"+(7*z+1-m)+"，周日是"+x+"-"+y+"-"+(7*z+7-m);
                }
        }
        return "";
    }
}
