// TODO 
// Implement Fast Mutex Algorithm

public class FastMutexLock implements MyLock {
    int[] flag; // down=1, right=2, left=3, up=4
    final int down=1;
    final int right=2;
    final int left=3;
    final int up=4;
    int x;
    int y;
    int numThread;
  
    public FastMutexLock(int numThread) {
      this.flag = new int[numThread];
      this.x = -1;
      this.y = -1;
      this.numThread = numThread;
    }

    @Override
    public void lock(int myId) {
      while(true){
        flag[myId] = up;
        x = myId;
        if (y != -1) {
          flag[myId] = down;
          while (y != myId); // wait for my turn
        } else {
          y = myId;
          if (x == myId) {
            return;
          } else {
            flag[myId] = down;
            for (int j=0; j<numThread; j++) {
              while (flag[j] != down); // wait for all to be down
              if (y == myId) {
                return;
              } else {
                while (y != 1); //wait
              }
            }
          }
        }
      }
    }

    @Override
    public void unlock(int myId) {
      // TODO: the unlocking algorithm
    }
}