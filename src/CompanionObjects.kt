/**
 * Created by dqromney on 6/26/17.
 */
package KotlinSamples.CompanionObjects

interface IFactory<T> {
    fun create() : T
}

class Point(var x: Double, var y: Double) {

//    companion object Factory {
//        fun createCarterian(x: Double, y: Double): Point = Point(x, y)
//        fun createPolar(x: Double, y: Double): Point = Point(x, y)
//    }

    companion object /*Factory*/ : IFactory<Point> {
        override fun create(): Point {
            return Point(0.0, 0.0)
        }
        fun createCarterian(x: Double, y: Double): Point = Point(x, y)
        fun createPolar(x: Double, y: Double): Point = Point(x, y)
    }


}

fun main(args: Array<String>) {
    var simplePoint = Point(1.0, 2.0)

    // var factoryPoint = Point.Factory.createPolar(3.0, Math.PI / 2)

    // Acts like a Static - when no static's exist in Kotlin.
    var factoryPoint2 = Point.createPolar(3.0, Math.PI / 2)

    var defaultPoint = Point.Companion.create()
}
