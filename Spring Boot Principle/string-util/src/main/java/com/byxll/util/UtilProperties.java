package com.byxll.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 源信息
 * @author By-Lin
 */
@ConfigurationProperties(prefix = "test.data")
public class UtilProperties {
    private String sourceString;

    public String getSourceString() {
        return sourceString;
    }

    public void setSourceString(String sourceString) {
        this.sourceString = sourceString;
    }
}
