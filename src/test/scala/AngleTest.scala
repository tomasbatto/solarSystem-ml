import org.scalatest.{FlatSpec, Matchers}


class AngleTest extends FlatSpec with Matchers {

  "Adding Angle(350) and Angle(50)" should "be equals to Angle(40)" in {
    Angle(350) + Angle(50) should be (Angle(40))
  }

  "Subtracting Angle(30) to Angle(10)" should "be equals to Angle(340)" in {
    Angle(10) - Angle(30) should be (Angle(340))
  }

  "Multiplying Angle(30)*10" should "be equals to Angle(300)" in {
    Angle(30)*10 should be (Angle(300))
  }

  "Multiplying Angle(40)*10" should "be equals to Angle(300)" in {
    Angle(40)*10 should be (Angle(40))
  }

  "Angle(50)" should "be equals to Angle(50)" in {
    Angle(50)==Angle(50) should be (true)
  }

}

