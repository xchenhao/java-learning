// 具体的观察者
public class User implements Observer{
    private String name;
    public User(String name){
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println("["+ name + "]收到消息：" + message);
    }
}
