import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand = new ArrayList<>();
    private int score;
    public Player(String name, ArrayList<Card> hand) {
        this.name = name;
        this.score = 0;
        this.hand = hand;
    }
    public Player(String name) {
        this.name = name;
        this.score = 0;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public void clearHand() {
        this.hand.clear();
    }

    public void addScore(int points) {
        this.score += points;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public ArrayList<Card> getHand() {
        return hand;
    }
    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    @Override
    public String toString() {
        return this.name + " has " + this.score + " points!" + "\n" + this.name + "'s cards: " + this.hand;
    }
}
