package org.talend.esbconsole.server.tools.base.result;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

/**
 * {@link ResponseResult} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/16
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ResponseResultTest {

    public void disPlay(ResponseResult result) {

        result.getCode();
        result.getData();
        result.getMsg();
    }

    @Test
    public void test() {

        ResponseResult resultByNoArgs = new ResponseResult();
        ResponseResult resultByArgs = new ResponseResult(200, "test", null);
        ResponseResult resultByCode = new ResponseResult(200, Arrays.asList("test"));
        ResponseResult resultByMsg = new ResponseResult(200, "test");
        disPlay(resultByArgs);
        resultByNoArgs.setCode(200);
        resultByNoArgs.setData(null);
        resultByNoArgs.setMsg("test");
    }

}
