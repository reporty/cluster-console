package clusterconsole.http

import com.typesafe.config.ConfigFactory

import scalatags.Text.all.{ html => htmlFrag, _ }
import scalatags.Text.tags2

object Page {

  private[Page] def headTag(title: String) =
    head(
      tags2.title(id := "title")(title),
      meta(name := "viewport", content := "width=device-width, initial-scale=1.0"),
      link(href := "stylesheets/main.min.css", rel := "stylesheet", tpe := "text/css")
    )

  def main(title: String, content: Frag*) =
    htmlFrag(
      headTag(title), {
        val prod = ConfigFactory.load().getBoolean("clusterConsole.productionMode")
        if (prod) {
          body(onload := "ClusterConsoleApp().main()")(
            script(src := "/js/cluster-console-jsdeps.js"),
            script(src := "/js/cluster-console-opt.js")
          )
        } else {
          body(onload := "ClusterConsoleApp().main()")(
            script(src := "/js/cluster-console-jsdeps.js"),
            script(src := "/js/cluster-console-fastopt.js")
          )
        }

      }
    )
}

