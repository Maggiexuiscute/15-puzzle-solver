/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle;


public class Puzzle {

    final static private String TEST1 = "ABGCEFKDIJOHMNL0";
    final static private String TEST2 = "EAGCIBKDMFOH0JNL";
    final static private String GOAL_STATE = "ABCDEFGHIJKLMNO0";



    public static void main(String[] args) {
        String rootState = TEST1;
        long startTime = System.currentTimeMillis();

        SearchTree search = new SearchTree(new Node(rootState), GOAL_STATE);
        search.iterativeDeepening(8);

        long finishTime = System.currentTimeMillis();
        long totalTime = finishTime - startTime;
        System.out.println("Time  :" + totalTime);


    }
    
}
