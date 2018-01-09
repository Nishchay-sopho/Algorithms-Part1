import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private int size;
	private Item[] itemArray;

   public RandomizedQueue(){
   	// construct an empty randomized queue
   	size =0;
   	itemArray=(Item[]) new Object[1];

   }    

   public boolean isEmpty(){
   	// is the randomized queue empty?
   	return size==0;
   }

   public int size() {
   	// return the number of items on the randomized queue
   	return size;

   }     

   public void enqueue(Item item){
   	// add the item
   	if(item ==null){
   		throw new java.lang.IllegalArgumentException();
   	}
   	if(size == itemArray.length){
   		resize(2*itemArray.length);
   	}
   	itemArray[size++]=item;

   }

   public Item dequeue(){
   	// remove and return a random item
   	if(size==0){
   		throw new java.util.NoSuchElementException();
   	}
   	int randomNumber= StdRandom.uniform(size);
   	Item item = itemArray[randomNumber];
   	itemArray[randomNumber]=itemArray[size-1];
   	itemArray[--size]=null;
   	if(size >0 && size == itemArray.length/4){
   		resize(itemArray.length/2);
   	}
   	return item;

   }

   public Item sample(){
   	// return a random item (but do not remove it)
   	if(size==0){
   		throw new java.util.NoSuchElementException();
   	}
   	int randomNumber= StdRandom.uniform(size);
   	return itemArray[randomNumber];

   }  

   public Iterator<Item> iterator(){
   	// return an independent iterator over items in random order
   	return new RandomizedQueueIterator();
   } 

   private void resize(int capacity){
   	Item[] copy=(Item[]) new Object[capacity];
   	for(int i=0;i<size;i++){
   		copy[i]=itemArray[i];
   	}
   	itemArray=copy;
   }        

   public static void main(String[] args){
   	// unit testing (optional)
   }

   private class RandomizedQueueIterator implements Iterator<Item>{
   	private int sizeOfArray =size;
   	private int[] order;

   	public RandomizedQueueIterator (){
   		order = new int[sizeOfArray];

   		for (int i=0;i<sizeOfArray;i++){
   			order[i]=i;
   		}
   		StdRandom.shuffle(order);
   	}

   		public boolean hasNext(){
   			return sizeOfArray>0;
   		}

   		public void remove (){
   			throw new java.lang.UnsupportedOperationException();
   		}

   		public Item next(){
   			if(!hasNext()){
   				throw new java.util.NoSuchElementException();
   			}
   			return itemArray[order[--sizeOfArray]];
   		}
   	}
      
}