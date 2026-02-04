package shape;

/**
 * Class shape.Point
 */
public class Point extends Shape {

  //
  // Fields
  //

  private int pos_x;
  private int pos_y;
  private Polygone new_attribute;
  
  //
  // Constructors
  //
  public Point () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of pos_x
   * @param newVar the new value of pos_x
   */
  public void setPos_x (int newVar) {
    pos_x = newVar;
  }

  /**
   * Get the value of pos_x
   * @return the value of pos_x
   */
  public int getPos_x () {
    return pos_x;
  }

  /**
   * Set the value of pos_y
   * @param newVar the new value of pos_y
   */
  public void setPos_y (int newVar) {
    pos_y = newVar;
  }

  /**
   * Get the value of pos_y
   * @return the value of pos_y
   */
  public int getPos_y () {
    return pos_y;
  }

  /**
   * Set the value of new_attribute
   * @param newVar the new value of new_attribute
   */
  public void setNew_attribute (Polygone newVar) {
    new_attribute = newVar;
  }

  /**
   * Get the value of new_attribute
   * @return the value of new_attribute
   */
  public Polygone getNew_attribute () {
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
    String str = "("+ this.pos_x + this.pos_y + ")";
    return str;
  }


}
