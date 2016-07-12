class ShoppingCart(discounts: Discount*) {

  // applying items in pence
  val prods = Map(
    "Apple" -> 60,
    "Orange" -> 25
  )

  def sumItems(items: List[String]): Double = {
    // can probably be rewritten as a for comprehension
    var initialItems = items.map { s => (s, prods(s)) }
    discounts.foreach { d =>
      initialItems = d.applyDiscounts(initialItems)
    }
    initialItems.map(_._2).sum.toDouble/100
  }

}

trait Discount {
  def applyDiscounts(items: List[(String,Int)]): List[(String,Int)]
}

case class ThreeForTwo(name: String) extends Discount{
  override def applyDiscounts(items: List[(String, Int)]): List[(String, Int)] = {
    val discountable = items.filter(_._1 == name)
    val num = discountable.length / 3
    items ::: (0 until num).map { i =>
      (s"$name 3-for-2 discount", -discountable.head._2)
    }.toList
  }
}

case class Bogof(name: String) extends Discount {
  override def applyDiscounts(items: List[(String, Int)]): List[(String, Int)] = {
    val discountable = items.filter(_._1 == name)
    val num = discountable.length / 2
    items ::: (0 until num).map { i =>
      (s"$name bogof discount", -discountable.head._2)
    }.toList
  }
}