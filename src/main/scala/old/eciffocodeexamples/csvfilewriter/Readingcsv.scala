/*

import java.io.File


import scala.collection.mutable.ListBuffer
import scala.util.Try


trait FinishedProductLIMSCSVReader {

  def getLIMSCsvData(csvFile: File): List[FinishedProductLIMSCSVData] = {
    val rows = CSVReader.readCSVFile(csvFile).toList
    var rowsCount = 0

    val tryRes = rows map { record =>
      val rowWithParams = for (i <- 0 until record.size) yield record.get(i)
      rowsCount += 1

      val sample_ref_no = record.get("sample_ref_no").trim
      val mine_code = record.get("mine_code").trim
      val activity = record.get("activity").trim
      val creation_date = record.get("creation_date").trim
      val ins_lot = record.get("ins_lot").trim
      val ins_lotqty = record.get("ins_lotqty").trim
      val ins_date = record.get("ins_date").trim
      val ins_time = record.get("ins_time").trim
      val ins_material = record.get("ins_material").trim
      val grade_val = record.get("grade_val").trim

      val rowWithOutParams = List(sample_ref_no, mine_code, activity, creation_date, ins_lot, ins_lotqty, ins_date,
        ins_time, ins_material, grade_val)

      val paramRecords = rowWithParams diff rowWithOutParams
      val tupleList = (0 until paramRecords.size - 1 by 2) map { x =>
        paramRecords(x) -> paramRecords(x + 1)
      }
      val params = Map(tupleList: _*).filter { case (k, v) => k.nonEmpty && v.nonEmpty }

      val errorList = verifyRows(sample_ref_no, mine_code, activity, creation_date, ins_lot,
        ins_lotqty, ins_date, ins_time, ins_material, grade_val, rowsCount)

      Try {
        if (errorList.isEmpty) {
          FinishedProductLIMSCSVData(sample_ref_no, mine_code, activity,
            creation_date, ins_lot, ins_lotqty, ins_date, ins_time, ins_material, grade_val, params)
        } else {
          val errList = errorList.mkString("\n")
          throw ValidationException(message = errList, exception = new Exception(errList))
        }
      }
    }
    if (tryRes.forall(_.isSuccess)) {
      tryRes.map(_.get)
    } else {
      val failList = tryRes.filter(_.isFailure).map(_.failed.get)
      val temp = failList.map(_.getMessage)
      val finalErrorList = ConnectorUtilities.connectorErrorString("LIMS", temp)
      throw ValidationException(message = finalErrorList, exception = new Exception(finalErrorList))
    }
  }

  def verifyRows(sample_ref_no: String, mine_code: String, activity: String, creation_date: String, ins_lot: String,
                 ins_lotqty: String, ins_date: String, ins_time: String, ins_material: String, grade_val: String, rowsCount: Int): List[String] = {
    val errorList = ListBuffer.empty[String]

    if (sample_ref_no.isEmpty) {
      errorList.append(s"sample_ref_no is empty in row $rowsCount")
    }
    if (mine_code.isEmpty) {
      errorList.append(s"mine_code is empty in row $rowsCount")
    }
    if (activity.isEmpty) {
      errorList.append(s"activity is empty in row $rowsCount")
    }
    if (creation_date.isEmpty) {
      errorList.append(s"creation_date is empty in row $rowsCount")
    }
    if (ins_lot.isEmpty) {
      errorList.append(s"ins_lot is empty in row $rowsCount")
    }
    if (ins_lotqty.isEmpty) {
      errorList.append(s"ins_lotqty is empty in row $rowsCount")
    }
    if (ins_date.isEmpty) {
      errorList.append(s"ins_date is empty in row $rowsCount")
    }
    if (ins_time.isEmpty) {
      errorList.append(s"ins_time is empty in row $rowsCount")
    }
    if (ins_material.isEmpty) {
      errorList.append(s"ins_material is empty in row $rowsCount")
    }
    if (grade_val.isEmpty) {
      errorList.append(s"grade_val is empty in row $rowsCount")
    }
    errorList.toList

  }
}

object ImplFinishedProductLIMSCSVReader extends FinishedProductLIMSCSVReader

object CSVReader {

  def readCSVFile(file:File): Seq[CSVRecord] = {
    val reader = new FileReader(file)
    CSVFormat.RFC4180.withFirstRecordAsHeader().withIgnoreSurroundingSpaces().parse(reader).getRecords.asScala.toList
  }
}


*/
