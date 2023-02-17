package com.brodog.springframework.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * 属性值集合 用于存放一个bean对象中所有的属性
 * @author By-BroDog
 * @createTime 2023-02-13
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue propertyValue) {
        propertyValueList.add(propertyValue);
    }

    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue propertyValue : propertyValueList) {
            if(propertyValue.getName().equals(propertyName)) {
                return propertyValue;
            }
        }
        return null;
    }
}
