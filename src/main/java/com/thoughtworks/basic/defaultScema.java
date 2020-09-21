package com.thoughtworks.basic;

public class defaultScema {

    public static Schema create(String flag) throws Exception {
        switch(flag){
            case "l" :
                return new Schema(flag,"boolean",false);
            case "p" :
                return new Schema(flag,"int",0);
            case "d" :
                return new Schema(flag,"String","");
        }
        throw new Exception("args输入格式是不标准的");
    }
}
