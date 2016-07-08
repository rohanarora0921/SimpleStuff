import scala.math.pow 

object KMeans {
  
def distanceSquared(p1: (Double,Double), p2: (Double,Double)) = 
{ pow(p1._1 - p2._1,2) + pow(p1._2 - p2._2,2 ) }  

def addPoints(p1: (Double,Double), p2: (Double,Double)) = 
{ (p1._1 + p2._1, p1._2 + p2._2) }  

def closestPoint(p: (Double,Double), points: Array[(Double,Double)]): Int = 
{ var index = 0;
 var bestIndex = 0;
 var closest = Double.PositiveInfinity  
    for (i ← 0 until points.length)
    { val dist = distanceSquared(p,points(i)) 
      if (dist < closest)
              { closest = dist 
               bestIndex = i
              } 
    } 
 bestIndex 
 }  

val filename = "file:/home/training/training_materials/sparkdev/data/devicestatus.txt"  
val K = 5  
val convergeDist = .1  
val devicedata=  sc.textFile(filename);  
val ldata = devicedata.map(line ⇒ (line.split(|)(12).toDouble,line.split(|)(13).toDouble))  
val fdata = ldata.filter{case (lat,lon) ⇒ (if(lat != 0 && lon != 0)true;else false)}  
fdata.cache();  
var kPoints = fdata.takeSample(false, K, 42)  
var tempDist = Double.PositiveInfinity  
while (tempDist > convergeDist) {  
// for each point, find the index of the closest kpoint.  map to (index, (point,1))  
val alloc = fdata.map(point => (closestPoint(point,kPoints),(point,1)))  
// For each key (k-point index), reduce by adding the coordinates and number of points  
var allindex = alloc.reduceByKey((v1,v2) => (addPoints(v1._1,v2._1), v1._2 + v2._2))  
// For each key (k-point index), find a new point by calculating the average of each closest point  
var avgindex = allindex.map{case(index, ponum) => (index,(ponum._1._1/ponum._2,ponum._1._2/ponum._2))}  
var centrePoints = avgindex.map{case(k,v) => v}  
var newKPoints = centrePoints.collect()  
// calculate the total of the distance between the current points and new points  
tempDist = 0  
for(i <- 0 to K-1){tempDist += distanceSquared(kPoints(i),newKPoints(i))}  
    // Copy the new points to the kPoints array for the next iteration  
    kPoints = newKPoints  
}  
kPoints.foreach(println) 
}
