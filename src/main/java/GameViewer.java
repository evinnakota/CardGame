import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameViewer extends JFrame {
    private Game game;
    public Image card;
    private JButton restartButton;
    private JButton inGameResetButton; // Add this field at the top of the class


    public GameViewer(Game game) {
        this.game = game;
        card = new ImageIcon("src/main/resources/1.png").getImage();
        setTitle("Card War");
        setSize(800, 675);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Absolute positioning

        // Safely set the background color so it doesn't cover components
        getContentPane().setBackground(new Color(109, 171, 100));

        // Initialize Restart Button
        restartButton = new JButton("Restart Game");
        restartButton.setBounds(300, 550, 200, 50);
        restartButton.setFont(new Font("Arial", Font.BOLD, 20));
        restartButton.setVisible(false);
        restartButton.addActionListener(e -> game.resetGame());
        add(restartButton);
        // Inside the GameViewer constructor, below your other button:
        inGameResetButton = new JButton("Use Reset (3)");
        // x = 570 (near the right edge), y = 20 (near the top), width = 200, height = 40
        inGameResetButton.setBounds(570, 20, 200, 40);        inGameResetButton.setFont(new Font("Arial", Font.BOLD, 16));
        inGameResetButton.setVisible(false);
        inGameResetButton.addActionListener(e -> {
            game.useInGameReset();
            inGameResetButton.setText("Use Reset (" + game.getResetsRemaining() + ")");
            if (game.getResetsRemaining() <= 0) {
                inGameResetButton.setVisible(false);
            }
        });
        add(inGameResetButton);

        setVisible(true);
    }

    public void showRestartButton(boolean show) {
        restartButton.setVisible(show);
    }

    public void paint(Graphics g) {
        super.paint(g); // Paints standard components (background and button) first

        if (game.isShowingInstructions()) {
            drawInstructions(g);
        } else if (game.isGameOver()) {
            drawGameOver(g);
        } else {
            drawGame(g);
        }
    }



    private void drawInstructions(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("WAR", 320, 120);

        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("You and I both hold 3 cards at a time.", 250, 200);
        g.drawString("Pick one each round. Higher card wins.", 250, 260);
        g.drawString("Don't get a negative score (you lose).", 250, 320);
        g.drawString("If total value > 20, both players lose points.", 220, 380);
        g.drawString("We play until no cards remain.", 270, 440);
        g.drawString("Good luck!", 350, 500);
    }

    private void drawGame(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString("WAR", 350, 100);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Score: " + game.p1.getScore(), 50, 150);
        g.drawString("Score: " + game.p2.getScore(), 50, 550);
        g.drawString(game.p1.getName(), 350, 150);
        g.drawString(game.p2.getName(), 350, 625);

        ArrayList<Card> hand = game.p1.getHand();
        for (int i = 0; i < hand.size(); i++) {
            hand.get(i).draw(g, i);
            game.p2.getHand().get(i).draw(g, i + 10); // Delete later
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString(game.getMessage(), 450, 367);

        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.drawString("change: " + game.getP1Change(), 450, 150);
        g.drawString("change: " + game.getP2Change(), 450, 625);

        // Show the button if they have chances left
        if (game.getResetsRemaining() > 0) {
            inGameResetButton.setVisible(true);
        }

        // Draw info box for remaining resets
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Resets remaining: " + game.getResetsRemaining() + " / 3", 50, 50);

    }

    private void drawGameOver(Graphics g) {

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("GAME OVER", 200, 300);
        g.drawString("Player 1 Score: " + game.p1.getScore(), 200, 400);
        g.drawString("Player 2 Score: " + game.p2.getScore(), 200, 500);
    }
}