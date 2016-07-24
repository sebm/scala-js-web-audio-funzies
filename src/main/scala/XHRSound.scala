package tutorial.webapp

import org.scalajs.dom._
import org.scalajs.dom.ext.Ajax

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.scalajs.js.typedarray.ArrayBuffer

class XHRSound(url: String) {
  val context = new AudioContext()

  val audioData: Future[ArrayBuffer] = Ajax.get(url, responseType = "arraybuffer").map { xhr =>
    xhr.response.asInstanceOf[ArrayBuffer]
  }

  val audioBuffer: Future[AudioBuffer] = {
    audioData.flatMap { data =>
      context.decodeAudioData(data).toFuture
    }
  }

  def play(): Unit = {
    audioBuffer.map { buffer =>
      val source = context.createBufferSource()
      source.buffer = buffer
      source.connect(context.destination)

      source.start()
    }
  }

}
