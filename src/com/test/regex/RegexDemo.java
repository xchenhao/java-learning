package com.test.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {

    /**
     * 没有使用正则表达式来检查字符串是否由数字组成
     */
    @Test
    public void test() {
        String s = "5201314";
        char[] chars = s.toCharArray();
        boolean flag = true;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] < '0' || chars[i] > '9') {
                flag = false;
                break;
            }
        }
        if (flag) {
            System.out.println("是由数字组成");
        } else {
            System.out.println("不是由数字组成");
        }
    }

    // JDK API 文档
    @Test
    public void test2() {
        // 创建一个匹配的模式（模板）
        Pattern p = Pattern.compile("a*b");  // * 表示 0 到多个
        Matcher matcher = p.matcher("aaaaaaab");
       // Matcher matcher = p.matcher("ab");
        boolean b = matcher.matches();  // 匹配与否
        System.out.println(b);
    }

    @Test
    public void test3() {
        String s = "5201314";
        boolean b = s.matches("[0-9]+");
        boolean b1 = s.matches("\\d+");
        System.out.println(b + "," + b1);
    }

    @Test
    public void test4() {
        // 匹配电话号码
        String phoneNumber = "010-38389438";
        boolean b = phoneNumber.matches("\\d{3,4}-\\d{7,8}");
        System.out.println(b);

        // 匹配手机号
        String phone = "13895271234";
        System.out.println(phone.matches("[1][3-9]\\d{9}"));

        // 匹配用户名、字母开头、数字字母、下划线组合
        String username = "abc1314";
        System.out.println(username.matches("[a-zA-Z]+[\\w|_]*"));

        // 匹配 IP 地址
        String ip = "20.10.20.123";
        System.out.println(ip.matches("\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}"));

        // 匹配网址
        String addr = "http://www.baidu.com";
        System.out.println(addr.matches("http://\\w+.\\w+.\\S*"));

        // 匹配年龄
        String age = "18";
        System.out.println(age.matches("\\d{1,3}"));

        // 匹配金额
        String price = "19.8";
        System.out.println(price.matches("\\d+.\\d+"));
    }
}
