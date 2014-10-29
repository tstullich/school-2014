import java.util.Map;

/**
    Tests methods for aligning character strings
    @author Jeff Smith   
    @version for Assignment 3, CS 155, SJSU, Fall 2014
 */

public class A3
{

    /**
        Pretty-prints a 2D array of small integers
        @param the array to be printed
     */
    
    public static void print(int[][] a)                     {
      if (a == null) return;
      for (int i=0; i<a.length; i++)            {
        for (int j=0; j<a[i].length; j++)       
          System.out.printf("%3d", a[i][j]);     
        System.out.println();                   }           }
        
        
    /**
        Tests two alignment methods on a pair of strings
        @param s1 the first string to be aligned
        @param s2 the second string to be aligned
     */    
    
    public static void test(String s1, String s2)            {
      Aligner a1 = new Aligner();      
      System.out.println("  " + s1 + " vs. " + s2);
      a1.align(s1, s2);
      int[][] table = a1.getTable();
      print(table);
      Map.Entry<String, String> al1 = a1.getAlignment();
      System.out.println(al1.getKey());           
      System.out.println(al1.getValue());    
      System.out.println();    
        
      Aligner a2 = new Aligner();      
      a2.newAlign(s1, s2);
      table = a2.getTable();
      print(table);
      Map.Entry<String, String> al2 = a2.getAlignment();
      System.out.println(al2.getKey());           
      System.out.println(al2.getValue());    
      System.out.println();                                  }
    
      
    /**
        Tests two alignment methods on various test cases
        @param  args is ignored 
     */
    
    public static void main(String[] args)                          {
      
      Aligner a = new Aligner();      
      int[][] table = a.getTable();
      table = a.getTable();
      print(table);
      Map.Entry<String, String> al = a.getAlignment();
      System.out.println(al.getKey());           
      System.out.println(al.getValue());    
      System.out.println();    
            
      test("NUCULAR", "NUCLEAR");           
      test("NICKEL", "NICKLE");           
      test("CC", "CS");    
      test("ABA", "BAB");    
      test("aB", "bc");           
      test("aBC", "bcc");           
      test("BCc", "bc");           
      test("McCall", "MCcall");           
      test("McFall", "MCfall");           
      test("GTTCCTAATA", "CGATAATTGAGA");           
      test("ALASKA", "MALAYA");           
      test("ALASKA", "DALLAS");                                    }
}
