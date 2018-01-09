import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

   private Node first, last;
   private int size;

   private class Node {
      Item item;
      Node next;
      Node previous;
   }
	
   public Deque(){
	   // construct an empty deque
      size =0;
      first =null;
      last=null;	  
   }
   public boolean isEmpty(){
	   // is the deque empty?
      return size ==0;

   }
   public int size()  {
	   // return the number of items on the deque
      return size;
   }   
   public void addFirst(Item item){
      // add the item to the front
      if(item ==null){
         throw new java.lang.IllegalArgumentException("Can't add null Item");
      }
      Node oldFirst = first;
      first=new Node ();
      first.item = item;
      first.next=oldFirst;
      first.previous=null;
      if(isEmpty()){
         last=first;
      }else{
         oldFirst.previous=first;
      }
      size++;

	   
   }
   public void addLast(Item item){
	   // add the item to the end
      if(item ==null){
         throw new java.lang.IllegalArgumentException("Can't add null Item");
      }
      Node oldLast= last;
      last= new Node();
      last.item =item;
      last.next=null;
      last.previous=oldLast;
      if(isEmpty()){
         first=last;
      }else{
         oldLast.next=last;
      }
      size++;
   }
   public Item removeFirst(){
	   // remove and return the item from the front
      if(this.isEmpty()){
         throw new java.util.NoSuchElementException("The Deque is empty");
      }
      Item item = first.item;
      first= first.next;
      size--;
      if(isEmpty()){
         last=first;
      }else{
      first.previous=null;
      }
      return item;
   }
   public Item removeLast(){
	   // remove and return the item from the end
      if(this.isEmpty()){
         throw new java.util.NoSuchElementException("The Deque is empty");
      }
      Item item = last.item;
      last = last.previous;
      size--;
      if (isEmpty()){
         first = last;
      }
      else{ 
         last.next = null;
      }
      return item;
   }
   public Iterator<Item> iterator(){
	   // return an iterator over items in order from front to end
      return new DequeIterator();
   }

   public static void main(String[] args){
	   // unit testing (optional)
   }

   private class DequeIterator implements Iterator<Item>{
      private Node current = first; 

      public boolean hasNext(){
         return current !=null;
      }

      public void remove(){
         throw new java.lang.UnsupportedOperationException();
      }

      public Item next(){
         if(!hasNext()){
            throw new java.util.NoSuchElementException();
         }
         Item item = current.item;
         current=current.next;
         return item;
      }
   }
}