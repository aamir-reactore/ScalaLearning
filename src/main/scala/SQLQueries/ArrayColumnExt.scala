import scala.concurrent.Future

/**
  * To flatten ArrayColumn and get distinct out of it
  */
/*
case object UniqueItems
def getUniqueItens: Future[UniqueTagsContainer] = {
  val monitoringModules = findAllQuery().map(_.tags.unnest).distinct
  for {
    tagList <- db.run(monitoringModules.result)
  } yield UniqueItems(tagList.toList)
}*/
//List IN
//kendoFilterQuery(filterProps)._1.filter(x => x.tags.asColumnOf[List[String]].@&(query.tags))

