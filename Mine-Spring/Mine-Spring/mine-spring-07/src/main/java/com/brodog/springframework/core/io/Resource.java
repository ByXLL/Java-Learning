package com.brodog.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 资源加载接口定义
 * 主要用于处理资源加载流
 * @author By-BroDog
 * @createTime 2023-02-13
 */
public interface Resource {
    /**
     * 获取输入流
     * 子类去实现 classPath、FileSystem、URL 三种流文件的操作
     * @return
     * @throws IOException
     */
    InputStream getInputStream() throws IOException;
}
