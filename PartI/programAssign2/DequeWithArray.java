package programAssign2;

import java.util.Iterator;

public class DequeWithArray<Item> implements Iterable<Item> {// array implementation
	   private Item[] s;
	   private int N = 0;
	   private int head,  tail;
	   private int capacity;
	   
	   
	   public DequeWithArray(int m)                           // construct an empty deque
	   {
		   head=0;
		   tail=0;
		   capacity = m;
		   s = (Item[])new Object[capacity];
	   }
	   public boolean isEmpty()                 // is the deque empty?
	   {
		return N==0;
		   
	   }
	   public int size()                        // return the number of items on the deque
	   {
		return N;
	   }
	   public void addFirst(Item item)          // add the item to the front
	   {
		   //when the array is full
		   if(N == s.length)
			   resize(2 * s.length);
		   if(head==0){
			   head = capacity -1;// the last index of array
		   	   s[head]=item;
		   }else{
			   s[--head]=item;
		   }
	   }
	   private void resize(int m) {
		// TODO Auto-generated method stub
		// head tail; multiple situation
		Item[] copy = (Item[]) new Object[m];
		
		
		
		for (int i=0;i<N;i++){
			copy[i] = s[i];
		}
		s = copy;	
		capacity = s.length; 
	}
	public void addLast(Item item)           // add the item to the end
	   {
		   if( (N == s.length) && (tail == capacity -1)){
			   resize(2 * s.length);
			   s[++tail] = item;
		   }else if( (N != s.length) && (tail == capacity -1)){
			   tail =0;
			   s[tail]=item;
		   }else if ((N == s.length)&&(tail != capacity -1)){
			   resize(2 * s.length);
			   for(int i=0;i<=tail;i++){
				   s[N+i]=s[i];
				   s[i] = null; 
			   }
		   }else if ((N != s.length)&&(tail != capacity -1)){
			   s[++tail] = item;
		   }
	   }
	   public Item removeFirst()                // remove and return the item from the front
	   {
		   int tmp =0;
		   Item item = s[head];
		   s[head] = null;
		   if(N > 0 && N == s.length/4){
			   //1. head tail in the latter part of array
			   if(head < tail){
				   tmp = head;
				   for(int i=head;i<=tail;i++){
					   s[i-tmp]=s[i];
				   }
				   tail = tail-head;
				   head = 0;
			   }
			   //2. tail head in the latter part of array
			   if(head > tail){
				   tmp = capacity - head;
				   for(int i=tail;i>=0;i--){
					   s[tmp+i]=s[i];
				   }
				   for(int j=0;j<tmp;j++){
					   s[j]=s[head++];
				   }
				   head = 0;
				   tail = tmp+tail;
			   }
		   }    
		   resize(s.length/2);
		   head++;
		   return item;
	   }
	   public Item removeLast()                 // remove and return the item from the end
	   {
		   
		   
	   }
	   public Iterator<Item> iterator()         // return an iterator over items in order from front to end
	   {
		return new DequeIterator();
	   }
	   private class DequeIterator implements Iterator<Item>
	   {

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Item next() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		   
	   }
	   
	   public static void main(String[] args)   // unit testing
	   {
		   
	   }	
}