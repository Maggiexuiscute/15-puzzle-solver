/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Node {
    private boolean visited;

    private String state;
    private ArrayList<Node> children;
    private Node parent;
    private int cost;
    private int estimatedCostToGoal;
    private int totalCost;
    private int depth;
    private NodeUtil util;
    
    
    public static class NodeUtil {
    public enum MovementType{UP, DOWN, LEFT, RIGHT};
    public static List<String> getSuccessors(String state) {
        List<String> successors = new ArrayList<String>();

        switch (state.indexOf("0")) {
            case 0: {
                successors.add(state.replace(state.charAt(0), '*').replace(state.charAt(1), state.charAt(0)).replace('*', state.charAt(1)));
                successors.add(state.replace(state.charAt(0), '*').replace(state.charAt(4), state.charAt(0)).replace('*', state.charAt(4)));
                break;
            }
            case 1: {
                successors.add(state.replace(state.charAt(1), '*').replace(state.charAt(0), state.charAt(1)).replace('*', state.charAt(0)));
                successors.add(state.replace(state.charAt(1), '*').replace(state.charAt(2), state.charAt(1)).replace('*', state.charAt(2)));
                successors.add(state.replace(state.charAt(1), '*').replace(state.charAt(5), state.charAt(1)).replace('*', state.charAt(5)));
                break;
            }
            case 2: {

                successors.add(state.replace(state.charAt(2), '*').replace(state.charAt(1), state.charAt(2)).replace('*', state.charAt(1)));
                successors.add(state.replace(state.charAt(2), '*').replace(state.charAt(3), state.charAt(2)).replace('*', state.charAt(3)));
                successors.add(state.replace(state.charAt(2), '*').replace(state.charAt(6), state.charAt(2)).replace('*', state.charAt(6)));
                break;
            }
            case 3: {
                successors.add(state.replace(state.charAt(3), '*').replace(state.charAt(2), state.charAt(3)).replace('*', state.charAt(2)));
                successors.add(state.replace(state.charAt(3), '*').replace(state.charAt(7), state.charAt(3)).replace('*', state.charAt(7)));
                break;
            }
            case 4: {
                successors.add(state.replace(state.charAt(4), '*').replace(state.charAt(0), state.charAt(4)).replace('*', state.charAt(0)));
                successors.add(state.replace(state.charAt(4), '*').replace(state.charAt(5), state.charAt(4)).replace('*', state.charAt(5)));
                successors.add(state.replace(state.charAt(4), '*').replace(state.charAt(8), state.charAt(4)).replace('*', state.charAt(8)));
                break;
            }
            case 5: {
                successors.add(state.replace(state.charAt(5), '*').replace(state.charAt(1), state.charAt(5)).replace('*', state.charAt(1)));
                successors.add(state.replace(state.charAt(5), '*').replace(state.charAt(4), state.charAt(5)).replace('*', state.charAt(4)));
                successors.add(state.replace(state.charAt(5), '*').replace(state.charAt(6), state.charAt(5)).replace('*', state.charAt(6)));
                successors.add(state.replace(state.charAt(5), '*').replace(state.charAt(9), state.charAt(5)).replace('*', state.charAt(9)));
                break;
            }
            case 6: {
                successors.add(state.replace(state.charAt(6), '*').replace(state.charAt(2), state.charAt(6)).replace('*', state.charAt(2)));
                successors.add(state.replace(state.charAt(6), '*').replace(state.charAt(5), state.charAt(6)).replace('*', state.charAt(5)));
                successors.add(state.replace(state.charAt(6), '*').replace(state.charAt(7), state.charAt(6)).replace('*', state.charAt(7)));
                successors.add(state.replace(state.charAt(6), '*').replace(state.charAt(10), state.charAt(6)).replace('*', state.charAt(10)));
                break;

            }
            case 7: {
                successors.add(state.replace(state.charAt(7), '*').replace(state.charAt(3), state.charAt(7)).replace('*', state.charAt(3)));
                successors.add(state.replace(state.charAt(7), '*').replace(state.charAt(6), state.charAt(7)).replace('*', state.charAt(6)));
                successors.add(state.replace(state.charAt(7), '*').replace(state.charAt(11), state.charAt(7)).replace('*', state.charAt(11)));
                break;
            }
            case 8: {
                successors.add(state.replace(state.charAt(8), '*').replace(state.charAt(4), state.charAt(8)).replace('*', state.charAt(4)));
                successors.add(state.replace(state.charAt(8), '*').replace(state.charAt(9), state.charAt(8)).replace('*', state.charAt(9)));
                successors.add(state.replace(state.charAt(8), '*').replace(state.charAt(12), state.charAt(8)).replace('*', state.charAt(12)));
                break;
            }
            case 9: {
                successors.add(state.replace(state.charAt(9), '*').replace(state.charAt(5), state.charAt(9)).replace('*', state.charAt(5)));
                successors.add(state.replace(state.charAt(9), '*').replace(state.charAt(8), state.charAt(9)).replace('*', state.charAt(8)));
                successors.add(state.replace(state.charAt(9), '*').replace(state.charAt(10), state.charAt(9)).replace('*', state.charAt(10)));
                successors.add(state.replace(state.charAt(9), '*').replace(state.charAt(13), state.charAt(9)).replace('*', state.charAt(13)));
                break;
            }
            case 10: {
                successors.add(state.replace(state.charAt(10), '*').replace(state.charAt(6), state.charAt(10)).replace('*', state.charAt(6)));
                successors.add(state.replace(state.charAt(10), '*').replace(state.charAt(9), state.charAt(10)).replace('*', state.charAt(9)));
                successors.add(state.replace(state.charAt(10), '*').replace(state.charAt(11), state.charAt(10)).replace('*', state.charAt(11)));
                successors.add(state.replace(state.charAt(10), '*').replace(state.charAt(14), state.charAt(10)).replace('*', state.charAt(14)));
                break;
            }
            case 11: {
                successors.add(state.replace(state.charAt(11), '*').replace(state.charAt(7), state.charAt(11)).replace('*', state.charAt(7)));
                successors.add(state.replace(state.charAt(11), '*').replace(state.charAt(10), state.charAt(11)).replace('*', state.charAt(10)));
                successors.add(state.replace(state.charAt(11), '*').replace(state.charAt(15), state.charAt(11)).replace('*', state.charAt(15)));
                break;
            }
            case 12: {
                successors.add(state.replace(state.charAt(12), '*').replace(state.charAt(8), state.charAt(12)).replace('*', state.charAt(8)));
                successors.add(state.replace(state.charAt(12), '*').replace(state.charAt(13), state.charAt(12)).replace('*', state.charAt(13)));
                break;
            }
            case 13: {
                successors.add(state.replace(state.charAt(13), '*').replace(state.charAt(9), state.charAt(13)).replace('*', state.charAt(9)));
                successors.add(state.replace(state.charAt(13), '*').replace(state.charAt(12), state.charAt(13)).replace('*', state.charAt(12)));
                successors.add(state.replace(state.charAt(13), '*').replace(state.charAt(14), state.charAt(13)).replace('*', state.charAt(14)));
                break;
            }
            case 14: {
                successors.add(state.replace(state.charAt(14), '*').replace(state.charAt(10), state.charAt(14)).replace('*', state.charAt(10)));
                successors.add(state.replace(state.charAt(14), '*').replace(state.charAt(13), state.charAt(14)).replace('*', state.charAt(13)));
                successors.add(state.replace(state.charAt(14), '*').replace(state.charAt(15), state.charAt(14)).replace('*', state.charAt(15)));
                break;
            }
            case 15: {
                successors.add(state.replace(state.charAt(15), '*').replace(state.charAt(11), state.charAt(15)).replace('*', state.charAt(11)));
                successors.add(state.replace(state.charAt(15), '*').replace(state.charAt(14), state.charAt(15)).replace('*', state.charAt(14)));
                break;
            }
               
        }

        return successors;
    }



    /**
     *
     *  prints the transitions along with the states starting from the initial states
     *  to the goal state.
     *
     */
    public static void printSolution(Node goalNode, Set<String> visitedNodes, Node root, int time) {

        int totalCost = 0;

        Stack<Node> solutionStack = new Stack<Node>();
        solutionStack.push(goalNode);
        while (!goalNode.getState().equals(root.getState())) {
            solutionStack.push(goalNode.getParent());
            goalNode = goalNode.getParent();
        }
        String sourceState = root.getState();
        String destinationState;
        int cost = 0;
        for (int i = solutionStack.size() - 1; i >= 0; i--) {
            System.out.println("===================================================================");
            destinationState = solutionStack.get(i).getState();
            if (!sourceState.equals(destinationState)) {
                System.out.println("Move " + destinationState.charAt(sourceState.indexOf('0')) + " " + findTransition(sourceState, destinationState));
                cost = Character.getNumericValue(destinationState.charAt(sourceState.indexOf('0')));
                totalCost += cost;
            }

            sourceState = destinationState;
            System.out.println("Cost of the movement: " + cost);


            System.out.println(solutionStack.get(i).getState().substring(0, 4));
            System.out.println(solutionStack.get(i).getState().substring(4, 8));
            System.out.println(solutionStack.get(i).getState().substring(8, 12));
            System.out.println(solutionStack.get(i).getState().substring(12, 16));


        }
        System.out.println("===================================================================");
        System.out.println("** Number of transitions to get to the goal state from the initial state:  " + (solutionStack.size() - 1));
        System.out.println("** Number of visited states:  " + (visitedNodes.size()));
        System.out.println("** Total cost for this solution: " + totalCost);
        System.out.println("** Number of Nodes poped out of the queue: " + time);
        System.out.println("===================================================================");

    }
  
//*******************************************************************************************
    
    public static void printSolution(Node goalNode, Node root, int time) {

        int totalCost = 0;

        Stack<Node> solutionStack = new Stack<Node>();
        solutionStack.push(goalNode);
        
        System.out.println("===================================================================");
        System.out.println("** The first five states:**");
        while (!goalNode.getState().equals(root.getState())) {
            solutionStack.push(goalNode.getParent());
            goalNode = goalNode.getParent();
        }
        String sourceState = root.getState();
        String destinationState;
        int cost = 0;
        for (int i = solutionStack.size() - 1; i >= 0; i--) {
            System.out.println("===================================================================");
            destinationState = solutionStack.get(i).getState();
            if (!sourceState.equals(destinationState)) {
                System.out.println("Move " + destinationState.charAt(sourceState.indexOf('0')) + " " + findTransition(sourceState, destinationState));
                cost = Character.getNumericValue(destinationState.charAt(sourceState.indexOf('0')));
                totalCost += cost;
            }

            sourceState = destinationState;
            System.out.println("Cost of the movement: " + cost);


            System.out.println(solutionStack.get(i).getState().substring(0, 4));
            System.out.println(solutionStack.get(i).getState().substring(4, 8));
            System.out.println(solutionStack.get(i).getState().substring(8, 12));
            System.out.println(solutionStack.get(i).getState().substring(12, 16));


        }



    }

//*******************************************************************************************
    /**
     *
     * @return returns the transition between two states.
     *
     */
    public static MovementType findTransition(String source, String destination) {
        int zero_position_difference = destination.indexOf('0') - source.indexOf('0');
        switch (zero_position_difference) {
            case -4:
                return MovementType.DOWN;
            case 4:
                return MovementType.UP;
            case 1:
                return MovementType.LEFT;
            case -1:
                return MovementType.RIGHT;
        }
        return null;
    }
}

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public void setTotalCost(int cost, int estimatedCost) {
        this.totalCost = cost + estimatedCost;
    }

    public int getEstimatedCostToGoal() {
        return estimatedCostToGoal;
    }

    public void setEstimatedCostToGoal(int estimatedCostToGoal) {
        this.estimatedCostToGoal = estimatedCostToGoal;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }


    // Constructor
    public Node(String state) {
        this.state = state;
        children = new ArrayList<Node>();
    }

    // Properties
    public String getState() {
        return state;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    // Public interface
    public void addChild(Node child) {
        children.add(child);
    }
    
    public NodeUtil getUtil(){
        return this.util;
    }
}
