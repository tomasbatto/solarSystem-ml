/** A Solar System to simulate.
  *
  *  @constructor create a new Solar System with the planets in it and the sun.
  *  @param planets list of Planets in the Solar System
  *  @param sun the sun of this Solar Systema
  */
case class SolarSystem(planets: List[Planet], sun: SpaceObject = Sun){

  def moveXDays(days: Int): Unit ={
    planets.foreach(_.move(days))
  }

  def move1day() : Unit = {
    moveXDays(1)
  }

  def moveXyears(years: Int) : Unit = {
    moveXDays(years*365)
  }

  def resetSolarSystem(): Unit = planets.foreach(_.resetPosition())

}

/** A Space Object to give a position in the space.
  *
  *  @constructor create a new Solar System with the planets in it and the sun.
  *  @param position a Point(x,y), representing the position of the object in the space
  */
class SpaceObject(var position: Point)

/** A Space Object to give a position in the space.
  *
  *  @constructor create a new Solar System with the planets in it and the sun.
  *  @param distance a number representing the distance of the planet to the sun.
  *  @param movement a Movement(Angle(v),clockwise) representing the speed and direction of the planet.
  *  @param angle an Angle(z) representing the angle formed with the x-axis.
  */
case class Planet(distance: Double, movement: Movement, var angle: Angle) extends SpaceObject(Point(distance,0)){

  def move(): Unit ={
    move(1)
  }

  def move(days: Int): Unit ={
    //if the movement is clockwise the angle decrement and if it is clockwise it increment
    angle = if(movement.clockwise) angle - (movement.speed * days) else angle + (movement.speed * days)
    this.position = angle.toPoint(distance)
  }

  def resetPosition(): Unit ={
    angle = Angle(90)
    this.position = Point(0,distance)
  }
}

/** Movement represent the angular speed in [days]/[°] of a certain object
  *
  *  @constructor create a new Solar System with the planets in it and the sun.
  *  @param speed Angle(x) that the object moves every day.
  *  @param clockwise direction of the movement.
  */
case class Movement(speed: Angle, clockwise: Boolean)

/** Solar Systems for the test */
object SolarSystem extends SolarSystem(List(Vulcano, Ferengi, Betasoide), sun = Sun)

/** The Sun a SpaceObject on the Point(0,0) */
object Sun extends SpaceObject(Point(0,0))

/** Planets provided for the test */
object Vulcano extends Planet(1000,Movement(Angle(5),clockwise = true),Angle(90))

object Ferengi extends Planet(500, Movement(Angle(1),clockwise = true), Angle(90))

object Betasoide extends Planet(2000, Movement(Angle(3),clockwise = false), Angle(90))




