package com.test.json;

import com.google.gson.stream.JsonReader;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class JsonDemo {

    public static void main(String[] args) {

    }

    /**
     * 解析一个 JSON 数组
     */
    @Test
    public void parseJSONNames() {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/test/json/names.json");
        InputStreamReader isr = new InputStreamReader(is);
        JsonReader reader = new JsonReader(isr);  // JSON 的解析器

        ArrayList<Name> list = new ArrayList<>();
        try {
            reader.beginArray();  // 开始解析数组
            while (reader.hasNext()) {
                list.add(parseName(reader));
            }

            reader.endArray();  // 结束解析数组
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.toString(list.toArray()));
    }

    // 解析对象 Name
    private Name parseName(JsonReader jsonReader) {
        Name name = null;
        try {
            jsonReader.beginObject();  // 开始解析对象
            name = new Name();

            while (jsonReader.hasNext()) {
                String attrName = jsonReader.nextName();
                if ("firstName".equals(attrName)) {
                    name.setFirstName(jsonReader.nextString());
                } else if ("lastName".equals(attrName)) {
                    name.setLastName(jsonReader.nextString());
                } else if ("email".equals(attrName)) {
                    name.setEmail(jsonReader.nextString());
                }
            }

            jsonReader.endObject();  // 结束解析对象
        } catch (IOException e) {
            e.printStackTrace();
        }


        return name;
    }
}
