logLevel := Level.Warn

// The Typesafe repository
resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/maven-releases/"

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.5.4")
addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.10")
addSbtPlugin("com.vmunier" % "sbt-play-scalajs" % "0.3.0")
addSbtPlugin("com.typesafe.sbt" % "sbt-gzip" % "1.0.0")
