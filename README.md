# Utilities
Utilities in Java language.

### [Wiki](https://github.com/dxinl/Utilities/wiki)

### PfpUtil
Mostly, states of a mutable object will be changed by context. But in some infrequent cases, we need to keep an object immutable to represent some business logic such as 'undo' or 'revert'. Usually, we implement these business logic with adequate caution. But no matter how carefully developers are, the maintainability of such codes are very low.
</p>
This utility helps to implement business logic like above in higher maintainability way. It prevents an object to be published by way of publishing its copy.

### StickyObservable
Activities will be destroy and recreate if users change device's `orientation on Android. If a background task had been started before destroyed, in normally ```Observable``` implementation, activities will not get the result after recreate. StickyObservable can help to resolve this problem.
</p>
This utility use generic type to avoid class cast exception also.
