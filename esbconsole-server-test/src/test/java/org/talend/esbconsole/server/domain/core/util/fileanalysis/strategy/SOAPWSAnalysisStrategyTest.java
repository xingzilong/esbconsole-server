package org.talend.esbconsole.server.domain.core.util.fileanalysis.strategy;

import org.talend.esbconsole.server.domain.repository.entity.ServiceDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.internal.MockedConstructionImpl;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * {@link SOAPWSAnalysisStrategy} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/13
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SOAPWSAnalysisStrategyTest {

    @Test
    public void init() {
        SOAPWSAnalysisStrategy soap = new SOAPWSAnalysisStrategy();
        SOAPWSAnalysisStrategy soapwsAnalysisStrategy = new SOAPWSAnalysisStrategy("test");
    }

    @Test
    public void analysis() {
        SOAPWSAnalysisStrategy analysis = Mockito.spy(SOAPWSAnalysisStrategy.class);
        Mockito.doNothing().when(analysis).analysisFile(Mockito.any(File.class), Mockito.any());
        File file = new File("test");
        ServiceDO serviceDO = Mockito.mock(ServiceDO.class);
        analysis.analysis(file, serviceDO);
    }

    @Test
    public void analysisFileTest() {
        SOAPWSAnalysisStrategy analysis = Mockito.spy(SOAPWSAnalysisStrategy.class);
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
                    Mockito.doReturn(zipEntry).doReturn(null).when(mock).getNextEntry();
                });
        //Mockito.doReturn(false).doReturn(true).when(zipEntry).isDirectory();
        Mockito.doReturn("test").when(zipEntry).getName();
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

    @Test
    public void analysisFile_firstTest() {
        SOAPWSAnalysisStrategy analysis = Mockito.spy(SOAPWSAnalysisStrategy.class);
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
        //Mockito.doReturn(false).doReturn(true).when(zipEntry).isDirectory();
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

    @Test
    public void analysisFile_sencondTest() {
        SOAPWSAnalysisStrategy analysis = Mockito.spy(SOAPWSAnalysisStrategy.class);
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
        //Mockito.doReturn(false).doReturn(true).when(zipEntry).isDirectory();
        Mockito.doReturn("testblueprint.xml").when(zipEntry).getName();
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

    @Test
    public void analysisFile_threeTest() {
        SOAPWSAnalysisStrategy analysis = Mockito.spy(SOAPWSAnalysisStrategy.class);
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
                    Mockito.doReturn(zipEntry).doReturn(null).when(mock).getNextEntry();
                });
        //Mockito.doReturn(false).doReturn(true).when(zipEntry).isDirectory();
        Mockito.doReturn("testcontrol-bundle.jar").doReturn("sss").when(zipEntry).getName();
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

    @Test
    public void analysisFile_endTest() {
        SOAPWSAnalysisStrategy analysis = Mockito.spy(SOAPWSAnalysisStrategy.class);
        File file = new File("textfeatures.xml");
        ServiceDO serviceDO = Mockito.mock(ServiceDO.class);
        MockedConstructionImpl<ZipFile> zipFile = (MockedConstructionImpl<ZipFile>) Mockito.mockConstruction(ZipFile.class,
                (mock, context) -> {

                });
        try {
            analysis.analysisFile(file, serviceDO);
        } catch (Exception e) {

        } finally {
            zipFile.close();

        }
    }

}
