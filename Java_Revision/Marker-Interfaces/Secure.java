// Now this is a Marker interface , no fields , no methods
public interface Secure {

}

/*
 What is a Marker Interface?

A marker interface is an interface that has no methods or fields — it’s completely empty.
Its purpose is not to define behavior, but to mark or tag a class with some metadata.

In simple words:

A marker interface “marks” a class to tell the JVM or compiler something special about that class.



Where are marker interfaces used in real projects?

Serialization frameworks: Java serialization, JSON/XML serializers.

Cloning: Cloneable used by Object.clone().

Remote communication (RMI).

Collections: RandomAccess marker in ArrayList.

Custom frameworks (legacy codebases) — before annotations became common.
 */