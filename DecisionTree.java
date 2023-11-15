import java.util.Scanner;
import java.io.File;

/** 
 *  Class to represent a decision tree 
 */
public class DecisionTree extends BinaryTree<String> {
  /** leaf constructor */
  public DecisionTree(String data) {
    super(data);
  }

  /** branch constructor */
  public DecisionTree(String data, DecisionTree left, DecisionTree right) {
    super(data,left,right);
  }
  
  /** accessor */
  public DecisionTree getLeft() {
    return (DecisionTree)super.getLeft();
  }

  /** manipulator */
  public void setLeft(BinaryTree<String> left) {
    if (left instanceof DecisionTree) {
      //this.left = left;
      super.setLeft(left);
    } else {
      throw new UnsupportedOperationException("Tried to add non-DecisionTree as child");
    }
  }

    /** accessor */
  public DecisionTree getRight() {
    return (DecisionTree)super.getRight();
  }

  /** manipulator */
  public void setRight(BinaryTree<String> right) {
    if (right instanceof DecisionTree) {
      //this.right = right;
      super.setRight(right);
    } else {
      throw new UnsupportedOperationException("Tried to add non-DecisionTree as child");}
    }


    public DecisionTree followPath(String answers){
        if(answers.length() == 0){
          return this;
        }
        if(answers.substring(0, 1).equals("Y")){
          return getLeft().followPath(answers.substring(01));
        }
        if(answers.substring(0, 1).equals("N")){
          return getRight().followPath(answers.substring(01));
        }
        else{
          throw new UnsupportedOperationException("Not a Y or N found in the path");
        }
    }

    public void createTree(String input, String question){
      String input1 = input.substring(0, input.length() - 1);
      DecisionTree finalTree = followPath(input1);
      if(input.substring(input.length() - 1).equalsIgnoreCase("Y")){
        DecisionTree newTree = new DecisionTree(question);
        finalTree.setLeft(newTree);
      } else if(input.substring(input.length() - 1).equalsIgnoreCase("N")){
        DecisionTree newTree = new DecisionTree(question);
        finalTree.setRight(newTree);
      } else{
        System.out.println(input);
        throw new IllegalArgumentException("You must only type in Y or N");
      }
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
          root.createTree(words[0], line.substring(words[0].length() + 1));
      }
      file.close();
      return root;
  }
}



