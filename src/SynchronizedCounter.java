public class SynchronizedCounter extends Counter {
	@Override
   public synchronized void increment() {
   	this.count++;
   }
}