println("**************repeated parameters******************")
//args is Array of specified type
def echo1(args: String*) = for (arg <- args) println(arg)
echo1()
echo1("abc")
echo1("def","ghi")

val arr = Array("What's", "up", "doc?")
//echo1(arr) CTE
echo1(arr: _*)

def echo2(l:Int,args: String*) = for (arg <- args) println(arg)
echo2(1)
echo2(1,"abc")
echo2(2,"def","ghi")


println("**************Named parameters******************")
