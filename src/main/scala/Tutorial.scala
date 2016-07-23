package tutorial.webapp

import scala.scalajs.js.JSApp

object TutorialApp extends JSApp {

  def main(): Unit = {
    val amen = new XHRSound("https://sebm.github.io/squrt/amen.mp3")
  }
}
