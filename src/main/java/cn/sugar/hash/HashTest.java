package cn.sugar.hash;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 创建时间:   2018/5/12
 * ZhangQing
 * 功能描述:已知一个 HashMap<Integer，User>集合，
 * User 有 name（String）和 age（int）属性。请写一个方法实现对 HashMap 的排序功能，
 * 该方法接收 HashMap<Integer，User>为形参，返回类型为 HashMap<Integer，User>，
 * 要求对 HashMap 中的 User 的 age 倒序进行排序。排序时 key=value 键值对不得拆散
 */
public class HashTest {
    public static void main(String[] args) {

        Map<Integer, User> users = new HashMap<>();
        users.put(1, new User("zhangsan", 23));
        users.put(2, new User("lisi", 24));
        users.put(3, new User("wangwu", 25));

        System.out.println(users);
        HashMap<Integer, User> sortHashMap = sortedHashMap(users);
        System.out.println(sortHashMap);
    }

    private static HashMap<Integer, User> sortedHashMap(Map<Integer, User> users) {

        // 获取键值对对象
        Set<Map.Entry<Integer, User>> set = users.entrySet();
        //将 set 集合转为 List 集合，为什么，为了使用工具类的排序方法
        List<Map.Entry<Integer, User>> list = new ArrayList<>();

        for (Map.Entry<Integer, User> entry : set) {
            list.add(entry);
        }
        // 使用 Collections 集合工具类对 list 进行排序，排序规则使用匿名内部类来实现
        Collections.sort(list, new Comparator<Map.Entry<Integer, User>>() {
            @Override
            public int compare(Map.Entry<Integer, User> o1, Map.Entry<Integer, User> o2) {
                //按照要求根据 User 的 age 的倒序进行排
                return o2.getValue().getAge() - o1.getValue().getAge();
            }
        });
        //创建一个新的有序的 HashMap 子类的集合
        LinkedHashMap<Integer, User> linkedHashMap = new LinkedHashMap<>();
        //将 List 中的数据存储在 LinkedHashMap 中
        for (Map.Entry<Integer, User> entry : list) {
            linkedHashMap.put(entry.getKey(), entry.getValue());
        }
        //返回结果
        return linkedHashMap;
    }
}