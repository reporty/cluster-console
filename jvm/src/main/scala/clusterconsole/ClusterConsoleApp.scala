package clusterconsole

import akka.actor.{ ActorRef, ActorSystem, Props }
import akka.util.Timeout
import clusterconsole.core.LogF
import clusterconsole.http._
import com.typesafe.config.ConfigFactory

import scala.concurrent.duration._

object ClusterConsoleApp extends App with LogF {

  args.logDebug("ClusterConsoleApp starting with args:" + _.toList.toString)

  val prod = ConfigFactory.load().getBoolean("clusterConsole.productionMode")
  val hostname = prod match { case true => "0.0.0.0" case false => "127.0.0.1" }
  val port = prod match { case true => 80 case false => 9000 }

  // todo - abstract clusterconsole role into a variable
  val akkaConf =
    """akka.remote.netty.tcp.hostname="0.0.0.0"
      |akka.remote.netty.tcp.port=3001
      |akka.cluster.roles = [clusterconsole]
      |""".stripMargin

  val config = ConfigFactory.parseString(akkaConf).withFallback(ConfigFactory.load())

  val clusterConsoleSystem = ActorSystem("ClusterConsoleSystem", config)

  val router: ActorRef = clusterConsoleSystem.actorOf(Props[RouterActor], "router")

  clusterConsoleSystem.actorOf(HttpServiceActor.props(hostname, port, Timeout(30 seconds), router), "clusterconsolehttp")

}
