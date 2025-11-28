package StoreApp.Controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Utility class to validate customer information against previous receipts.
 * This class reads all saved receipt files to check for existing emails and membership cards.
 */
public class Receipt_Validating {
    private static final String RECEIPTS_DIRECTORY = "receipts/";
    
    /**
     * This method checks if an email address already exists in any saved receipt.
     * @param email is the email address to check.
     * @return true if the email exists in any receipt, false otherwise.
     */
    public static boolean isEmailAlreadyTaken(String email) {
        Set<String> registeredEmails = getAllRegisteredEmails();
        return registeredEmails.contains(email.toLowerCase());
    }
    
    /**
     * This method checks if a membership card number exists in any saved receipt.
     * @param cardNumber is the membership card number to check.
     * @return true if the card number exists in any receipt, false otherwise.
     */
    public static boolean isMembershipCardAlreadyTaken(String cardNumber) {
        Set<String> validCards = getAllMembershipCards();
        return validCards.contains(cardNumber);
    }
    
    /**
     * This method retrieves all registered email addresses from saved receipts.
     * @return Set of email addresses found in receipts.
     */
    private static Set<String> getAllRegisteredEmails() {
        Set<String> emails = new HashSet<>();
        File receiptsDir = new File(RECEIPTS_DIRECTORY);
        
        if (!receiptsDir.exists() || !receiptsDir.isDirectory()) {
            return emails;
        }
        
        File[] receiptFiles = receiptsDir.listFiles((dir, name) -> name.endsWith(".txt"));
        
        if (receiptFiles != null) {
            for (File receiptFile : receiptFiles) {
                try (BufferedReader br = new BufferedReader(new FileReader(receiptFile))) {
                    String line;
                    while ((line = br.readLine()) != null) {

                        // look for email line in the receipt
                        if (line.startsWith("Email: ")) {
                            String email = line.substring(7).trim();
                            emails.add(email.toLowerCase());
                            break;
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Error reading receipt file: " + receiptFile.getName());
                    e.printStackTrace();
                }
            }
        }
        
        return emails;
    }
    
    /**
     * This method retrieves all membership card numbers from saved receipts.
     * @return Set of membership card numbers found in receipts.
     */
    private static Set<String> getAllMembershipCards() {
        Set<String> cardNumbers = new HashSet<>();
        File receiptsDir = new File(RECEIPTS_DIRECTORY);
        
        if (!receiptsDir.exists() || !receiptsDir.isDirectory()) {
            return cardNumbers;
        }
        
        File[] receiptFiles = receiptsDir.listFiles((dir, name) -> name.endsWith(".txt"));
        
        if (receiptFiles != null) {
            for (File receiptFile : receiptFiles) {
                try (BufferedReader br = new BufferedReader(new FileReader(receiptFile))) {
                    String line;

                    while ((line = br.readLine()) != null) {
                        // look for membership card line in the receipt
                        if (line.startsWith("Membership Card: ")) {
                            String cardNumber = line.substring(17).trim();
                            cardNumbers.add(cardNumber);
                            break;
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Error reading receipt file: " + receiptFile.getName());
                    e.printStackTrace();
                }
            }
        }
        
        return cardNumbers;
    }
}
