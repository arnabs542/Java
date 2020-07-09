package blackJackGame;

import java.util.Scanner;
public class GameOfBlackJack {

	public static void main(String[] args) {
		GameOfBlackJack a = new GameOfBlackJack();
		while (true)
			a.playAGame();

	}
	
	void playAGame() {
		CardDispenser cd = new CardDispenser();
		Player p1 = new Player();
		Player cpu = new Player();
		
		System.out.println("Distributing cards...");
		p1.addCard(cd.dispense());
		cpu.addCard(cd.dispense());
		p1.addCard(cd.dispense());
		cpu.addCard(cd.dispense());
		
		System.out.println("You get " + p1.hand + " " + p1.getValues() );
		if (cpu.isAA() || p1.isAA() || p1.isDirectBlackJack() || cpu.isDirectBlackJack() )
			showHand(p1,cpu);
		else {
			hitOrStand(p1, cd);
			computerTurn(cpu, cd);
			showHand(p1, cpu);
		}
		
	}		//end of playAGame()

	void computerTurn(Player cpu, CardDispenser cd) {
		if (cpu.isDirectBlackJack() || cpu.isAA() || cpu.getLargest() >= 16 || cpu.hand.size() == 5) {
			return;
		}
		else {
			while (cpu.getLargest() < 16 && cpu.getLargest() != -999) cpu.addCard(cd.dispense());
		}
	}
	
	void hitOrStand(Player p1, CardDispenser cd) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("0 for hit, 1 for stand");
		int choice = scan.nextInt();
		while (choice != 0 && choice != 1) {
			System.out.println("Invalid input! Enter 0 or 1!");
			choice = scan.nextInt();
		}
		
		while (choice != 1) {
			p1.hand.add(cd.dispense());
			System.out.println("Your hand now: " + p1.hand + " " + p1.getValues());
			
			if (p1.getLargest() == -999 || p1.hand.size() == 5) {
				break;
			}
			
			System.out.println("0 for hit, 1 for stand");
			choice = scan.nextInt();
			while (choice != 0 && choice != 1) {
				System.out.println("Invalid input! Enter 0 or 1!");
				choice = scan.nextInt();
			}
		}
		
	}
	
	
	void showHand(Player p1, Player cpu) {
		if (p1.isAA() && cpu.isAA()) {
			System.out.println("Both get double A. Draw.");
		}
		else if (p1.isAA()) {
			System.out.println("You get double A. You win triple");
		}
		else if (cpu.isAA()) {
			System.out.println("CPU get double A. You lose triple");
		}
		else if (p1.isDirectBlackJack() && cpu.isDirectBlackJack()) {
			System.out.println("Both get blackjack. Draw.");
		}
		else if (p1.isDirectBlackJack()) {
			System.out.println("You get blackjack. You win double");
		}
		else if (cpu.isDirectBlackJack()) {
			System.out.println("CPU get blackjack. You lose double");
		}
		else if (p1.hand.size() == 5){
			if (p1.getLargest() == 21) {
				System.out.println("Five cards and blackjack. You won triple");
			}
			else if (p1.getLargest() != -999) {
				System.out.println("Five cards. You won double");
			}
			else if (p1.getLargest() == -999) {
				System.out.println("Five cards and burst. You lost double");
			}
		}
		else if (cpu.hand.size() == 5) {
			if (cpu.getLargest() == 21) {
				System.out.println("CPU Five cards and blackjack. You lost triple");
			}
			else if (cpu.getLargest() != -999) {
				System.out.println("CPU Five cards. You lost double");
			}
			else if (cpu.getLargest() == -999) {
				System.out.println("CPU Five cards and burst. You win double");
			}
		}
		else if (p1.getLargest() == 21 && cpu.getLargest() == 21) {
			System.out.println("Both get 21. Draw");
		}
		else if (p1.getLargest() == 21) {
			System.out.println("You get 21. You won double");
		}
		else if (cpu.getLargest() == 21) {
			System.out.println("CPU get 21. You lost double");
		}
		else if (p1.getLargest() == -999 && cpu.getLargest() == -999) {
			System.out.println("Both bursted.");
		}
		else if (p1.getLargest() == -999) {
			System.out.println("You bursted. You lost.");
		}
		else if (cpu.getLargest() == -999) {
			System.out.println("CPU bursted. You win.");
		}
		else {
			if (p1.getLargest() == cpu.getLargest()) {
				System.out.println("Draw");
			}
			else if (p1.getLargest() > cpu.getLargest()) {
				System.out.println("You win!");
			}
			else {
				System.out.println("You lose!");
			}
		}
		System.out.println("CPU's hand: " + cpu.hand + " " + cpu.getValues() );
		System.out.println("----------------------------------------------------------------");
	}
	
	
}		//end of GameOfBlackJack
