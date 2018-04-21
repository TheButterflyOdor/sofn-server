package com.sofn.core;

/**
 * Sort
 * */

public class Sort {
    public final String A = "1";
	static {
		System.out.println("静态代码块");
	}
	{
		System.out.println("构造代码块");
	}
	public Sort(){
		System.out.println("无参构造函数");
	}
	public Sort(String a){
		System.out.println("有参构造函数a");
	}
	public Sort(String a,String b){
		System.out.println("有参构造函数b");
	}
	public static void sort(){
		System.out.println("静态方法");
	}
	public static void main(String[] args){
	    String b = "";
		Sort sort = new Sort(b);
		System.out.println("main方法");
		String a = 1+01+"1";
		System.out.println(a);
	}
}
