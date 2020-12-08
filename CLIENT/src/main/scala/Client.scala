import java.net.Socket
import scala.io.StdIn
import java.io.{DataInputStream, DataOutputStream}
import java.util.ArrayList

object Client extends App {

    var connected = true

    while(connected){
        val socket = new Socket("localhost", 5000)
        val dis = new DataInputStream(socket.getInputStream())
        val dout = new DataOutputStream(socket.getOutputStream())
    
    println ("+----------------------------------------------------------------------------+")
    println ("|                          Morg's Armoursmith Shop                           |")
    println ("|                    (Type an Item Number to View Details)                   |")
    println ("|                                                                            |")
    println ("| [1] Guardian Scaled Armour                                                 |")
    println ("| [2] Adamantite Chainmail                                                   |")
    println ("| [3] Chainmail Gauntlets                                                    |")
    println ("| [4] Scaled Mithril Handguards                                              |")
    println ("| [5] Guardian's Waistguard                                                  |")
    println ("| [6] Iron Chainbelt                                                         |")
    println ("| [7] Twilight Mail Treads                                                   |")
    println ("| [8] Iron Handguards                                                        |")
    println ("| [9] Oathkeeper's Aegis                                                     |")
    println ("| [10] Steel Vambraces                                                       |")
    println ("|                                                                            |")
    println ("| [0] Exit                                                                   |")
    println ("|                                                                            |")
    println ("|             ~~~ Please ENTER one of the following choices ~~~              |")
    println ("+----------------------------------------------------------------------------+")

        dout.writeBytes(s"${StdIn.readLine()}\n")
        val data = dis.readLine()
        parseData(data)

        println("\n#----------------------------------------------------------------------------#\n")
    }

    def parseData(st : String) : Unit = {
        st match {
            case "terminate" => terminateClient()
            case _ => println("\n\n" + st + "\n\n")
        }
    }

    def terminateClient() : Unit = {
        println("\nYou Have Been Disconnected From The Server\n")
        connected = false;
    }
}