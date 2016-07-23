package tutorial.webapp

import org.scalajs.dom._

import scala.scalajs.js.typedarray.ArrayBuffer

class XHRSound(url: String) {
  val xhr = new XMLHttpRequest
  xhr.responseType = "arraybuffer"

  xhr.open("GET", url)

  xhr.onload = { (e: Event) =>
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

  xhr.send()
}
