/**
  * Created by batto on 5/23/17.
  */

/* Object to calculate the weather condition in a SolarSystem**/
object ConditionCalculator {

  /** Checks if it a Drought day in the SolarSystem
    *
    *  @param solarSystem SolarSystem to check
    *  @return true if it is drought, false it is not
    *
    */
  def isDrought(solarSystem: SolarSystem): Boolean ={
    if(solarSystem.planets.length >= 3){
      val positions = solarSystem.planets.map(_.position)
      val line = Line(positions.head, positions(1))
      return line.isInLine(positions(2)) && line.isInLine(solarSystem.sun.position)
    }
    false
  }

  /** Checks if it a Rainy day in the SolarSystem
    *
    *  @param solarSystem SolarSystem to check
    *  @return true if it is drought, false it is not
    *
    */
  def isRainy(solarSystem: SolarSystem): (Boolean, Double) = {
    if(solarSystem.planets.length >= 3){
      val positions = solarSystem.planets.map(_.position)
      val triangle = Triangle(positions.head, positions(1), positions(2))
      return (triangle.isInTriangle(solarSystem.sun.position),triangle.perimeter)
    }
    (false, 0)
  }

  /** Checks if it a Optimum day in the SolarSystem
    *
    *  @param solarSystem SolarSystem to check
    *  @return true if it is drought, false it is not
    *
    */
  def isFine(solarSystem: SolarSystem): Boolean = {
    if(solarSystem.planets.length >= 3){
      val positions = solarSystem.planets.map(_.position)
      val line = Line(positions.head, positions(1))
      return line.isInLine(positions(2)) && !line.isInLine(solarSystem.sun.position)
    }
    false
  }
}


