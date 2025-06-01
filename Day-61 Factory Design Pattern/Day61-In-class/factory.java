/*  How do we write code that creates objects in which the precise type of the
    object may not be known until runtime?
*/

interface shape {
  void draw ();
}

class circle implements shape {
  @Override public void draw () { System.out.println("Circle draw called"); }
}

class square implements shape {
  @Override public void draw () { System.out.println("Square draw called"); }
}

class rectangle implements shape {
  @Override public void draw () { System.out.println("Rectangle draw called"); }
}

class hexagon implements  shape{
  @Override public void draw(){System.out.println("Hexagon draw called");}
}
enum SHAPES { 
  CIRCLE, RECTANGLE, SQUARE,HEXAGON;
}

class ShapeFactory {	
  // getShape method returns the object of type shape 
  public shape getShape(SHAPES shapeType) {
    switch(shapeType)
    {
      case CIRCLE -> {
          return new circle();
          }
      case RECTANGLE -> {
          return new rectangle();
          }
      case SQUARE -> {
          return new square();
          }
      case HEXAGON -> {
          return new hexagon();
          }
      default -> throw new UnsupportedOperationException("Unsupported shape");
    }
  }
}

class FactoryDemo {
  public static void main(String[] args) {
    ShapeFactory shapeFactory = new ShapeFactory();

    shape shape1 = shapeFactory.getShape(SHAPES.CIRCLE);
    shape1.draw();

    shape shape2 = shapeFactory.getShape(SHAPES.RECTANGLE);
    shape2.draw();

    shape shape3 = shapeFactory.getShape(SHAPES.SQUARE);
    shape3.draw();
  }
}
