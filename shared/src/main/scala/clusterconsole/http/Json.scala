package clusterconsole.http

import upickle.Js

object Json {

  implicit val clusterProtocolWriter = upickle.Writer[ClusterProtocol] {

    case r: CurrentClusterStateInitial =>
      Js.Arr(
        Js.Num(1),
        Js.Str(upickle.write[CurrentClusterStateInitial](r))
      )


    case r: ClusterMemberUp =>
      Js.Arr(
        Js.Num(2),
        Js.Str(upickle.write[ClusterMemberUp](r))
      )

    case r: ClusterMemberUnreachable =>
      Js.Arr(
        Js.Num(3),
        Js.Str(upickle.write[ClusterMemberUnreachable](r))
      )

    case r: ClusterMemberRemoved =>
      Js.Arr(
        Js.Num(4),
        Js.Str(upickle.write[ClusterMemberRemoved](r))
      )

    case r: ClusterUnjoin =>
      Js.Arr(
        Js.Num(5),
        Js.Str(upickle.write[ClusterUnjoin](r))
      )

    case other =>  Js.Str("Json error " + other)
  }


  implicit  val clusterProtocolReader = upickle.Reader[ClusterProtocol] {


    case Js.Arr(Js.Num(1), Js.Str(v)) =>
      upickle.read[CurrentClusterStateInitial](v)


    case Js.Arr(Js.Num(2), Js.Str(v)) =>
      upickle.read[ClusterMemberUp](v)

    case Js.Arr(Js.Num(3), Js.Str(v)) =>
      upickle.read[ClusterMemberUnreachable](v)

    case Js.Arr(Js.Num(4), Js.Str(v)) =>
      upickle.read[ClusterMemberRemoved](v)

    case Js.Arr(Js.Num(5), Js.Str(v)) =>
      upickle.read[ClusterUnjoin](v)


  }


}
