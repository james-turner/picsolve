import org.scalatest._
import org.scalatest.prop.TableDrivenPropertyChecks._

class GoSpec extends FlatSpecLike with Matchers {

  behavior of "The App"

  val shopping =
    Table(
      ("items", "total"), // First tuple defines column names
      (List("Apple", "Apple", "Orange", "Apple"), 2.05),
      (List("Apple", "Apple", "Apple"), 1.80),
      (List(), 0.0) // empty cart
    )

  val discountShopping =
    Table(
      ("items", "total"),
      (List("Apple", "Apple"), .6), // simple bogof
      (List("Apple", "Apple", "Apple"), 1.2), // make sure we dont apply the discount again
      (List("Orange", "Orange", "Orange"), .5), // simple 3 for 2
      (List("Orange", "Orange"), .5), // no discount
      (List("Orange", "Orange", "Orange", "Orange"), 0.75) // 1x 3-4-2 & 1 extra
    )

  it should "sum prices rights" in {

    forAll(shopping) { (items, total) =>

      val cart = new ShoppingCart()

      val sum = cart.sumItems(items)

      sum should be(total)
    }

  }

  it should "apply discounts properly" in {

    forAll(discountShopping) { (items, total) =>
      val cart = new ShoppingCart()

      val sum = cart.sumItems(items)

      sum should be(total)
    }

  }

}
