package ir.mtajik.android.kotlintest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import java.util.*
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {

    val LOG = Logger.getLogger(MainActivity::class.java.name)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        variablesExample()
        rangeExanples()
        conditionalExamples()
        loopingExample()
        functionsExample()
        collectionOperatorExample()
        exceptionHandlerExample()
        mapExample()
        classExample()
        nullcheckAndSmartCastExample()
    }

    private fun nullcheckAndSmartCastExample() {

        var nullVal : String? = null

        fun returnNull(): String? {
            return "Mahdi"
        }

        var nullVal2 = returnNull()

        if(nullVal2 != null){
            logg("*** Length :${nullVal2!!.length}")
        }

        val nullVal3 : String = returnNull() ?: "No name"
        logg("nullVal3 : $nullVal3")
    }

    private fun classExample() {

        class Dog(name : String , height: Double ,var owner : String) : Animal (name, height){

            override fun getInfo() :Unit {
                logg("$name is $height and  its owner is $owner")
            }
        }

        val bowser = Animal("Bowsere" , 13.5)
        bowser.getInfo()

        val spot = Dog("titan",2.5,"Shaby")
        spot.getInfo()

        val nichi = Bird("Nichi",true)
        nichi.fly(134.5)

    }

    interface Flyable{
        var flies : Boolean
        fun fly(distMile : Double) : Unit
    }

    class Bird constructor(val name : String , override var flies: Boolean = true): Flyable{
        override fun fly(distMile: Double) {
            if(flies){
                System.err.println("$name flies $distMile miles")
            }

        }
    }

    open class Animal(val name : String , val height: Double){

        init {
            val regex = Regex(".*\\d+.*")

            require(!name.matches(regex)){"Animal names can not contain numbers"}
            require(height > 0){"Height must grater than 0"}
        }
        open fun getInfo(): Unit {
            System.err.println("$name is $height")

        }
    }

    private fun mapExample() {

        val map = mutableMapOf<Int , Any?>()
        val map2 = mutableMapOf(1 to "Tajik", 2 to 25)
        map[1] = "Mahdi"
        map[2] = 35

        map.put(3, 195.5)

        map.remove(2)

        for((x,y) in map){
            logg("Key :$x Value :$y")
        }

    }

    private fun exceptionHandlerExample() {

        //mutable list - changeable
        var list1 : MutableList<Int> = mutableListOf(1,2,3,4,5,6)

        //emutable list - unchangeable
        val list2 :  List<Int> = listOf(1,2,3,4,5,6)

    }

    private fun collectionOperatorExample() {

        val myList  = 1..20

        val listSum = myList.reduce{x, y -> x + y}
        logg("Reduce Sum : $listSum")

        //sum + given number
        val listSum2 = myList.fold(1){x, y -> x + y}
        logg("Fold Sum : $listSum2")

        //if condition
        logg("Evens : ${myList.any { it % 2 ==0 } }")
        logg("Evens : ${myList.all { it % 2 ==0 } }")

        val biggerThan3 = myList.filter { it > 3 }
        biggerThan3.forEach { n -> logg(" $n > 3") }

        //map

        val times7 = myList.map { it * 7 }
        times7.forEach { n -> logg("*7 : $n") }
    }

    private fun functionsExample() {


        fun add(num1 : Int , num2 : Int):Int = num1 + num2
        logg("5+4= ${add(5,4)}")


        fun sayHellow(name: String){
            logg("Hello $name")
        }
        //or -- use unit when there is no return type but want to do some thing
        fun sayHellow2(name :String) : Unit = logg("Hello $name")

        fun nextTwo(num : Int) :Pair<Int , Int>{
            return Pair(num+1, num+2)
        }

        val (two , three) = nextTwo(1)
        logg("1 $two $three")

        logg("Sum : ${getSum(1,2,4,5)}")
        logg("Sum : ${getSum(234,54,6,7,8,89,7,56,54)}")


        //fun with lambda
        val multiply = {num1 :Int, num2 : Int -> num1 * num2}
        logg("5 x 12 = ${multiply(5,13)}")

        //Factorial
        logg("$5! : ${fact(5)}")

        //func that creates another func
        val multi3 = makeMathFun(3)
        logg("5 * 3 = ${multi3(5)}")

        //func that uses func

        val multiply2 = { num1:  Int -> num1 * 2}
        val numList2 = arrayOf(1,2,3,4,5)

        mathonList(numList2 , multiply2)


    }

    fun mathonList(numList : Array<Int> , myFunc: (num: Int)-> Int){
        for(num in numList){
            logg("MathOnList ${myFunc(num)}")
        }
    }

    fun makeMathFun( num1: Int): (Int) -> Int = {num2 -> num1 *  num2}

    fun fact(x : Long) : Long {
         fun factTail(y : Long , z :Long):Long {
            return if (y == 0L)  z
            else  {
                return factTail(y-1 ,y*z)
            }
        }
        return factTail(x ,1)
    }


    fun getSum(vararg nums : Int) : Int{

        var sum = 0
        nums.forEach { n -> sum += n }
        return sum

    }

    private fun loopingExample() {
        //--1
        for (x in 1..10) {
            logg("loop : $x")
        }

        //--2
        val rand = Random()
        val randNumber = rand.nextInt(50) + 1 // 1..50

        var guess = 0

        while (randNumber != guess) {
            guess += 1
        }
        logg("Our random number was : $guess ")

        //--3
        for (x in 1..20) {
            if (x % 2 == 0) {
                continue
            }

            logg("Odd : $x")

            if (x == 15) break
        }

        //--4
        var arr3: Array<Int> = arrayOf(3,6,9)

        for(i in arr3.indices){
            logg("Multiple 3: ${arr3[i]}")
        }

        for((index , value) in arr3.withIndex()){
            logg("Index : $index value: $value" )
        }
    }

    private fun conditionalExamples() {
        val age = 8

        if (age < 5) {
            logg("goto kinder garden")
        } else if (age > 5) {
            val grade = age - 5
            logg("goto grade:$grade")
        }

        when (age) {
            0, 1, 2, 3, 4 -> logg("goto kinder garden")

            5 -> logg("nothing")

            in 6..17 -> {
                val grade = age - 5
                logg("got to grade $grade")
            }

            else -> logg("got collage")
        }
    }

    private fun variablesExample() {
        val arrayM = Array(5, { x -> x * x })
        var arrayP = arrayOf(1, 2.3, "salam")
        var arrayInt: Array<Int> = arrayOf(1, 2, 3)

        var longString = """ Hi this is
                test for kotlin"""

        print(arrayM[2])

        //        LOG.warning("Hello from MyClass"+arrayM)
        //        LOG.warning("Hello from MyClass "+arrayM[4])
        //        LOG.warning("Hello from MyClass "+arrayM[2])
        //        LOG.warning("Hello from MyClass"+arrayM[6])
    }

    private fun rangeExanples() {

        val oneToTen = 1..10
        val tenToOne = 10.downTo(1)
        val twoTo20 = 2.rangeTo(20)
        val alpha = "A".."Z"

        val range3 = oneToTen.step(3)

        LOG.warning("R in alpha : ${"R" in alpha} ") // return true or false


        for (x in range3) LOG.warning("range3 : $x")
        for (x in tenToOne.reversed()) LOG.warning("reversed : $x")

    }

    private fun logg(message: String) {
        LOG.warning(message)
    }
}
