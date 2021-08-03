package nuc.edu;

/**
 * @author 薛东
 * @date 2021/3/16 10:42
 */
public class Main {
    public static void main(String[] args) {
        /*Queue<Integer> queue = new Queue<>();
        queue.enQueue(11);
        queue.enQueue(22);
        queue.enQueue(33);
        queue.enQueue(44);*/

        /*Deque<Integer> deque = new Deque<>();
        deque.enQueueFront(11);
        deque.enQueueFront(22);
        deque.enQueueRear(33);
        deque.enQueueRear(44);

        while (!deque.isEmpty()){
            System.out.println(deque.deQueueFront());
        }*/

        CircleQueue<Integer> queue = new CircleQueue<Integer>();
        // 0 1 2 3 4 5 6 7 8 9
        for (int i = 0; i < 10; i++) {
            queue.enQueue(i);
        }
        // null null null null null 5 6 7 8 9
        for (int i = 0; i < 5; i++) {
            queue.deQueue();
        }
        // 15 16 17 18 19 f[5] 6 7 8 9
        for (int i = 15; i < 30; i++) {
            queue.enQueue(i);
        }
		while (!queue.isEmpty()) {
			System.out.println(queue.deQueue());
		}

    }
}
