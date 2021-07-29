import java.util.concurrent.ConcurrentLinkedQueue;

public class CallCenter {
    final int DAY_LIMIT_MAX_CALL = 30;
    final int CALL_DELAY = 1000;
    final int DELAY_OPERATOR = 5000;
    final int TALK = 4000;

    static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

    public void call() {
        for (int i = 1; i < DAY_LIMIT_MAX_CALL + 1; i++) {
            System.out.println("Поступил звонок " + i);
            queue.add("Звонок " + i);
            try {
                Thread.sleep(CALL_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void operatorWork() {
        String queuePoll;
        while (true) {
            queuePoll = queue.poll();
            if (queuePoll != null) {
                System.out.println(Thread.currentThread().getName() + " обработал: " + queuePoll);
                try {
                    Thread.sleep(TALK);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (queue.size() == 0) {
                try {
                    Thread.sleep(DELAY_OPERATOR);
                    if (queue.size() == 0) {
                        System.out.println(Thread.currentThread().getName() + " ушел домой");
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
