import java.util.concurrent.atomic.AtomicIntegerArray;

public class BakeryLock implements MyLock {
	int N;
	boolean[] choosing;
   AtomicIntegerArray number;
  
   public BakeryLock(int numThread) {
     N = numThread;
     choosing = new boolean[N];
     number = new AtomicIntegerArray(N);
     for (int j=0; j<N; j++) {
       choosing[j] = false;
       number.set(j, 0);
     }
   }

   @Override
   public void lock(int myId) {
     // choose a number
     choosing[myId] = true;
     for (int j=0; j<N; j++) {
       if (number.get(j) > number.get(myId)) {
         number.set(myId, number.get(j));
       }
     }
     number.getAndIncrement(myId);
     choosing[myId] = false;
     
     // compare numbers
     for (int j=0; j<N; j++) {
       while (choosing[j]);
       while (( number.get(j)>0 && number.get(j)<number.get(myId))
             || (number.get(j)==number.get(myId) && j<myId));
     }
   }

   @Override
   public void unlock(int myId) {
     number.set(myId,0);
   }
}