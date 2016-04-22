package martinez.james;

/**
 * A Queue that can be parametrized by type
 * <p>
 * (FIFO)
 * Created by james on 4/22/16.
 */
public class Queue<E> {
    private Member<E> head;
    private Member<E> foot;

    public void enqueue(E incoming) {
        Member<E> newMember = new Member<>(incoming);
        if (head == null) {
            head = newMember;
            foot = head;
        } else {
            foot.next = newMember;
            newMember.previous = foot;
            newMember.next = null;
            foot = newMember;
        }
    }

    public E dequeue() {
        if (head == null) {
            return null;
        }
        E selected = head.key;
        head = head.next;
        if (head != null) {
            head.previous = null;
        }
        return selected;
    }


    private class Member<F> {
        F key;
        Member<F> next;
        Member<F> previous;

        Member(F key) {
            this.key = key;
        }
    }
}
