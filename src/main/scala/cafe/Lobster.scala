package cafe

import cafe.AttemptTwo.MenuItems

object Lobster extends MenuItems("name", 4, true, true, true) {
  def overrideName(inputName: String) = {
    this.copy(name = inputName)
  }

}

