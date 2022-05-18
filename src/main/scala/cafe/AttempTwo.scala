package cafe

import java.util.Calendar

object AttempTwo extends App {

  //menu items using case classes
  case class MenuItems(val name: String, val price: Double, val isHot: Boolean, val isFood: Boolean, val isPrem: Boolean) //TODO: case classes should be in another file, "val" is default so not needed
  //TODO: Wouldn't this be a "MenuItem"? What was your reason behind "MenuItems"?

  val cola = MenuItems("cola", 0.50, false, false, false) //TODO: Using named parameters would make this clear
  val coffee = MenuItems("coffee", 1.00, true, false, false)
  val cheeseSandwhich = MenuItems("cheeseSandwhich", 2.00, false, true, false)
  val steakSandwhich = MenuItems("steakSandwhich", 4.5, true, true, false)
  val lobster = MenuItems("lobster", 25.00, true, true, true)

  //TODO: Would you say that a lobster is a value or an Object? What benefit is there of lobster being an Object over val? Not saying you're wrong but there are many ways to represent things

  //customers using case classes, how to update stars??
  case class Customer(val name: String, camePreviousMonth: Boolean, numStars: Int)
  val Lily = Customer("Lily", false, 3)
  val Alfie = Customer("Alfie", true, 6) //TODO: lowercase for vals

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
  /*
  TODO: once val Alfie is made, nothing about it should be changed. It is immutable. Think about how you can use the `new` customer you've made
    Changing aspects about the initial Alfie without naming leads to a less transparent process,
    i.e. checkCustomerStarsAndIncrement(Alfie) returns a Customer, if I then call that Alfie/replace Alfie, then how will I know what has happened to Alfie throughout many defs
    Calling the new Customer something like alfieAfterOneCheck lets us know that this is Alfie after one pass.
    This is a very silly example, but hopefully it will help you answer your question
   */

  //loyalty percentage off menu items
  def percentOff (customer: Customer, menuItems: List[MenuItems]): Double = {
    val notPremItems =  menuItems.filter(x => !x.isPrem) //combine the filter and map?? //TODO: You can, just by menuItems.filter(x => !x.isPrem).map(x => x.price).sum. Up to you
    val costNotPrem = notPremItems.map(x => x.price).sum //TODO: Try using something more descriptive than "x"
    val onlyPrem = menuItems.filter(x => x.isPrem)
    val costOnlyPrem = onlyPrem.map(x => x.price).sum
    if (customer.numStars <= 2){
        val costNoDiscount = menuItems.map(x => x.price).sum //TODO: Hint: do you need this val here???
      costNoDiscount
      }else{
        val percentOff = customer.numStars * 0.025
        val finalCostNoPrem = costNotPrem * (1 - percentOff)
      finalCostNoPrem + costOnlyPrem
      }
  }
/*
  TODO: costOnlyPrem and some other vals are only used in else. Might be easier to follow if the vals are constructed in the if and else
      e.g.
      def percentOff (customer: Customer, menuItems: List[MenuItems]): Double =
        if (customer.numStars <= 2)
          menuItems.map(x => x.price).sum
        else {
          val costNotPrem = menuItems
            .filter(item => !item.isPrem)
            .map(item => item.price).sum
          val costOnlyPrem = menuItems
            .filter(item => item.isPrem)
            .map(item => item.price).sum
          val percentOff = customer.numStars * 0.025
          val finalCostNoPrem = costNotPrem * (1 - percentOff)
            finalCostNoPrem + costOnlyPrem
        }
 */


  //WORKING ON HAPPY HOUR - rethink, not working yet
  def isHappyHour (menuItems: List[MenuItems]): Double = { //TODO: isHappyHour returns a Double? Sounds like it should return a Boolean, possibly rename
    val totalDrinkPrice =(menuItems.filter(item => item.isFood == false).map(drinks => drinks.price).sum) //TODO: Yes! Good use of these functions, but what can item.isFood == false be simplified to?
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
    val isHot =  menuItems.map(x => x.isHot) //list of bools //TODO: Instead of commenting list of bools, go ahead and give this val a type!
    val isFood = menuItems.map(x => x.isFood)
    val isPrem = menuItems.map(x => x.isPrem)

    if (isPrem.contains(true)){ //TODO: could you do menuItems.contains(...)???
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
      if (customer.camePreviousMonth == true){ //TODO: again could customer.camePreviousMonth == true be simplified?
        val updateLoyalty = checkCustomerStarsAndIncrement(customer) //TODO: updatedLoyalty is a Customer? Possibly rename or change type?
        println(s"The bill for ${customer.name} is $fin. You now have ${updateLoyalty.numStars} loyalty stars!")
      }else{
        println(s"The bill for ${customer.name} is $fin. You have ${customer.numStars} loyalty stars, come again next month to get more stars!")
      }

  }



  finalBillMethod(List(lobster,cola, coffee, lobster, steakSandwhich, cheeseSandwhich), Alfie)
  /*
  TODO: Testing here seems a bit sparse,
   for instance I assume you tested as you went on with println but how do you know those old tests are passing still???
   Also comparing printlns as a way of testing makes life difficult, highly recommend a tests suite that compares values over messages
   */

}
