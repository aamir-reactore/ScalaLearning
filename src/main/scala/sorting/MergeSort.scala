package Sorting


object MergeSortAlgorithm extends App {

    val array = Array(6, 5, 4, 3, 1, 2)
    mergeSort(array, 0, 5)
    for (i <- array.indices) print(array(i) + " ")

  def mergeSort(array: Array[Int], low: Int, high: Int): Unit = {
    if (low < high) {
      val mid: Int = (low + high) / 2
      mergeSort(array, low, mid)
      mergeSort(array, mid + 1, high)
      merge(array, low, mid, high)
    }
  }

  def merge(array: Array[Int], low: Int, mid: Int, high: Int): Unit = {
    var left: Int = low
    var right: Int = mid + 1
    val temp: Array[Int] = Array.ofDim[Int](high - low + 1)
    var k: Int = 0
    while (left <= mid && right <= high) {
      if (array(left) < array(right)) {
        temp(k) = array(left)
        left = left + 1
      } else {
        temp(k) = array(right)
        right = right + 1
      }
      k = k + 1
    }
    if (left <= mid) {
      while (left <= mid) {
        temp(k) = array(left)
        k = k + 1
        left = left + 1
      }
    } else if (right <= high) {
      while (right <= high) {
        temp(k) = array(right)
        k = k + 1
        right = right + 1
      }
    }
    for (m <- temp.indices) {
      array(low + m) = temp(m)
    }
  }

}
