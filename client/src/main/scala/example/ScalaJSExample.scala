package example

import scala.scalajs.js
import org.scalajs.dom
import shared.SharedMessages
import sounds.XHRSound

object ScalaJSExample extends js.JSApp {
  def main(): Unit = {
    val amen = new XHRSound("assets/audio/amen.mp3")

    // The instrument shouldn't have to deal with its sequencing
    // The instrument should expose its slices such that they can be played from this outer context

    amen.playBySlices()
  }
}
