package com.hong.extension.util;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <h1>字段值到实体类的映射工具类</h1>
 *
 * @Author: ZhangDehong
 * @Describe: TODO
 * @Date Create in  1:04 上午 2020/10/27
 */
public class MapToEntity {

    public static <T> T mapToEntity (Map<String, Object> map, Class<T> clazz)
            throws IllegalAccessException, InstantiationException {
        // 存储父类
        Class superClass;
        // 存储父类中的属性
        Field[] fields;
        // new 一个实例对象
        T target = clazz.newInstance();
        // 存储clazz的所有field
        List<Field> targetFieldList = new LinkedList<>();
        // 先把目标类赋值给父类
        superClass = clazz;
        // 寻找所有属性
        while (null != superClass && superClass != Object.class) {
            // 获取当前类的参数
            fields = superClass.getDeclaredFields();
            // 存储当前所以类的所以属性字段
            targetFieldList.addAll(Arrays.asList(fields));
            // 获取父类
            superClass = superClass.getSuperclass();
        }
        // 完成匹配并赋值
        for (Field targetField : targetFieldList) {
            for (Map.Entry<String, Object> mapEntity : map.entrySet()) {
                if (targetField.getName().equals(mapEntity.getKey())) {
                    // 暂时保存权限
                    boolean accessible = targetField.isAccessible();
                    // 赋予修改权限
                    targetField.setAccessible(true);
                    // 赋值
                    targetField.set(target, mapEntity.getValue() instanceof BigInteger ?
                            ((BigInteger) mapEntity.getValue()).longValue() : mapEntity.getValue());
                    // 恢复权限
                    targetField.setAccessible(accessible);
                    break;
                }
            }
        }
        return target;
    }
}
