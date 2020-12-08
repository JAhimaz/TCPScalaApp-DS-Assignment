package model

import util.Database

import scalikejdbc._

import scala.util.Try

class ShopItem(itemIdS : Int = 0,
              itemNameS : String,
              itemDescS : String,
              itemPriceS : Double,
    )extends Database {

    var itemId = itemIdS
    var itemName = itemNameS
    var itemDesc = itemDescS
    var itemPrice = itemPriceS

}


object ShopItem extends Database {

    def getItem(sId : Int) : Option[ShopItem] = {
        DB readOnly { implicit session =>
            sql"""SELECT * FROM items WHERE
                id = ${sId}""".map(rs => ShopItem(
                    rs.int("id"),
                    rs.string("itemName"),
                    rs.string("itemDesc"),
                    rs.double("itemPrice"))).single.apply()
        }
    }

    def apply(itemId : Int = 0,
                itemName : String,
                itemDesc : String,
                itemPrice : Double
            ): ShopItem = {
        new ShopItem(itemId, itemName, itemDesc, itemPrice)
    }

    def initializeTable() = {
        DB autoCommit { implicit session =>
        sql"""
                create table items(
                id int not null GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
                itemName varchar(64) NOT NULL,
                itemDesc varchar(255),
                itemPrice double
                )
            """.execute.apply()
        }
    }

    def initialSetup() = {
        var preItemNames = Array("Guardian Scaled Armour", 
                            "Adamantite Chainmail", 
                            "Chainmail Gaunlets", 
                            "Scaled Mithril Handguards", 
                            "Guardian's Waistguard", 
                            "Iron Chainbelt", 
                            "Twilight Mail Treads", 
                            "Iron Handguards", 
                            "Oathkeeper's Aegis", 
                            "Steel Vambraces")
        var preItemDesc = Array("Scaled Armour blessed by the Guardians of Lightingale",
                            "Forged from the Admantite of Dwavern ruins",
                            "Heat forged Chainmail Gaunlets",
                            "Forged Handguards from the finest Mithril in Erandel",
                            "Waistguard blessed by the Guardians of Lightingale",
                            "A plain Iron Chainbelt",
                            "Mail Treads from the Enchanted Twilight Forest",
                            "A plain pair of Iron Handguards",
                            "Aegis from the Oathkeepers",
                            "Heavy Steel Forged Vambraces")
        var preItemPrice = Array(529.99,
                            139.75,
                            75.56,
                            98.33,
                            319.99,
                            65.51,
                            133.13,
                            95.52,
                            759.68,
                            40.90)
        for(i <- 0 to 9){
            (DB autoCommit {
                implicit session =>
                sql"""
                    insert into items(itemName, itemDesc, itemPrice) values
                    (${preItemNames(i)}, ${preItemDesc(i)}, ${preItemPrice(i)})
                    """.updateAndReturnGeneratedKey().apply()
            })
        }
  }
}

