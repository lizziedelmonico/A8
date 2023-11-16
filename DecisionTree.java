import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

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

    /**
     * Traces the path of the tree from the root
     * @param answers   The string of yes/no answers (in the form of "Y" or "N")
     * @return  The path following the specific string of answers the user provides
     */
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

    /**
     * Creates tree from different combinations of user input
     * @param answer  The user's answer to the question
     * @param question  The question being asked to the user
     */
    public void createTree(String answer, String question){
      String answer1 = answer.substring(0, answer.length() - 1);
      DecisionTree finalTree = followPath(answer1);
      if(answer.substring(answer.length() - 1).equalsIgnoreCase("Y")){
        DecisionTree newTree = new DecisionTree(question);
        finalTree.setLeft(newTree);
      } else if(answer.substring(answer.length() - 1).equalsIgnoreCase("N")){
        DecisionTree newTree = new DecisionTree(question);
        finalTree.setRight(newTree);
      } else{
        System.out.println(answer);
        throw new IllegalArgumentException("You must only type in Y or N");
      }
    }

    /**
     * Reads a file and creates a tree using the information found
     * @return  The root of the new tree
     */
    public static DecisionTree fileTree(String filename){
      Scanner file = new Scanner(System.in);

      String line = file.nextLine();
      DecisionTree root = new DecisionTree(line.substring(1));
      while(file.hasNextLine()){
          line = file.nextLine();
          String[] words = line.split("\\s+");
          root.createTree(words[0], line.substring(words[0].length() + 1));
      }
      file.close();
      return root;
  }

  public static void writeTree(){
    Queue<DecisionTree> output = new LinkedList<>();
    Queue<DecisionTree> pathqueue = new LinkedList<>();
    DecisionTree root = DecisionTree.fileTree(null);

    output.add(root);

    while(!output.isEmpty()){
        DecisionTree current = output.poll();
        pathqueue.poll();

        if(current.getLeft() != null){
            output.add(current.getLeft());
            DecisionTree new_path = current.followPath("Y");
            pathqueue.add(new_path);
        }
        if(current.getRight() != null){
            output.add(current.getRight());
            DecisionTree new_path = current.followPath("N");
            pathqueue.add(new_path);
        }
        else{
            continue;
        }
    }
    System.out.println(pathqueue);
  }

}



