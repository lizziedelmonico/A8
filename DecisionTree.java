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
      //this.left = left;
      super.setLeft(right);
    } else {
      throw new UnsupportedOperationException("Tried to add non-DecisionTree as child");}
    }


    public String followPath(DecisionTree node, String answers){
        for(int i = 0; i <= answers.length(); i++){
            if(answers.charAt(i) == ('Y')){
                String data = node.getLeft().getData();
                return data;
            }
            if(answers.charAt(i) == ('N')){
                String data = node.getRight().getData();
                return data;
            }
        }
        System.out.println(data);
        return data;
        
    }
}



