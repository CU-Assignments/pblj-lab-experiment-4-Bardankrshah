Experiment 4.2: Card Collection System

Objective:
Develop a Java program that collects and stores playing cards to help users find all the cards of a given symbol (suit).
The program should utilize the Collection interface (such as ArrayList, HashSet, or HashMap) to manage the card data efficiently.

Understanding the Problem Statement

1. Card Structure:
Each card consists of a symbol (suit) and a value (rank).

Example card representations:
Ace of Spades
King of Hearts
10 of Diamonds
5 of Clubs

2. Operations Required:
Add Cards → Store card details in a collection.
Find Cards by Symbol (Suit) → Retrieve all cards belonging to a specific suit (e.g., all "Hearts").
Display All Cards → Show all stored cards.

3. Collections Usage:
ArrayList: To store cards in an ordered manner.
HashSet: To prevent duplicate cards.
HashMap<String, List<Card>>: To organize cards based on suits for faster lookup.
  CODE:
  class Card {
    private String rank;
    private String suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card card = (Card) obj;
        return Objects.equals(rank, card.rank) && Objects.equals(suit, card.suit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }
}

class CardCollection {
    private Map<String, List<Card>> cardsBySuit;
    private Set<Card> uniqueCards;

    public CardCollection() {
        cardsBySuit = new HashMap<>();
        uniqueCards = new HashSet<>();
    }

    public void addCard(String rank, String suit) {
        Card card = new Card(rank, suit);
        if (uniqueCards.contains(card)) {
            System.out.println("Error: Card \"" + card + "\" already exists.");
            return;
        }
        uniqueCards.add(card);
        cardsBySuit.putIfAbsent(suit, new ArrayList<>());
        cardsBySuit.get(suit).add(card);
        System.out.println("Card added: " + card);
    }

    public void findCardsBySuit(String suit) {
        if (!cardsBySuit.containsKey(suit) || cardsBySuit.get(suit).isEmpty()) {
            System.out.println("No cards found for " + suit + ".");
            return;
        }
        for (Card card : cardsBySuit.get(suit)) {
            System.out.println(card);
        }
    }

    public void displayAllCards() {
        if (uniqueCards.isEmpty()) {
            System.out.println("No cards found.");
            return;
        }
        for (Card card : uniqueCards) {
            System.out.println(card);
        }
    }

    public void removeCard(String rank, String suit) {
        Card card = new Card(rank, suit);
        if (!uniqueCards.contains(card)) {
            System.out.println("Card \"" + card + "\" not found.");
            return;
        }
        uniqueCards.remove(card);
        cardsBySuit.get(suit).remove(card);
        if (cardsBySuit.get(suit).isEmpty()) {
            cardsBySuit.remove(suit);
        }
        System.out.println("Card removed: " + card);
    }
}

public class CardGameManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CardCollection cardCollection = new CardCollection();
        while (true) {
            System.out.println("\nChoose an option: \n1. Add Card\n2. Find Cards by Suit\n3. Display All Cards\n4. Remove Card\n5. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter card rank: ");
                    String rank = scanner.nextLine();
                    System.out.print("Enter card suit: ");
                    String suit = scanner.nextLine();
                    cardCollection.addCard(rank, suit);
                    break;
                case 2:
                    System.out.print("Enter suit to search: ");
                    suit = scanner.nextLine();
                    cardCollection.findCardsBySuit(suit);
                    break;
                case 3:
                    cardCollection.displayAllCards();
                    break;
                case 4:
                    System.out.print("Enter card rank to remove: ");
                    rank = scanner.nextLine();
                    System.out.print("Enter card suit to remove: ");
                    suit = scanner.nextLine();
                    cardCollection.removeCard(rank, suit);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }
}



Test Cases

Test Case 1: No Cards Initially
Input:
Display All Cards
Expected Output:
No cards found.

Test Case 2: Adding Cards
Input:
Add Card: Ace of Spades
Add Card: King of Hearts
Add Card: 10 of Diamonds
Add Card: 5 of Clubs
Expected Output:
Card added: Ace of Spades
Card added: King of Hearts
Card added: 10 of Diamonds
Card added: 5 of Clubs

Test Case 3: Finding Cards by Suit
Input:
Find All Cards of Suit: Hearts
Expected Output:
King of Hearts

Test Case 4: Searching Suit with No Cards
Input:
Find All Cards of Suit: Diamonds
(If no Diamonds were added)
Expected Output:
No cards found for Diamonds.

Test Case 5: Displaying All Cards
Input:
Display All Cards
Expected Output:
Ace of Spades
King of Hearts
10 of Diamonds
5 of Clubs

Test Case 6: Preventing Duplicate Cards
Input:
Add Card: King of Hearts
Expected Output:
Error: Card "King of Hearts" already exists.

Test Case 7: Removing a Card
Input:
Remove Card: 10 of Diamonds
Expected Output:
Card removed: 10 of Diamonds
