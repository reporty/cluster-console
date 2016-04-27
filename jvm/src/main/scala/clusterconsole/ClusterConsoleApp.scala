package clusterconsole

import akka.actor.{ ActorRef, ActorSystem, Props }
import akka.util.Timeout
import clusterconsole.core.LogF
import clusterconsole.http._
import com.typesafe.config.ConfigFactory

import scala.concurrent.duration._

object ClusterConsoleApp extends App with LogF {

  args.logDebug("ClusterConsoleApp starting with args:" + _.toList.toString)

  // todo - abstract clusterconsole role into a variable
  val akkaConf =
    """akka.remote.netty.tcp.hostname="0.0.0.0"
      |akka.remote.netty.tcp.port=3001
      |akka.cluster.roles = [clusterconsole]
      |""".stripMargin

  val config = ConfigFactory.parseString(akkaConf).withFallback(ConfigFactory.load())

  val clusterConsoleSystem = ActorSystem("ClusterConsoleSystem", config)

  val router: ActorRef = clusterConsoleSystem.actorOf(Props[RouterActor], "router")

  clusterConsoleSystem.actorOf(HttpServiceActor.props("0.0.0.0", 80, Timeout(30 seconds), router), "clusterconsolehttp")

}
