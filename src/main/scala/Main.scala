
/**
  * Created by batto on 5/23/17.
  */

import akka.actor.ActorSystem
import spray.routing.SimpleRoutingApp
import spray.httpx.SprayJsonSupport._
import SimulationResultJsonProtocol._
import com.mongodb.casbah.commons.MongoDBObject
import spray.http.StatusCode
import salat.global._

import scala.util.Try

object Main extends App with SimpleRoutingApp {

  implicit val system = ActorSystem("my-system")

  startServer(interface = "0.0.0.0", port = 8080) {
    path("") {
      get {
        complete {
          "Bienvenido al API de Vulcano:\n" +
          "- Para correr la simulación => GET -> /simular \n" +
            "-Para obtener los resultados => GET -> /resultado \n" +
            "-Para consultar el clima en un dia => GET -> /clima?dia=256"
        }
      }
    } ~
    path("simular") {
      get {
        complete {
          Rounder.decimals(2)
          StatusCode.int2StatusCode(200) -> Simulation.simulateXyears(10,SolarSystem)
        }
      }
    } ~
    path("resultado") {
      get {
        // ask for the result of the simulation
        complete {
          var list = List("lluvioso","optimo","sequia")
          val results = ResultDAO.find(ref = MongoDBObject("_id" -> MongoDBObject("$in" -> list))).toList
          if(results.nonEmpty) {
            StatusCode.int2StatusCode(200) ->results
          }else{
            StatusCode.int2StatusCode(404) -> Error("Resultado no encontrado, primero debe simular GET -> /simular ")
          }
        }
      }
    } ~
    path("clima") {
      get {
        // ask for the climate of certain day /clima?dia={diaX}
        parameters('dia){
          (dia)=> complete {
            if(Try(dia.toInt).isFailure){
              StatusCode.int2StatusCode(404) -> Error("Dia no encontrado")
            }else {
              val maybeDay = DayDAO.findOneById(dia.toInt)
              if(maybeDay.isDefined) {
                StatusCode.int2StatusCode(200) ->maybeDay.get
              }else{
                StatusCode.int2StatusCode(404) -> Error("Dia no encontrado")
              }
            }
          }
        }
      }
    }
  }
}

