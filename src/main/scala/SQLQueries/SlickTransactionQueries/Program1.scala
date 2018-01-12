import slick.dbio.{DBIOAction, NoStream}
import slick.dbio.Effect.Write

import scala.concurrent.Future

//Example1
/*

override def deleteById(id: Long): Future[StatusContainer] = {
  for {
    dbQuery <- buildTransactionQuery(id, prs)
    result  <- run[Unit](dbQuery.transactionally, Nil)(prs).transform(s => {
      StatusContainer(true)
    }, f => f)
  } yield result
}

def buildTransactionQuery(rosterId: Long): Future[DBIOAction[Unit, NoStream, Effect.Write]] = {
  val deleteRosterQuery = deleteByIdQuery(rosterId)
  for {
    rosterEmployeesDeleteQueries = DBIO.sequence(rosterEmployees.map(x => rosterEmployeeRepo.deleteByIdQuery(x.id, prs)))
    query                        = DBIO.seq(deleteRosterQuery, rosterEmployeesDeleteQueries)
  } yield query
}

 def run[R](action: DBIOAction[_, NoStream, _], keys: List[Class[_]])(prs: SysParams) = {
    val res = db(prs.mineId).run(action).map(_.asInstanceOf[R]).transform(s => s, f => RelationalRepositoryUtility.handleBatchOperationErrors(f))
    //clear
    keys.foreach { x =>
      removeAll(getCacheKey(prs, x.getName.replaceDollarSymbol))
      ImplCacheVersionRepository.updateCacheVersion(x.getSimpleName, List(), "CLEARALL", prs.mineId)
    }
    res
  }

def someQuey = {

  val dbWork:Seq[DBIOAction[Unit,Stream,NoEffect.Write]] = buildPrePostQuestionOptionsTransactionQueries(id,options,prs)
      val finalQuery:DBIOAction[Seq[Unit],Stream,NoEffect.Write]] = DBIO.sequence(dbWork)
       status <- run[Seq[Unit]](finalQuery, Nil)(prs).transform(_ => {
        StatusContainer(true)
      }, f => {
        RelationalRepositoryUtility.handleBatchOperationErrors(f)
        f
      })
      }

*/

//Example2
/*
def buildUpdatePrePostDBQuery(cont: UpdatePrePostQuestionAnswer, prs: SysParams): Future[DBIOAction[Unit, NoStream, Write]] = {
  val t1 = (FixedSqlAction[Int,CustomPostgresDriver.api.NoStream,Effect.Write],PrePostQuestion)
  val zz:FixedSqlAction[Int,CustomPostgresDriver.api.NoStream,Effect.Write] = prePostQuesQuery._1
  val t2 = List[Future[(FixedSqlAction[Int,CustomPostgresDriver.api.NoStream,Effect.Write],PrePostOptions)]]
  val t3 = List[(FixedSqlAction[Int,CustomPostgresDriver.api.NoStream,Effect.Write],PrePostOptions)]
  val t4 = DBIOAction[List[Int]],NoStream,Effect.Write]
  val t5 = DBIOAction[Unit,NoStream,Effect.Write]

  val dbWork = for {
    prePostQuesQuery:t1<- updateByIdQuery(cont.prePostQuestion.id, cont.prePostQuestion, prs)
    recordsToInsert = cont.prePostAnswer.filter(_.id == 0)
    prePostAnsQuery:t2 = cont.prePostAnswer.filterNot(_.id == 0).map { prePostAnswer =>
      prePostOptionsRepository.updateByIdQuery(prePostAnswer.id, prePostAnswer, prs)
    }
    res1:t3 <- Future.sequence(prePostAnsQuery)
    res2:t4 = DBIOAction.sequence(res1.map(_._1))
    res3 = prePostOptionsRepository.insertBatchQuery(recordsToInsert, prs)

    transaction:t2 = DBIOAction.seq(zz, res2, res3)
  } yield transaction

   status <- run[Unit](dbWork.transactionally, Nil)(prs).transform(s => {
        StatusContainer(true)
      }, f => {
        RelationalRepositoryUtility.handleBatchOperationErrors(f)
        f
      })
}
*/