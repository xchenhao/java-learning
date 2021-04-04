package com.test.json;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class JsonDemo2 {


    /**
     * 使用 JsonReader 解析复杂的 JSON 数据
     */
    @Test
    public void parseJSONMessages() {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/test/json/message.json");
        InputStreamReader in = new InputStreamReader(is);
        JsonReader jsonReader = new JsonReader(in);
        ArrayList<Message> list = readerMessageArray(jsonReader);
        for (Message m: list) {
            System.out.println(m);
        }

    }

    // 读取 Message 数组
    private ArrayList<Message> readerMessageArray(JsonReader jsonReader) {
        ArrayList<Message> list = new ArrayList<>();
        try {
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                list.add(readMessage(jsonReader));
            }
            jsonReader.endArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    // 解析一个 Message 对象
    private Message readMessage(JsonReader jsonReader) {
        Message m = new Message();
        try {
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String attrName = jsonReader.nextName();
                if ("id".equals(attrName)) {
                    m.setId(jsonReader.nextLong());
                } else if ("text".equals(attrName)) {
                    m.setText(jsonReader.nextString());
                } else if ("geo".equals(attrName) && jsonReader.peek() != JsonToken.NULL) {
                    m.setGeo(readDoubleArray(jsonReader));
                } else if ("user".equals(attrName)) {
                    m.setUser(readUser(jsonReader));
                } else {
                    jsonReader.skipValue();
                }
            }

            jsonReader.endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return m;
    }

    /**
     * 解析 User 对象
     *
     * @param jsonReader
     * @return
     */
    private User readUser(JsonReader jsonReader) {
        User user = new User();
        try {
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String attrName = jsonReader.nextName();
                if ("name".equals(attrName)) {
                    user.setName(jsonReader.nextString());
                } else if ("followers_count".equals(attrName)) {
                    user.setFlolowersCount(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                }
            }
            jsonReader.endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     * 解析 GEO
     *
     * @param jsonReader
     * @return
     */
    private ArrayList<Double> readDoubleArray(JsonReader jsonReader) {
        ArrayList<Double> list = new ArrayList<>();
        try {
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                list.add(jsonReader.nextDouble());
            }
            jsonReader.endArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }


}
