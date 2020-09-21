package com.thoughtworks.basic;

import java.util.List;

public class Arg {
    private String flag;
    private Object value;
    private String type;

    public Arg(String flag,Object value,String type) {
        this.flag = flag;
        this.value = value;
        this.type = type;
    }

    public static Arg getArgFun(List<Object> argList) throws Exception {
        String valueType = getValueType((String) argList.get(0));
        return new Arg((String) argList.get(0),argList.get(1),valueType);
    }
//获取类型值
    private static String getValueType(String flag) throws Exception{
        switch (flag){
            case "p":
                return "int";
            case "l":
                return "boolean";
            case "d":
                return "String";
        }throw new Exception("输入错误");
    }
//改变toString函数用于测试输出
    @Override
    public String toString() {
        return "Arg{" +
                "flag='" + flag + '\'' +
                ", value=" + value +
                ", type='" + type + '\'' +
                '}';
    }

    public String getFlag() {
        return flag;
    }

    public Object getValue() {
        return value;
    }
}
