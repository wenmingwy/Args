package com.thoughtworks.basic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;



public class SchemaTest {
//    按照标准格式进行输入测试
    @Test
    public void should_return_args_when_inputArgs_is_standard() throws Exception {
        String inputArgs = "-l true -p 8080 -d /usr/logs";
        List<Arg> argList = new ArrayList<>();
        Args args = new Args(inputArgs);
        argList = args.inputArgsParse();
        assertEquals("[Arg{flag='l', value=true, type='boolean'}, " +
                        "Arg{flag='p', value=8080, type='int'}, " +
                        "Arg{flag='d', value=/usr/logs, type='String'}]"
                        ,argList.toString());
    }
//flag缺少value，采用默认值测试
    @Test
    public void should_return_defaultValue_when_inputArgs_without_value() throws Exception {
        String inputArgs = "-l  -p  -d ";
        List<Arg> argList = new ArrayList<>();
        Args args = new Args(inputArgs);
        argList = args.inputArgsParse();
        assertEquals("[Arg{flag='l', value=false, type='boolean'}, " +
                        "Arg{flag='p', value=0, type='int'}, " +
                        "Arg{flag='d', value=, type='String'}]"
                ,argList.toString());
    }
//输入不是标准格式测试
    @Test
    public void should_throw_value_no_legal_when_inputArgs_is_not_standard() throws Exception {
        String inputArgs = "-l -true";

        try {
            Args args = new Args(inputArgs);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            assertThat(exception.getMessage(), is("args输入格式是不标准的"));
        }
    }
//flag重复时测试
    @Test
    public void should_throw_error_no_legal_when_repeat_flag() throws Exception {
        String inputArgs = "-l true -l false";
        try {
            Args args = new Args(inputArgs);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            assertThat(exception.getMessage(), is("不允许输入重复flag"));
        }
    }

    //输入存在-l-p类似输入时测试
    @Test
    public void should_throw_error_no_legal_when_inputArgs_like__l_p() throws Exception {
        String inputArgs = "-l-p 8080 -d /usr/logs";
        List<Arg> argList = new ArrayList<>();
        Args args = new Args(inputArgs);
        argList = args.inputArgsParse();
        assertEquals(null,argList);
    }
}
