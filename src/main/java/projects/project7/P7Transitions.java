package projects.project7;

import nfa.Transitions;
import nfa.State;
import com.github.skrupellos.follow.MapHelper;

public class P7Transitions extends MapHelper<String, P7State> implements Transitions {
	/*package*/ P7Transitions add(String character, P7State state) {
		define(character, state);
		return this;
	}
	
	
	public State getStateForCharacter(String s) {
		return lookup(s);
	}
}