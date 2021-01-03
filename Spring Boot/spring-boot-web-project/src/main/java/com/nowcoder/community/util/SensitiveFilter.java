package com.nowcoder.community.util;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 敏感词过滤 工具集
 */
@Component
public class SensitiveFilter {
    private static final Logger logger = LoggerFactory.getLogger(SensitiveFilter.class);
    // 替换符 常量
    private static final String REPLACEMENT = "***";
    // 根节点
    private TrieNode rootNode = new TrieNode();

    // 初始化方法 当容器去实例化 Component 注解的 Bean 的时候 在调用构造器后 会执行当前声明的方法
    @PostConstruct
    public void init() {
        try(
            // 当项目启动的时候 去获取 类路径下声明的 sensitive-words
            // 通过 获取当前类 再拿到类加载器 然后使用文件流方法 去获取
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("sensitive-words.txt");
            // 使用缓冲流接收
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        ) {
            // 读取每一个敏感词
            String keyValue;
            while ((keyValue = bufferedReader.readLine()) != null) {
                // 将敏感词 添加到树节点
                this.addKeyWord(keyValue);
            }
        } catch (IOException e) {
            logger.error("敏感词文件读取失败："+e.getMessage());
        }
    }

    /**
     * 将敏感词 添加到树节点
     * @param keyValue
     */
    private void addKeyWord(String keyValue) {
        // 先创建一个 暂时的 TrieNode
        TrieNode tempTrieNode = rootNode;
        for(int i=0; i<keyValue.length(); i++) {
            char c = keyValue.charAt(i);
            // 判断是否存在子节点
            TrieNode subNode = tempTrieNode.getSubNode(c);
            if(subNode == null) {
                // 初始化子节点
                subNode = new TrieNode();
                tempTrieNode.addSubNode(c,subNode);
            }
            // 指向子节点 开启下一轮循环
            tempTrieNode = subNode;

            // 循环到最后位置 打上结束标志
            if(i == keyValue.length() - 1) {
                tempTrieNode.setEnd(true);
            }
        }
    }

    /**
     * 过滤敏感词
     * @param text  待过滤的数据
     * @return  // 过滤后的文本
     */
    public String filterKeyWord(String text) {
        if(StringUtils.isBlank(text)) {
            return null;
        }
        // 指针一
        TrieNode tempNode = rootNode;
        // 指针二
        int begin = 0;
        // 指针三
        int position = 0;
        // 结果
        StringBuilder sb = new StringBuilder();

        // 循环判断
        while (position < text.length()) {
            char c = text.charAt(position);
            // 跳过符号
            if(isSymbol(c)) {
                // 若指针1处于根节点，将此字符计入结果，让指针2向下走一步
                if(tempNode == rootNode) {
                    sb.append(c);
                    begin++;
                }
                // 无论符号在开头还是中间 指针3都向下走一步
                position++;
                continue;
            }
            // 检查下级节点
            tempNode = tempNode.getSubNode(c);
            if(tempNode == null) {
                // 当前 以begin开头的字符串不是敏感词
                sb.append(text.charAt(begin));
                // 然后 进入下一个位置
                position = ++begin;
                // 重新指向根节点
                tempNode = rootNode;
            }else if(tempNode.isEnd()) {
                // 发现敏感词
                sb.append(REPLACEMENT);
                // 进入下一个位置
                begin = ++position;
                tempNode = rootNode;
            }else {
                // 检查下一个字符
                position++;
            }
        }
        // 都不是敏感词的时候
        sb.append(text.substring(begin));
        return sb.toString();
    }

    /**
     * 判断是不是存在符号
     * @param c     字符
     * @return      判断结果
     */
    private boolean isSymbol(Character c) {
        // 判断是否是特殊字符
        return !CharUtils.isAsciiAlphanumeric(c) && (c<0x2E80 || c>0x9FFF);
    }

    /**
     *   内部类 描述 树的某一个节点
     */
    private class TrieNode {
        // 关键词结束标识
        private boolean isEnd = false;

        // 子节点  子节点可能会存在多个 然后子节点可能也会存在有子节点 使用map去做处理 key 就是当前字符 value 是一个 TrieNode
        private Map<Character, TrieNode> subNodes = new HashMap<>();

        /**
         * 添加子节点方法
         * @param c     字符
         * @param node  节点
         */
        public void addSubNode(Character c, TrieNode node) {
            subNodes.put(c,node);
        }

        /**
         * 获取子节点
         * @param c     字符
         * @return      子节点 TrieNode
         */
        public TrieNode getSubNode(Character c) {
            return subNodes.get(c);
        }


        public boolean isEnd() { return isEnd; }

        public void setEnd(boolean end) { isEnd = end; }
    }
}
