package queries

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}

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
* */
object SpatialApp extends Serializable{

  case class point(x:Double,y:Double) extends Serializable {
    override def toString() = {
      "X-Cor:" + x.toString()+" Y-Cor: "+y.toString()
    }
  }

  case class MBR(min:point,max:point) extends Serializable {
    override def toString() = {
      min+" "+max
    }
  }

  case class DataRecords(DataID:Double, location:point, tText:Array[String],tweetL:Int, tTextL:Int, tTime:String) extends Serializable{
    override def equals(that:Any):Boolean = {
      true
    }
  }

  case class QueryRecords(QueryID:Double, location:point, tText:Array[String],tweetL:Int, tTextL:Int, tTime:String) extends Serializable{
    override def equals(that:Any):Boolean = {
      true
    }
  }

  case class KNNPredicate(PredicateID: Double, k: Int, location:point){
    override def toString() = {
      "PredicateID:" + PredicateID.toString()+"K: "+k.toString()
    }
  }

  case class RangePredicate(PredicateID: Double, Region:MBR){
    override def toString() = {
      "PredicateID:" + PredicateID.toString()+"MBR: "+Region.toString()
    }
  }

  case class TimePredicate(PredicateID: Double, Window:Int){
    override def toString() = {
      "PredicateID:" + PredicateID.toString()+"MBR: "+Window.toString()
    }
  }


  def main(args: Array[String]): Unit = {

    if (args.length < 3) {
      System.err.println("Usage: SpatialApp <StreamServerIP> <Query_port> <Data_port>")
      System.exit(2)
    }


    val sparkConf = new SparkConf().setAppName("SpatialApp")

    // Define a streaming Context with 1 second batch
    val ssc = new StreamingContext(sparkConf, Seconds(1))

    // Listen to Data stream source
    val DataStream = ssc.socketTextStream(args(0), 2015, StorageLevel.MEMORY_ONLY)
    val QueryStream = ssc.socketTextStream(args(0), 2015, StorageLevel.MEMORY_ONLY)

    // Define a hardcoded query record

    val sampleQuery = (1, true, point(100,100),3 )

    // Parsing Data Stream






  }

}
