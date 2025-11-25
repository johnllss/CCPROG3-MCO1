package StoreApp.Models;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

/**
 * Manages membership data persistence and operations.
 * Singleton pattern ensures single source of truth for membership data.
 */
public class MembershipDataManager {
    private static MembershipDataManager instance;
    private ArrayList<Customer_Model> members;
    private static final String DATA_FILE = "data/members.txt";
    private static final String DELIMITER = "\\|";

    /**
     * Private constructor for singleton pattern
     */
    private MembershipDataManager() {
        members = new ArrayList<>();
        loadMembersFromFile();
    }

    /**
     * Gets singleton instance
     * @return MembershipDataManager instance
     */
    public static MembershipDataManager getInstance() {
        if (instance == null) {
            instance = new MembershipDataManager();
        }
        return instance;
    }

    /**
     * Loads members from file into memory
     */
    private void loadMembersFromFile() {
        File file = new File(DATA_FILE);

        // Create file if it doesn't exist
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs(); // Create data/ directory
                file.createNewFile();
                System.out.println("Created new members file: " + DATA_FILE);
                return; // Empty file, no members to load
            } catch (IOException e) {
                System.err.println("Error creating members file: " + e.getMessage());
                return;
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                if (line.trim().isEmpty()) {
                    continue; // Skip empty lines
                }

                try {
                    Customer_Model member = parseMemberFromLine(line);
                    members.add(member);
                } catch (Exception e) {
                    System.err.println("Error parsing line " + lineNumber + ": " + e.getMessage());
                    // Continue loading other members
                }
            }

            System.out.println("Loaded " + members.size() + " members from file.");

        } catch (IOException e) {
            System.err.println("Error reading members file: " + e.getMessage());
        }
    }

    /**
     * Parses a Customer_Model from a file line
     * Format: cardNumber|fullName|email|points|registrationDate
     * @param line the line to parse
     * @return Customer_Model parsed from the line
     */
    private Customer_Model parseMemberFromLine(String line) {
        String[] parts = line.split(DELIMITER);

        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid line format: expected 5 fields, got " + parts.length);
        }

        String cardNumber = parts[0].trim();
        String fullName = parts[1].trim();
        String email = parts[2].trim();
        int points = Integer.parseInt(parts[3].trim());
        LocalDate registrationDate = LocalDate.parse(parts[4].trim());

        MembershipCard_Model card = new MembershipCard_Model(cardNumber, registrationDate);
        card.setPoints(points); // Restore saved points

        Customer_Model customer = new Customer_Model(
            fullName,
            email,
            "", // password not used
            card,
            false, // senior status not stored in membership file
            null // cart not needed
        );

        return customer;
    }

    /**
     * Saves all members to file
     */
    private void saveMembersToFile() {
        File file = new File(DATA_FILE);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Customer_Model member : members) {
                String line = formatMemberToLine(member);
                writer.write(line);
                writer.newLine();
            }

            System.out.println("Saved " + members.size() + " members to file.");

        } catch (IOException e) {
            System.err.println("Error writing members file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Formats a Customer_Model to file line
     * Format: cardNumber|fullName|email|points|registrationDate
     * @param member the member to format
     * @return formatted string line
     */
    private String formatMemberToLine(Customer_Model member) {
        MembershipCard_Model card = member.getMembershipCard();

        return String.format("%s|%s|%s|%d|%s",
            card.getCardNumber(),
            member.getName(),
            member.getEmail(),
            card.getPoints(),
            card.getRegistrationDate().toString()
        );
    }

    /**
     * Finds member by card number
     * @param cardNumber the card number to search for
     * @return Customer_Model if found, null otherwise
     */
    public Customer_Model findByCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.trim().isEmpty()) {
            return null;
        }

        String normalized = cardNumber.trim();

        for (Customer_Model member : members) {
            if (member.getMembershipCard().getCardNumber().equals(normalized)) {
                return member;
            }
        }

        return null;
    }

    /**
     * Finds member by full name and email (case-insensitive)
     * @param name the full name to search for
     * @param email the email to search for
     * @return Customer_Model if found, null otherwise
     */
    public Customer_Model findByNameAndEmail(String name, String email) {
        if (name == null || email == null) {
            return null;
        }

        String normalizedName = name.trim().toLowerCase();
        String normalizedEmail = email.trim().toLowerCase();

        for (Customer_Model member : members) {
            boolean nameMatch = member.getName().trim().toLowerCase().equals(normalizedName);
            boolean emailMatch = member.getEmail().trim().toLowerCase().equals(normalizedEmail);

            if (nameMatch && emailMatch) {
                return member;
            }
        }

        return null;
    }

    /**
     * Checks if card number already exists
     * @param cardNumber the card number to check
     * @return true if exists, false otherwise
     */
    public boolean isCardNumberExists(String cardNumber) {
        return findByCardNumber(cardNumber) != null;
    }

    /**
     * Generates a unique card number
     * @return unique 4-digit card number as String
     */
    public String generateUniqueCardNumber() {
        Random random = new Random();
        int maxAttempts = 10;

        // Try random generation first
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            int randomNum = 1000 + random.nextInt(9000); // 1000-9999
            String cardNumber = String.valueOf(randomNum);

            if (!isCardNumberExists(cardNumber)) {
                return cardNumber;
            }
        }

        // Fallback: Sequential generation
        for (int i = 1000; i <= 9999; i++) {
            String cardNumber = String.valueOf(i);
            if (!isCardNumberExists(cardNumber)) {
                return cardNumber;
            }
        }

        throw new RuntimeException("All card numbers (1000-9999) are exhausted!");
    }

    /**
     * Adds a new member and saves to file
     * @param newMember the new member to add
     * @return true if successful, false otherwise
     */
    public boolean addMember(Customer_Model newMember) {
        if (newMember == null || newMember.getMembershipCard() == null) {
            return false;
        }

        // Check for duplicates
        String cardNumber = newMember.getMembershipCard().getCardNumber();
        if (isCardNumberExists(cardNumber)) {
            System.err.println("Card number already exists: " + cardNumber);
            return false;
        }

        // Add to in-memory list
        members.add(newMember);

        // Persist to file
        saveMembersToFile();

        return true;
    }

    /**
     * Updates member points and saves to file
     * @param cardNumber the card number of the member
     * @param newPoints the new points value
     * @return true if successful, false otherwise
     */
    public boolean updateMemberPoints(String cardNumber, int newPoints) {
        Customer_Model member = findByCardNumber(cardNumber);

        if (member == null) {
            return false;
        }

        member.getMembershipCard().setPoints(newPoints);
        saveMembersToFile();

        return true;
    }

    /**
     * Gets all members (for admin/reporting purposes)
     * @return ArrayList of all members
     */
    public ArrayList<Customer_Model> getAllMembers() {
        return new ArrayList<>(members); // Return copy to prevent external modification
    }
}
