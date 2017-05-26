import org.scalatest.{FlatSpec, Matchers}

class SolarTest extends FlatSpec with Matchers {

  "A SolarSystem with 3 planets aligned with the sun" should "be in drought season" in {
    val planet = Planet(500,Movement(Angle(3), clockwise = true), Angle(20))
    val planet1 = Planet(600,Movement(Angle(1), clockwise = false), Angle(200))
    val planet2 = Planet(200,Movement(Angle(2), clockwise = true), Angle(20))
    val solarSystem = SolarSystem(List(planet,planet1,planet2))
    ConditionCalculator.isDrought(solarSystem) should be (true)
  }

  "A SolarSystem with 3 planets shaped as a triangle with the sun inside" should "be in a rainy season" in {
    val planet = Planet(500,Movement(Angle(3), clockwise = true), Angle(20))
    val planet1 = Planet(600,Movement(Angle(1), clockwise = false), Angle(140))
    val planet2 = Planet(200,Movement(Angle(2), clockwise = true), Angle(260))
    val solarSystem = SolarSystem(List(planet,planet1,planet2))
    ConditionCalculator.isRainy(solarSystem)._1 should be (true)
  }

  "A SolarSystem with 3 planets aligned from each other but not with the sun" should "be in an optimum season" in {
    val planet = Planet(500,Movement(Angle(3), clockwise = true), Angle(20))
    planet.position = Point(500,1500)
    val planet1 = Planet(600,Movement(Angle(1), clockwise = false), Angle(200))
    planet1.position = Point(-700,-900)
    val planet2 = Planet(200,Movement(Angle(2), clockwise = true), Angle(20))
    val solarSystem = SolarSystem(List(planet,planet1,planet2))
    planet2.position = Point(0,500)
    ConditionCalculator.isFine(solarSystem) should be (true)
  }

}