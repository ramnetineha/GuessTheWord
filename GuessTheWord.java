import java.util.Random;
import java.util.Scanner;

public class GuessTheWord {
	private  String[] words = {
			"admin", "word", "shark", "lion", 
			"amazing", "delicious", "world", "fantastic", 
			"morose", "empty","shell", "computer", 
			"mastermind", "composer","package", "user", 
			"programmer", "software"
	};

	private  Scanner scan = new Scanner(System.in);
	private  String currentWord;
	private  String hiddenWord;
	private  Player player;
	
	public void startGame() {
		inputPlayerName();
		
		while(true){
			initializeWord();
			
			do {
				char letter = inputLetter();
				scan.nextLine();
				showLetterIfMatch(letter);
			}while(!player.hasRunOutOfLives() && !wordIsCompleted());
		
			if(wordIsCompleted()) {
				displayCongratulationMessage();
				player.addScore(currentWord.length());
			}
			
			if(player.hasRunOutOfLives()) {
				displayGameOverMessage();
				break;
			}
		}
	}

	private void displayCongratulationMessage() {
		System.out.println("Congratulations, you found the hidden word");
		scan.hasNextLine();
	}

	private void displayGameOverMessage() {
		clearScreen();
		System.out.println("Too bad, " + player.getName() + ".\n");
		System.out.println("The actual word is " + currentWord);
		System.out.println("Your final score is " + player.getScore());
	}

	private boolean wordIsCompleted() {
		return hiddenWord.equals(currentWord);
	}

	private void inputPlayerName() {
		String name;
		
		do {
			System.out.println("Input your name [1 - 20 character(s)]: ");
			name = scan.nextLine();
		}while(name.length() < 1 || name.length() > 20);
		
		player = new Player(name);
	}
	
	private char inputLetter() {
		clearScreen();
		System.out.println(hiddenWord);
		System.out.println("Current score: " + player.getScore());
		System.out.println("Life: " + player.getLife());
		System.out.println("\nInput a letter: ");
		
		return scan.next().charAt(0);
	}
	
	private void initializeWord() {
		initializeCurrentword();
		initializeHiddenWord();
	}

	private void initializeCurrentword() {
		Random rand = new Random();
		
		int numberOfWords = words.length;
		currentWord = words[rand.nextInt(numberOfWords)];
	}
	
	private void showLetterIfMatch(char letter) {
		int wordLength = currentWord.length();
		StringBuilder builder = new StringBuilder(hiddenWord);
		boolean letterExists = false;
		
		for(int i = 0; i < wordLength; i++) {
			if(currentWord.charAt(i) == letter) {
				builder.replace(i, i + 1, Character.toString(letter));
				hiddenWord = builder.toString();
				letterExists = true;
			}
		}
		
		if(!letterExists) {
			deductPlayerLife();
		}
	}
	
	private void deductPlayerLife() {
		player.deductLife();
	}
	
	private void clearScreen() {
		for(int i = 0; i < 25; i++) {
			System.out.println();
		}
	}

	private void initializeHiddenWord() {
		int wordLength = currentWord.length();
		
		hiddenWord = "";
		
		for(int i = 0; i < wordLength; i++) {
			hiddenWord += "_";
		}
	}
}
