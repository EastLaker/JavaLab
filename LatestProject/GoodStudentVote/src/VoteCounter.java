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
import java.util.List;
import java.util.Map.Entry;

public class VoteCounter extends JFrame implements ActionListener{

    JPanel Rules=new JPanel();
    JPanel Buttons=new JPanel();
    int SpoiltVote;
    int SuccessVote;
    Login parent;
    JLabel label0=new JLabel("投票规则",JLabel.CENTER);
    JTextArea rules=new JTextArea(2,50);
    JButton AddCandidate=new JButton("新建候选人");
    JButton vote=new JButton("投票");
    JButton DeleteCandidate=new JButton("删除候选人");
    JLabel invalidvotes;
    JLabel validvotes;
    JLabel abstentionvotes;
    JScrollPane Table = new JScrollPane();
    Vector Vcolumns =new Vector();
    Vector<Vector> VData =new Vector();

    Map<String ,Integer> candidate=new HashMap<>();
    DefaultTableModel model = new DefaultTableModel();
    JTable table;


    Menu File=new Menu("File");MenuBar bar=new MenuBar();
    private MenuItem Open=new MenuItem("Open");
    private MenuItem Save=new MenuItem("Save");

    public VoteCounter(Login parent,boolean admin){
        this.parent=parent;
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

        this.candidate=parent.candidate;
        this.VData=parent.VData;
        
        this.SpoiltVote=parent.SpoiltVote;
        this.SuccessVote=parent.SuccessVote;
        invalidvotes=new JLabel("废票："+SpoiltVote);
        validvotes=new JLabel("有效票："+SuccessVote);
        abstentionvotes=new JLabel("弃权票："+parent.abstentionvote);

        Vcolumns.add("姓名");Vcolumns.add("票数");
        model.setDataVector(VData,Vcolumns);
        table= new JTable(model){
            public boolean isCellEditable(int row, int column) { return false;}//表格不允许被编辑
        };
        Table.setViewportView(table);
        Buttons.add(validvotes);
        Buttons.add(AddCandidate);AddCandidate.addActionListener(this);
        Buttons.add(vote);vote.addActionListener(this);
        Buttons.add(DeleteCandidate);DeleteCandidate.addActionListener(this);
        Buttons.add(invalidvotes);Buttons.add(abstentionvotes);

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
    public void Sort() {
		candidate=sortMapByValue(candidate);
		VData.removeAllElements();
		for(String key:candidate.keySet()){
            Vector data=new Vector();
            data.add(key);data.add(candidate.get(key));
            VData.add(data);
        }
		model.setDataVector(VData,Vcolumns);
	}
    public static Map<String, Integer> sortMapByValue(Map<String, Integer> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        List<Map.Entry<String, Integer>> entryList = 
        		new ArrayList<Map.Entry<String, Integer>>(oriMap.entrySet());
        //将map转换成list
        Collections.sort(entryList, new MapValueComparator());

        Iterator<Map.Entry<String, Integer>> iter = entryList.iterator();
        Map.Entry<String, Integer> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
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
                Login temp=(Login)tableIn.readObject();
                parent.VData=temp.VData;
                VData=parent.VData;
                parent.SpoiltVote=temp.SpoiltVote;
                SpoiltVote=parent.SpoiltVote;
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
                tableOut.writeObject(parent);
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

            //Vector temp=new Vector();
            //temp.add(name);temp.add(0);
            //VData.add(temp);
            candidate.put(name, 0);
            parent.candidate.put(name,0);
            Sort();
        }
        else if(e.getSource()==DeleteCandidate){
            int row=table.getSelectedRow();
            String temp=(String) VData.get(row).get(0);
            candidate.remove(temp);

            Sort();
        }
    }


}
class MapValueComparator implements Comparator<Map.Entry<String, Integer>> {
    public int compare(Entry<String, Integer> me1, Entry<String, Integer> me2) {

        return -me1.getValue().compareTo(me2.getValue());
    }
}

