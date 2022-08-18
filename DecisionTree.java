import java.io.*;  
import java.util.*;  

/**
 *  Implements a binary decision tree
 *
 *  @author Sonora Halili, Frankie Fan
 *  @version April 2022
 *
 */
public class DecisionTree extends BinaryTree<String> {
  
  /** leaf constructor */
  public DecisionTree(String s) {
    super(s);
  }

  /** Field to store path of each node */  
  public String path; 

  /** @return path */
  public String getPath() {
    if (this.path == null) { 
      return ("");
    } else {
      return this.path; 
    }
  }

  /** Set path to @param String*/ 
  public void setPath(String path) {
    this.path = path;
  }


  
  /** Set left child of tree as @param left */
  public void setLeft(BinaryTree<String> left) {
    if ((left==null)||(left instanceof DecisionTree)) {
      super.setLeft(left);
    } else {
      throw new UnsupportedOperationException();
    }
  }

  /** @return the left child of the current node */
  public DecisionTree getLeft() {
    return (DecisionTree)super.getLeft();
  }

  /** Set right child of tree as @param right */
  public void setRight(BinaryTree<String> right) {
    if ((right==null)||(right instanceof DecisionTree)) {
      super.setRight(right);
    } else {
      throw new UnsupportedOperationException();
    }
  }

  /** @return the right child of the current node */  
  public DecisionTree getRight() {
    return (DecisionTree)super.getRight();
  }
  
  /** Copy Constructor*/
  public DecisionTree(DecisionTree tree){
    super(tree.getData());
    this.setLeft(tree.getLeft());
    this.setRight(tree.getRight());
  } 


  /** 
  *. Trace path from root and @return respecive node 
  *. @param String path and @param node tree to trace.
  */
  public DecisionTree followPath(String s, DecisionTree node) {
    DecisionTree result = null;

    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == 'Y'){
        result = node.getLeft();
        node = result; //update result
      } else if (s.charAt(i) == 'N'){
        result = node.getRight();
        node = result;
      } else {
        System.out.println("String should be Y or N");
      }      
    }
    
    return result;
  }



  /** 
  *. Write @param tree into a @param txt file using Breadth First Search. 
  *. @param overwrite if boolean is true
  *. Updates paths as necessary.
  */  
  public void treeToFile (DecisionTree tree, boolean overwrite, String fileName) {

    try {
      
      Queue <DecisionTree> result = new LinkedList<DecisionTree>(); 
      result.add(tree); //store tree in queue
      
      PrintWriter out = new PrintWriter(new FileWriter(fileName), overwrite); //writer
     
      while (!result.isEmpty()) { //while there are nodes in queue
        DecisionTree temp = result.remove(); //remove first node

        if (temp.path == " ") { //if this is the root of tree
          out.println(temp.path + temp.getData()); //avoid adding new space
        } else {
          out.println(temp.path + " " + temp.getData()); //print path and data
        }
          
        
        if (temp.getLeft() != null ) {
          result.add(temp.getLeft()); //update left node
        } if (temp.getRight() != null ) {
          result.add(temp.getRight()); //update right node
        }
      }

      out.close();
      
    } catch (Exception e) {
      if (e instanceof FileNotFoundException)
        System.out.println("File not found");
        System.exit(0);
    }
  }    
   
}



