/*

object ll extends App {

  val attributeListFromRules = List("coldd1", "coldd2", "col3")


  val bucketGroups = Map("Bucket1" -> List("col1", "col2", "col3"),
    "Bucket2" -> List("col11", "col111"),
    "Bucket3" -> List("col1", "col33"),
    "Bucket4" -> List("coldd", "col3")
  )

  val ruleAttributeBucketsIterable = bucketGroups map { x =>
    if (attributeListFromRules.exists(x._2.contains)) Some(BucketHelpers.similarityBucketMapAdjustment(x._1)) else None
  }

  val ruleAttributeBucketsList = ruleAttributeBucketsIterable.toList.filter(_.isDefined).map(_.get)

  println(ruleAttributeBucketsList) //ListBuffer(similarity_zs_bucket_1, similarity_zs_bucket_3)

}

object jj extends App {
  val r = List(1000, 20, 11, 4).exists(List(1, 2, 3, 4).contains)
  println(r)
}*/
