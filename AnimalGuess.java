public class AnimalGuess {
    
    public static void main(String[] args){

    /* to keep track of position in binary tree use breadth-frist approach and make each node a queue + iterate through(USE FOR PHASE 3 NOT HERE)*/
        DecisionTree tree = new DecisionTree("Human-ish?");
            tree.setLeft(new DecisionTree("Royalty?"));
            tree.setRight(new DecisionTree("Animal?"));
            tree.getLeft().setLeft(new DecisionTree("Queen/Princess?"));
    }


}
