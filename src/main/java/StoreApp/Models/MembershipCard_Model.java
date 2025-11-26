package StoreApp.Models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/***
 * Represents users membership card, should there be a presence of it
 */
public class MembershipCard_Model {
    private String cardNumber;
    private int points;

    /**
     * Class MembershipCard parameterized constructor
     * @param cardNumber is the membership card number of the Customer
     * @param registrationDate is the date the membership was created
     */
    public MembershipCard_Model(String cardNumber) {
        this.cardNumber = cardNumber;
        this.points = 0;
    }

    /**
     * Class MembershipCard_Model default constructor for new members. This version automatically generates a unique card number.
     */
    public MembershipCard_Model() {
        this.cardNumber = generateCardNumber();
        this.points = 0;
    }

    /**
     * This method adds points to the customer's membership points.
     * @param points is the number of points to add.
     * @return boolean for success/failure.
     */
    public boolean addPoints(int points) {
        this.points += points;
        return true;
    }

    /**
     * This method deducts points from the customer after redeeming.
     * @param points is the number of points to deduct.
     * @return boolean for success/failure.
     */
    public boolean redeemPoints(int points) {
        if (points >= this.points) {
            return false;
        }
        else  {
            this.points -= points;
            return true;
        }
    }

    /**
     * This method generates a unique card number using timestamp format, following YYYYMMDD-HHMMSS.
     * @return String for the generated card number.
     */
    public String generateCardNumber()
    {
        LocalDateTime dateNow = LocalDateTime.now();
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        return dateNow.format(formattedDate);
    }

    /**
     * This is a getter method to get the cardNumber.
     * @return String for the cardNumber.
     */
    public String getCardNumber() {
        return cardNumber;
    }


    /**
     * This is a getter method to get the points of the customer.
     * @return int for the points.
     */
    public int getPoints() {
        return points;
    }
}
