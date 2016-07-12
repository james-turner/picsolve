import org.scalatest._
import org.scalatest.prop.TableDrivenPropertyChecks._

class GoSpec extends FlatSpecLike with Matchers {

  behavior of "The App"

  val shopping =
    Table(
      ("items", "total"),  // First tuple defines column names
      (List("Apple","Apple","Orange","Apple"), 2.05),
      (List("Apple","Apple","Apple"), 1.80),
      (List(), 0.0) // empty cart
    )

  it should "sum prices rights" in {

    forAll(shopping) { (items: List[String], total: Double) =>

      val cart = new ShoppingCart()

      val sum = cart.sumItems(items)

      sum should be (total)
    }

  }

}
