// Use MyLock to protect the count
// This counter can use either BakeryLock or FastMutexLock to protect the count

public class LockCounter extends Counter {
    MyLock lock;
  
    public LockCounter(MyLock lock) {
      this.lock = lock;
    }

    @Override
    public void increment() {
      MyThread currentMyThread = MyThread.getCurrentMyThread();
      int myId = currentMyThread.getIntId();
      this.lock.lock(myId);
      this.count++;
      this.lock.unlock(myId);
    }
}