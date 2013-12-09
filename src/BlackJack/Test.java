/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BlackJack;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author wua9596
 */
public class Test {
    public static void main(String[] args){
        ArrayList<Integer> a = new ArrayList();
        a.add(2);
        a.add(1);
        a.add(3);
        
        Collections.sort(a);
        System.out.println(a.toString());
        
    }
}
