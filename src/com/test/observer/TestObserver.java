package com.test.observer;

//import org.junit.Test;
//
//public class TestObserver {
//
//    @Test
//    public void testOb(){
//
//    }
//}

public class TestObserver {
    public static void main(String[] args) {
        Message message = new Message();
        User user1 = new User("lily");
        User user2 = new User("tom");
        User user3 = new User("vince");
        message.registerObserver(user1);
        message.registerObserver(user2);
        message.registerObserver(user3);

        message.setMessage("我来了");

        message.removeObserver(user1);
        message.setMessage("哈哈哈");
    }




}
