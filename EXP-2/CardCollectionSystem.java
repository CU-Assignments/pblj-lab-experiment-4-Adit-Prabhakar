import java.util.*;

class Card {
    String suit;
    String rank;

    Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}

public class CardCollectionSystem {
    static Map<String, Set<Card>> cardCollection = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void addCard() {
        System.out.print("Enter Card Rank: ");
        String rank = sc.nextLine();
        System.out.print("Enter Card Suit: ");
        String suit = sc.nextLine();
        
        cardCollection.putIfAbsent(suit, new HashSet<>());
        Card newCard = new Card(rank, suit);
        
        if (cardCollection.get(suit).contains(newCard)) {
            System.out.println("Error: Card \"" + newCard + "\" already exists.");
        } else {
            cardCollection.get(suit).add(newCard);
            System.out.println("Card added: " + newCard);
        }
    }

    public static void findCardsBySuit() {
        System.out.print("Enter Suit to Search: ");
        String suit = sc.nextLine();
        
        if (cardCollection.containsKey(suit) && !cardCollection.get(suit).isEmpty()) {
            System.out.println("Cards in " + suit + ":");
            for (Card card : cardCollection.get(suit)) {
                System.out.println(card);
            }
        } else {
            System.out.println("No cards found for " + suit + ".");
        }
    }

    public static void displayAllCards() {
        if (cardCollection.isEmpty()) {
            System.out.println("No cards found.");
            return;
        }
        
        for (String suit : cardCollection.keySet()) {
            for (Card card : cardCollection.get(suit)) {
                System.out.println(card);
            }
        }
    }

    public static void removeCard() {
        System.out.print("Enter Card Rank to Remove: ");
        String rank = sc.nextLine();
        System.out.print("Enter Card Suit to Remove: ");
        String suit = sc.nextLine();
        
        if (cardCollection.containsKey(suit)) {
            Card cardToRemove = new Card(rank, suit);
            if (cardCollection.get(suit).remove(cardToRemove)) {
                System.out.println("Card removed: " + cardToRemove);
                if (cardCollection.get(suit).isEmpty()) {
                    cardCollection.remove(suit);
                }
            } else {
                System.out.println("Card not found.");
            }
        } else {
            System.out.println("No cards found for suit " + suit + ".");
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nCard Collection System");
            System.out.println("1. Add Card");
            System.out.println("2. Find Cards by Suit");
            System.out.println("3. Display All Cards");
            System.out.println("4. Remove Card");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            int choice = Integer.parseInt(sc.nextLine());
            
            switch (choice) {
                case 1:
                    addCard();
                    break;
                case 2:
                    findCardsBySuit();
                    break;
                case 3:
                    displayAllCards();
                    break;
                case 4:
                    removeCard();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
