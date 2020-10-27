import javax.swing.*;

/**
 * Created by: Anton Rolin
 * Date: 26/10/2020
 * Time: 22:16
 * Project: Inlamningsuppgift_3
 * Copyright: MIT
 */
public class Button {
    private int col;
    private int row;
    public JButton jButton;

    Button(String text){
        this.jButton = new JButton(text);
    }

    public void setCol(int col) { this.col = col; }

    public void setRow(int row) { this.row = row; }

    public int getCol() { return col; }

    public int getRow() { return row; }

    public JButton getjButton() { return jButton; }
}
