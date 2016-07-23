package tutorial.webapp

import org.scalajs.dom._
import org.scalajs.dom.ext.Ajax

import scala.scalajs.js.typedarray.ArrayBuffer

class XHRSound(url: String) {
  
  Ajax.get(url).onSuccess { case xhr =>
    if (xhr.status == 200) {
      val audioData = xhr.response.asInstanceOf[ArrayBuffer]

      val context = new AudioContext()

      context.decodeAudioData(audioData, { (buffer: AudioBuffer) =>

        val source = context.createBufferSource()
        source.buffer = buffer
        source.connect(context.destination)

        source.start()
      })
    }
  }
}
