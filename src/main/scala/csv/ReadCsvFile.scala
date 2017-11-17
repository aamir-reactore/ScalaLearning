/*
package csv

import java.io.{File, FileReader}

import org.apache.commons.csv.{CSVFormat, CSVRecord}

import scala.collection.JavaConverters._

/**
  * include apache common library
  *
  */

object ReadCsvFile extends App {


  def readCSVFile(file: File): List[CSVRecord] = {
    val reader = new FileReader(file)
    CSVFormat.RFC4180.withFirstRecordAsHeader().withIgnoreSurroundingSpaces().parse(reader).getRecords.asScala.toList
  }

  val rows = readCSVFile(new File("/home/administrator/JSTL-VJW-T1-T1DP01-5-11-2017-000004.csv"))
  rows foreach { record =>

    val rowWithParams = for (i <- 0 until record.size) yield record.get(i)
    val sample_ref_no = record.get("sample_ref_no").trim
    val mine_code = record.get("mine_code").trim
    val activity = record.get("activity").trim
    val lotno = record.get("lotno").trim
    val creation_date = record.get("creation_date").trim
    val bulk_permit = record.get("bulk_permit").trim
    val sample_loc = record.get("sample_loc").trim
    val material_code = record.get("material_code").trim
    val ack_by = record.get("ack_by").trim
    val ack_date = record.get("ack_date").trim
    val grade_value = record.get("grade_value").trim
    val samp_entry_date = record.get("samp_entry_date").trim
    val rowWithOutParams = List(sample_ref_no, mine_code, activity, lotno, creation_date, bulk_permit, sample_loc, material_code,
      ack_by, ack_date, grade_value, samp_entry_date)
    val paramRecords = (rowWithParams diff rowWithOutParams).filter(_.nonEmpty)
    val res =
    println(paramRecords)
  }

  def abc(file: File) = {
    val reader = new FileReader(file)


  }

}
/**

val l = List("Moisture", "1", "Fe%", "5",
  "SiO2%", "6", "Al2O3%", "7", "Cao%", "8", "MgO%", "9",
  "MnO%", "10")

val tupleList = (0 until l.size - 1 by 2) map {x =>
  l(x) -> l(x+1)
}
val map1 = Map(tupleList:_*)
  **/*/
