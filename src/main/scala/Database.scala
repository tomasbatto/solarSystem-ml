import com.mongodb.casbah.MongoConnection
import salat.global._
import salat.dao.SalatDAO

/** DAO for accessing the MongoDB Database and their respective collections*/
object DayDAO extends SalatDAO[Day, Int](collection= MongoConnection()("solarSystem")("day"))
object ResultDAO extends SalatDAO[ClimateResult, String](collection= MongoConnection()("solarSystem")("result"))


