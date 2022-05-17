package cafe

object AttempTwo extends App {

  case class MenuItems(val name: String, val price: Double, val isHot: Boolean, val isFood: Boolean, val isPrem: Boolean)

  val cola = MenuItems("cola", 0.50, false, false, false)
  val coffee = MenuItems("coffee", 1.00, true, false, false)
  val cheeseSandwhich = MenuItems("cheeseSandwhich", 2.00, false, true, false)
  val steakSandwhich = MenuItems("steakSandwhich", 4.5, true, true, false)
  val lobster = MenuItems("lobster", 25.00, true, true, true)

  case class Customer(val name: String, camePreviousMonth: Boolean, numStars: Int)

  val Lily = Customer("Lily", true, 3)
  val Alfie = Customer("Alfie", true, 6)

  def percentOff (customer: Customer, menuItems: List[MenuItems]): Double = {
    val noPremItems =  menuItems.filter(x => !x.isPrem)
    val costNoPrem = noPremItems.map(x => x.price).sum
    val onlyPrem = menuItems.filter(x => x.isPrem)
    val costOnlyPrem = onlyPrem.map(x => x.price).sum
    if (customer.numStars <= 2){
        val costNoDiscount = menuItems.map(x => x.price).sum
      costNoDiscount
      }else{
        val percentOff = customer.numStars * 0.025
        val finalCostNoPrem = costNoPrem * (1 - percentOff)
      finalCostNoPrem + costOnlyPrem
      }
  }

  def bill (menuItems: List[MenuItems], priceAfterLoyalty:Double): String = {
    val isHot =  menuItems.map(x => x.isHot) //list of bools
    val isFood = menuItems.map(x => x.isFood)
    val isPrem = menuItems.map(x => x.isPrem)
//    val totalPrice = menuItems.map (x => x.price).sum
    if (isPrem.contains(true)){
      if ( priceAfterLoyalty * 0.25>= 40.00){
        val fin = priceAfterLoyalty + 40.00
        f"1)Your bill is $fin%.2f, service charge of 40.00 included"
      }else{
        val service = priceAfterLoyalty * 0.25
        val fin = priceAfterLoyalty * 1.25
        f"2) $fin%.2f, service charge of $service included"
      }
    }
    else if(isFood.contains(true) && isHot.contains(true) && isPrem.contains(false)){
      if (priceAfterLoyalty * 0.2 >= 20.00){
        val fin = priceAfterLoyalty + 20.00
        f"3)Your bill is $fin%.2f, service charge of 20.00 included"
      }else{
        val fin = priceAfterLoyalty * 1.2
        val service = priceAfterLoyalty * 0.2
        f"4) Your bill is $fin%.2f, service charge of $service included"
      }
    }
    else if (isFood.contains(false)){
      val fin = priceAfterLoyalty
      f"5) Your bill is $fin%.2f, no service charge"
    } else{
      val fin = priceAfterLoyalty
      f"6) Your bill is $fin%.2f"
    }
  }

  def fin (menuItemList: List[MenuItems], customer: Customer): Unit = {
    val loyalty = percentOff(customer,menuItemList)
    val fin = bill(menuItemList, loyalty)
    println(s"The bill for ${customer.name} is $fin")
  }

  fin(List(lobster,cola, coffee, lobster), Alfie)

}
