package sounds

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

  val length: Future[Double] = {
    audioBuffer.map { buffer => buffer.duration }
  }

  def play(): Unit = {
    val source = context.createBufferSource()

    audioBuffer.map { buffer =>
      source.buffer = buffer
      source.connect(context.destination)

      source.start()
    }
  }

  @deprecated
  def playSliceAtIndex(i: Int, slices: Int): Unit = {
    val source = context.createBufferSource()

    audioBuffer.map { buffer =>
      val sliceDuration = buffer.duration / slices

      source.buffer = buffer
      source.connect(context.destination)

      source.start(0, sliceDuration*(i+1), sliceDuration)

    }
  }

  @deprecated
  def playBySlices(): Unit = {
    val slices = 16

    audioBuffer.map { buffer =>
      val sliceDuration = buffer.duration / slices

      val now = context.currentTime

      (0 to slices-1).foreach { i =>
        println(i)
        val source = context.createBufferSource()
        source.buffer = buffer
        source.connect(context.destination)

        source.start(now + sliceDuration*i , sliceDuration*i, sliceDuration )
      }
    }

  }

}
