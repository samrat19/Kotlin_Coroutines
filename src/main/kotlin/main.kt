fun main(){
    println("One")
    println("Two")
    println("Three")
    // this is called synchronous code because it runs one after another
}

fun delayedFunction(message:String){
    Thread.sleep(1000)
    println(message)
}