package comp2402a3;

//import java.util.Random;
import java.util.List;
//import java.util.ArrayList;
//import java.util.Collections;

public class Tester {
  
  // Handy for testing correctness now that we know A2Table works
  public static <T> boolean tableEquals(Table<T> t1, Table<T> t2) {
    if (t1.rows() != t2.rows()) return false;
    if (t1.cols() != t2.cols()) return false;
    for (int i = 0; i < t1.rows(); i++) {
      for (int j = 0; j < t2.cols(); j++) {
        T x1 = t1.get(i, j);
        T x2 = t2.get(i, j);
        if (x1 != null && x2 == null) return false;
        if (x1 == null && x2 != null) return false;
        if (x1 != null && !x1.equals(x2)) return false;
      }
    }
    return true;
  }
  
  
  public static boolean testPart1(Table<Integer> t) {
    int nrows = 9, ncols = 6;
    for (int i = 0; i < ncols; i++) {
      t.addCol(t.cols());
      
    }
    if (t.cols() != ncols) {return false;}
    
    for (int i = 0; i < nrows; i++) {
      t.addRow(t.rows());
    }
    if (t.rows() != nrows) {return false;}
    if (t.cols() != ncols) {return false;}
    
    for (int i = 1; i <= nrows; i++) {
      t.set(i-1, (i-1)%t.cols(), 1111*i);
    }
    System.out.println(t);
    t.addCol(2);
    t.addRow(3);
    System.out.println(t);
    t.removeCol(2);
    t.removeRow(3);
    System.out.println(t);
    t.set(0,0,1234);
    if (t.get(0,0) != 1234) {return false;}
    return true;
  }
  
  public static void testTable(Table<Integer> tab) {
    long start = System.nanoTime();
    boolean result = Tester.testPart1(tab);
    long stop = System.nanoTime();
    double elapsed = (stop-start)/1e9;
    System.out.println("testPart1 returns " + result + " in " + elapsed + "s"
                         + " when testing a " + tab.getClass().getName());
  }
  
  
  public static boolean testPart2(List<Integer> ell) {
    int n = 20;
    for (int i = 0; i < n; i++) {
      ell.add(i);
    }
    System.out.println(ell);
    for (int i = -1; i > -n; i--) {
      ell.add(0,i);
      if (ell.get(0) != i) {return false;}
    }
    System.out.println(ell);
    for (int i = 0; i < 10000; i++) {
      ell.add(n+i,1000+i);
      if (ell.get(n+i) != 1000+i) {return false;}
    }
    System.out.println(ell);
    for (int i = 0; i < 10000; i++) {
      System.out.println("Failed here: " + i);
      if (ell.get(i) !=  ell.remove(i)) {return false;}
    }
    for (int i = 0; i < 10000; i++) {
      ell.set(i, 0);
      if (ell.get(i) != 0) {
        return false;
      }
    }
    System.out.println(ell);
    return true;
  }
  
  public static void testDefaultList(List<Integer> ell) {
    long start = System.nanoTime();
    boolean result = Tester.testPart2(ell);
    long stop = System.nanoTime();
    double elapsed = (stop-start)/1e9;
    System.out.println("testPart2 returns " + result + " in " + elapsed + "s"
                         + " when testing a " + ell.getClass().getName());
  }
  
  public static void main (String[] args) {
    testTable(new FasterTable<Integer>(Integer.class));
    testDefaultList(new FastDefaultList<Integer>());
  }
  
}
