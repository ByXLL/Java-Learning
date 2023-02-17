package com.brodog.springframework.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件系统资源加载类实现
 * 通过指定文件路径的方式读取文件信息
 * @author By-BroDog
 * @createTime 2023-02-12
 */
public class FileSystemResource implements Resource{

    private final File file;

    private final String path;

    public FileSystemResource(File file) {
        this.file = file;
        this.path = file.getPath();
    }

    public FileSystemResource(String path) {
        this.file = new File(path);
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    /**
     * 获取输入流
     * 通过指定文件路径的方式读取文件输入流
     * @return
     * @throws IOException
     */
    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }
}
