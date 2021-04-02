import java.awt.*;
import java.awt.event.*;

public class MyFrame extends Frame implements ActionListener {

    public static void main(String[] args) {
        new MyFrame();
    }

    public MyFrame() {
        this.setSize(600, 400);
        this.setTitle("我的第一个 GUI 窗体");

        Button button = new Button("点我一下，有惊喜");
        button.addActionListener(this);

        FlowLayout flowLayout = new FlowLayout();
        this.setLayout(flowLayout);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });

        this.add(button);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("惊喜来了，获得 100 元大红包");
    }
}
