package com.thoughtworks.basic;


import java.util.*;
import java.util.stream.Collectors;

public class Args {
    private String argsText;
    private List<Arg> argList = new ArrayList<>();
    private List<String> argExceptionList = new ArrayList<>();

    public Args(String argsText) {
        this.argsText = argsText;
    }



//输入解析函数入口
    public List<Arg> inputArgsParse() throws Exception {
//      判断是否非标准输入
        boolean checkArg = checkArgException();
            if(checkArg == true){
            List<String> inputArgs =this.scan();
            for(String inputArg: inputArgs ){
                Arg arg = getArg(inputArg);
                checkRepeatFlag(argList,arg);
                argList.add(arg);
            }
            return argList;
        }else{
                return null;
            }

    }
//
//根据输入获取arg对象,当无vlaue进入catch
    private Arg getArg(String inputArg) throws Exception {
        try{
            return Arg.getArgFun(Arrays.asList(inputArg.split(" ")));
        }
        catch (Exception e){
            Schema schema = defaultScema.create(inputArg.trim());
            return new Arg(inputArg,schema.getDefaultValue(),schema.getValueType());
        }
    }
//检测是否存在重复 flag
    private void checkRepeatFlag(List<Arg> argList, Arg arg) throws Exception {
        for (Arg arg1:argList){
            checkFlag(arg1,arg);
        }
    }
    private void checkFlag(Arg arg1,Arg arg) throws Exception {
        if(arg1.getFlag().equals(arg.getFlag())){
            throw new Exception("不允许输入重复flag");
        }
    }

    public List<Arg> getArgSet() {
        return argList;
    }

    public List<String> scan() {
        List<String> keyValues = Arrays.asList(this.argsText.split("-"));
        keyValues = keyValues.stream()
//                .map(String::trim)
                .map(keyValue -> keyValue.trim())
                .collect(Collectors.toList());
        return keyValues.subList(1, keyValues.size());
    }

    public boolean checkArgException(){
        argExceptionList.add("-p-d");
        argExceptionList.add("-p-l");
        argExceptionList.add("-l-d");
        argExceptionList.add("-l-p");
        argExceptionList.add("-d-l");
        argExceptionList.add("-d-p");
        int i = 1;
        for (String argException:argExceptionList) {
            if(this.argsText.contains(argException)){
                i += 1;
                break;
            }
        }
        if(i==2){
            return false;
        }else{
            return  true;
        }
    }
}
