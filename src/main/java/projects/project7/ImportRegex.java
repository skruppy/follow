package projects.project7;

import com.github.skrupellos.follow.regex.RegexVisitor;
import com.github.skrupellos.follow.regex.RegexNode;
import com.github.skrupellos.follow.regex.RegexCatenation;
import com.github.skrupellos.follow.regex.RegexEmptySet;
import com.github.skrupellos.follow.regex.RegexEpsilon;
import com.github.skrupellos.follow.regex.RegexStar;
import com.github.skrupellos.follow.regex.RegexSymbol;
import com.github.skrupellos.follow.regex.RegexUnion;
import com.github.skrupellos.follow.regex.RegexExtNode;
import com.github.skrupellos.follow.AlgorithmBase;
import regex.RegularExpressionVisitor;
import regex.AlternationExpression;
import regex.Char;
import regex.ConcatenationExpression;
import regex.KleeneStarExpression;
import regex.OptionalExpression;
import regex.PlusExpression;
import regex.RegularExpression;


public class ImportRegex extends AlgorithmBase<RegularExpression, RegexNode<String>> implements RegularExpressionVisitor {
	public static RegexNode<String> apply(RegularExpression root) {
		return (new ImportRegex(root)).result();
	}
	
	
	public ImportRegex(RegularExpression root) {
		super(root);
		root.accept(this);
	}
	
	
	public void post(AlternationExpression expression){
		define(expression, new RegexUnion<String>(
			lookup(expression.getLHS()),
			lookup(expression.getRHS())
		));
	}
	
	
	public void visit(Char character) {
		define(character, new RegexSymbol<String>(character.getCharacter()));
	}
	
	
	public void post(ConcatenationExpression expression){
		define(expression, new RegexCatenation<String>(
			lookup(expression.getLHS()),
			lookup(expression.getRHS())
		));
	}
	
	
	public void post(KleeneStarExpression expression) {
		define(expression, new RegexStar<String>(
			lookup(expression.getChild())
		));
	}
	
	
	public void post(OptionalExpression expression) {
		define(expression, new RegexUnion<String>(
			new RegexEpsilon<String>(),
			lookup(expression.getChild())
		));
	}
	
	
	public void post(PlusExpression expression) {
		define(expression, new RegexCatenation<String>(
			lookup(expression.getChild()),
			new RegexStar<String>(
				lookup(expression.getChild())
			)
		));
	}
	
	
	public void post(RegularExpression expression) {
		throw new RuntimeException("Forgot how to "+expression);
	}
}
