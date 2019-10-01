import kotlinx.coroutines.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

fun main(){
   // exampleBlocking()
   // exampleBlockingDispatcher()
   // exampleLaunchCoroutineScope()
   // exampleAsync()
    exampleWithContext()
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

    //our custom dispatcher
    val executedDispatcher = Executors.newFixedThreadPool(2).asCoroutineDispatcher()

    println("Three from ${Thread.currentThread().name}")

    (executedDispatcher.executor as ExecutorService).shutdown()
}

suspend fun calculateHard(num : Int):Int{
    delay(1000)
    return num*1000
}

fun exampleAsync() = runBlocking {

    val startTime = System.currentTimeMillis()

    val result = async { calculateHard(1000) }
    val result1 = async { calculateHard(2000) }
    val result2 = async { calculateHard(3000) }

    val sum = result.await()+result1.await()+result2.await()

    println("The ultimate result = $sum")

    val endTime = System.currentTimeMillis()

    println("Total time of execution = ${endTime - startTime}")
}

fun exampleWithContext() = runBlocking {

    val startTime = System.currentTimeMillis()

    val result = withContext(Dispatchers.Default){ calculateHard(1000) }
    val result1 = withContext(Dispatchers.Default){ calculateHard(2000) }
    val result2 = withContext(Dispatchers.Default){ calculateHard(3000) }

    val sum = result+result1+result2

    println("The ultimate result = $sum")

    val endTime = System.currentTimeMillis()

    println("Total time of execution = ${endTime - startTime}")
}
