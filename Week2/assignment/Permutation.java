import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class Permutation {

	public static void main (String[] args){
		RandomizedQueue<String> strings = new RandomizedQueue<String>();
		while(!StdIn.isEmpty()){
			strings.enqueue(StdIn.readString());
		}

		int k=Integer.parseInt(args[0]);
		for(int i=0;i<k;i++){
			StdOut.println(strings.dequeue());
		}

		// Iterator<String> iterator = strings.iterator();
		// while(k>0 && iterator.hasNext()){
		// 	StdOut.println(iterator.next());
		// 	k--;
		// }
	}
}