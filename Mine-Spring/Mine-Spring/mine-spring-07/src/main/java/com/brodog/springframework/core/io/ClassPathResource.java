package com.brodog.springframework.core.io;

import cn.hutool.core.lang.Assert;
import com.brodog.springframework.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author By-BroDog
 * @createTime 2023-02-13
 */
public class ClassPathResource implements Resource {
    private final String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path, "Path must not be null");
        this.path = path;
        this.classLoader = Objects.nonNull(classLoader) ? classLoader : ClassUtils.getDefaultClassLoader();
    }


    /**
     * 获取输入流
     * 子类去实现 classPath、FileSystem、URL 三种流文件的操作
     *
     * @return
     * @throws IOException
     */
    @Override
    public InputStream getInputStream() throws IOException {
        InputStream inputStream = classLoader.getResourceAsStream(path);
        if(Objects.isNull(inputStream)) {
            throw new FileNotFoundException( this.path + " cannot be opened because it does not exist");
        }
        return inputStream;
    }
}
