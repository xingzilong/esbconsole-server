package org.talend.esbconsole.server.domain.core.util.fileanalysis.strategy;

import org.talend.esbconsole.server.domain.repository.entity.ServiceDO;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.internal.MockedConstructionImpl;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

/**
 * {@link RestFulWSAnalysisStrategy} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/16
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class RestFulWSAnalysisStrategyTest {

    @InjectMocks
    RestFulWSAnalysisStrategy restFulWSAnalysisStrategy;

    @Before
    public void setUp() {
        //初始化
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void analysisFileTest() {
        File file = new File("textfeatures.xml");
        ServiceDO serviceDO = Mockito.mock(ServiceDO.class);
        MockedStatic<AnalysisUtil> analysisUtil = Mockito.mockStatic(AnalysisUtil.class);
        MockedConstructionImpl<ZipFile> zipFile = (MockedConstructionImpl<ZipFile>) Mockito.mockConstruction(ZipFile.class,
                (mock, context) -> {

                });
        MockedConstruction<FileInputStream> fileInput = Mockito.mockConstruction(FileInputStream.class, (mock, context) -> {
        });

        MockedConstruction<BufferedInputStream> inputStream = Mockito.mockConstruction(BufferedInputStream.class, (mock, context) -> {
        });

        ZipEntry zipEntry = Mockito.mock(ZipEntry.class);
        MockedConstructionImpl<ZipInputStream> zip = (MockedConstructionImpl<ZipInputStream>) Mockito.mockConstruction(ZipInputStream.class, (mock, context) -> {
            Mockito.doReturn(zipEntry).when(mock).getNextEntry();
        });

        doReturn("testjob.xml").when(zipEntry).getName();

        File tempFile = mock(File.class);
        analysisUtil.when(() -> AnalysisUtil.writeTempFile(Mockito.any(ZipEntry.class), Mockito.any(ZipFile.class), Mockito.any())).thenReturn(tempFile);
        analysisUtil.when(() -> AnalysisUtil.isEnabledSAM(Mockito.any())).thenReturn(true);

        restFulWSAnalysisStrategy.analysis(file, serviceDO);

        analysisUtil.when(() -> AnalysisUtil.isEnabledSAM(Mockito.any())).thenReturn(false);

        restFulWSAnalysisStrategy.analysis(file, serviceDO);

        zipFile.close();
        analysisUtil.close();
        fileInput.close();
        zipEntry.clone();
        inputStream.close();
        zip.close();
    }

}
