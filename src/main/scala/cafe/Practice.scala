package cafe

object Practice extends App {

 class Car( val make: String, val model:String, val year:Int)

  val mazda = new Car("a","b",1992)

  case class Aircraft (name: String, inService: Boolean, buildNo: Int)

  val plane1 = Aircraft("Plane1", true, 2)
  val plane1copy = plane1.copy(inService = false)

  //case objects

  sealed trait Weekday
  case object Monday extends Weekday
  case object Wednesday extends Weekday
  case object Friday extends Weekday

  def test(weekday: Weekday) = {
    weekday match {
      case Monday => "oh no"

      case Friday => "tgif"
    }
  }

  println(test(Friday))

//  abstract class error (val status: String, val message: String)
//  case object InternalServer("servererror", "an internal server error occured")
  //further explanation needed for abstract class error example

  //pattern matching

  sealed trait temp
  case object Hot extends temp
  case object Cold extends temp

  sealed trait foodType
  case object Drink extends foodType
  case object Food extends foodType

  case class MenuItems(val name: String, temp: Practice.temp, val price: Double, foodType: Practice.foodType)

  val cola = MenuItems("cola", temp = Cold, 0.50, foodType = Drink)
  val coffee = MenuItems("coffee", temp = Hot, 1.00, foodType = Drink)
  val cheeseSandwhich = MenuItems("cheeseSandwhich", temp = Cold, 2.00, foodType = Food)
  val steakSandwhich = MenuItems("steakSandwhich", temp = Hot, 4.50, foodType = Food)

  def simpleBill(menuItems: List[MenuItems]):Double = {
    val pricemap = menuItems.map(menuItems => menuItems.price)
    val total = pricemap.sum
    val bill = total * 1.15
    bill
  }
  println(simpleBill(List(cola, coffee, cola)))
  //   val mapFoodTypes = menuItems.map(menuItems=> menuItems.foodType)


  def billWithService(menuItems: List[MenuItems]): Double = {
    val z: List[MenuItems] = menuItems
    val x = menuItems.map(x => x.price)
    val y = x.sum * 1.2
    val v = x.sum
    if (z.contains((cheeseSandwhich )) || z.contains(steakSandwhich)) {
      y
    } else {
      v
    }
  }

  val a: List[MenuItems] = List(cheeseSandwhich, cola)
  val b = a.contains(cheeseSandwhich)
  println(b)

  println(billWithService(List(cola,steakSandwhich, steakSandwhich)) + "is your bill")

//  val c: List[MenuItems] = List(cheeseSandwhich,steakSandwhich,cola)
//  println(c.contains(cheeseSandwhich))
//  val e = c.map(menuItems => menuItems.price)
//  println(e)
//  val f = (e.sum) *1.2
//  println(f)





//  def trying (menuItems: List[MenuItems]):Double ={
//    val mapFoodTypes = menuItems.map(menuItems=> menuItems.foodType)
//    mapFoodTypes match {
//      case "cheesesandwhich" => "cheeseSandwhich"
//      case "cola" =>
//    }

  val bt= 1*0.2.ceil
  println(bt)
  }









