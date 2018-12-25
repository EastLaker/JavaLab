import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.*;

//用户登陆界面
//三个按钮，以管理员身份登陆和用户登陆
//退出
public class Login extends JFrame implements ActionListener, Serializable {
    Vector<Vector> VData =new Vector();
    Map<String ,Integer> candidate=new HashMap<>();
    int SpoiltVote=0;
    int SuccessVote=0;
    int abstentionvote=0;
    JPanel Buttons=new JPanel();
    JButton loginAsAdmin=new JButton("以管理员身份登陆");
    JButton loginAsUser=new JButton("以用户身份登陆");
    JButton exit=new JButton("退出");
    public Login()
    {
        setUp();
        setSize(350,150);
        setTitle("Vote Manager");
        this.setLocationRelativeTo(getOwner());
    }
    public void setUp() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(3, 1));
        loginAsAdmin.addActionListener(this);
        loginAsUser.addActionListener(this);
        exit.addActionListener(this);
        JTextArea statements=new JTextArea("管理员可以对名单进行增删操作，用户只可以投票");
        statements.setEditable(false);
        add(Buttons,"Center");
        add(statements,"North");
        Buttons.add(loginAsAdmin,"Center");
        Buttons.add(loginAsUser,"Center");
        Buttons.add(exit,"Center");
        setVisible(true);

        pack();
        //candidate.put("xiaoming",0);
        //candidate.put("Jack Ma",0);
        //candidate.put("Pony Ma",0);
        //candidate.put("wanghong",0);
        for(String key:candidate.keySet())
        {
            Vector data=new Vector();
            data.add(key);data.add(candidate.get(key));
            VData.add(data);
        }
    }

    //事件处理
    public void actionPerformed(ActionEvent e) {
        //退出
        if(e.getSource()==exit)
            System.exit(0);
            //管理员
        else if(e.getSource()==loginAsAdmin)
        {
            VoteCounter voteCounter=new VoteCounter(this, true);
        }
        //用户
        else if (e.getSource()==loginAsUser)
        {
            VoteCounter voteForm=new VoteCounter(this,false);
        }
    }
    public static void main(String args[])
    {
        Login login=new Login();
    }
}
