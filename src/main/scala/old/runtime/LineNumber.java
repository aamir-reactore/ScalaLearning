package old.runtime;

public class LineNumber {

   public static void main(String [] args) {



       System.out.println("The line number is " + new Exception().getStackTrace()[0].getLineNumber());

   }
}