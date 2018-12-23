import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class VoteForm extends JFrame implements ActionListener {
    public class WindowCloser extends WindowAdapter
    {
        public void windowClosing(WindowEvent we)
        {
            System.exit(0);
        }
    }

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

    public VoteForm(VoteCounter Father){
        setLayout(new BorderLayout());
        CheckBoxes.setLayout(new GridLayout(4,2));
        add(CheckBoxes,"Center");
        add(Buttons,"South");

        //comboBox1.addItem(Father.VData);
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
        addWindowListener(new WindowCloser());
        pack();
        setSize(400,150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==Confirm){

            this.dispose();
        }
        else if(e.getSource()==Exit){
            this.dispose();
        }
    }

}
