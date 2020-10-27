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
public class Game extends JFrame implements ActionListener {
    int rows = 4;
    int cols = 4;
    boolean cheatModeEnabled;
    List<Button> buttonList;
    JFrame frame = new JFrame();
    JPanel mainPanel;
    JPanel gridPanel;

    Game(){

        restartGame();
    }

    private void restartGame()
    {
        mainPanel = new JPanel();
        gridPanel = new JPanel();

        mainPanel.setLayout(new BorderLayout());
        gridPanel.setLayout(new GridLayout(rows,cols));
        mainPanel.add(BorderLayout.CENTER, gridPanel);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout());
        JButton scrambleButton = new JButton ("Nytt spel");
        JButton cheatButton = new JButton("Ändra svårighetsgrad");
        cheatButton.addActionListener(this);
        scrambleButton.addActionListener(this);
        menuPanel.add(scrambleButton);
        menuPanel.add(cheatButton);
        mainPanel.add(BorderLayout.NORTH, menuPanel);

        generateGameBoard();

        frame.add(mainPanel);
        frame.setVisible(true);
        frame.setLocation(700, 300);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public void generateGameBoard(){
        this.buttonList = scrambleButtons(rows, cols);
        for (Button button: buttonList) {
            gridPanel.add(button.getjButton());
            button.jButton.addActionListener(this);
        }
    }


    public List<Button> scrambleButtons(int rows, int cols){
        List<Button> buttonList = new ArrayList<>();

        int numOfButtons = rows*cols;
        for (int i = 1; i < numOfButtons; i++) {

            buttonList.add(new Button("" +i));
        }
        buttonList.add(new Button(""));

        if(!cheatModeEnabled)
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

        if(pressedJButton.getText().equals("Nytt spel")){
            mainPanel.removeAll();
            restartGame();
            return;
        }

        if(pressedJButton.getText().equals("Ändra svårighetsgrad")){
            this.cheatModeEnabled = !cheatModeEnabled;
            return;
        }

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

            String temp = pressedButton.jButton.getText();
            pressedButton.jButton.setText(emptyButton.jButton.getText());
            emptyButton.jButton.setText(temp);

            if(checkWinCondition()){
                System.out.println("Du vann!");
            }

        }
        else {
            System.out.println("Fel knapp");
        }
    }

    public boolean checkWinCondition(){
        return buttonList.get(0).jButton.getText().equals("1") && buttonList.get(1).jButton.getText().equals("2") && buttonList.get(2).jButton.getText().equals("3") &&
            buttonList.get(3).jButton.getText().equals("4") && buttonList.get(4).jButton.getText().equals("5") && buttonList.get(5).jButton.getText().equals("6") &&
            buttonList.get(6).jButton.getText().equals("7") && buttonList.get(7).jButton.getText().equals("8") && buttonList.get(8).jButton.getText().equals("9") &&
            buttonList.get(9).jButton.getText().equals("10") && buttonList.get(10).jButton.getText().equals("11") && buttonList.get(11).jButton.getText().equals("12") &&
            buttonList.get(12).jButton.getText().equals("13") && buttonList.get(13).jButton.getText().equals("14") && buttonList.get(14).jButton.getText().equals("15") &&
            buttonList.get(15).jButton.getText().equals("");

    }
}

