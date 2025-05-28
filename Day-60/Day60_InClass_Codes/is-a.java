abstract class shape {
  abstract void draw ();
}

class circle extends shape {
  @Override void draw () { System.out.println("Circle draw called"); }
}

class square extends shape {
  @Override void draw () { System.out.println("Square draw called"); }
}

class test {
  public static void main(String[] args) {
    shape obj = new circle();
    obj.draw();
    obj = new square();
    obj.draw();
    System.out.println(obj instanceof shape);
    System.out.println(obj instanceof circle);
    System.out.println(obj instanceof square);
    System.out.println(obj instanceof Object);
  }
}

// http://stackoverflow.com/questions/11389137/interfaces-and-abstract-classes-confusion-in-java-with-examples