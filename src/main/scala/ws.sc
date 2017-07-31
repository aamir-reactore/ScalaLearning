def ll(n:Int):Int = {
  if(n < 2) 1 else ll(n-1) + ll(n-2)
}

ll(5)








