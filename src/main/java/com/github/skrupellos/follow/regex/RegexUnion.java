package com.github.skrupellos.follow.regex;

import com.github.skrupellos.follow.tree.TreeNode;
import java.util.List;
import java.util.LinkedList;

public class RegexUnion extends RegexIntNode {
	private static List<TreeNode> cnstHeler(TreeNode left, TreeNode right) {
		List<TreeNode> subs = new LinkedList<TreeNode>();
		subs.add(left);
		subs.add(right);
		return subs;
	}
	
	public RegexUnion(TreeNode left, TreeNode right) {
		this(cnstHeler(left, right), null);
	}
	
	public RegexUnion(List<TreeNode> subs) {
		this(subs, null);
	}
	
	public RegexUnion(TreeNode left, TreeNode right, RegexIntNode parent) {
		this(cnstHeler(left, right), parent);
	}
	
	public RegexUnion(List<TreeNode> subs, RegexIntNode parent) {
		super(parent, subs);
	}
	
	@Override
	public String toString() {
		return "+";
	}
	
	public TreeNode getLeft() {
		return getChild(0);
	}
	
	public TreeNode getRight() {
		return getChild(1);
	}
	
	public TreeNode replaceLeft(TreeNode sub) {
		return replaceChild(0, sub);
	}
	
	public TreeNode replaceRight(TreeNode sub) {
		return replaceChild(1, sub);
	}
	
	@Override
	protected void invariant(List<TreeNode> newChildren) {
		if(newChildren.size() != 2) {
			throw new IllegalArgumentException("RegexUnion must have exactly 2 children");
		}
	}
}
