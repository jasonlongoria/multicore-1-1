public class MyThread extends Thread {
  private int myId;
  private int increments;
  Counter counter;

  public MyThread(int id, int increments, Counter counter) {
    this.myId = id;
    this.increments = increments;
    this.counter = counter;
  }
  
  public static MyThread getCurrentMyThread() {
    return (MyThread) Thread.currentThread();
  }

  public int getIntId() {
    return this.myId;
  }

  public void run() {
    for (int i=0; i<increments; i++) {
      this.counter.increment();
    }
  }
}