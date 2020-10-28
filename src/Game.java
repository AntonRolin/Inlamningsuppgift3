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
    private int rows = 4;
    private int cols = 4;
    private boolean cheatModeEnabled;
    private List<Button> buttonList;
    private JFrame frame = new JFrame();
    private JPanel mainPanel;
    private JPanel gridPanel;

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

    private void generateGameBoard(){
        this.buttonList = scrambleButtons(rows, cols);
        for (Button button: buttonList) {
            gridPanel.add(button.getjButton());
            button.jButton.addActionListener(this);
        }
    }


    private List<Button> scrambleButtons(int rows, int cols){
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
    public void actionPerformed(ActionEvent e) throws NullPointerException{
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

        try {
            if (((pressedButton.getCol() == emptyButton.getCol()) && (pressedButton.getRow() == emptyButton.getRow() + 1 || pressedButton.getRow() == emptyButton.getRow() - 1))
                    || (pressedButton.getRow() == emptyButton.getRow()) && (pressedButton.getCol() == emptyButton.getCol() + 1 || pressedButton.getCol() == emptyButton.getCol() - 1)) {
                System.out.println("Giltig knapp");

                String temp = pressedButton.jButton.getText();
                pressedButton.jButton.setText(emptyButton.jButton.getText());
                emptyButton.jButton.setText(temp);

                if (checkWinCondition()) {
                    JOptionPane.showMessageDialog(null, "Du vann!", "Grattis!", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Du vann!");
                }

            } else {
                System.out.println("Ogiltig knapp");
            }
        }catch (NullPointerException exception){
            System.out.println("Error: Knapp har värdet null");
        }
    }

    private boolean checkWinCondition(){
        int i = 1;
        for (Button button: buttonList) {
            if(i == buttonList.size())
                return true;
            if(!button.jButton.getText().equals(""+i))
                return false;
            i++;
        }
                return true;
    }
}

