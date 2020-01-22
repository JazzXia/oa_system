package com.qtatelier.config;

import org.apache.commons.lang.StringEscapeUtils;

import java.lang.reflect.Field;

/**
 * @author xiaweiwei
 * @descrpition
 * @email xiaww@redoornetwork.com(xia.weiwei163@163.com)
 * @qq 1104841692
 * @time 2020-01-22 10:21
 */
public class NullParamUtil {

    public static <T> T setNullValue(T source) throws IllegalArgumentException, IllegalAccessException, SecurityException {
        Field[] fields = source.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getGenericType().toString().equals(
                    "class java.lang.String")) {
                field.setAccessible(true);
                Object obj = field.get(source);
                if (obj != null && obj.equals("")) {
                    field.set(source, null);
                } else if (obj != null) {
                    String str = obj.toString();
                    str = StringEscapeUtils.escapeSql(str);//StringEscapeUtils是commons-lang中的通用类
                    field.set(source, str.replace("\\", "\\" + "\\").replace("(", "\\(").replace(")", "\\)")
                            .replace("%", "\\%").replace("*", "\\*").replace("[", "\\[").replace("]", "\\]")
                            .replace("|", "\\|").replace(".", "\\.").replace("$", "\\$").replace("+", "\\+").trim()
                    );
                }
            }
        }
        return source;
    }
}
