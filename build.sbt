
organization := "io.newsbridge.sample"
name := "sample-akka-http-docker"
version := "1.0.0"

scalaVersion := "2.12.3"

val akkaVersion     = "2.5.4"
val akkaHttpVersion = "10.0.9"


libraryDependencies += "com.typesafe.akka"             %% "akka-http"             % akkaHttpVersion
libraryDependencies += "com.typesafe.akka"             %% "akka-parsing"          % akkaHttpVersion
libraryDependencies += "com.typesafe.akka"             %% "akka-http-spray-json"  % akkaHttpVersion

libraryDependencies += "com.typesafe.akka"             %% "akka-actor"            % akkaVersion
libraryDependencies += "com.typesafe.akka"             %% "akka-stream"           % akkaVersion
libraryDependencies += "com.typesafe.akka"             %% "akka-slf4j"            % akkaVersion

// ----------------
dependencyOverrides += "com.typesafe.akka"             %% "akka-stream"           % akkaVersion
dependencyOverrides += "com.typesafe.akka"             %% "akka-actor"            % akkaVersion


// ----------------
// Docker packaging
enablePlugins(DockerPlugin, JavaAppPackaging)

packageName in Docker := "newsbridge/"+name.value
version     in Docker := version.value
maintainer in Docker := "contrib@newsbridge.io"
dockerBaseImage := "openjdk:latest"
dockerExposedPorts := Seq(8080)
dockerUpdateLatest := true
