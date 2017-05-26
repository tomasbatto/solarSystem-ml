import salat.annotations.Key
import spray.json.DefaultJsonProtocol


/** Simulation is used to simulate certain time in a SolarSystem */
object Simulation {

  def simulateXDays(days: Int, solarSystem: SolarSystem): Array[ClimateResult] ={
    var drought = 0
    var droughtPeriods = (0, false)
    var rainy = 0
    var rainyPeriods = (0, false)
    var fine = 0
    var maxRainy: MaxRainResult = MaxRainResult(-1, 0.0)

    ResultDAO.collection.drop()
    DayDAO.collection.drop()

    for(a <- 0 to days){
      var day = Day(0,"")
      if(ConditionCalculator.isFine(solarSystem)){
        fine += 1
        droughtPeriods = (droughtPeriods._1, false)
        rainyPeriods = (rainyPeriods._1, false)
        day = Day(a,clima = "óptimo")
      }else if(ConditionCalculator.isDrought(solarSystem)){
        if(!droughtPeriods._2) droughtPeriods = (droughtPeriods._1 + 1, true)
        rainyPeriods = (rainyPeriods._1, false)
        droughtPeriods = (droughtPeriods._1, true)
        drought+= 1
        day = Day(a,clima = "sequía")
      }else {
        val rainyResult = ConditionCalculator.isRainy(solarSystem)
        if (rainyResult._1) {
          if(maxRainy.perimeter < rainyResult._2){
            maxRainy = MaxRainResult(a, rainyResult._2)
          }
          if(!rainyPeriods._2) rainyPeriods = (rainyPeriods._1 + 1, true)
          droughtPeriods = (droughtPeriods._1, false)
          rainyPeriods = (rainyPeriods._1, true)
          rainy += 1
          day = Day(a,clima = "lluvía")
        }else{
          day = Day(a,clima = "normal")
        }

      }
      DayDAO.insert(day)
      solarSystem.move1day()
    }

    val droughtResult = ClimateResult("sequia", droughtPeriods._1, drought)
    val rainyResult = ClimateResult("lluvioso", rainyPeriods._1, rainy, maxRainy.day)
    val fineResult = ClimateResult("optimo", 0, fine)
    val results = Array(droughtResult, rainyResult, fineResult)
    ResultDAO.insert(results)
    results
  }

  def simulateXyears(years: Int, solarSystem: SolarSystem) : Array[ClimateResult] = {
    simulateXDays(years * 365, solarSystem)
  }

}


/** classes for database usage and json parsing*/
case class MaxRainResult(day: Int, perimeter: Double)
case class ClimateResult(@Key("_id")clima: String, periodos: Int, dias: Int, max: Int = 0)
case class Day(@Key("_id") dia : Int, clima: String)
case class Error(message: String)

object SimulationResultJsonProtocol extends DefaultJsonProtocol{
  implicit val errorFormat = jsonFormat1(Error)
  implicit val climateFormat = jsonFormat4(ClimateResult)
  implicit val dayFormat = jsonFormat2(Day)
  implicit val maxRainFormat = jsonFormat2(MaxRainResult)
}
