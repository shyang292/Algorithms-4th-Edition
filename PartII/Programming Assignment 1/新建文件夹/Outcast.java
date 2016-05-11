
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
/*
 *.......
 *.. */
public class Outcast {
      private  WordNet wn;
      // constructor takes a WordNet object
      public Outcast(final WordNet wordnet) {
           wn = wordnet;
      }
      // given an array of WordNet nouns, return an outcast
      public String outcast(final String[] nouns) {
           int dissum = 0;
           String maxnoun = "";
           int maxdis = 0;
           boolean flag = true; //initialization
           for (String s : nouns) {
               for (String t : nouns) {
                   //System.out.println(" "+s+" "+t);
                   dissum += wn.distance(s, t);
                   //System.out.println(" "+s+" "+dissum);
               }
               if (flag) {
                   maxdis = dissum;
                   maxnoun = s;
                   flag = false;
               } else if (maxdis < dissum) {
                   maxdis = dissum;
                   maxnoun = s;
               }
               dissum = 0;
           }
         return maxnoun;
       }
       // see test client below
      /*
       * */
       public static void main(String[] args) {
            WordNet wordnet = new WordNet(args[0], args[1]);
            Outcast outcast = new Outcast(wordnet);
            for (int t = 2; t < args.length; t++) {
                In in = new In(args[t]);
                String[] nouns = in.readAllStrings();
                StdOut.println(args[t] + ": " + outcast.outcast(nouns));
            }
       }
    }
