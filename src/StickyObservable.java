import java.util.ArrayList;
import java.util.List;

/**
 * Sticky Observable will update observer with last changed value.
 */
public class StickyObservable<T> {
    private final List<Observer<T>> mObservers = new ArrayList<>();
    private boolean mChanged;
    private boolean mHasSticky;
    private T mStickyValue;

    public void addObserver(Observer<T> o) {
        if (o == null) {
            throw new NullPointerException("StickyObservable cannot accept null observer.");
        }

        boolean hasSticky;
        T stickyValue;
        synchronized (this) {
            if (mObservers.contains(o)) {
                return;
            }
            mObservers.add(o);
            hasSticky = mHasSticky;
            stickyValue = mStickyValue;
        }

        if (hasSticky) {
            o.update(stickyValue);
        }
    }

    public synchronized void deleteObserver(Observer<T> o) {
        mObservers.remove(o);
    }

    public synchronized void deleteObservers() {
        mObservers.clear();
    }

    public void notifyObservers() {
        notifyObservers(null);
    }

    public void notifyObservers(T value) {
        Observer[] arrLocal;
        int length;
        synchronized (this) {
            if (!mChanged) {
                return;
            }

            mStickyValue = value;
            mHasSticky = true;

            length = mObservers.size();
            arrLocal = mObservers.toArray(new Observer[length]);
            clearChanged();
        }

        for (int i = 0; i < length; i++) {
            //noinspection unchecked
            arrLocal[i].update(value);
        }
    }

    public synchronized void setChanged() {
        mChanged = true;
    }

    private synchronized void clearChanged() {
        mChanged = false;
    }

    public synchronized int countObservers() {
        return mObservers.size();
    }
}
