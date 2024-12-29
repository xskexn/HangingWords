import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        //Array containing the different state of tbe hangman
        String[] hangman = {
                "",
                """
            ____
            |/   |
            |   
            |    
            |    
            |    
            |
            |_____
            """,
                """
             ____
            |/   |
            |   (_)
            |    
            |    
            |    
            |
            |_____
            """,
                """
             ____
            |/   |
            |   (_)
            |    |
            |    |    
            |    
            |
            |_____
            """,
                """
             ____
            |/   |
            |   (_)
            |   \\|
            |    |
            |    
            |
            |_____
            """,
                """
             ____
            |/   |
            |   (_)
            |   \\|/
            |    |
            |    
            |
            |_____
            """,
                """
             ____
            |/   |
            |   (_)
            |   \\|/
            |    |
            |   / 
            |
            |_____
            """,
                """
             ____
            |/   |
            |   (_)
            |   \\|/
            |    |
            |   / \\
            |
            |_____
            """,
                """
             ____
            |/   |
            |   (_)
            |   /|\\
            |    |
            |   | |
            |
            |_____
            """
        };

    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    int wordSelectionNumber = random.nextInt(0,148); //Random number generator to select the word from the file
    int score = 0; // keep tracks of the state of the hangman

    ArrayList<String> wordArray = new ArrayList<String>();
    ArrayList<String> listOfStrings = new ArrayList<String>();
    ArrayList<String> revealedWord = new ArrayList<String>();

    String userInput = "";
    System.out.println("Welcome to Hanging words...\nThe game is simple: Just guess one letter at the time and try to guess the whole word before the hangman is completed!\n");

	try {

        // loading content of file based on specific delimiter
        Scanner sc = new Scanner(new FileReader("D:\\Docs\\Ken's Hidden Codebase\\Hanging Words\\HangingWords\\src\\Dictionary.txt")).useDelimiter(",\\s*");
        String str;

        // checking end of file
        while (sc.hasNext()) {
            str = sc.next();

            // adding each string to arraylist
            listOfStrings.add(str);
        }

        // converting any arraylist to array
        String[] dictionary = listOfStrings.toArray(new String[0]);
        String chosenWord = dictionary[wordSelectionNumber];

        //System.out.println(chosenWord.toUpperCase());
        String maskedWord = "_".repeat(chosenWord.length());
        System.out.println("This is the word you need to guess: " + maskedWord);

        //Splitting the word into letters and adding them into an arraylist
        for (int i = 0; i < chosenWord.length(); i ++){
            char letter = chosenWord.charAt(i);
            wordArray.add(Character.toString(letter).toUpperCase());
            revealedWord.add(i, "_"); //Adding the same number of letter as underscores in the reveled word array
        }

        //TEST
//        System.out.println(wordArray);
//        System.out.println(revealedWord);

        //Game logic conditions to keep playing
        while(!userInput.equals("exit") && revealedWord.contains("_") && score < 8){
            System.out.print("Enter your letter: ");
            userInput = scanner.nextLine();

            //Checking if the user guessed the right word
            if(wordArray.contains(userInput.toUpperCase())){
                System.out.println("The letter: "+ userInput.toUpperCase() +" is correct!");
                int index = wordArray.indexOf(userInput.toUpperCase()); //Storing the index of the correct letter
                revealedWord.set(index, userInput.toUpperCase()); //replacing the correct letter in the same index of the reveled word
                wordArray.set(index, "*"); //Setting the chosen word letter to an * in order to allow for duplicate letter
                maskedWord = String.join(",", revealedWord); //Displaying the word array in a nicer format
                System.out.println("The word: " + maskedWord);
            } else {
                score += 1; //Increasing the player score everytime they make a mistake
                System.out.println("The letter: " + userInput + " was wrong!");
                System.out.println("The word: " + maskedWord);
            }
            //Displaying the hangman
            System.out.println(hangman[score]);

            //Checking if the user won the game
            if (!revealedWord.contains("_")){
                System.out.println("Congratulation, You won the game!\nThe word was indeed " + chosenWord);
            } else if (score == 8) { //Checking if the user lost the game
                System.out.println("Your should consider improving your guessing game...\nThe word was: " + chosenWord);
            }
        }
        
	} catch (IOException e) {
        System.out.println("An error occurred, the file has not been found.");
        scanner.close();
	}
    }
}
