package cafe

object AttemptUsingSealedTraits extends App{

  sealed trait temp
  case object Hot extends temp
  case object Cold extends temp

  sealed trait foodType
  case object Drink extends foodType
  case object Food extends foodType

  case class MenuItems(val name: String, temp: AttemptUsingSealedTraits.temp, val price: Double, foodType: AttemptUsingSealedTraits.foodType)

  val cola = MenuItems("cola", temp = Cold, 0.50, foodType = Drink)
  val coffee = MenuItems("coffee", temp = Hot, 1.00, foodType = Drink)
  val cheeseSandwhich = MenuItems("cheeseSandwhich", temp = Cold, 2.00, foodType = Food)
  val steakSandwhich = MenuItems("steakSandwhich", temp = Hot, 150.00, foodType = Food)

  def test(ft: foodType): String ={
    ft match {
      case Drink => "adrink"
    }
  }

//  def anotherAttemptOMG(menuItems: List[MenuItems]): Unit = {
//    val x = menuItems.map(x => x.foodType) //foodtype list
//    val temp = menuItems.map(temp => temp.temp) //temp list
//    val y = menuItems.map(y => y.price) //price list
//
//    def hotFoodServiceCharge(foodtype: List[foodType]): Unit = {
//      foodtype match {
//        case Drink => "adrink"
//      }
//    }
//  }

//  def billWFood(menuItems: List[MenuItems]):String = {
//    val x = menuItems.map(x => x.foodType)
//    if (x.contains(Food)){
//      val y = menuItems.map(x => x.price)
//      val finalTotal = y.sum *1.1
//      val str = f"your total is $finalTotal%.2f"
//      str
//    }else{
//      val str2 = f"your total is ${menuItems.map(x=>x.price).sum}%.2f"
//      str2
//    }
//  }

//  println(billWFood(List(cola)))

//DOESNT WORK
  def billWHotFood(menuItems: List[MenuItems]):String = {
    val x = menuItems.map(x => x.foodType)
    val temp = menuItems.map(temp => temp.temp)
    val y = menuItems.map(y => y.price)
    val totalWOService = y.sum
      if (x.contains(Food) && temp.contains(Hot)){
        val hotFoodFinalService = totalWOService * 0.2.max(20)
          if(hotFoodFinalService >= 20.00) {val n = hotFoodFinalService}
          else {hotFoodFinalService}
        val hotfoodFinal = y.sum + hotFoodFinalService
        val str3 = f"your total for hot food service is $hotFoodFinalService and total is $hotfoodFinal"
        str3
      }else if (x.contains(Food)){
        val finalTotal = y.sum *1.1
        val str = f"your total is $finalTotal%.2f"
        str
      }
      else{
        val str2 = f"your total is ${menuItems.map(x=>x.price).sum}%.2f"
        str2
      }
  }

 println(billWHotFood(List(steakSandwhich)))






//  ASK ABOUT PATTERN MATCHING SEALED TRAITS/CASE OBJECTS
//
//  val menuItems: List[MenuItems] = List(steakSandwhich, cheeseSandwhich, cola)
//  val x = menuItems.map(x => x.temp)
//  println(x)
//  val z = x match {
//    case food: foodType => "this is food"
//    case
//  }
//  println(x.contains(Hot))



}
