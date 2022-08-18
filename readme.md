# Authors
This assginment is completed by Sonora Halili and Frankie Fan. We get started on the task together last Thursday, and completed it late on Tuesday. We had some questions answered during the code review session on Friday, as well as the help from professors during class.

# Process
* **Phase 1 & Phase 2:**
  * Phase 1 went smoothly. We had some questions about building the decision tree and were a little bit confused about the parameters the constructor should take in (as we were unsure about whether it was building a "tree" at once or building single nodes). We got most of our questions answered in class, and wrote tests to check if our phase 1 methods were working.
  * However, we underestimated how difficult phase 2 would be. In the beginning, we thought we just needed to create a duplicate of the conversation in the instructions. Sonora built an `answeredYes()` method to save us time in examining the answer in user input (i.e:  `"Y"`, `"yes"`, `"Yes"`, `"YES"` are all accepted as "yes-es", our method saves us many lines of code). Sonora also wrote the game-playing loop and `helpLearn(DecisionTree)` method that expands the tree. She also made sure to add `input` and `treeToFile` in the main method of `AnimalGuess` to allow for the program to run from the console and update the txt file after it finished. This portion of the assignment kept producing what seemed to be unreasonable errors. The path wasn't being followed right, despite continuous adjustments to the code. However, after re-formatting the game-playing loop, these issues got resolved. 

* **Phase 3:**
    * When reading the input from a file and building a tree from there, Frankie could only have the method return the last node being built instead of the whole decision tree. With Jordan's help during Monday's class, we found that we should return the root (so essentially all the nodes build upon it) instead of the current node, and fixed the error. However, upon trying to play the game based on `AnimalTree.txt`, Sonora noticed that the structure of the tree wasn't right, returning null pointer exceptions where there shouldn't have been. Sonora fixed this by locating the parent of each node past the first row using `followPath()` and set their children as either left or right depending on the last letter of their path. As per the Output portion, Sonora wrote a method called `treeToFile` that implements the BreadthFirst algorithm, recording each level of the tree in order from left to right and printing it on a txt file. To save the path of each node as it gets read/ created in `helpLearn()`, she also created a field for `path` in `DecisionTree`, along with a getter and a setter (`getPath()` and `setPath()`). 


# Resource & Exploration

- **Marta Sapizhak:** We had code review with Marta on Friday. We were in the early stages of the assignment, but we talked a little bit about out `followPath()` methods and debated on whether they should be recursive or iterative.

- **Emi Neuwalder:** Emi was another member of our code review team. She was also working on phase 1, and we helped her clarify some basic points such as deciding what class some of the methods should go.

- **TA hours:** We attended TA hours on Sunday night, and didn't really make as much progress as we had hoped, but Ananda helped fix a small bug in the input method.    

- **Office hours:**  We attended Jordan's office hours on Tuesday morning to get help with repairing the loop and get started on recording the path of the tree. Got really good help and finally understood exactly what our program is supposed to do.

  
# References
Below is a list of resources we've used:

- [Breath First Search Algorithm](https://www.geeksforgeeks.org/level-order-tree-traversal/): used in our `treeToFile()` method. 

# Final Remarks 
This assignment came with challenges that we hadn't seen in a minute. We've noticed that instructions have gotten so much shorter and more vague with every new assignment, which makes for a lot of decisions to be made on our end. While we're sure that is making us better programmers, it is also taking more time to really visualize what the end result should look like, allowing for so many detours. Over the one week it took to finish this assignment, the fundamental structure of each of our methods has changed a lot, making for a lot of failed prototypes. It got us very comfortable working with trees, however, as well as some of the tree traversing algorithms. Also, lots of fun to play. 