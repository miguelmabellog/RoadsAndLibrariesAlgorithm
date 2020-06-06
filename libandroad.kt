import java.io.*
import java.math.*
import java.security.*
import java.text.*
import java.util.*
import java.util.concurrent.*
import java.util.function.*
import java.util.regex.*
import java.util.stream.*
import kotlin.collections.*
import kotlin.comparisons.*
import kotlin.io.*
import kotlin.jvm.*
import kotlin.jvm.functions.*
import kotlin.jvm.internal.*
import kotlin.ranges.*
import kotlin.sequences.*
import kotlin.text.*



// Complete the roadsAndLibraries function below.
fun roadsAndLibraries(n: Int, c_lib: Int, c_road: Int, cities: Array<Array<Int>>):Long{

    var id=mutableListOf<Int>()
    var sz=mutableListOf<Int>()
    var ncity=mutableListOf<Int>()
    for(i in 0..n+1){
        id.add(i)
        sz.add(1)
        ncity.add(0)
    }
    //var id= Array(n+1, {i -> i})
    //var sz= Array(n+1, {1})
    //var ncity= Array(n+1, {0})



    var k=cities.size
    var m=0L
    var nn=n.toLong()
    var ncon=0L
    var r=0L

    if(c_lib<c_road){
        r=n*c_lib.toLong()
        return (r)
    }

    fun root(i:Int ):Int{
        var ii=i
        while (ii != id[ii]){
            id[ii] = id[id[ii]];
            ii=id[ii]
        }
        return ii
    }


    fun union(p:Int, q:Int){
        var i = root(p)
        var j = root(q)
        if (!(i==j)){
            m--
            if (sz[i] < sz[j]) {
                id[i] = j
                sz[j] += sz[i]
            }
            else {
                id[j] = i
                sz[i] += sz[j]
            }
        }
    }

    if(k!=0){
        m=nn
        for(i in cities.indices){

            if(ncity[cities[i][0]]==0){
                ncity[cities[i][0]]=1
                ncon++
            }
            if(ncity[cities[i][1]]==0){
                ncity[cities[i][1]]=1
                ncon++
            }
            union(cities[i][0],cities[i][1])
        }

    }else{
        m=nn
    }

    if(c_lib>=c_road){
        r=((m)*c_lib)+((ncon-(m-(n-ncon)))*c_road)
    }

    return (r)

}


fun main(args: Array<String>) {

    val scan = Scanner(System.`in`)

    val q = scan.nextLine().trim().toInt()

    for (qItr in 1..q) {
        //val nmC_libC_road = scan.nextLine().split(" ")

        val n = scan.nextInt()

        var m = scan.nextInt()

        val c_lib = scan.nextInt()

        val c_road = scan.nextInt()

        val cities = Array<Array<Int>>(m, { Array<Int>(2, { 0 }) })

        for (i in 0 until m) {
            cities[i][0] = scan.nextInt()
            cities[i][1] = scan.nextInt()
        }

        val result = roadsAndLibraries(n, c_lib, c_road, cities)
        println(result)


    }
}
