package com.test.interfacecallback;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame2 extends Frame {
    private TextField textField = new TextField(20);
    private Button btn = new Button("付款");
    public Frame2(){
        this.setSize(400, 200);
        this.setLayout(new FlowLayout());
        this.add(btn);
        this.add(textField);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String money = textField.getText();
                moneyListener.setMoney(money);
            }
        });
        this.setVisible(true);
    }

    private MoneyListener moneyListener;

    public void setMoneyListener(MoneyListener moneyListener){
        this.moneyListener = moneyListener;
    }

    // 接口回调
    // 当一个对象需要给外部对象提供数据时，不直接依赖外部对象
    // 可以定义一个内部接口把数据通过接口传递出去
    // 所有外部对象需要这个数据时，就实现这个接口，
    // 这样的好处是传递数据的对象不直接依赖接收数据的对象
    // （降低耦合性）
    public static interface MoneyListener {
        public void setMoney(String money);
    }
}
