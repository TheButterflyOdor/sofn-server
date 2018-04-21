package com.sofn.core;

import java.util.Scanner;

/**
 * @author sofn
 * @version 2017年03月09日 下午 1:07
 */
public class MaxCommonDivisorAndMinCommonMultiple {

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        System.out.println("请输入第一个整数");
        long num1 = s.nextLong();
        System.out.println("请输入第二个整数");
        long num2 = s.nextLong();
        MaxCommonDivisorAndMinCommonMultiple m = new MaxCommonDivisorAndMinCommonMultiple();
        System.out.println("递归法，最大公约数是："+m.maxCommonDivisor(num1,num2));
        System.out.println("循环法，最大公约数是："+m.maxCommonDivisor2(num1,num2));
        System.out.println("更相减损法，最大公约数是："+m.maxCommonMinus(num1,num2));
        System.out.println("最小公倍数是："+m.minCommonMultiple(num1,num2));
    }

    //求最大公约数，辗转相除法，递归实现
    public long maxCommonDivisor(long num1,long num2){
        if(num1>num2){
            long temp = num2;
            num2 = num1;
            num1 = temp;
        }
        if(num2%num1==0){
            return num1;
        }else
            return maxCommonDivisor(num2%num1,num1);
    }

    //求最大公约数，辗转相除法，循环实现
    public long maxCommonDivisor2(long num1,long num2){
        if(num1>num2){
            long temp = num2;
            num2 = num1;
            num1 = temp;
        }
        while(num2%num1!=0){
            long temp = num2%num1;
            num2 = num1;
            num1 = temp;
        }
        return num1;
    }

    //求最大公约数，更相减损法
    public long maxCommonMinus(long num1,long num2){
        if(num1>num2){
            long temp = num2;
            num2 = num1;
            num1 = temp;
        }
        long maxCommon = 1;
        while(num1%2==0&&num2%2==0){
            maxCommon = maxCommon * 2;
            num1 = num1/2;
            num2 = num2/2;
        }
        while(true) {
            long temp = num2 - num1;
            if(temp==num1){
                return maxCommon*temp;
            }else{
                if(temp>num1){
                    long temp2 = num1;
                    num1 = temp;
                    temp = temp2;
                }
                num2 = num1;
                num1 = temp;
            }
        }
    }

    //最小公倍数，两数相乘除以最大公约数
    public long minCommonMultiple(long num1,long num2){
        return num1*num2/maxCommonDivisor(num1,num2);
    }
}
