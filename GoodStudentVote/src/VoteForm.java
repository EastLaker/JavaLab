import java.awt.*;
import java.awt.event.*;

public class VoteForm extends Frame implements ActionListener {
    public class WindowCloser extends WindowAdapter
    {
        public void windowClosing(WindowEvent we)
        {
            System.exit(0);
        }
    }

    Panel Buttons=new Panel();
    Button Confirm=new Button("Confirm");

    public VoteForm(){
        setLayout(new BorderLayout());
        add(Buttons,"South");

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
