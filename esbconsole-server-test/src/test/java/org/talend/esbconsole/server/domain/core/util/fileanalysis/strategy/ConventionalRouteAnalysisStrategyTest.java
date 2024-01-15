package org.talend.esbconsole.server.domain.core.util.fileanalysis.strategy;

import org.talend.esbconsole.server.domain.repository.entity.ServiceDO;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * {@link ConventionalRouteAnalysisStrategy} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/13
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class ConventionalRouteAnalysisStrategyTest {

    @InjectMocks
    ConventionalRouteAnalysisStrategy conventionalRouteAnalysisStrategy;


    @Test
    public void analysisTest() {
        File codeFile = mock(File.class);
        ServiceDO service = new ServiceDO();
        ZipEntry zipEntry = new ZipEntry("testfeatures.xml");

//        when(codeFile.getPath()).thenReturn("ttt");


        MockedConstruction<FileInputStream> mocked = Mockito.mockConstruction(FileInputStream.class, (mock, context) -> {
        });

        MockedConstruction<ZipInputStream> zipInputStream = Mockito.mockConstruction(ZipInputStream.class, (mock, context) -> {
            when(mock.getNextEntry()).thenReturn(null);
        });

        conventionalRouteAnalysisStrategy.analysis(new File("test"), service);

//		zipInputStream = Mockito.mockConstruction(ZipInputStream.class,(mock,context)->{
//			when(mock.getNextEntry()).thenReturn(zipEntry);
//		});

        MockedConstruction<ZipFile> zip = Mockito.mockConstruction(ZipFile.class, (mock, context) -> {
        });
        conventionalRouteAnalysisStrategy.analysis(codeFile, service);


        mocked.close();
        zip.close();
        zipInputStream.close();
    }

    @Test
    public void analysisFile_firstTest() {
        ConventionalRouteAnalysisStrategy analysis = Mockito.spy(ConventionalRouteAnalysisStrategy.class);
        File file = new File("textfeatures.xml");
        ServiceDO serviceDO = Mockito.mock(ServiceDO.class);
        MockedStatic<AnalysisUtil> util = Mockito.mockStatic(AnalysisUtil.class);
        MockedConstructionImpl<ZipFile> zipFile = (MockedConstructionImpl<ZipFile>) Mockito.mockConstruction(ZipFile.class,
                (mock, context) -> {

                });
        MockedConstructionImpl<FileInputStream> fileinput = (MockedConstructionImpl<FileInputStream>) Mockito.mockConstruction(FileInputStream.class,
                (mock, context) -> {
                });
        MockedConstructionImpl<BufferedInputStream> buffer = (MockedConstructionImpl<BufferedInputStream>) Mockito.mockConstruction(BufferedInputStream.class,
                (mock, context) -> {

                });
        ZipEntry zipEntry = Mockito.mock(ZipEntry.class);
        MockedConstructionImpl<ZipInputStream> zip = (MockedConstructionImpl<ZipInputStream>) Mockito.mockConstruction(ZipInputStream.class,
                (mock, context) -> {
                    Mockito.doReturn(zipEntry).when(mock).getNextEntry();
                });
        Mockito.doReturn("textfeatures.xml").when(zipEntry).getName();
        util.when(() -> AnalysisUtil.writeTempFile(Mockito.any(ZipEntry.class), Mockito.any(ZipFile.class), Mockito.anyString())).thenReturn(file);
        util.when(() -> AnalysisUtil.readXML(Mockito.any(File.class), Mockito.anyString(), Mockito.anyString())).thenReturn("testtesttesttesttesttest");
//		util.when(()->AnalysisUtil.getBundleNameListForXML(Mockito.any(File.class))).thenReturn(JSONObject.toJSONString(Arrays.asList("test")));
//		util.when(()->AnalysisUtil.processAndFilterBundleNameListFroSOAP(Mockito.anyList(),Mockito.anyString())).thenReturn(JSONObject.toJSONString(Arrays.asList("test")));
        //Mockito.doReturn("stss.xml").when(zipEntry).getName();
        try {
            analysis.analysisFile(file, serviceDO);
            //analysis.analysisFile(file, serviceDO);
        } catch (Exception e) {

        } finally {
            zipFile.close();
            fileinput.close();
            buffer.close();
            util.close();
            zip.close();
        }
    }


}
