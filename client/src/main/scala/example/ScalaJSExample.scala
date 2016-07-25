package example

import scala.scalajs.js
import org.scalajs.dom
import shared.SharedMessages
import sounds.XHRSound

object ScalaJSExample extends js.JSApp {
  def main(): Unit = {
    val amen = new XHRSound("assets/audio/amen.mp3")

    amen.play()
  }
}
