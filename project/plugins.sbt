logLevel := Level.Warn

resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.8")

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.0.3")

addSbtPlugin("com.lihaoyi" % "workbench" % "0.2.3")

addSbtPlugin("com.typesafe.sbt" % "sbt-less" % "1.0.6")

addSbtPlugin("io.spray" % "sbt-revolver" % "0.7.1")

