import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.metal.*;
import java.awt.event.*;
import javax.swing.text.*;
import java.awt.print.PrinterException;
import java.io.*;

class Notepad extends JFrame implements ActionListener{
    JTextArea t;
    JFrame f;

    Notepad() {
        //Initializing the frame and TextArea.
        f = new JFrame("NOTEPAD");
        t = new JTextArea();

        //Initializing MenuBar and alll individual Menu.
        JMenuBar menu = new JMenuBar();

        //Initializing menu item
        JMenu file = new JMenu("File");
        JMenuItem f1 = new JMenuItem("New");
        JMenuItem f2 = new JMenuItem("Open");
        JMenuItem f3 = new JMenuItem("Save");
        JMenuItem f4 = new JMenuItem("Print");

        //Adding ActionListener to Individual what action performed by the File MenuItem.
        f1.addActionListener(this);
        f2.addActionListener(this);
        f3.addActionListener(this);
        f4.addActionListener(this);

        //add MenuItem into the File MenuBar.
        file.add(f1);
        file.add(f2);
        file.add(f3);
        file.add(f4);

        //Initializing the EditMenu and all individual Edit MenuItem
        JMenu edit = new JMenu("Edit");
        JMenuItem e1 = new JMenuItem("Cut");
        JMenuItem e2 = new JMenuItem("Copy");
        JMenuItem e3 = new JMenuItem("Paste");

        //Add ActionListener to Individual what action performed by the Edit MenuItem.
        e1.addActionListener(this);
        e2.addActionListener(this);
        e3.addActionListener(this);

        //Add MenuItem into the EditBar.
        edit.add(e1);
        edit.add(e2);
        edit.add(e3);

        //Intializing the CloseBar.
        JMenuItem close = new JMenuItem("Close");

        //Add item into menuBar.
        menu.add(file);
        menu.add(edit);
        menu.add(close);

        f.setJMenuBar(menu);   //Add the MenuBar in the Frame.
        f.add(t);       //Add the TextArea in the Frame.
        f.setSize(800 , 500);  //Set the size of frame.
        f.show();     //For Frame is visible(by default it is not visible).
    }

    //functionallity performed.
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        //Processing the string s.
        switch (s)
        {
            case "New":
                t.setText("");  //new blank TextArea is showed.
                break;

            case "Open":
                //Select the file from my computer.
                JFileChooser j = new JFileChooser("/Users/hritikupadhyay/Documents:");

                //Initializing the OpenDialogBox.
                int r = j.showOpenDialog(null);

                //If the user selects a file.
                if(r == JFileChooser.APPROVE_OPTION){
                    //Extracting the Absolute Path of the Selected File.
                    File fi = new File(j.getSelectedFile().getAbsolutePath());

                    String s1 , s2;
                    try {
                        //Place the pointer at the Starting of the file.
                        FileReader fr = new FileReader(fi);  //Read the file for a particular path(fi).

                        //use BufferReader to read line by line.
                        BufferedReader br = new BufferedReader(fr);

                        //Storing the first line in s1.
                        s1 = br.readLine();

                        //appending subsequent lines till end of file is reached.
                        while((s2 = br.readLine()) != null){
                            s1 = s1 + "\n" + s2;
                        }

                        t.setText(s1);

                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(f,"Operation Cancelled");
                }

                break;

            case "Save":
                //Select the file from my computer.
                JFileChooser jc = new JFileChooser("/Users/hritikupadhyay/Documents:");

                //Initializing the OpenDialogBox.
                int rc = jc.showSaveDialog(null);

                //If the user selects a file.
                if(rc == JFileChooser.APPROVE_OPTION){
                    //Extracting the Absolute Path of the Selected File.
                    File fi = new File(jc.getSelectedFile().getAbsolutePath());

                    try {
                        //Place the pointer at the Starting of the file.
                        FileWriter fr = new FileWriter(fi);  //Read the file for a particular path(fi).

                        //use BufferReader to read line by line.
                        BufferedWriter br = new BufferedWriter(fr);

                        br.write(t.getText());
                        br.flush();
                        br.close();

                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(f,"Operation Cancelled");
                }

                break;

            case "Print":
                try {
                    t.print();
                } catch (PrinterException ex) {
                    throw new RuntimeException(ex);
                }
                break;

            case "Copy":
                t.copy();
                break;

            case "Cut":
                t.cut();
                break;

            case "Paste":
                t.paste();
                break;

            case "Close":
                f.setVisible(false);
                break;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Notepad note = new Notepad();
    }
}