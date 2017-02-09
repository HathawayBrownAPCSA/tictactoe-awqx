/**
 * Starts a tic-tac-toe game
 * Originally from http://kodingtutorials.weebly.com/tic-tac-toe-gui.html
 * Modified by Alison Xin on 2017.02.03
 * Things to fix
 * fix font
 * can't recognize tie
 * options are dumb
 * you can override a previous box
 * doesn't tell you who won
 * change from 1d to 2d array
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe extends JPanel
{
    JButton buttons[][] = new JButton[3][3]; 
    
    int alternate = 0;//if this number is a even, then put a X. If it's odd, then put an O
    
    public TicTacToe()
    {
      setLayout(new GridLayout(3,3));
      initializeButtons(); 
    }
    
    public void initializeButtons()
    {
      for(int r = 0; r < 3; r++)
      {
        for (int c = 0; c < 3; c++)
        {
            buttons[r][c] = new JButton();
            buttons[r][c].setFont(new Font("Arial", Font.BOLD, 70));
            buttons[r][c].setText("");
            buttons[r][c].addActionListener(new buttonListener());
            
            add(buttons[r][c]); //adds this button to JPanel (note: no need for JPanel.add(...)
                                //because this whole class is a JPanel already           
        }
      }
    }
    public void resetButtons()
    {
      for(int r = 0; r < 3; r++)
       {
          for (int c = 0; c < 3; c++)
          {
            buttons[r][c].setText("");
          }
       }
      alternate = 0; 
    }
    
// when a button is clicked, it generates an ActionEvent. Thus, each button needs an ActionListener. 
//When it is clicked, it goes to this listener class that I have created and goes to the actionPerformed method. 
//There (and in this class), we decide what we want to do.
    private class buttonListener implements ActionListener
    {
       
        public void actionPerformed(ActionEvent e) 
        {
            
            JButton buttonClicked = (JButton)e.getSource(); //get the particular button that was clicked
            if (buttonClicked.getText().length() > 0)
            {
              JOptionPane.showMessageDialog(null, "You can't do that!", "Don't be a cheater.", JOptionPane.WARNING_MESSAGE);
            }
            else if(alternate%2 == 0)
            {
                buttonClicked.setText("X");
                buttonClicked.setForeground(Color.RED); 
                alternate++; 
            }
            else
            {
                buttonClicked.setText("O");
                buttonClicked.setForeground(Color.BLUE); 
                alternate++; 
            }
            
            String winner = checkForWin(); 
            if(winner.length() > 0)
            {
              if (winner.equals("T"))
                    {
                JOptionPane.showConfirmDialog(null, "It was a tie.", "Click OK", JOptionPane.DEFAULT_OPTION);
                resetButtons();
            } 
            else 
              {
                JOptionPane.showConfirmDialog(null, winner + " was the winner", "Game over", JOptionPane.DEFAULT_OPTION);
                resetButtons();
            } 
        }
        }
        
        public String checkForWin()
        {
          //horizontal win check
          
          for (int r = 0; r < 3; r++)
          {
          if (checkThree(buttons[r][0].getText(), buttons[r][1].getText(), buttons[r][2].getText()))
            return buttons[r][0].getText(); 
          }
          //vertical win check
          for (int c = 0; c < 3; c++)
          {
          if (checkThree(buttons[0][c].getText(), buttons[1][c].getText(), buttons[2][c].getText()))
                return buttons[0][c].getText(); 
           }
          //diagonal win check
          if (checkThree(buttons[0][0].getText(), buttons[1][1].getText(), buttons[2][2].getText()) ||
          checkThree(buttons[2][0].getText(), buttons[1][1].getText(), buttons[0][2].getText()))
            return buttons[1][1].getText(); 
         
          if (alternate == 9)
            return "T"; 
          
          return ""; 
              
        }
        
        public boolean checkThree (String s1, String s2, String s3)
        {
          if (s1.equals(s2) && s1.equals(s3) && s1.length() > 0)
            return true; 
          else
            return false; 
        }
    } 
    
    public static void main(String[] args) 
    {
        JFrame window = new JFrame("Tic-Tac-Toe");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(new TicTacToe());
        window.setBounds(300,200,300,300);
        window.setVisible(true);
    }
}