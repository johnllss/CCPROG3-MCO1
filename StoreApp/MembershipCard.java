package StoreApp;

public class MembershipCard {
    private String cardNumber;
    private int points;

    public MembershipCard(String cardNumber) {
        this.cardNumber = cardNumber;
        this.points = 0;
    }

    public boolean addPoints(int points) {
        this.points += points;
        return true;
    }

    public boolean redeemPoints(int points) {
        if (points >= this.points) {
            return false;
        }
        else  {
            this.points -= points;
            return true;
        }
    }
    
    public String getCardNumber() {
        return cardNumber;
    }

    public int getPoints() {
        return points;
    }

}
