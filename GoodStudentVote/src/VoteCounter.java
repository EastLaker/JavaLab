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

import java.awt.*;
import java.awt.event.*;

public class VoteCounter extends Frame implements ActionListener{
    public class WindowCloser extends WindowAdapter
    {
        public void windowClosing(WindowEvent we)
        {
            System.exit(0);
        }
    }
    Panel Rules=new Panel();
    Panel Buttons=new Panel();
    Panel Table=new Panel();
    Label label0=new Label("Rules of Vote",Label.CENTER);
    TextArea rules=new TextArea(5,50);
    List candidate=new List();
    Button vote=new Button("vote");

    public VoteCounter(){
        setLayout(new BorderLayout());
        add(Rules,"North");
        Rules.setLayout(new BorderLayout());
        add(Table,"Center");
        Table.setLayout(new GridBagLayout());
        add(Buttons,"South");


        Rules.add(label0,"North");
        rules.setText("每个人给三个候选人投票....etc");
        rules.setEditable(false);
        Rules.add(rules,"Center");
        Table.add(candidate);
        Buttons.add(vote);vote.addActionListener(this);

        addWindowListener(new WindowCloser());
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==vote){
            VoteForm voteForm=new VoteForm();
        }
    }
    public static void main(String args[])
    {
        VoteCounter counter0=new VoteCounter();
    }
}
