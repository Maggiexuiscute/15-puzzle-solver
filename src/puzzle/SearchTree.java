/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle;

import java.util.*;


public class SearchTree {
    public enum Heuristic{H_ONE, H_TWO};
    private Node root;
    private String goalSate;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public String getGoalSate() {
        return goalSate;
    }

    public void setGoalSate(String goalSate) {
        this.goalSate = goalSate;
    }

    public SearchTree(Node root, String goalSate) {
        this.root = root;
        this.goalSate = goalSate;
    }


//**********************************************************************************************

    public void depthFirstSearch() {
        // stateSet is a set that contains node that are already visited
        Set<String> stateSets = new HashSet<String>();
        int totalCost = 0;
        int time = 0;
        Node node = new Node(root.getState());
        //the queue that store nodes that we should expand
        MyQueue<Node> mainQueue = new MyQueue<>();
        //the queue that contains the successors of the expanded node
        MyQueue<Node> successorsQueue = new MyQueue<>();
        Node currentNode = node;
        while (!currentNode.getState().equals(goalSate)) {
            if(stateSets.size() > 1000000){
                System.out.print("\n**Has expanded over 1 million search nodes.**\n**No solution found!**\n");
                //NodeUtil.printSolution(currentNode, stateSets, root, time);
                return;
            }
            if(time == 5){
               currentNode.getUtil().printSolution(currentNode, root, time);
            }
            stateSets.add(currentNode.getState());
            List<String> nodeSuccessors = currentNode.getUtil().getSuccessors(currentNode.getState());
            for (String n : nodeSuccessors) {
                if (stateSets.contains(n))
                    continue;
                stateSets.add(n);
                Node child = new Node(n);
                currentNode.addChild(child);
                child.setParent(currentNode);
                successorsQueue.enqueue(child);

            }
            //we add the queue that contains the successors of the visted node to the beginning of the main queue
            mainQueue.addQueue(successorsQueue);
            //successors queue should be cleared in order to store the next expaneded's successors
            successorsQueue.clear();
            currentNode = mainQueue.dequeue();
            time += 1;
            nodeSuccessors.clear();
        }
        node.getUtil().printSolution(currentNode, stateSets, root, time);

    }

    /**
     * Find the goal using A* algorithm. The huristic is the real cost to the current node from the initial Node
     * plus the estimated cost from the current node to the goal node
     */
    //*************************************************************************************************
    public void aStar(Heuristic heuristic) {
        // stateSet is a set that contains node that are already visited
        Set<String> stateSets = new HashSet<String>();
        int totalCost = 0;
        int time = 0;
        Node node = new Node(root.getState());
        node.setTotalCost(0);

        // the comparator compare the cost values and make the priority queue to be sorted based on cost values
        NodePriorityComparator nodePriorityComparator = new NodePriorityComparator();

        // a queue that contains nodes and their cost values sorted. 10 is the initial size
        PriorityQueue<Node> nodePriorityQueue = new PriorityQueue<Node>(17, nodePriorityComparator);
        Node currentNode = node;
        while (!currentNode.getState().equals(goalSate)) {
            stateSets.add(currentNode.getState());
            List<String> nodeSuccessors = currentNode.getUtil().getSuccessors(currentNode.getState());
            for (String n : nodeSuccessors) {
                if (stateSets.contains(n))
                    continue;
                stateSets.add(n);
                Node child = new Node(n);
                currentNode.addChild(child);
                child.setParent(currentNode);

                if (heuristic == Heuristic.H_ONE)
                    child.setTotalCost(currentNode.getTotalCost() + Character.getNumericValue(child.getState().charAt(child.getParent().getState().indexOf('0'))), heuristicOne(child.getState(), goalSate));
                else if (heuristic == Heuristic.H_TWO)
                    child.setTotalCost(currentNode.getTotalCost() + Character.getNumericValue(child.getState().charAt(child.getParent().getState().indexOf('0'))), heuristicTwo(child.getState(), goalSate));
                nodePriorityQueue.add(child);

            }
            currentNode = nodePriorityQueue.poll();
            time += 1;
        }
        currentNode.getUtil().printSolution(currentNode, stateSets, root, time);
    }


    /**
     * Search for the goal state using limited depth First Search algorithm
     * the depth limit starts from 1 and it increases to the defiend value by user that passed to the method
     */
    //*****************************************************************************************************
    public void iterativeDeepening(int depthLimit) {
        Node currentNode = new Node(root.getState());
        boolean solutionFound = false;
        // stateSet is a set that contains node that are already visited
        Set<String> stateSets = new HashSet<String>();
        Set<String> totalVisitedStates = new HashSet<>();
        int time = 0;
        for (int maxDepth = 0; maxDepth <= depthLimit; maxDepth++) {
            //we should clear the visited list in each iteration
            stateSets.clear();
            //the queue that store nodes that we should expand
            MyQueue<Node> mainQueue = new MyQueue<>();
            //the queue that stores the successors of the expanded node
            MyQueue<Node> successorsQueue = new MyQueue<>();
            Node node = new Node(root.getState());
            mainQueue.enqueue(node);
            currentNode = node;
            List<String> nodeSuccessors = null;
            stateSets.add(currentNode.getState());
            while (!mainQueue.isEmpty()) {
                currentNode = mainQueue.dequeue();
                time += 1;
                if (currentNode.getState().equals(goalSate)) {
                    solutionFound = true;
                    break;
                }
                if (currentNode.getDepth() < maxDepth) {
                    nodeSuccessors = currentNode.getUtil().getSuccessors(currentNode.getState());
                    for (String n : nodeSuccessors) {
                        if (stateSets.contains(n))
                            continue;

                        stateSets.add(n);
                        Node child = new Node(n);
                        currentNode.addChild(child);
                        child.setParent(currentNode);
                        child.setVisited(true);
                        child.setDepth(currentNode.getDepth() + 1);
                        successorsQueue.enqueue(child);

                    }
                    //we add the queue that contains the successors of the visted node to the beginning of the main queue
                    mainQueue.addQueue(successorsQueue);
                    //successors queue should be cleared in order to store the next expaneded's successors
                    successorsQueue.clear();
                }
            }

            if (solutionFound)
                break;
            totalVisitedStates.addAll(stateSets);
        }
        if (!solutionFound)
            System.out.println("No solution Found!");
        else {
            currentNode.getUtil().printSolution(currentNode, totalVisitedStates, root, time);


        }


    }


    //****************************************************************************************************
    // This heuristic estimates the cost to the goal from current state by counting the number of misplaced tiles
    private int heuristicOne(String currentState, String goalSate) {
        int difference = 0;
        for (int i = 0; i < currentState.length(); i += 1)
            if (currentState.charAt(i) != goalSate.charAt(i))
                difference += 1;
        return difference;
    }

    //*************************************************************************************************
    // This heuristic estimates the cost to the goal from current state by calculating the Manhathan distance from each misplaced
    // tile to its right position in the goal state
    private int heuristicTwo(String currentState, String goalSate) {
        int difference = 0;
        for (int i = 0; i < currentState.length(); i += 1)
            for (int j = 0; j < goalSate.length(); j += 1)
                if (currentState.charAt(i) == goalSate.charAt(j))
                    difference = difference + ((Math.abs(i % 4 - j % 4)) + Math.abs(i / 4 + j / 4));
        return difference;
    }
}
