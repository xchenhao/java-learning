package com.test.generic;

/**
 * 泛型类
 * @param <T> 参数化类型，在实际使用时才会指定具体的类型
 *           泛型只作用于编译期检查，在编译后会被擦除掉
 */
public class Node<T> {

    private T data;

    public Node(){}

    public Node(T data){
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
