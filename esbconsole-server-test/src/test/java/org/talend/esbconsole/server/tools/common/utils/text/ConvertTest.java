package org.talend.esbconsole.server.tools.common.utils.text;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link Convert} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ConvertTest {

    @Test
    public void stringCovertTest() {

        assertEquals("test", Convert.toStr(null, "test"));
        assertEquals("tttt", Convert.toStr("tttt", "test"));
        assertEquals("1", Convert.toStr(1, "test"));
        assertNull(Convert.toStr(null));
    }

    @Test
    public void characterCovertTest() {
        assertNull(Convert.toChar(null));
        assertEquals('a', Convert.toChar("a"));
        assertEquals('b', Convert.toChar('b'));
        assertEquals('s', Convert.toChar("", 's'));
    }

    @Test
    public void byteCovertTest() {
        assertNull(Convert.toByte(null));
        Byte temp = 1;
        assertEquals(temp, Convert.toByte(temp));
        assertEquals("1", Convert.toByte(1).toString());
        assertNull(Convert.toByte(""));
        assertEquals("1", Convert.toByte("1").toString());
        assertNull(Convert.toByte("4da654fsa642a3"));
    }

    @Test
    public void shortConvertTest() {
        assertNull(Convert.toShort(null));
        Short temp = 1;
        assertEquals("1", Convert.toShort(temp).toString());
        assertEquals("100", Convert.toShort(100).toString());
        assertNull(Convert.toShort(""));
        assertEquals("127", Convert.toShort("127").toString());
        assertNull(Convert.toShort("465468751sdadasdads1a"));
    }

    @Test
    public void numberConvertTest() {
        assertNull(Convert.toNumber(null));
        assertEquals("1", Convert.toNumber(1).toString());
        assertEquals("100", Convert.toNumber(100).toString());
        assertNull(Convert.toNumber(""));
        assertEquals("127", Convert.toNumber("127").toString());
        assertNull(Convert.toNumber("gfdfgdg465d"));
    }

    @Test
    public void integerConvertTest() {
        assertNull(Convert.toInt(null));
        assertEquals("1", Convert.toInt(1).toString());
        assertEquals("100", Convert.toInt(100L).toString());
        assertNull(Convert.toInt(""));
        assertEquals("127", Convert.toInt("127").toString());
        assertNull(Convert.toInt("gfdfgdg465d"));
    }

    @Test
    public void intgerArrayConvertTest() {
        Convert.toIntArray("");
        Convert.toIntArray("1,2,3,4");
    }

    @Test
    public void longArrayConvertTest() {
        Convert.toLongArray("");
        Convert.toLongArray("100,200");
        Convert.toStrArray("");
    }

    @Test
    public void longConvertTest() {
        assertNull(Convert.toLong(null));
        assertEquals("1", Convert.toLong(1).toString());
        assertEquals("100", Convert.toLong(100L).toString());
        assertNull(Convert.toLong(""));
        assertEquals("127", Convert.toLong("127").toString());
        assertNull(Convert.toLong("gfdfgdg465d"));
    }

    @Test
    public void doubleConvertTest() {
        assertNull(Convert.toDouble(null));
        assertEquals("1.0", Convert.toDouble(1).toString());
        assertEquals("100.0", Convert.toDouble(100d).toString());
        assertNull(Convert.toDouble(""));
        assertEquals("127.0", Convert.toDouble("127").toString());
        assertNull(Convert.toDouble("gfdfgdg465d"));
    }

    @Test
    public void floatConvertTest() {
        assertNull(Convert.toFloat(null));
        assertEquals("1.0", Convert.toFloat(1).toString());
        assertEquals("100.0", Convert.toFloat(100f).toString());
        assertNull(Convert.toFloat(""));
        assertEquals("127.0", Convert.toFloat("127").toString());
        assertNull(Convert.toFloat("gfdfgdg465d"));
    }

    @Test
    public void boolConvertTest() {
        assertNull(Convert.toBool(null));
        assertTrue(Convert.toBool(true));
        assertNull(Convert.toBool(""));
        assertTrue(Convert.toBool("1"));
        assertFalse(Convert.toBool("0"));
        assertNull(Convert.toBool("yyyds"));
    }

    @Test
    public void EnumConvertTest() {
        assertNull(Convert.toEnum(Enum.class, null));
        Enum enums = Mockito.mock(Enum.class);
        assertNotNull(Convert.toEnum(Enum.class, enums));
        assertNull(Convert.toEnum(Enum.class, ""));
        assertNull(Convert.toEnum(Enum.class, enums.toString()));
    }

    @Test
    public void bigIntegerConvertTest() {
        assertNull(Convert.toBigInteger(null));
        assertEquals("1", Convert.toBigInteger(1L).toString());
        assertEquals("1", Convert.toBigInteger(1).toString());
        assertEquals("100", Convert.toBigInteger(new BigInteger("100")).toString());
        assertNull(Convert.toBigInteger(""));
        assertEquals("127", Convert.toBigInteger("127").toString());
        assertNull(Convert.toBigInteger("gfdfgdg465d"));
    }

    @Test
    public void bigDecimalConvertTest() {
        assertNull(Convert.toBigDecimal(null));
        assertEquals("1", Convert.toBigDecimal(1L).toString());
        assertEquals("1.0", Convert.toBigDecimal(1d).toString());
        assertEquals("1", Convert.toBigDecimal(1).toString());
        assertEquals("100", Convert.toBigDecimal(new BigDecimal("100")).toString());
        assertNull(Convert.toBigDecimal(""));
        assertEquals("127", Convert.toBigDecimal("127").toString());
        assertNull(Convert.toBigDecimal("gfdfgdg465d"));
    }

    @Test
    public void strConvertTest() {

        assertNull(Convert.utf8Str(null));
        assertEquals("test", Convert.utf8Str("test"));
        assertNotNull(Convert.utf8Str(new byte[]{1}));
        assertNotNull(Convert.utf8Str(new Byte[]{1}));
        assertNotNull(Convert.utf8Str(1L));
        assertEquals("s", Convert.str("s", "utf-8"));
        Convert.str(new byte[0], "utf-8");
        Charset chars = null;
        Convert.str(new byte[0], chars);
        byte[] data = null;
        Convert.str(data, chars);
        ByteBuffer bf = null;
        Convert.str(bf, "test");
        bf = Mockito.mock(ByteBuffer.class);
        Convert.str(bf, "utf-8");
        Convert.str(bf, chars);

    }

    @Test
    public void sbcConvertTest() {
        assertEquals("ｔｅｓｔ", Convert.toSBC("test"));
        assertEquals("ｔ　eｓｔ", Convert.toSBC("t est", new HashSet<Character>(Arrays.asList('e'))));
        Convert.toSBC("testá");
    }

    @Test
    public void dbcConvertTest() {
        assertEquals("test", Convert.toDBC("test"));
        Convert.toDBC("ﻬ４ｳe", new HashSet<Character>(Arrays.asList('e')));
        Convert.toDBC("　");

    }
}
