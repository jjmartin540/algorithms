package martinez.james;

/**
 * A Stack that can be parametrized by type
 * <p>
 * (LIFO)
 * Created by james on 4/22/16.
 */
public class Stack<E> {
    private Member<E> head;

    public void push(E incoming) {
        Member<E> newMember = new Member<>(incoming);
        if (head == null) {
            head = newMember;
        } else {
            newMember.next = head;
            head.previous = newMember;
            head = newMember;
        }
    }

    public E pop() {
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
