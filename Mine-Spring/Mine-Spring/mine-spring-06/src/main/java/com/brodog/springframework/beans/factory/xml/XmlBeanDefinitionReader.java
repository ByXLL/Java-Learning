package com.brodog.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.brodog.springframework.beans.BeansException;
import com.brodog.springframework.beans.PropertyValue;
import com.brodog.springframework.beans.factory.config.BeanDefinition;
import com.brodog.springframework.beans.factory.config.BeanReference;
import com.brodog.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.brodog.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.brodog.springframework.core.io.Resource;
import com.brodog.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * 解析XML处理Bean注册
 * BeanDefinitionReader接口的实现类
 * 实现对 XML 文件的解析，把我们本来在代码中的操作放到了通过解析 XML 实现自动注册
 * @author By-BroDog
 * @createTime 2023-02-08
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        super(beanDefinitionRegistry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry, ResourceLoader resourceLoader) {
        super(beanDefinitionRegistry, resourceLoader);
    }

    /**
     * 触发加载 BeanDefinitions
     *
     * @param resource 资源加载的实现方式
     * @throws BeansException
     */
    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            InputStream inputStream = resource.getInputStream();
            doLoadBeanDefinitions(inputStream);
        } catch (IOException | ClassNotFoundException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    /**
     * 触发加载 BeanDefinitions
     *
     * @param resources 资源加载的实现方式
     * @throws BeansException
     */
    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    /**
     * 触发加载 BeanDefinitions
     * 通过传入的路径加载通过文件系统资源加载器 FileSystemResource 拿到所有的资源
     * @param location 文件路径
     * @throws BeansException
     */
    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        // 通过拿到父类 AbstractBeanDefinitionReader 中定义的获取资源加载器的方法 根据当前入参 拿到其对于的资源加载器
        ResourceLoader resourceLoader = getResourceLoader();
        // 拿到对应资源类型
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    /**
     * 触发加载 BeanDefinitions
     *
     * @param locations 所有的文件路径
     * @throws BeansException
     */
    @Override
    public void loadBeanDefinitions(String... locations) throws BeansException {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }

    /**
     * 通过输入流 获取流中的信息 并创建加载 其中的BeanDefinition
     * @param inputStream
     * @throws ClassNotFoundException
     */
    private void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
// 获取当前xml输入流 将xml流解析成文档对象
        Document document = XmlUtil.readXML(inputStream);
        // 获取文档元素对象
        Element documentElement = document.getDocumentElement();
        // 获取文档元素对象的所有的子节点
        NodeList childNodes = documentElement.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node nodeItem = childNodes.item(i);
            // 判断元素
            if(!(nodeItem instanceof Element)) { continue; }
            // 判断对象
            if(!"bean".equals(nodeItem.getNodeName())) { continue; }

            // 解析标签
            Element bean = (Element) nodeItem;
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");

            // 获取Class，方便获取类信息
            Class<?> aClass = Class.forName(className);
            // 优先级 id > name
            String beanName = StrUtil.isNotBlank(id) ? id : name;
            if(StrUtil.isEmpty(beanName)) {
                // 如果beanName为空 则默认获取当前类的类名 并将首字母设置为小写
                beanName = StrUtil.lowerFirst(aClass.getSimpleName());
            }

            // 定义bean
            BeanDefinition beanDefinition = new BeanDefinition(aClass);

            // 读取属性并填充
            for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                Node nodeItem2 = bean.getChildNodes().item(j);
                if(!(nodeItem2 instanceof Element)) { continue; }
                if(!"property".equals(nodeItem2.getNodeName())) { continue; }

                // 解析标签：property
                Element property = (Element) bean.getChildNodes().item(j);
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");

                // 获取属性值：引入对象，值对象
                Object objectAttrValue = StrUtil.isNotBlank(attrRef) ? new BeanReference(attrRef) : attrValue;
                // 创建属性信息
                PropertyValue propertyValue = new PropertyValue(attrName, objectAttrValue);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }

            if(getBeanDefinitionRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }

            // 注册 beanDefinition
            getBeanDefinitionRegistry().registryBeanDefinition(beanName, beanDefinition);
        }
    }
}
