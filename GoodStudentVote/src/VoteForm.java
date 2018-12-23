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
    JButton Confirm=new JButton("Confirm");
    JLabel label1=new JLabel("选票一：");
    JLabel label2=new JLabel("选票二：");
    JLabel label3=new JLabel("选票三：");
    JComboBox comboBox1=new JComboBox();
    JComboBox comboBox2=new JComboBox();
    JComboBox comboBox3=new JComboBox();


    public VoteForm(VoteCounter Father){
        setLayout(new BorderLayout());
        CheckBoxes.setLayout(new GridLayout(3,2));
        add(CheckBoxes,"Center");
        add(Buttons,"South");

        CheckBoxes.add(label1);
        CheckBoxes.add(comboBox1);
        CheckBoxes.add(label2);
        CheckBoxes.add(comboBox2);
        CheckBoxes.add(label3);
        CheckBoxes.add(comboBox3);

        Buttons.add(Confirm);Confirm.addActionListener(this);

        addWindowListener(new WindowCloser());
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==Confirm){

            this.dispose();
        }
    }

}
