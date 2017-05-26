/** A Point on the space.
  *
  *  @constructor create a new Point.
  *  @param x position in the x-axis
  *  @param y position in the x-axis
  */
case class Point(x: Double, y: Double)

/** A Line on the space.
  *
  *  @constructor create a new Line using two Points on the space.
  *  @param point1 point on the space.
  *  @param point2 another point on the space.
  */
case class Line(point1: Point, point2: Point){

  /** Checks if a Point is in the line
    *
    *  @param point point on the space.
    *  @return true if it is part of line, false it is not
    *
    */
  def isInLine(point: Point): Boolean = {
    val result = Rounder.round (point.x - point1.x) * (point2.y - point1.y) - (point.y - point1.y) * (point2.x - point1.x)
    result == 0
  }

  /** Distance between the two points
    *
    *  @return the distance between the two points as a Double
    */
  def distance: Double = {
    math.sqrt(math.pow(point2.x - point1.x,2) + math.pow(point2.y - point1.y, 2))
  }

}

/** A Tringle on the space.
  *
  *  @constructor create a new Triangle using three Points on the space as vertices.
  *  @param point1 point on the space.
  *  @param point2 another point on the space.
  *  @param point3 another point on the space.
  */
case class Triangle(point1: Point, point2: Point, point3: Point){


  /** To calculate the perimeter of the triangle
    *
    *  @return the perimeter as a Double
    */
  def perimeter : Double = {
    Line(point1,point2).distance + Line(point1,point3).distance + Line(point2,point3).distance
  }


  /** Checks if a Point is inside the triangle
    *
    *  @param point point on the space.
    *  @return true if it is inside the triangle, false it is not
    *
    */
  def isInTriangle(point: Point) : Boolean = {
    val orientation1 = orientation(Triangle(point1,point2,point))
    val orientation2 = orientation(Triangle(point2,point3,point))
    val orientation3 = orientation(Triangle(point3,point1,point))
    (orientation1==orientation2==orientation3) && !(orientation1^orientation1)
  }

  /** Orientation of this triangle
    *
    *  @return true if it is positive, false if negative
    */
  def orientation : Boolean = orientation(this)

  /** Orientation of another triangle
    *
    *  @return true if it is positive, false if negative
    */
  private def orientation(triangle: Triangle) : Boolean = {
    (((triangle.point1.x - triangle.point3.x) * (triangle.point2.y - triangle.point3.y)) -
      ((triangle.point1.y - triangle.point3.y) * (triangle.point2.x - triangle.point3.x))) >= 0
  }
}



case class Angle(number: Double){

  /** Adding angles.
    *
    *  @param angle another Angle
    */
  def +(angle: Angle): Angle = {
    val result = number + angle.number
    if(result < 360) Angle(result)
    else Angle(result % 360)
  }

  /** Subtracting angles.
    *
    *  @param angle another Angle
    */
  def -(angle: Angle): Angle ={
    val result = number - angle.number
    if(result >= 0) Angle(result)
    else Angle(360 + result)
  }

  /** Multiplying the angle .
    *
    *  @param num a number
    */
  def *(num: Double): Angle = Angle(number * num % 360)

  /** Comparing angles.
    *
    *  @param angle another Angle
    */
  def ==(angle: Angle) : Boolean = this.number == angle.number

  /** Return a Point depending of the distance to Point(0,0) and the angle.
    *
    *  @param distance number representing the distance to Point(0, 0)
    */
  def toPoint(distance: Double) : Point = {
    val x = Rounder.round ((math cos math.toRadians(this.number)) * distance)
    val y = Rounder.round ((math sin math.toRadians(this.number)) * distance)
    Point(x,y)
  }

}

/** A Rounder used for rounding results and set precision */
object Rounder{
  private var decimals = 2

  /** Setter of number of decimals
    *
    *  @param number number of decimals desired
    */
  def decimals(number:Int): Unit = {
    decimals = 2
  }

  /** Round a number
    *
    *  @param x the number
    */
  def round(x: Double) : Double = {
    math rint (x * math.pow(10,decimals))/math.pow(10,decimals)
  }
}
