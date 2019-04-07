// ObjectPlus.java: An object that can show itself on screen.
// It is task independent.
abstract class ObjectPlus {
    // Show the object on screen.
    abstract void show();
    // For a complex object, show the part with the given index.
    abstract void showPart(int index);
}