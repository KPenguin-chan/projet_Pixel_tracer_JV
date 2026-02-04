package shape;

/**
 * Class shape.Square
 */
public class Square extends Shape {

  //
  // Fields
  //

  private int length;
  private Point new_attribute;
  
  //
  // Constructors
  //
  public Square () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of length
   * @param newVar the new value of length
   */
  public void setLength (int newVar) {
    length = newVar;
  }

  /**
   * Get the value of length
   * @return the value of length
   */
  public int getLength () {
    return length;
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
