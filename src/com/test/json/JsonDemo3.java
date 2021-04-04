package com.test.json;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JsonDemo3 {

    @Test
    public void createJSON() {
        List<Name> list = new ArrayList<>();

        list.add(new Name("chen", "hao", "121212@163.com"));
        list.add(new Name("ma", "jack", "mayun@qq.com"));
        JsonArray array = new JsonArray();
        for (Name n: list) {
            JsonObject obj = new JsonObject();
            obj.addProperty("firstName", n.getFirstName());
            obj.addProperty("lastName", n.getLastName());
            obj.addProperty("email", n.getEmail());

            array.add(obj);
        }
        System.out.println(array.toString());
    }

    /**
     * 把一个 JSON 对象转换成 Java 对象，或把 Java 对象转换成 JSON 对象
     */
    @Test
    public void gson1() {
        Gson gson = new Gson();

        InputStream is = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("com/test/json/name.json");
        InputStreamReader in = new InputStreamReader(is);
        Name name = gson.fromJson(in, Name.class);
        System.out.println(name);

        String json = gson.toJson(name);
        System.out.println(json);
    }

    /**
     * 把一组 JSON 对象转换成 Java 对象集合，或者把 Java 对象集合转换成 JSON 数组
     */
    @Test
    public void gson2() {
        Gson gson = new Gson();

        InputStream is = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("com/test/json/names.json");
        InputStreamReader in = new InputStreamReader(is);

        TypeToken<List<Name>> typeToken = new TypeToken<List<Name>>() {
        };
        List<Name> list = gson.fromJson(in, typeToken.getType());
        System.out.println(list);

        String json = gson.toJson(list, typeToken.getType());
        System.out.println(json);

        // 如果需要处理哪些字段不要，哪些要，则需要用自行解析（见 JsonDemo2）
    }

   // class MyTypeToken extends TypeToken<List<Name>> {}
}
