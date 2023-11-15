import java.io.File;
import java.util.Scanner;


public class CharacterGuess {
    
    public static void runGame(DecisionTree tree) {
        // Game
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
                System.out.println("Who was your character?");
                String actual = userInput.nextLine();
                System.out.println("Type a yes or no question that would distinguish between a " + actual + " and a " + tree.followPath(path).getData() + ".");
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

    /* FOR MAIN, IF NO ARGS, DO HARD CODE TREE */

    public static void main(String[] args){
        if(args.length == 0){
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

            runGame(tree);

        }   
        else{
            String start = "Y";
            String filename = args.toString();
        
            while(start.equalsIgnoreCase("Y")){
            
                DecisionTree currentTree = DecisionTree.fileTree(filename);
                String treeRes = "";
                String treeReses = "";
                DecisionTree root = DecisionTree.fileTree(filename);

                while(!currentTree.isLeaf()){
                    boolean leafPass = false;
                    while(!leafPass){
                        try{
                            Scanner question = new Scanner(System.in);
                            System.out.println(currentTree.getData());
                            treeRes = question.nextLine();
                            treeReses += treeRes;
                            currentTree = root.followPath(treeReses);
                            leafPass = true;
                        } catch(Exception e){
                            System.out.println("please enter either Y or N");
                            treeReses = treeReses.substring(0, treeReses.length() - treeRes.length());
                        }
                    }
                }
                System.out.println(currentTree.getData());
                boolean whilePass = false;
                while(!whilePass){
                    Scanner question = new Scanner(System.in);
                    System.out.println("Would you like to play again?");
                    start = question.nextLine();
                    if(!start.equalsIgnoreCase("Y") && !start.equalsIgnoreCase("N")){
                        System.out.println("Please enter either Y or N");
                    } else{
                        whilePass = true;
                    }
                }
            }
        }
    }
}

    

