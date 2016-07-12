class ShoppingCart {

  // applying items in pence
  val prods = Map(
    "Apple" -> 60,
    "Orange" -> 25
  )

  def sumItems(items: List[String]): Double = {
    items.map { s =>
      prods(s).toDouble
    }.sum/100
  }

}
