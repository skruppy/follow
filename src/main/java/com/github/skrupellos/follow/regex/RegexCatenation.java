package com.github.skrupellos.follow.regex;

import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;


public class RegexCatenation extends RegexIntNode {
	// Only children
	public RegexCatenation(RegexNode left, RegexNode right) {
		super(null, Arrays.asList(left, right));
	}
	
	public RegexCatenation(List<RegexNode> subs) {
		super(subs);
	}
	
	// Parent & children
	public RegexCatenation(RegexNode left, RegexNode right, RegexIntNode parent) {
		super(parent, Arrays.asList(left, right));
	}
	
	public RegexCatenation(List<RegexNode> subs, RegexIntNode parent) {
		super(parent, subs);
	}
	
	@Override
	public String toString() {
		return "meow";
	}
	
	public RegexNode left() {
		return getChild(0);
	}
	
	public RegexNode right() {
		return getChild(1);
	}
	
	public RegexNode replaceLeft(RegexNode sub) {
		return replaceChild(0, sub);
	}
	
	public RegexNode replaceRight(RegexNode sub) {
		return replaceChild(1, sub);
	}
	
	@Override
	protected void invariant(List<RegexNode> newChildren) {
		if(newChildren.size() != 2) {
			throw new IllegalArgumentException("RegexCatenation must have exactly 2 children");
		}
	}
	
	public RegexNode accept(RegexVisitor visitor) {
		visitor.pre(this);
		left().accept(visitor);
		visitor.inter(this);
		right().accept(visitor);
		visitor.post(this);
		return this;
	}
}
