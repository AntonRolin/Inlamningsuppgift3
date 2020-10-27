import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by: Anton Rolin
 * Date: 26/10/2020
 * Time: 16:18
 * Project: Inlamningsuppgift_3
 * Copyright: MIT
 */
public class GameBoard extends JFrame implements ActionListener {
    int rows = 4;
    int cols = 4;
    List<Button> buttonList;

    GameBoard(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(rows,cols));

        buttonList = scrambleButtons(rows, cols);
        for (Button button: buttonList) {
            panel.add(button.getjButton());
            button.jButton.addActionListener(this);
        }

        frame.add(panel);
        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public List<Button> scrambleButtons(int rows, int cols){
        List<Button> buttonList = new ArrayList<>();

        int numOfButtons = rows*cols;
        for (int i = 1; i < numOfButtons; i++) {

            buttonList.add(new Button("" +i));
        }
        buttonList.add(new Button(""));
        Collections.shuffle(buttonList);

        int col = 1;
        int row = 1;
        for (Button button: buttonList) {

           button.setCol(col);
           button.setRow(row);
           if(col<cols)
               col++;
           else {
               col = 1;
               row++;
           }

        }

        return buttonList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton pressedJButton = (JButton) e.getSource();
        String text = pressedJButton.getText();
        System.out.println(text);

        Button emptyButton = null;
        Button pressedButton = null;

        if (pressedJButton.getText().equals(""))
            return;

        for (Button b: buttonList) {
            if(b.jButton.getText().equals(""))
                emptyButton = b;
            if(b.jButton.getText().equals(pressedJButton.getText())){
                pressedButton = b;
            }
        }
        System.out.println(emptyButton.jButton.getText());
        System.out.println(pressedButton.jButton.getText());
        System.out.println(emptyButton.getCol());
        System.out.println(emptyButton.getRow());
        System.out.println(pressedButton.getCol());
        System.out.println(pressedButton.getRow());

        if(((pressedButton.getCol() == emptyButton.getCol()) && (pressedButton.getRow() == emptyButton.getRow()+1 || pressedButton.getRow() == emptyButton.getRow()-1))
            || (pressedButton.getRow() == emptyButton.getRow()) && (pressedButton.getCol() == emptyButton.getCol()+1 || pressedButton.getCol() == emptyButton.getCol()-1)){

            System.out.println("Rätt knapp");


        }
        else
            System.out.println("Fel knapp");
        }
    }

