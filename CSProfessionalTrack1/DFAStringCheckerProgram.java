package CSProfessionalTrack1;
import java.util.*;

public class DFAStringCheckerProgram {
	public enum State {
		q0,
		q1,
		q2
	}
	
	public static class DFA {
		private State currentState;
		private final Map<State, Map<Character, State>> transitionTable;
		
		public DFA() {
			this.currentState = State.q0;
			this.transitionTable = new HashMap<>();
			
			// Create DFA transition table for all states
			for(State state : State.values()) {
				transitionTable.put(state, new HashMap<>());
			}
			
			// Define state transitions
			transitionTable.get(State.q0).put('0', State.q1);
			transitionTable.get(State.q0).put('1', State.q0);
			transitionTable.get(State.q1).put('0', State.q1);
			transitionTable.get(State.q1).put('1', State.q2);
			transitionTable.get(State.q2).put('0', State.q1);
			transitionTable.get(State.q2).put('1', State.q0);
		}
		
		public boolean process(String input) {
			currentState = State.q0;
			
			for(char symbol : input.toCharArray()) {
				Map<Character, State> stateTransitions = transitionTable.get(currentState);
				
				if (stateTransitions.containsKey(symbol)) {
					currentState = stateTransitions.get(symbol);
				} else {
					// If symbol not in transition table, go back to starting state
					currentState = State.q0;
				}
			}
			
			return currentState == State.q2;
		}
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		DFA dfa = new DFA();
		
		System.out.println("---[DFA String Checker Program]---");
		
		while(true) {
			System.out.print("\nInput string (0, 1). Input 'x' to exit: ");
			String input = scan.nextLine().toLowerCase();
			
			if(!input.equals("x")) {
				if(input.matches("[01]+")) {
					if(dfa.process(input)) {
						System.out.println("The string is accepted.");
					} else {
						System.out.println("The string is rejected.");
					}
				} else {
					System.out.println("Invalid input. Please input a valid string.");
				}
			} else {
				System.out.print("Program exited.");
				break;
			}
		}
		
		scan.close();
	}
}