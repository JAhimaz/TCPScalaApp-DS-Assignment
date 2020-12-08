val AkkaVersion = "2.6.10"
val AkkaManagementVersion = "1.0.9"
 
resolvers += ("custome1" at "http://4thline.org/m2").withAllowInsecureProtocol(true)
 
addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)
 
libraryDependencies ++= Seq(
  "org.scalikejdbc" %% "scalikejdbc"       % "3.1.0",
  "com.h2database"  %  "h2"                % "1.4.196",
  "ch.qos.logback"  %  "logback-classic"   % "1.2.3",
  "org.apache.derby" % "derby" % "10.13.1.1"
)
 
fork := false

// sbt "-Djava.security.policy=no.policy"