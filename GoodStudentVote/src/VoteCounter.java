/*
* 一、实验目的
* 了解图形用户界面基本组件窗口、按钮、文本框、选择框、滚动条等的使用方法。
* 并能熟练运用Java 程序语法结构、选择结构和循环结构等语法的程序设计方法。
二、实验要求
* 开发一个“简单、实用”的基于Applet的三好学生投票管理系统。本系统运用到了窗口布局、按钮事件的触发和字符串分析器等相关知识，
* 界面简洁、清爽，操作简单，用户可以自定义候选人 （也可以读入候选人名单文件），支持多次投票，能够自动统计出投票数、废票数、
* 弃权票数和各个候选人的得票数，并按得票数为候选人排序显示。
*
* */

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import java.util.Map;
import java.util.HashMap;

public class VoteCounter extends JFrame implements ActionListener{
    public class WindowCloser extends WindowAdapter
    {
        public void windowClosing(WindowEvent we)
        {
            System.exit(0);
        }
    }

    protected void makebutton(String name,
                              GridBagLayout gridbag,
                              GridBagConstraints c) {
        JButton button = new JButton(name);
        gridbag.setConstraints(button, c);
        add(button);
    }

    JPanel Rules=new JPanel();
    JPanel Buttons=new JPanel();
    JPanel Table=new JPanel();
    JLabel label0=new JLabel("Rules of Vote",JLabel.CENTER);
    JTextArea rules=new JTextArea(5,50);
    //List candidate=new List(10,false);
    JButton vote=new JButton("vote");

    Map<String,Integer> candidate=new HashMap<>();
    JTable table;

    public VoteCounter(){
        setFont(new Font("SansSerif", Font.PLAIN, 16));
        GridBagConstraints c=new GridBagConstraints();
        GridBagLayout gridBagLayout=new GridBagLayout();

        setLayout(new BorderLayout());
        add(Rules,"North");
        Rules.setLayout(new BorderLayout());
        add(Table,"Center");
        Table.setLayout(new GridBagLayout());
        add(Buttons,"South");


        TableModel tableModel;
        Rules.add(label0,"North");
        rules.setText("每个人给三个候选人投票....etc");
        rules.setEditable(false);
        Rules.add(rules,"Center");
        //Table.add(candidate);
        Buttons.add(vote);vote.addActionListener(this);
        for(int i=0;i<20;i++) {
            candidate.put("xiao ming",0);
        }
        addWindowListener(new WindowCloser());
        pack();
        setSize(this.getPreferredSize());
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==vote){
            VoteForm voteForm=new VoteForm(this);
        }
    }
    public static void main(String args[])
    {
        VoteCounter counter0=new VoteCounter();
    }
}
