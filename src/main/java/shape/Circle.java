package shape;

/**
 * Class shape.Circle
 */
public class Circle extends Shape {

  //
  // Fields
  //

  private int radius;
  private Point new_attribute;
  
  //
  // Constructors
  //
  public Circle () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of radius
   * @param newVar the new value of radius
   */
  public void setRadius (int newVar) {
    radius = newVar;
  }

  /**
   * Get the value of radius
   * @return the value of radius
   */
  public int getRadius () {
    return radius;
  }

  /**
   * Set the value of new_attribute
   * @param newVar the new value of new_attribute
   */
  public void setNew_attribute (Point newVar) {
    new_attribute = newVar;
  }

  /**
   * Get the value of new_attribute
   * @return the value of new_attribute
   */
  public Point getNew_attribute () {
    return new_attribute;
  }

  //
  // Other methods
  //

  /**
   * @return       String
   */
  public String toString()
  {
  }


}
