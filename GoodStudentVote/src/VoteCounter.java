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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class VoteCounter extends JFrame implements ActionListener{

    JPanel Rules=new JPanel();
    JPanel Buttons=new JPanel();

    JLabel label0=new JLabel("投票规则",JLabel.CENTER);
    JTextArea rules=new JTextArea(2,50);
    JButton AddCandidate=new JButton("新建候选人");
    JButton vote=new JButton("投票");
    JButton DeleteCandidate=new JButton("删除候选人");

    JScrollPane Table = new JScrollPane();
    Vector Vcolumns =new Vector();
    Vector<Vector> VData =new Vector();
    //Map<String ,Integer> candidate=new HashMap<>();
    DefaultTableModel model = new DefaultTableModel();
    JTable table;

    Menu File=new Menu("File");MenuBar bar=new MenuBar();
    private MenuItem Open=new MenuItem("Open");
    private MenuItem Save=new MenuItem("Save");

    public VoteCounter(Login parent,boolean admin){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setFont(new Font("SansSerif", Font.PLAIN, 18));
        setMenuBar(bar);
        setLayout(new BorderLayout());
        add(Rules,"North");
        Rules.setLayout(new BorderLayout());
        add(Table,"Center");
        add(Buttons,"South");

        bar.add(File);
        File.add(Open);Open.addActionListener(this);
        File.add(Save);Save.addActionListener(this);

        Rules.add(label0,"North");
        rules.setText("每个人给三个候选人投票....etc");
        rules.setEditable(false);
        Rules.add(rules,"Center");


        this.VData=parent.VData;


        Vcolumns.add("姓名");Vcolumns.add("票数");
        model.setDataVector(VData,Vcolumns);
        table= new JTable(model){
            public boolean isCellEditable(int row, int column) { return false;}//表格不允许被编辑
        };
        Table.setViewportView(table);
        Buttons.add(AddCandidate);AddCandidate.addActionListener(this);
        Buttons.add(vote);vote.addActionListener(this);
        Buttons.add(DeleteCandidate);DeleteCandidate.addActionListener(this);
        /*for(int i=0;i<20;i++) {
            candidate.put(getRandomString(10,"Name"),0);
        }*/
        //addWindowListener(new WindowCloser());
        pack();
        setSize(this.getPreferredSize());
        setVisible(true);
        vote.setVisible(false);
        if(!admin){
            AddCandidate.setVisible(false);
            DeleteCandidate.setVisible(false);
            vote.setVisible(true);
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==vote){
            VoteForm voteForm=new VoteForm(this);
        }
        else if(e.getSource()==Open){
            try{
                FileDialog fd=new FileDialog(this,"Open",FileDialog.LOAD);
                fd.setVisible(true);
                //fd.setDirectory(".");

                ObjectInputStream tableIn=new ObjectInputStream(new FileInputStream(fd.getDirectory()+fd.getFile()));
                fd.dispose();
                VData=(Vector<Vector>)tableIn.readObject();
                tableIn.close();
                model.setDataVector(VData,Vcolumns);
            }
            catch (IOException ioe){
                System.out.println(ioe);
            }
            catch (ClassNotFoundException cnfe){
                System.out.println(cnfe);
            }
        }
        else if(e.getSource()==Save) {
            try {
                FileDialog fd = new FileDialog(this, "Open", FileDialog.SAVE);
                fd.setFile(".dat");
                fd.setVisible(true);
                ObjectOutputStream tableOut = new ObjectOutputStream(new FileOutputStream(fd.getDirectory() + fd.getFile()));
                tableOut.writeObject(VData);
                fd.dispose();
                tableOut.close();
            } catch (IOException ioe) {
                System.out.println(ioe);
            }
        }
        else if(e.getSource()==AddCandidate){
            String name=JOptionPane.showInputDialog("请输入候选人姓名：");
            if(name==""||name==null)
                return;

            Vector temp=new Vector();
            temp.add(name);temp.add(0);
            VData.add(temp);
            model.setDataVector(VData,Vcolumns);
        }
        else if(e.getSource()==DeleteCandidate){
            int row=table.getSelectedRow();
            VData.remove(row);
            model.setDataVector(VData,Vcolumns);
        }
    }

    //length用户要求产生字符串的长度
    public static String getRandomString(int length,String type){
        String str;
        int bound;
        if(!type.contentEquals("phone")){str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";bound=52;}
        else {str="0123456789";bound=10;}
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(bound);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}

