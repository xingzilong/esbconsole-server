package org.talend.esbconsole.server.tools.common.utils.text;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.Charset;

import static org.mockito.Mockito.mockStatic;

/**
 * {@link CharsetKit} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class CharsetKitTest {

    @InjectMocks
    CharsetKit charsetKit;

    @Test
    public void charsetTest() {
        String charset = "";
        charsetKit.charset(charset);
        charset = "GBK";
        charsetKit.charset(charset);
    }

    @Test
    public void convertTest() {
        String source = "test";
        String srcCharset = "UTF-8";
        String destCharset = "GBK";

        charsetKit.convert(source, srcCharset, destCharset);
        source = "";
        charsetKit.convert(source, srcCharset, destCharset);

        source = "t";
        srcCharset = "GBK";
        charsetKit.convert(source, srcCharset, destCharset);

        MockedStatic<Charset> charsetStatic = mockStatic(Charset.class);
        charsetStatic.when(() -> Charset.forName(Mockito.any())).thenReturn(null);
        charsetKit.convert(source, srcCharset, destCharset);


        charsetStatic.close();
    }

    @Test
    public void systemCharsetTest() {
        charsetKit.systemCharset();
    }

}
