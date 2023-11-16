import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Queue;
import java.util.LinkedList;

/*
*   Allows the user to play the guessing game 
*/
public class CharacterGuess {

    /**
     * The main loop for the game 
     * @param tree  The tree being used in the game 
     */
    public static void game(DecisionTree tree) {
        Scanner userInput = new Scanner(System.in);
        String playAgain = "";

        while (!playAgain.equals("no")) {
            System.out.println("Think of a character!");
            String path = "";

            while (tree.followPath(path).getLeft() != null && tree.followPath(path).getRight() != null) {

                if (path.equals("")) {
                    System.out.println(tree.followPath(path).getData());
                }

                String response = userInput.nextLine();

                if (response.equals("yes")) {
                    path += "Y";
                    if (tree.followPath(path).getLeft() == null && tree.followPath(path).getRight() == null) {
                        break;
                    }
                    System.out.println(tree.followPath(path).getData());
                } else if (response.equals("no")) {
                    path += "N";
                    if (tree.followPath(path).getLeft() == null && tree.followPath(path).getRight() == null) {
                        break;
                    }
                    System.out.println(tree.followPath(path).getData());
                }

            }

            String guessedCharacter = tree.followPath(path).getData();
            System.out.println("Is your character " + guessedCharacter + "?");
            String response = userInput.nextLine();

            if (response.equals("yes")) {
                System.out.println("I knew it!");
            } 
            else if (response.equals("no")) {
                System.out.println("Uh oh.. I got it wrong");
                System.out.println("Who was your character so I can remember them when you play again?");
                String actual = userInput.nextLine();
                System.out.println("Could you give me a yes or no question that would help me choose between " + actual + " and " + tree.followPath(path).getData() + ".");
                String actualAnimalQuestion = userInput.nextLine();
                System.out.println("Would you answer yes to this question for " + actual);
                String actualQA = userInput.nextLine();

                // take path of original answer; replace it with new question, and use original
                // answer and actual answer as the children of the question.
                tree.followPath(path).setData(actualAnimalQuestion);
                if (actualQA.equals("yes")) {
                    tree.followPath(path).setLeft(new DecisionTree(actual));
                    tree.followPath(path).setRight(new DecisionTree(guessedCharacter));
                }
                if (actualQA.equals("no")) {
                    tree.followPath(path).setRight(new DecisionTree(actual));
                    tree.followPath(path).setLeft(new DecisionTree(guessedCharacter));
                }


            }
            System.out.println("Play again?");
            playAgain = userInput.nextLine();
        } 
        userInput.close();
    }


    /**
     * Runs the main program depending on whether or not you're reading the tree from the file (to use hard-coded tree, type any string in command line to start)
     * @param args  The arguments used in the program
     * @throws FileNotFoundException  In case the program cannot find the file the user choses
     */
    public static void main(String[] args) throws FileNotFoundException{
        if(args.length > 0){
            DecisionTree tree = new DecisionTree("Human-ish?", null, null);
                tree.setLeft(new DecisionTree("Royalty?"));
                tree.setRight(new DecisionTree("Animal?"));
                tree.getLeft().setLeft(new DecisionTree("Queen or Princess?"));
                tree.getLeft().setRight(new DecisionTree("Finn"));
                tree.getLeft().getLeft().setLeft(new DecisionTree("Bright colors?"));
                tree.getLeft().getLeft().getLeft().setRight(new DecisionTree("Marceline"));
                tree.getLeft().getLeft().getLeft().setLeft(new DecisionTree("Bubblegum"));
                tree.getLeft().getLeft().setRight(new DecisionTree("Cold?"));
                tree.getLeft().getLeft().getRight().setLeft(new DecisionTree("Ice King"));
                tree.getLeft().getLeft().getRight().setRight(new DecisionTree("Flame King"));
                tree.getRight().setLeft(new DecisionTree("Common pet?"));
                tree.getRight().getLeft().setLeft(new DecisionTree("Jake"));
                tree.getRight().getLeft().setRight(new DecisionTree("Tree Trunks"));
                tree.getRight().setRight(new DecisionTree("Smiling?"));
                tree.getRight().getRight().setRight(new DecisionTree("Lumpy Space Princess"));
                tree.getRight().getRight().setLeft(new DecisionTree("Food?"));
                tree.getRight().getRight().getLeft().setLeft(new DecisionTree("Peppermint Butler"));
                tree.getRight().getRight().getLeft().setRight(new DecisionTree("BMO"));

            game(tree);

        }   
        else{
            String filename = args.toString();
            game(DecisionTree.fileTree(filename));
            


        }
    }
}

    

