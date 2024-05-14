package org.talend.esbconsole.server.tools.common.utils;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * {@link StringUtil} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class StringUtilTest {

    @InjectMocks
    StringUtil stringUtil;

    @Test
    public void nvlTest() {
        assertEquals("", stringUtil.nvl("", "test"));
        assertEquals("test", stringUtil.nvl(null, "test"));
    }

    @Test
    public void isNotEmptyTest() {
        List<String> strings = new ArrayList<String>();

        assertEquals(false, stringUtil.isNotEmpty(strings));

        strings.add("test");
        assertEquals(true, stringUtil.isNotEmpty(strings));

        strings = null;
        assertEquals(false, stringUtil.isNotEmpty(strings));

    }

    @Test
    public void isNotEmptyTest1() {
        Object[] objects = new Object[3];

        assertEquals(true, stringUtil.isNotEmpty(objects));

        objects = new Object[0];
        assertEquals(false, stringUtil.isNotEmpty(objects));


        objects = null;
        assertEquals(false, stringUtil.isNotEmpty(objects));

    }

    @Test
    public void isNotEmptyTest2() {
        Map<String, String> map = new HashMap<String, String>();
        assertEquals(false, stringUtil.isNotEmpty(map));

        map.put("t", "test");
        assertEquals(true, stringUtil.isNotEmpty(map));

        map = null;
        assertEquals(false, stringUtil.isNotEmpty(map));

    }

    @Test
    public void isNotEmptyTest3() {
        String str = "";
        assertEquals(false, stringUtil.isNotEmpty(str));

        str = "test";
        assertEquals(true, stringUtil.isNotEmpty(str));

        str = null;
        assertEquals(false, stringUtil.isNotEmpty(str));
    }

    @Test
    public void isArrayTest() {
        Object object = null;
        assertEquals(false, stringUtil.isArray(object));

        object = new Object();
        assertEquals(false, stringUtil.isArray(object));

        String[] strings = new String[1];
        strings[0] = "test";
        assertEquals(true, stringUtil.isArray(strings));
    }

    @Test
    public void trimTest() {
        assertEquals("", stringUtil.trim(null));
        assertEquals("tsss", stringUtil.trim(" tsss"));
    }

    @Test
    public void substringTest() {
        assertEquals("", stringUtil.substring(null, 1));

        assertEquals("t", stringUtil.substring("test", -1));

        assertEquals("est", stringUtil.substring("test", 1));

        assertEquals("", stringUtil.substring("", -1));

        assertEquals("", stringUtil.substring("test", 5));
    }

    @Test
    public void substringTest1() {
        assertEquals("", stringUtil.substring(null, 1, 2));

        assertEquals("", stringUtil.substring("test", -1, -1));

        assertEquals("", stringUtil.substring("test", -1, 1));

        assertEquals("est", stringUtil.substring("test", 1, 5));

        assertEquals("test", stringUtil.substring("test", -5, 5));

        assertEquals("", stringUtil.substring("test", -6, -5));

    }

    @Test
    public void ishttpTest() {
        String link = "";
        assertEquals(false, stringUtil.ishttp(link));

    }

    @Test
    public void str2SetTest() {
        String str = new String();
        String sep = "t";
        Set<String> set = new HashSet<String>();
        assertEquals(set, stringUtil.str2Set(str, sep));

        str = "zjjnktnbl";
        set.add("zjjnk");
        set.add("nbl");
        assertEquals(set, stringUtil.str2Set(str, sep));
    }

    @Test
    public void str2ListTest() {
        String str = "test";
        String sep = "t";
        boolean filterBlank = true;
        boolean trim = true;

        stringUtil.str2List(str, sep, filterBlank, trim);
    }

    @Test
    public void containsAnyTest() {
        List<String> collection = new ArrayList<String>();

        String array = "";
        assertEquals(false, stringUtil.containsAny(collection, array));


        array = "t";
        collection.add("test");
        assertEquals(false, stringUtil.containsAny(collection, array));

        collection.add("t");
        assertEquals(true, stringUtil.containsAny(collection, array));
    }

    @Test
    public void containsAnyIgnoreCaseTest() {
        CharSequence cs = null;
        CharSequence searchCharSequences = "t";

        assertEquals(false, stringUtil.containsAnyIgnoreCase(cs, searchCharSequences));

        cs = "jkl";
        assertEquals(false, stringUtil.containsAnyIgnoreCase(cs, searchCharSequences));

        cs = "test";
        assertEquals(true, stringUtil.containsAnyIgnoreCase(cs, searchCharSequences));

    }

    @Test
    public void inStringIgnoreCaseTest() {

        String str = null;
        String strs = null;

        assertEquals(false, stringUtil.inStringIgnoreCase(str, strs));

        strs = "test";
        assertEquals(false, stringUtil.inStringIgnoreCase(str, strs));

        str = "test";
        strs = null;
        assertEquals(false, stringUtil.inStringIgnoreCase(str, strs));

        strs = "test";
        str = "test";
        assertEquals(true, stringUtil.inStringIgnoreCase(str, strs));
    }

    @Test
    public void convertToCamelCaseTest() {
        String name = "";
        assertEquals("", stringUtil.convertToCamelCase(name));

        name = null;
        assertEquals("", stringUtil.convertToCamelCase(name));

        name = "test";
        assertEquals("Test", stringUtil.convertToCamelCase(name));

        name = "test_zjj";
        assertEquals("TestZjj", stringUtil.convertToCamelCase(name));

    }

    @Test
    public void toCamelCaseTest() {
        String s = null;
        assertEquals(null, stringUtil.toCamelCase(s));

        s = "test";
        assertEquals("test", stringUtil.toCamelCase(s));

        s = "user_name";
        assertEquals("userName", stringUtil.toCamelCase(s));
    }

    @Test
    public void matchesTest() {
        String str = "";
        List<String> strs = new ArrayList<String>();
        assertEquals(false, stringUtil.matches(str, strs));

        strs.add("127.0.0.1");


        str = "127.?.?.1";
        assertEquals(false, stringUtil.matches(str, strs));

        strs.add("127.?.?.1");
        assertEquals(true, stringUtil.matches(str, strs));

    }

    @Test
    public void castTest() {
        stringUtil.cast(new Object());
    }

    @Test
    public void padlTest() {

        assertEquals("000000", stringUtil.padl(0, 6));
        assertEquals("235", stringUtil.padl(1235, 3));
    }


}
