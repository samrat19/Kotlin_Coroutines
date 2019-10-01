import kotlinx.coroutines.*

fun main(){
   // exampleBlocking()
   // exampleBlockingDispatcher()
    exampleLaunchCoroutineScope()
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

fun exampleLaunchCoroutineScope() = runBlocking {
    println("One from ${Thread.currentThread().name}")

    launch {
        printlnDelayed("Two from ${Thread.currentThread().name}")
    }
    launch(Dispatchers.IO) {
        printlnDelayed("Two from ${Thread.currentThread().name}")
    } // IO dispatchers can rapidly spins up multiple threads

    println("Three from ${Thread.currentThread().name}")
}
