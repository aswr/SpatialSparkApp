package queries

/**
 * Created by samy on 1/26/15.
 */

/*
*  Assumptions
*  Stream Schema: {ID, X, Y, Time, Text}
*
*  Query Format: {
*  ID,
*  [KNN Predicate: (K,ID or (X,Y))],
*  [Spatial Range Predicate :MBR],
*  [Time Range Predicate: WindowInSec],
*  [Text Predicate: text]
*  }
*
*
* */
object SpatialApp extends Serializable{


  // Point
  case class point(x:Double,y:Double) extends Serializable {
    override def toString() = {

      "X-Cor:" + x.toString()+" Y-Coordniate: "+y.toString()
    }
  }

  // Query Regions
  case class queryRegion(min:point,max:point) extends Serializable {
    override def toString() = {
      min+" "+max
    }

  }



  def main(args: Array[String]): Unit = {

    if (args.length < 4) {
      System.err.println("Usage: Spatial App <StreamServerIP> <Query_port> <Data_port>")
      System.exit(2)
    }


    println("Hello, This is Ahmed")


  }

}
