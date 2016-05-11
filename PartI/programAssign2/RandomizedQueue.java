package programAssign2;

import java.util.Iterator;


import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	   private Item[] s;
	   private int N = 0;
	   
	   public RandomizedQueue(int capacity)                 // construct an empty randomized queue
	   {
		   s = (Item[])new Object[capacity];
	   }
	   public boolean isEmpty()                 // is the queue empty?
	   {
		return N == 0;
		   
	   }
	   public int size()                        // return the number of items on the queue
	   {
		return N;
		   
	   }
	   public void enqueue(Item item)           // add the item
	   {
		   if(N == s.length) resize(2 * s.length);
		   s[N++] = item;
	   }
	   public Item dequeue()                    // remove and return a random item
	   {
		if(N>0 && N <= s.length/4) resize(s.length/2);
		//shuffle array
		StdRandom.shuffle(s,0,N-1);
		Item item = s[--N];
		s[N]= null;
		return item;
	   }
	   private void resize(int capacity){
		Item[] copy = (Item[])new Object[capacity];
		for(int i=0;i<N;i++){
			copy[i] = s[i];
		}
		s = copy;
	   }
	   public Item sample()                     // return (but do not remove) a random item
	   {
		StdRandom.shuffle(s,0,N-1);
		return s[N-1];
	   }
	   public Iterator<Item> iterator()         // return an independent iterator over items in random order
	   {
		   return new RandomizedQueueIterator();
	   }
	   private class RandomizedQueueIterator implements Iterator<Item>
	   {
		   private int i=0;
		RandomizedQueueIterator(){
			StdRandom.shuffle(s,0,N-1);
		}
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return i < N;
		}

		@Override
		public Item next() {
			// TODO Auto-generated method stub
			return s[i++];
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		   
	   }
	   
	   public void printQueue(){
		   for(int i=0;i<N;i++){
			   System.out.print("  "+s[i]);
		   }
	   }
	   
	   public static void main(String[] args)   // unit testing
	   {
		   RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>(2);
		   //if(rq.isEmpty()) System.out.println("rq is empty");
		   rq.enqueue(10);
		   rq.enqueue(20);
		   rq.enqueue(30);
		   rq.enqueue(40);
//		   System.out.println("rq sample is  "+rq.sample());
//		   System.out.println("rq sample is  "+rq.sample());
//		   System.out.println("rq sample is  "+rq.sample());
		   rq.enqueue(50);
		   rq.enqueue(60);
		   //rq.printQueue();
		   Iterator<Integer> it = rq.iterator();
		   //rq.printQueue();
		   while(it.hasNext()){
			   System.out.println("iterate:  "+it.next());
		   
		   }
	   }
}
