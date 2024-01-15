package org.talend.esbconsole.server.util;

import java.io.IOException;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class Test {

    @org.junit.Test
    public void test() {
        String jarPath = "C:\\Users\\Lenovo\\Desktop\\esb需求规格说明\\rest-oracle-0.1.jar";
        try(JarFile jarFile = new JarFile(jarPath)) {
            Manifest manifest = jarFile.getManifest();
            if (manifest != null) {
                String value = manifest.getMainAttributes().getValue("Bundle-SymbolicName");
                System.out.println("Bundle-SymbolicName:"+value);
                String value1 = manifest.getMainAttributes().getValue("Bundle-Version");
                System.out.println("Bundle-Version:"+value1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
