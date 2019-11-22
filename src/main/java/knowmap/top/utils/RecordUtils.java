package knowmap.top.utils;

import com.jfinal.plugin.activerecord.Record;
import knowmap.top.managers.userAccount.entity.UserAccount;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;

public class RecordUtils {

    private static volatile RecordUtils singleInstance = null;

    private RecordUtils(){}

    public static RecordUtils getInstance() {
        if (singleInstance == null) {
            synchronized (RecordUtils.class) {
                if (singleInstance == null) {
                    singleInstance = new RecordUtils();
                }
            }
        }

        return singleInstance;
    }


    // test
    public static void main(String[] args) {
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername("jp").
                setBirthday(new Timestamp(System.currentTimeMillis())).
                setEmail("1257196064@qq.com").setCreateTime(new Timestamp(System.currentTimeMillis()));

        // 获取record
        Record record = RecordUtils.getInstance().objToRecord(userAccount, UserAccount.class);

        Arrays.stream(record.getColumnNames()).forEach(name -> {
            System.out.println(name + " : "  + record.getObject(name));
        });

        System.out.println("===========================================");

        UserAccount userAccount1 = RecordUtils.getInstance().recordToObj(record, UserAccount.class);
        System.out.println(userAccount1);


    }

    /**
     *
     * @param object 要转化的对象
     * @param clazz 要转化的类型
     * @param <T>
     * @return Record
     */
    public <T> Record objToRecord(Object object, Class<T> clazz) {
        if (object == null || clazz == null) {
            throw new RuntimeException("params is invalid");
        }

        T obj = (T)object;

        Field[] fields = obj.getClass().getDeclaredFields();
        Record record = new Record();
        Arrays.stream(fields).forEach(field -> {
            try {
                field.setAccessible(true);
                Object value = field.get(obj);
                if (value != null) {
                    String name = field.getName();
                    // 驼峰转下划线 A-Z：65-90 a-z：97-122
                    StringBuilder sb = new StringBuilder();
                    for (char c : name.toCharArray()) {
                        if (c >= 65 && c <= 90) {
                            sb.append("_").append(Character.toLowerCase(c));
                        } else {
                            sb.append(c);
                        }
                    }
                    record.set(sb.toString(), value);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        });


        return record;
    }


    /**
     *
     * @param record 数据库获取得记录
     * @param clazz 转化的类型
     * @param <T>
     * @return
     */
    public <T> T recordToObj(Record record, Class<T> clazz) {
        if (record == null || clazz == null) {
            return null;
        }

        T obj;
        try {
            // 根据类名实例化对象
            obj = clazz.newInstance();

            // 获取所有方法名字
            Method[] methods = obj.getClass().getMethods();
            for (String key : record.getColumnNames()) {
                // table -> obj 驼峰转换
                if (key != null && !"".equals(key)) {
                    StringBuilder sb = new StringBuilder();
                    boolean flag = false;
                    for (Character c : key.toCharArray()) {
                        if (c == '_') {
                            flag = true;
                        } else if (flag) {
                            c = Character.toUpperCase(c);
                            sb.append(c);
                            flag = false;
                        } else {
                            sb.append(c);
                        }
                    }

                    // 将属性首字符大写
                    sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
                    for (Method m : methods) {
                        //判断方法名是否匹配
                        if (record.getObject(key) != null && m.getName().contains("set" + sb.toString())) {
                            //执行方法，传入值
                            m.invoke(obj, record.getObject(key));
                            break;
                        }
                    }
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (SecurityException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return obj;
    }
}
