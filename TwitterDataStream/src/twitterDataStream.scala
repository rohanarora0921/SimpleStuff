import org.apache.spark.streaming.twitter._
import org.apache.spark.SparkConf
import org.apache.spark.Logging
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.SparkContext._


object twitterDataStream {
  
  def main(args : Array[String])
  {
    if(args.length<=3)
    {
      System.err.println("Required AT LEAST four parameters for connecting to twitter");
      System.exit(1);
    }
    
    val log4jInit = Logger.getRootLogger.getAllAppenders.hasMoreElements
    if(!log4jInit)
    {
      logInfo("Setting Warning for log level")
      Logger.getRootLogger.setLevel(Level.WARN)
    }
    
    val Array(consumerKey, consumerSecret, accesToken, accessTokenSecret) = args.take(4)
    val filters = args.takeRight(args.length-4);
    
    
    System.setProperty("twitter4j.oauth.consumerKey", consumerKey)
    System.setProperty("twitter4j.oauth.consumerSecret", consumerSecret)
    System.setProperty("twitter4j.oauth.accessToken", accesToken)
    System.setProperty("twitter4j.oauth.accessTokenSecret", accessTokenSecret)
    
    val sparkConf = new SparkConf().setAppName("TwitterDataStream").setMaster("local[2]")
    val ssc = new StreamingConext(sparkConf, Seconds(3))
    val stream = TwitterUtils.createStream(ssc, None, filters)
    val hashTags =   stream.flatMap(status=>status.getText.split(" ").filter( . startsWith("#")))
    val topCounts120 = hashTags.map(( , 1)).reduceByKeyAndWindow(_ + _, seconds(120)).map{case (topic, count)=>(count, topic)}.transform( _.sortByKey(false))
   
    
    topCounts120.foreachRDD(rdd=> {
      val List = rdd.take(10)
      println("(%s)Popular Tags in last 120 seconds are: ".format(rdd.count()))
      List.foreach{ case (count, tag) => println("%s (%s total)".format(tag, count))}
    })
    
   
    ssc.start()
    ssc.awaitTermination()
    
  }    
  
  
}