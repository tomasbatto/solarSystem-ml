
import org.scalatest._

class GeometryTest extends FlatSpec with Matchers {

  "With a Line formed by Point(1,1) and Point(0,0) a Point(2,2)" should "be in the line" in {
    val line =  Line(Point(1,1),Point(0,0))
    line.isInLine(Point(2,2)) should be (true)
  }

  "With a Line formed by Point(1,1) and Point(1,0) a Point(0,0)" should  "not be in the line" in {
    val line =  Line(Point(1,1),Point(1,0))
    line.isInLine(Point(0,0)) should not be (true)
  }

  "Point(0,0)" should  "be inside a Triangle with Point(0,500), Point(-600,-200) and Point(300,-1000) as vertices" in {
    val triangle =  Triangle(Point(0,500),Point(-600,-200),Point(300, -1000))
    triangle.isInTriangle(Point(0,0)) should be (true)
  }

  "Point(600,1000)" should  "be inside a Triangle with Point(0,500), Point(-600,-200) and Point(300,-1000) as vertices" in {
    val triangle =  Triangle(Point(0,500),Point(-600,-200),Point(300, -1000))
    triangle.isInTriangle(Point(600,1000)) should not be (true)
  }

}
