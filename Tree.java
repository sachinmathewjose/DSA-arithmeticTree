package arithematic;

public class Tree {
	protected Node root;
	private char symbol;
	private String expression;
	private int pos = 0;
	
	static protected class Node {
		Object element;
		Node left, right;
		public Node() {
			element = "";
			left = right = null;
		}
	}
	
	public Tree(String v_expression) {
		root = null;
		this.expression = v_expression;
		createTree();
	}
	public void createTree() {
		root = new Node();
		root = makeExpression(root);
	}
	private Node makeExpression(Node n) {
		Node left = new Node();
		left = makeTerm(left);
		nextSymbol();
		if (symbol == '+' || symbol == '-') {
			pos++;
			n.left = left;
			n.element = symbol;
			n.right = makeExpression(new Node());
		} 
		else {
			n = left;
		}
		return n;
	}
	
	private Node makeTerm(Node n) {
		Node left = new Node();
		left = makeFactor(left);
		nextSymbol();
		if (symbol == '*' || symbol == '/') {
			pos++;
			n.left = left;
			n.element = symbol;
			n.right = makeTerm(new Node());
		} else {
			n = left;
		}
		return n;
	}
	
	private Node makeFactor(Node n) {
		nextSymbol();
		if (symbol == '(') {
			pos++;
			n = makeExpression(new Node());
			nextSymbol();
			if (symbol == ')')
				pos++;
			else
				System.out.println("missing ')' ");
		} else {
			n.element = getConstant();
		}
		return n;
	}
	
	private String getConstant() {
		StringBuilder sb = new StringBuilder();
		for (int i = pos; i < expression.length(); i++) {
			if (expression.substring(i, i + 1).matches("[0-9,.]")) {
				sb.append(expression.charAt(i));
				continue;
			}
			pos = i;
			return sb.toString();
		}
		pos = expression.length();
		return sb.toString();
	}
	private void nextSymbol() {
		for (int i = pos; i < expression.length(); i++) {
			if (expression.substring(i, i + 1).matches("[0-9]"))
				continue;
			symbol = expression.charAt(i);
			return;
		}
		symbol = ' ';
	}
	
	public void printTree() {
		printUtil(this.root, 0);
	}
	public double evaluvate(Node n) {
		if (n != null) {
			if (n.left != null && n.right != null) {
				double right = evaluvate(n.right);
				double left = evaluvate(n.left);
				if (n.element.equals('+')) {
					return left + right;
				} else if (n.element.equals('-')) {
					return left - right;
				} else if (n.element.equals('*')) {
					return left * right;
				} else if (n.element.equals('/')) {
					return left / right;
				}
				System.out.println("Error: Symbol not found");
				return 0.0d;
			}
			try {
				return Double.parseDouble((String) n.element);
			} 
			catch (NumberFormatException ex) {
				System.out.println("Error: number not found");
				return 0.0d;
			}
		}
		System.out.println("Error: Reached a null node");
		return 0.0d;
	}
	
	public static void printUtil(Node root, int space)
	{
	    if (root == null)
	        return;
	    // Increase distance between levels
	    space ++;
	    // Process right child first
	    printUtil(root.right, space);
	    // Print current node after space
	    System.out.print("\n");
	    for (int i = 1; i < space; i++)
	    	System.out.print("      ");
	    System.out.println(root.element);
	    // Process left child
	    printUtil(root.left, space);
	}
}
