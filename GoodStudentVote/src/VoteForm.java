import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;


public class VoteForm extends JFrame implements ActionListener {

    JPanel CheckBoxes=new JPanel();
    JPanel Buttons=new JPanel();
    JButton Confirm=new JButton("确认");
    JButton Exit=new JButton("退出");
    JLabel label1=new JLabel("选票一：");
    JLabel label2=new JLabel("选票二：");
    JLabel label3=new JLabel("选票三：");
    JLabel label4=new JLabel("Your ID:");
    JComboBox comboBox1=new JComboBox();
    JComboBox comboBox2=new JComboBox();
    JComboBox comboBox3=new JComboBox();
    JTextPane IDPane=new JTextPane();

    VoteCounter father;
    public VoteForm(VoteCounter Father){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        father=Father;
        setLayout(new BorderLayout());
        CheckBoxes.setLayout(new GridLayout(4,2));
        add(CheckBoxes,"Center");
        add(Buttons,"South");

        for(int i=0;i<Father.VData.size();i++)
        {
            comboBox1.addItem(Father.VData.get(i));
            comboBox2.addItem(Father.VData.get(i));
            comboBox3.addItem(Father.VData.get(i));
        }

        CheckBoxes.add(label1);
        CheckBoxes.add(comboBox1);
        CheckBoxes.add(label2);
        CheckBoxes.add(comboBox2);
        CheckBoxes.add(label3);
        CheckBoxes.add(comboBox3);
        CheckBoxes.add(label4);
        CheckBoxes.add(IDPane);

        Buttons.add(Confirm);Confirm.addActionListener(this);
        Buttons.add(Exit);
        Exit.addActionListener(this);
        pack();
        setSize(400,150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==Confirm){
            int index1=comboBox1.getSelectedIndex();int index2=comboBox2.getSelectedIndex();int index3=comboBox3.getSelectedIndex();
            if(index1==index2&&index1==index3&&index2==index3)
            {
                JOptionPane.showMessageDialog(this,"一个候选人仅能投一票，否则作为废票!");
                father.SpoiltVote+=1;
                father.invalidvotes.setText("废票："+father.SpoiltVote);
                this.dispose();
                return;
            }
            int temp1=(int)father.VData.get(index1).get(1);
            father.VData.elementAt(index1).remove(1);father.VData.elementAt(index1).add(temp1+1);
            int temp2=(int)father.VData.get(index2).get(1);
            father.VData.elementAt(index2).remove(1);father.VData.elementAt(index2).add(temp2+1);
            int temp3=(int)father.VData.get(index3).get(1);
            father.VData.elementAt(index3).remove(1);father.VData.elementAt(index3).add(temp3+1);

            //Sort(father.VData);
            father.model.setDataVector(father.VData,father.Vcolumns);
            this.dispose();
        }
        else if(e.getSource()==Exit){
            this.dispose();
        }
    }

    void Sort(Vector<Vector> Data)
    {
        Vector temp;
        for(int i=0;i<Data.size();i++) {
            for (int j = 0; j < Data.size() - i; j++) {
                temp = Data.get(j);
                //Integer c1=Integer.parseInt(temp.get(1));
                //if(temp.get(1)<Data.get(j+1).get(1)){

            }


        }
    }

}
