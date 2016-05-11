
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

/**/
public class WordNet {
	   private Digraph dg;
	   private HashMap<Integer, String> map = new  HashMap<Integer, String>();
	   //ArrayList<String> list = new ArrayList<String>();
	    /**/
	    // constructor takes the name of the two input files
	    /**/
	   public WordNet(String synsets, String hypernyms){
		   readSynsets(synsets);
		   readHypernyms(hypernyms);
	   }
	   private void readSynsets(String synsets){
		   In in = new In(synsets);
		   //in.readInt();
		   int count = 0;
		   while(in.hasNextLine()) {
			   String[] arr = in.readLine().split(",");
			   map.put(Integer.parseInt(arr[0]), arr[1]);
			   count++;
		   }
		   dg = new Digraph(count);
	   }
	   private void readHypernyms(String hypernyms){
		   //hypernyms.txt read the hypernym relationships
		   In in = new In(hypernyms);
		   while(in.hasNextLine()) {
			   String temp = in.readLine();
			   String[] arr = temp.split(",");
			   for(int i=1;i<arr.length;i++){
				   dg.addEdge(Integer.parseInt(arr[0]), Integer.parseInt(arr[i].trim()));
			   }
		   }
	   }
	   /* returns all WordNet nouns*/
	   public Iterable<String> nouns(){
		   String[] arr = map.values().toArray(new String[map.size()]);
		   ArrayList<String> list = new ArrayList<String>();
		   for(String s: arr){
			   list.add(s);
		   }
		   return list;
	   }
	   /* is the word a WordNet noun?*/
	   public boolean isNoun(String word){
		   String[] arr = map.values().toArray(new String[map.size()]);
		   for(String s : arr){
			   if(s.contains(word))
				   return true;
		   }
		   return false;
	   }
	   /*distance between nounA and nounB (defined below) */
	   public int distance(String nounA, String nounB){
		   if(isNoun(nounA) && isNoun(nounB)){
		   ArrayList<Integer> list1 = (ArrayList<Integer>) parseNountoVertexSet(nounA);
		   ArrayList<Integer> list2 = (ArrayList<Integer>) parseNountoVertexSet(nounB);
		   //System.out.println("list2:-------  "+list2.toString());
		   SAP sap = new SAP(dg);
		   return sap.length(list1, list2);
		   }else{
			   return -1;
		   }
	   }
	   private Iterable<Integer> parseNountoVertexSet(String noun){
		   ArrayList<Integer> list = new ArrayList<Integer>();// save vertex set containing noun
		   //String[] arr = noun.split(" ");
		   Set<Integer> sets = map.keySet();
		   for(Integer key : sets){
			   String value = map.get(key);
			   String[] arr = value.split(" ");
			   for(String s : arr){
				   if(s.equals(noun)){
					   list.add(key);
					   break;
				   }
			   }
		   }
		   return list;
	   }
	   /* a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
	   // in a shortest ancestral path (defined below) */
	   public String sap(String nounA, String nounB){
		   ArrayList<Integer> list1 = (ArrayList<Integer>) parseNountoVertexSet(nounA);
		   ArrayList<Integer> list2 = (ArrayList<Integer>) parseNountoVertexSet(nounB);
		   SAP sap = new SAP(dg);
		   int i = -1;
		   if(!list1.isEmpty() && !list2.isEmpty())
			   i = sap.ancestor(list1, list2);
		   return map.get(i);
	   }
	   /* do unit testing of this class */
	   public static void main(String[] args){
		   /*In in = new In("test.txt");
		   while(in.hasNextLine()) {
			   String temp = in.readLine();
			   String[] arr = temp.split(",");
			   for(int i=0;i<arr.length;i++){
				   int j = Integer.parseInt(arr[i].trim());
				   System.out.print(" "+j);
			   }
			   System.out.println("------");
		   }*/
		   //WordNet wn = new WordNet("synsets.txt", "hypernyms.txt");
		   /*String s = "this is china";
		   if(s.contains("chi"))
			   System.out.println("------------");*/
/*		   WordNet wn = new WordNet("synsets15.txt", "hypernyms15Path.txt");
		   ArrayList<String> list = new ArrayList<String>();
		   if(wn.isNoun("m"))
			   System.out.println("-------------");
		   list = (ArrayList<String>) wn.nouns();
		   for(String s:list){
			   System.out.println(" "+s);
		   }*/
/*		   WordNet wn = new WordNet("synsets15.txt", "hypernyms15Tree.txt");
		   int i = wn.distance("i", "l");
		   System.out.println(" "+i);*/
/*		   WordNet wn = new WordNet("synsets15.txt", "hypernyms15Tree.txt");
		   String tmp = wn.sap("f", "h i");
		   System.out.println("ancestor:  "+tmp);*/
		   WordNet wn = new WordNet("synsets.txt","hypernyms.txt");
		   int tmp = wn.distance("coffee", "tea");
		   System.out.println(" "+tmp);
	   }
	}