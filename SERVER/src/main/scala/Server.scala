import java.net.{ServerSocket, Socket}
import java.io.{DataInputStream, DataOutputStream}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import util.Database
import model.ShopItem

object Server extends App {
    val ssocket = new ServerSocket(5000)
    val clientSocket : Future[Socket] = Future { ssocket.accept() } 

    Database.setupDB()

    def processSocket(x : Socket) : Unit = {
        val clientSocket2 : Future[Socket] = Future { ssocket.accept() }
        clientSocket2.foreach(x => {
            processSocket(x)
        })

        val dis = new DataInputStream( x.getInputStream() )
        val dout = new DataOutputStream( x.getOutputStream() )

        val incomingData = dis.readLine()
        println("\n")
        println("Client " + x.getRemoteSocketAddress() + " is requesting for Item with ID: " + incomingData)
        println("\n")

        val msg = parseIData(incomingData)
        dout.writeBytes(msg)

        x.close()
    }

    def parseIData(dt : String) : String = {

        val ldt = dt.toInt

        ldt match {
            case 0 => return "terminate"
            case a => retrieveItemDetails(a)
        }
    }

    def retrieveItemDetails(itemId : Int) : String = {

        val shopItem = (ShopItem.getItem(itemId - 1))
        var msg = ""

        if(shopItem == None){
            msg = "No Item Found"
        }else{
            val itemName = shopItem.get.itemName
            val itemDesc = shopItem.get.itemDesc
            val itemPrice = shopItem.get.itemPrice

            msg = "Item Name: " + itemName + " | Price: " + itemPrice + " Coins" + " | Description: " + itemDesc 
        }
        
        return msg
    }

    clientSocket.foreach(x => {
        processSocket(x)
    })

    scala.io.StdIn.readLine("Press Any Key To Shutdown Server\n")
    ssocket.close()
}