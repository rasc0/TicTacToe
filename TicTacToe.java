import javax.swing.*; // GUI elements
import javax.swing.event.*; // GUI events
import java.awt.*;
import java.awt.event.*;
import java.util.*; // General tools
import static java.lang.System.*; // Code reduction/simplification


public class TicTacToe extends JFrame{

    private static TicTacToe mainframe;

    private boolean naughts = true; // Naughts goes first

    private int[] matrix = {2,2,2,2,2,2,2,2,2};

    private JButton s1, s2, s3, s4, s5, s6, s7, s8, s9;

    private int moveCount;

    JButton[] gridButtons = {s1, s2, s3, s4, s5, s6, s7, s8, s9};

    private JPanel panel;

    public static void main(String[] args){
        mainframe = new TicTacToe();
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setSize(800,800);
        mainframe.setVisible(true);
    }

    public TicTacToe(){ // Primary constructor
        s1 = new JButton("");
        s2 = new JButton("");
        s3 = new JButton("");
        s4 = new JButton("");
        s5 = new JButton("");
        s6 = new JButton("");
        s7 = new JButton("");
        s8 = new JButton("");
        s9 = new JButton("");

        panel = new JPanel();
        panel.setLayout(new GridLayout(4,3));

        for(int i = 0; i < gridButtons.length; i++){
            JButton temp = gridButtons[i];
            final int ic = i;
            temp.setFont(new Font("Arial", Font.PLAIN, 40));
            temp.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    //
                    moveCount++;

                    // Change button name:
                    temp.setText(naughts ? "O":"X");

                    // Make Button Unclickable:
                    temp.setEnabled(false);

                    // Switch the player move (not current)
                    naughts = !naughts;

                    // Change matrix value:
                    matrix[ic] = naughts ? 0 : 1;

                    // Check if a player has won/drawn
                    if(moveCount >= 5){
                        checkBoard();
                    }
                }
            });

            panel.add(temp);
        }

        setupBoard();
    }

    public void setupBoard(){
        moveCount = 0;

        // Reset the matrix:
        for(int i : matrix){
            out.print(i);
        }
        out.println("");

        for(int i = 0; i < matrix.length; i++){
            matrix[i] = 2;
            gridButtons[i].setEnabled(true);
            gridButtons[i].setText("");
        }

        for(int i : matrix){
            out.print(i);
        }
        out.println("");

        

        add(panel);
    }

    public void checkBoard(){
        // Diaganols
        if(matrix[0] == matrix[4] && matrix[4] == matrix[8] && matrix[0] != 2){
            JOptionPane.showMessageDialog(null, String.format("%s win!", (matrix[4] == 1)? "Naughts":"Crosses"), "Game Over", JOptionPane.PLAIN_MESSAGE);
            setupBoard();
        } else if(matrix[6] == matrix[4] && matrix[4] == matrix[2] && matrix[6] != 2){
            JOptionPane.showMessageDialog(null, String.format("%s win!", (matrix[4] == 1)? "Naughts":"Crosses"), "Game Over", JOptionPane.PLAIN_MESSAGE);
            setupBoard();
        }

        // Vertical
        for(int i = 0; i <3; i++){
            if(matrix[i] == matrix[i + 3] && matrix[i + 3] == matrix[i + 6] && matrix[i] != 2){
                JOptionPane.showMessageDialog(null, String.format("%s win!", (matrix[i] == 1)? "Naughts":"Crosses"), "Game Over", JOptionPane.PLAIN_MESSAGE);
                setupBoard();
            }
        }

        // Horizontal
        for(int i = 0; i <7; i += 3){
            if(matrix[i] == matrix[i + 1] && matrix[i + 1] == matrix[i + 2] && matrix[i] != 2){
                JOptionPane.showMessageDialog(null, String.format("%s win!", (matrix[i] == 1)? "Naughts":"Crosses"), "Game Over", JOptionPane.PLAIN_MESSAGE);
                setupBoard();
            }
        }

        if(moveCount == 9){
            // Draw
            JOptionPane.showMessageDialog(null, "The Players Have Drawn!", "Game Over", JOptionPane.PLAIN_MESSAGE);
            setupBoard();
        }
    }
}