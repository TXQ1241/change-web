package net.sycu.lxp.utils;

import java.io.Serializable;
import java.util.UUID;

/**
 * String工具类
 *
 * @author lxp
 **/
public class StringUtils extends org.apache.commons.lang3.StringUtils implements Serializable {

    /**
     * @return 返回拼接后的字符串
     * @throws
     * @Description: 获取到首字母的大写, 然后拼接
     * @author lxp
     * @method subString
     * params  str : 需要拼接的字符串
     **/
    public static String subString(String str) {
        if (str != null && !"".equals(str)) {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        } else {
            return "";
        }
    }

    /**
     * @return UUID的字符串
     * @throws
     * @Description: 获取到UUID的字符串
     * @author lxp
     * @method getUUID
     * params
     **/
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }
}

