public class Main {
    public static void main (String[] args) {
        Counter counter = null;
        MyLock lock;
        long executeTimeMS = 0;
        int numThread = 6;
        int numTotalInc = 1200000;
        MyThread t[];

        if (args.length < 3) {
            System.err.println("Provide 3 arguments");
            System.err.println("\t(1) <algorithm>: fast/bakery/synchronized/"
                    + "reentrant");
            System.err.println("\t(2) <numThread>: the number of test thread");
            System.err.println("\t(3) <numTotalInc>: the total number of "
                    + "increment operations performed");
            System.exit(-1);
        }

        numThread = Integer.parseInt(args[1]);
        numTotalInc = Integer.parseInt(args[2]);

        if (args[0].equals("fast")) {
            lock = new FastMutexLock(numThread);
            counter = new LockCounter(lock);
        } else if (args[0].equals("bakery")) {
            lock = new BakeryLock(numThread);
            counter = new LockCounter(lock);
        } else if (args[0].equals("synchronized")) {
            counter = new SynchronizedCounter();
        } else if (args[0].equals("reentrant")) {
            counter = new ReentrantCounter();
        } else {
            System.err.println("ERROR: no such algorithm implemented");
            System.exit(-1);
        }

        // numThread threads to increment the counter
        // Each thread executes numTotalInc/numThread increments
        // Total execute time in milliseconds stored in executeTimeMS
        t = new MyThread[numThread];
        int numThreadInc = numTotalInc/numThread; // number of increments per thread
        long startTime = System.currentTimeMillis();
      
        for (int i=0; i<numThread; i++) {
          t[i] = new MyThread(i, numThreadInc, counter);
          t[i].run();
        }
      
        try{
          for (int i=0; i<numThread; i++) {
            t[i].join();
          }          
        } catch (InterruptedException e) {};

      
        long endTime = System.currentTimeMillis();
        executeTimeMS = endTime - startTime;

        // all threads finish incrementing
        // Checking if the result is correct
        if (counter == null ||
            counter.getCount() != (numTotalInc/numThread) * numThread) {
          System.err.println("Error: The counter is not equal to the number "
              + "of total increment");
        } else {
          // print total execute time if the result is correct
          System.out.println(executeTimeMS);
        }
    }
}