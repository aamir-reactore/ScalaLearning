val x = 0x00FF
val b:Byte = 127
val d = 10.9030e2
println("## unicode character representation##")
val c = '\u0077'
val bs = '\\'
val str = "\\\"\'"

val s =
  """helloo . how ,
    | are yuou ]]\\""".stripMargin.replace("\n","")

println("## symbol literal ##")
val sym1 = Symbol.apply("Abc")
val sym2 = Symbol("Abc")
sym1.name
def symTest(s:Symbol) = s.name
symTest('nameupdate)

println("## string interpolation ##")
val interop1 = s" \\ PIE is ${math.Pi}"
val interop2 = f"PIE is ${math.Pi}%.2f"
val interop3 = raw"No \\\\ escapes"
println("## operators are methods ##")
val prefix1 = -2
val prefix2 = 2.unary_-
val prefix3 = 2.unary_+
println("## string index ##")
"abcdefac".indexOf('a',2) // skip first two and then find, -1 if not found

println("## arithmetic operators ##")
'b' - 'a'
11/4
11.0 / 4.0
11 % 4
11.0 % 4.0

println("## IEE reminder = rounding not truncated division##")
math.IEEEremainder(11.0,4.0)
println("##Bit wise operator##")
val bitAnd = 1 & 2
val bitOR = 1 | 2
val bitXOR = 1 ^ 3 // if bits same 0 else 1
val bitComplement = ~1 //unary

val leftShift = 1 << 2 // fills bits with 0 while shifting
val rightShift = -1 >> 31 // fills higehr bits with higer bit while shift
val unSignedRightShift = -1 >>> 31 // fills wit 0 while shifting

println("##Equality operators#")
1 == 1.0
null == List(1,2)

println("###operator precedence and associativity###")
var pr1 = 2
val pr2 = 4
pr1 *= pr2 + 1 // *= has precedence equal to assignment = , same for +=,/=
println(pr1)
var pr11 = 2
val pr22 = 4
pr11 = pr11 * pr22 + 1
println(pr11)

println("### Rich operations ###")
0 max 5
0 min 5
math.abs(2.7)

-2.7.abs //Implicitly converted to RichDouble of class package scala.runtime.RichDouble
-2.7.round

1.5.isInfinity
(1.0 / 0).isInfinity
4 to 6
4 to 6 by 2
4 until 6
"aamir".capitalize
"robert" drop 2














