package org.talend.esbconsole.server.domain.core.impl;

import org.talend.esbconsole.server.domain.api.service.KarService;
import org.talend.esbconsole.server.domain.core.jmx.karaf.Bundle;
import org.talend.esbconsole.server.domain.core.jmx.karaf.Kar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * kar相关功能服务接口 {@link KarService} 的实现类
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Slf4j
@Service
public class KarServiceImpl implements KarService {

    @Autowired
    private Kar kar;

    @Autowired
    private Bundle bundle;

    @Override
    public void installKar(String karFile) {
        kar.install(karFile);
    }

    @Override
    public void uninstallKar(String fileName) {
        kar.uninstall(fileName);
    }

    @Override
    public void startKar(List<String> karBundlesMVNName) {
        for (String bundleMVNName : karBundlesMVNName) {
            bundle.start(bundleMVNName);
        }
    }

    @Override
    public void stoplKar(List<String> karBundlesMVNName) {
        for (String bundleMVNName : karBundlesMVNName) {
            bundle.stop(bundleMVNName);
        }
    }
}
