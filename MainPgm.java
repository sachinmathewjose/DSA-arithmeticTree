package arithematic;

import java.util.Scanner;

public class MainPgm {

	public static void main(String[] args) {
		System.out.println("Enter the expression: ");
		Scanner scanner = new Scanner(System.in);
		String expression_str = scanner.nextLine();
		Tree tree = new Tree(expression_str);
		System.out.println("Printing the tree from left to right as a tree structure: ");
		tree.printTree();
		double result = tree.evaluvate(tree.root);
		System.out.printf("\nResult of the expression is :%f", result );
	}

}
