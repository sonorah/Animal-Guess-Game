import java.util.*;
import java.io.*;

/**
 *  Implements 20-Questions to Guess an Animal
 *
 *  @author Sonora Halili, Frankie Fan
 *  @version April 2022
 *
 */
public class AnimalGuess {

  /** Main method to run the guessing game */
  public static void main (String args []) {
    boolean keepPlaying = true;
    DecisionTree root = input (args[0]); //build tree from input file
    DecisionTree current = new DecisionTree(root); //copy of tree
    
    
    while (keepPlaying) { //a round of each game
      
      while (!current.isLeaf()) { //traverse while the tree is a branch
        System.out.println(current.getData());
        
        if (answeredYes()) { //move left if yes
          current = current.getLeft();
        } else {
          current = current.getRight(); //move right if no
        }
      }

      if (current.isLeaf()) { //one single last guess
        System.out.println(current.getData());
        
        if (answeredYes()) { 
          System.out.println("Guessed it!");
        } else { //time to expand tree
          //record current location on tree
          String initPath = current.path;
          //expand tree and get a copy of the expansion
          DecisionTree subTree = helpLearn(current);

          //store data from subTree
          String currentData = subTree.getData();
          String right = subTree.getRight().getData();
          String left = subTree.getLeft().getData();
          String initPathRight = subTree.getRight().path;

          //set data
          current.setData(currentData);
          current.setLeft(new DecisionTree (left));
          current.setRight(new DecisionTree (right));

          //set paths
          current.setPath(initPath);
          current.getRight().setPath(initPathRight);
          current.getLeft().setPath(subTree.getLeft().path);

        }

        System.out.println("Play again?");
          
        if (answeredYes()) {
          current = root; //updates location at the root of tree
          continue; //plays next round
        } else {
          root.treeToFile (root, false, args[0]); //records changes on txt file
          keepPlaying = false; //stop playing
            
        }
      }     
    }
  }  
  

  /**   
   *.    Expand the @param DecisionTree via user input and
   *.    @return current as a new tree.
   */
  public static DecisionTree helpLearn (DecisionTree current) {
    
    String parentPath = current.path; //mark the point we're at
    Scanner answer = new Scanner(System.in);
    System.out.println("Got it wrong. Help me learn.");  
    System.out.println("What was your animal?");
    String animal = answer.nextLine(); //store user animal
    
    System.out.println("What's a yes/ no question to distinguish " 
                     + animal + " from "+ current.getData()+ "?");
    
    String question = answer.nextLine(); //store question   
    System.out.println("Is that a Yes or No for " + animal + "? ");
    
    DecisionTree newRoot = new DecisionTree (question); //new root with question
    newRoot.setPath(parentPath);

    //set left/right to animal or current, accordingly
    if (answeredYes()) {
      newRoot.setLeft(new DecisionTree(animal));
      newRoot.getLeft().setPath(parentPath + "Y" );
      newRoot.setRight(current);
      newRoot.getRight().setPath(parentPath + "N" );
    } else {
      newRoot.setRight(new DecisionTree(animal));
      newRoot.getRight().setPath(parentPath + "N" );
      newRoot.setLeft(current);
      newRoot.getLeft().setPath(parentPath + "Y" );
    }

    
    current = newRoot;
    return newRoot;
  }
  


  /**  @return boolean if user answered yes/no */
  public static boolean answeredYes() {
    
    boolean answer = false;
    Scanner scanner = new Scanner(System.in);
    String resp = scanner.nextLine();
     
    if (resp.equals("no") || resp.equals("NO") 
         || resp.equals("No")|| resp.equals("nah") 
         || resp.equals("n") || resp.equals("N")) {
      answer = false;  
    } else if (resp.equals("yes") || resp.equals("YES") 
            || resp.equals("Yes")|| resp.equals("yeah") 
            || resp.equals("y") || resp.equals("Y"))  {
      answer = true;
    } else {
      System.out.println("Enter a valid response (yes/no).");
      answeredYes(); //recursively calls itself until valid response
    }
     
    return answer;
  }
  

  /**  
  *.   Reads in a @param txt file and returns it
  *.   as a @return Decision Tree
  */
  public static DecisionTree input(String Filename){
   
    Scanner file = null;
    
    try {
      file = new Scanner (new File(Filename)); //read in txt file

    } catch (FileNotFoundException e) {
      System.err.println("Cannot locate file.");
      System.exit(-1);
    }

  
    DecisionTree root = null;
    
    while (file.hasNextLine()) {

      String text = file.nextLine(); //record each line of txt file
      int n = text.indexOf(' '); //mark index of space
      
      if (n == 0) { //if we're at the root of tree
        root = new DecisionTree(text.substring(n+1)); //set root as data of that node
        root.setPath(" "); //set path to empty space
      } else {
        char dir = text.charAt(n-1); //stores the last letter of path        

        if (n == 1) { //if we're at level one
          if (dir == 'Y') { //append node to left if Y
            root.setLeft(new DecisionTree (text.substring(n+1)));
            root.getLeft().setPath(text.substring(0,n)); //set path
          } else if (dir == 'N') { //append node to right if N
            root.setRight(new DecisionTree (text.substring(n+1)));  
            root.getRight().setPath(text.substring(0,n)); //set path
          }  
          
        } else if (n > 1) { //if we're at level 2 or higher
          DecisionTree parent = root.followPath(text.substring(0,n-1), root); //locate parent
          if (dir == 'Y') { //add to left of parent if Y
            parent.setLeft(new DecisionTree (text.substring(n+1)));
            parent.getLeft().setPath(text.substring(0,n)); //set path
          } else if (dir == 'N') { //add to right of parent if N
            parent.setRight(new DecisionTree (text.substring(n+1)));
            parent.getRight().setPath(text.substring(0,n)); //set path
          }
          
        }       
      }
    }     
    file.close();   
    return root;
  }  
}