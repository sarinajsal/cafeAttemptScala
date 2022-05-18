package cafe

import java.util.Calendar

object AttempTwo extends App {

  //menu items using case classes
  case class MenuItems(val name: String, val price: Double, val isHot: Boolean, val isFood: Boolean, val isPrem: Boolean)

  val cola = MenuItems("cola", 0.50, false, false, false)
  val coffee = MenuItems("coffee", 1.00, true, false, false)
  val cheeseSandwhich = MenuItems("cheeseSandwhich", 2.00, false, true, false)
  val steakSandwhich = MenuItems("steakSandwhich", 4.5, true, true, false)
  val lobster = MenuItems("lobster", 25.00, true, true, true)

  //customers using case classes, how to update stars??
  case class Customer(val name: String, camePreviousMonth: Boolean, numStars: Int)
  val Lily = Customer("Lily", false, 3)
  val Alfie = Customer("Alfie", true, 6)

  //method to check stars and increase by 1
  def checkCustomerStarsAndIncrement(customer: Customer):Customer ={
    if(customer.numStars >= 8){
      val update = customer.copy(numStars = 8)
      update
    }else {
      val newNumStars = customer.numStars + 1
      val update = customer.copy(numStars = newNumStars)
      update
//      val newVersion = new Customer(update.name, update.camePreviousMonth, update.numStars)
//      newVersion
      //how to return a new person that replaces Alfie?
    }
  }

  //loyalty percentage off menu items
  def percentOff (customer: Customer, menuItems: List[MenuItems]): Double = {
    val notPremItems =  menuItems.filter(x => !x.isPrem) //combine the filter and map??
    val costNotPrem = notPremItems.map(x => x.price).sum
    val onlyPrem = menuItems.filter(x => x.isPrem)
    val costOnlyPrem = onlyPrem.map(x => x.price).sum
    if (customer.numStars <= 2){
        val costNoDiscount = menuItems.map(x => x.price).sum
      costNoDiscount
      }else{
        val percentOff = customer.numStars * 0.025
        val finalCostNoPrem = costNotPrem * (1 - percentOff)
      finalCostNoPrem + costOnlyPrem
      }
  }


  //WORKING ON HAPPY HOUR - rethink, not working yet
  def isHappyHour (menuItems: List[MenuItems]): Double = {
    val totalDrinkPrice =(menuItems.filter(item => item.isFood == false).map(drinks => drinks.price).sum)
    val now = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    if (now >= 10 && now<=21){
      val happyHourDrinkPrice = totalDrinkPrice/2
      happyHourDrinkPrice
    }else{
      totalDrinkPrice
    }
  }


  //bill including service charge
  def bill (menuItems: List[MenuItems], priceAfterLoyalty:Double): String = {
    val isHot =  menuItems.map(x => x.isHot) //list of bools
    val isFood = menuItems.map(x => x.isFood)
    val isPrem = menuItems.map(x => x.isPrem)

    if (isPrem.contains(true)){
      if ( priceAfterLoyalty * 0.25>= 40.00){
        val fin = priceAfterLoyalty + 40.00
        f"1)Your bill is $fin%.2f, service charge of 40.00 included"
      }else{
        val service = priceAfterLoyalty * 0.25
        val finalBill = priceAfterLoyalty * 1.25
        f"2) $finalBill%.2f, service charge of $service included"
      }
    }
    else if(isFood.contains(true) && isHot.contains(true) && isPrem.contains(false)){
      if (priceAfterLoyalty * 0.2 >= 20.00){
        val finalBill = priceAfterLoyalty + 20.00
        f"3)Your bill is $finalBill%.2f, service charge of 20.00 included"
      }else{
        val finalBill = priceAfterLoyalty * 1.2
        val service = priceAfterLoyalty * 0.2
        f"4) Your bill is $finalBill%.2f, service charge of $service included"
      }
    }
    else if (isFood.contains(false)){
      val finalBill = priceAfterLoyalty
      f"5) Your bill is $finalBill%.2f, no service charge"
    } else{
      val finalBill = priceAfterLoyalty
      f"6) Your bill is $finalBill%.2f"
    }
  }

  //final method
  def finalBillMethod(menuItemList: List[MenuItems], customer: Customer): Unit = {
    val loyalty = percentOff(customer,menuItemList)
    val fin = bill(menuItemList, loyalty)
      if (customer.camePreviousMonth == true){
        val updateLoyalty = checkCustomerStarsAndIncrement(customer)
        println(s"The bill for ${customer.name} is $fin. You now have ${updateLoyalty.numStars} loyalty stars!")
      }else{
        println(s"The bill for ${customer.name} is $fin. You have ${customer.numStars} loyalty stars, come again next month to get more stars!")
      }

  }



  finalBillMethod(List(lobster,cola, coffee, lobster, steakSandwhich, cheeseSandwhich), Alfie)

}
