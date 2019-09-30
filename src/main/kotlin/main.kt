fun main(){
    exampleBlocking()
}

fun exampleBlocking() {
    println("One")
    printlnDelayed("Two")
    println("Three")
}

fun printlnDelayed(message:String){
    Thread.sleep(1000)
    println(message)
}