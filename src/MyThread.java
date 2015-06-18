public class MyThread extends Thread {
  //private int myId;
  private int increments;
  Counter counter;

  public MyThread(int id, int increments, Counter counter) {
    this.setName(String.valueOf(id));
    //this.myId = id;
    this.increments = increments;
    this.counter = counter;
  }

  public int getIntId() {
    return Integer.parseInt(this.getName());
  }

  public void run() {
    for (int i=0; i<increments; i++) {
      this.counter.increment();
    }
  }
}