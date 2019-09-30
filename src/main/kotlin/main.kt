import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main(){
    exampleBlocking()
}

fun exampleBlocking() {
    println("One")
    runBlocking {
        printlnDelayed("Two")
    } //we can call suspending functions from runBlocking it is a Coroutine scope
    println("Three")
}

suspend fun printlnDelayed(message:String){
   // Thread.sleep(1000)
    delay(1000)
    println(message)
}