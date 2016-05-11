package programAssign2;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {// linkedlist implementation
	   private Node first = null;
	   private Node last  = null;
	   private int  count = 0; // deque size
	   
	   private class Node{
		   Item item;
		   Node next;
		   Node previous;
	   }
	   public Deque()                           // construct an empty deque
	   {
		   
	   }
	   public boolean isEmpty()                 // is the deque empty?
	   {
		return first == null && last == null;
		   
	   }
	   public int size()                        // return the number of items on the deque
	   {
		return count;
		   
	   }
	   public void addFirst(Item item)          // add the item to the front
	   {
		 if (item == null){
			 throw new java.lang.NullPointerException();
		 }
		   if(isEmpty()){// first last = null
			   first = new Node();
			   first.item = item;
			   first.previous = null;
			   first.next = null;
			   last = first;
			   count++;
		   }else{
		   Node oldfirst = first;
		   first = new Node();
		   first.item = item;
		   first.next = oldfirst;
		   oldfirst.previous = first;
		   first.previous = null;
		   count++;
		   }
	   }
	   public void addLast(Item item)           // add the item to the end
	   {
		   if(isEmpty()){//first last = null
			   last = new Node();
			   last.item = item;
			   last.previous = null;
			   last.next = null;
			   first = last;
			   count++;
		   }else{
		   Node oldlast = last;
		   last = new Node();
		   last.item = item;
		   oldlast.next = last;
		   last.previous = oldlast;
		   last.next =null;
		   count++;
		   }
	   }
	   public Item removeFirst()                // remove and return the item from the front
	   {
		   Item item = first.item;
		   if(count == 1){//after removal, isEmpty
			   first = null;
			   last = null;
		   }else{
			   first = first.next;
			   first.previous =null;
		   }
		   count--;   
		   return item;
	   }
	   public Item removeLast()                 // remove and return the item from the end
	   {
		   Item item = last.item;
		   if(count == 1){//after removal, isEmpty
			   last = null;
			   first = null;
		   }else{
			   last = last.previous;
			   last.next = null;
		   }
		   count--;
		   return item;		   
	   }
	   public Iterator<Item> iterator()         // return an iterator over items in order from front to end
	   {
		return new DequeIterator();
	   }
	   private class DequeIterator implements Iterator<Item>
	   {
		private Node current = first;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current != null;
		}

		@Override
		public Item next() {
			// TODO Auto-generated method stub
			Item item = current.item;
			current = current.next;
			return item;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		   
	   }
	   
	   public static void main(String[] args)   // unit testing
	   {
		   Deque<Integer> dq = new Deque<Integer>();
		   dq.addFirst(10);
		   dq.addFirst(null);
		   dq.addFirst(30);
		   dq.addLast(100);
		   dq.addLast(200);
		   Iterator it = dq.iterator();
		   while(it.hasNext()){
			   System.out.println(" "+it.next());
		   }
	   }	
}