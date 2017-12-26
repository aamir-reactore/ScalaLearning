package DateTimeExamples

object ttt extends App {

  case class PDD(id:Long,name:String)
  val pdd1 = PDD(1,"abc")
  val pdd2 = PDD(2,"def")
  val pdd3 = PDD(3,"ghi")
  val pdd4 = PDD(4,"jkl")

  case class PDDM(pDD:PDD,hours:Int)

  val pDDM1 = PDDM(pdd1,2)
  val pDDM2 = PDDM(pdd2,5)
  val pDDM3 = PDDM(pdd3,5)
  val pDDM4 = PDDM(pdd4,1)

  val pddMList:List[PDDM] = List(pDDM1,pDDM2,pDDM3,pDDM4)

  val res = pddMList.maxBy(_.hours)
  println(res)
}