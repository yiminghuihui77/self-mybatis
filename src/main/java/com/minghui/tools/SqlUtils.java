package com.minghui.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Sql工具类
 *
 * @author minghui.y BG358486
 * @create 2019-05-07 15:35
 **/
public class SqlUtils {

    /**
     * 匹配SQL和方法参数
     * @param sql
     * @param map 参数名和参数值匹配的map
     * @return 按照SQL参数顺序返回的数组
     */
    public static Object[] getParamFormInsertSql(String sql, Map<Object, Object> map) {
        int startIndex = sql.indexOf("values") + 6;
        int endindex = sql.length();
        String[] params =  sql.substring(startIndex, endindex).replace("(", "")
                .replace(")", "").replace("#{", "")
                .replace("}", "").split(",");

        List<Object> result = new ArrayList<>();
        for (int i = 0; i < params.length;i++) {
            result.add(map.get(params[i].trim()));
        }

        return result.toArray();
    }

    /**
     * 将#{xx}替换成?
     * @param sql
     * @return
     */
    public static String getNewSql(String sql, String[] paramNames) {
        for (int i = 0;i < paramNames.length; i++) {
            sql = sql.replace("#{" + paramNames[i] + "}", "?");
        }
        return sql;
    }

    public static void main(String[] args) {
        //测试获取sql中的参数
        String sql = "insert into account(id, name, account) values(#{id}, #{name}, #{account})";
//        Map<Object, Object> map = new HashMap<>();
//        map.put("id", 1);
//        map.put("name", "灰灰");
//        map.put("account", 900);
//        Object[] result = getParamFormInsertSql(sql, map);
//        for (int i = 0;i < result.length;i++) {
//            System.out.println(result[i]);
//        }

        //测试替换#{}为?
        String[] names = new String[]{"name","id", "account"};
        System.out.println(getNewSql(sql, names));
    }
}
