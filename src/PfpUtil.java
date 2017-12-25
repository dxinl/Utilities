/**
 * Prevent an mutable object from publishing.
 *
 * Mostly, states of a mutable object will be changed by context. But in some infrequent
 * cases, we need to keep an object immutable to represent some business logic such as
 * 'undo' or 'revert'. Usually, we implement these business logic with adequate caution.
 * But no matter how carefully developers are, the maintainability of such codes are very
 * low.
 *
 * This utility helps to implement business logic like above in higher maintainability way.
 * It prevents an object to be published by way of publishing its copy.
 */
public class PfpUtil {
    private final Object mReproducible;

    public PfpUtil(Object reproducible) {
        this(reproducible, true);
    }

    public PfpUtil(Object reproducible, boolean saveCopy) {
        if (!(reproducible instanceof Reproducible)) {
            throw new IllegalArgumentException("PfpUtil can only accept Reproducible instance.");
        }

        if (saveCopy) {
            mReproducible = ((Reproducible) reproducible).reproduce();
        } else {
            mReproducible = reproducible;
        }
    }

    public Object get() {
        return ((Reproducible) mReproducible).reproduce();
    }
}
