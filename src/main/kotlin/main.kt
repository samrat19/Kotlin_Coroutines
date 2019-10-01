import kotlinx.coroutines.*

fun main(){
   // exampleBlocking()
   // exampleBlockingDispatcher()
    exampleLaunchGlobalWaiting()
}

fun exampleBlocking() {
    println("One")
    runBlocking {
        printlnDelayed("Two")
    }
    //we can call suspending functions from runBlocking
    // it is a Coroutine scope
    println("Three")
}

suspend fun printlnDelayed(message:String){
   // Thread.sleep(1000)
    delay(1000)
    println(message)
}

fun exampleBlockingDispatcher(){
    runBlocking (Dispatchers.Default){
        println("One from Thread- ${Thread.currentThread().name}")
        printlnDelayed("Two from Thread - ${Thread.currentThread().name}")
    }
    println("Three from Thread ${Thread.currentThread().name}")
}

fun exampleLaunchGlobal() = runBlocking {
    println("One from ${Thread.currentThread().name}")

    GlobalScope.launch {
        printlnDelayed("Two from ${Thread.currentThread().name}")
    }

    println("Three from ${Thread.currentThread().name}")
    delay(3000)
}

fun exampleLaunchGlobalWaiting() = runBlocking {
    println("One from ${Thread.currentThread().name}")

    val job = GlobalScope.launch {
        printlnDelayed("Two from ${Thread.currentThread().name}")
    }

    println("Three from ${Thread.currentThread().name}")
    job.join()
}
