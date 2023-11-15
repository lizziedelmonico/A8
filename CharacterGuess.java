import java.util.Scanner;
import java.io.File;

public class CharacterGuess {
    private static DecisionTree tree = new DecisionTree("Human-ish?");
    
    public void characterTree(){
            tree.setLeft(new DecisionTree("Royalty?"));
            tree.setRight(new DecisionTree("Animal?"));
            tree.getLeft().setLeft(new DecisionTree("Queen/Princess?"));
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
    }

    public static DecisionTree fileTree(String filename){
        Scanner file = null;
        DecisionTree root = null;
        try{
            file = new Scanner(new File(filename));
        } catch(Exception e){
            System.err.println("Cannot locate file");
            System.exit(-1);
        }
        String line = file.nextLine();
        root = new DecisionTree(line.substring(1));
        while(file.hasNextLine()){
            line = file.nextLine();
            String[] words = line.split("\\s+");
            root.makeTreeInPath(words[0], line.substring(words[0].length() + 1));
        }
        file.close();
        return root;
    }
    public static void main(String[] args){
        String start = "Y";
    /* to keep track of position in binary tree use breadth-frist approach and make each node a queue + iterate through(USE FOR PHASE 3 NOT HERE)*/
       Scanner scanner = new Scanner(System.in);
       DecisionTree current = tree;
       
        System.out.println(current);

        while(current.getLeft() != null && current.getRight() != null){
            String answers = scanner.next();

            if(answers.equals("YES") || answers.equals("Yes") || answers.equals("yes") || answers.equals("Y")){
                System.out.println(current.getLeft().getData());
                current = current.getLeft();
            }
            if(answers.equals("NO") || answers.equals("No") || answers.equals("no") || answers.equals("N")){
                System.out.println(current.getRight().getData());
                current = current.getRight();
            }
            else{
                System.out.println("I'm sorry.. I didn't quite understand that. Please try again!");
                System.out.println(current);
            }
        }

    }
}
    

