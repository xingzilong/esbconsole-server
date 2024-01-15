package org.talend.esbconsole.server.domain.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * jar文件中的MANIFEST.MF文件数据对象
 *
 * @author xingzilong
 * @date 2023/11/23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManifestInfo {

    private String bundleSymbolicName;

    private String bundleVersion;

    /**
     * 对比值是否相等
     *
     * @param manifestInfo
     * @return
     */
    public boolean equalsValue(final ManifestInfo manifestInfo) {
        if (manifestInfo == this) {
            return true;
        } else {
            if (this.bundleSymbolicName == null) {
                if (manifestInfo.bundleSymbolicName != null) {
                    return false;
                }
            } else if (!this.bundleSymbolicName.equals(manifestInfo.bundleSymbolicName)) {
                return false;
            }
            if (this.bundleVersion == null) {
                if (manifestInfo.bundleVersion != null) {
                    return false;
                }
            } else if (!this.bundleVersion.equals(manifestInfo.bundleVersion)) {
                return false;
            }
            return true;
        }
    }
}
