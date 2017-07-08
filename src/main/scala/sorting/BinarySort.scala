package sorting

  object BinarySort extends App {
    def binarySearchRecursive(list: Array[Int], target: Int)
                             (start: Int = 0, end: Int = list.length - 1): Int = {
      if (start > end) return -1
      val mid = start + (end - start + 1) / 2
      if (list(mid) == target)
        return mid
      else if (list(mid) > target)
        return binarySearchRecursive(list, target)(start, mid - 1)
      else
        return binarySearchRecursive(list, target)(mid + 1, end)
    }

    def binarySearchFunctional(list: Array[Int], target: Int): Int = {
      def bsf(list: Array[Int], target: Int, start: Int, end: Int): Int = {
        if (start > end) return -1
        val mid = start + (end - start + 1) / 2
        list match {
          case (arr: Array[Int]) if (arr(mid) == target) => mid
          case (arr: Array[Int]) if (arr(mid) > target) => bsf(list, target, start, mid - 1)
          case (arr: Array[Int]) if (arr(mid) < target) => bsf(list, target, mid + 1, end)
        }
      }

      bsf(list, target, 0, list.length - 1)
    }
  }

