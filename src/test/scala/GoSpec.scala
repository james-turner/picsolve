import org.scalatest._

class GoSpec extends FlatSpecLike with Matchers {

  behavior of "The App"

  it should "sum prices rights" in {

    val cart = new ShoppingCart()

    val total = cart.sumItems(List("Apple","Apple","Orange","Apple"))

    total should be (2.05)

  }

}
