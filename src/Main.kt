import kotlinx.coroutines.*

suspend fun main2() {
    for(i in 0..5){
        delay(500)
        println(i)
    }
    println("Message!")
}

suspend fun main3() {
    coroutineScope {
        launch {
            for(i in 0..5){
                delay(500)
                println(i)
            }
        }
        println("Message!")
    }
}
suspend fun main4() {
    coroutineScope {
        val job: Job = launch {
            delay(1000)
            println("123")
        }
        job.join() // и ждет завершения работы корутины
        println("Finished!")
        job.cancel() // отмена корутины
        job.join()  // нужен
        job.cancelAndJoin() // отменить и ждем завершения
    }
}
suspend fun main5() {
    coroutineScope {
        // создаю корутину, но не запускаю
        val job: Job = launch(start = CoroutineStart.LAZY) {
            delay(1000)
            println("123")
        }
        delay(3000)
        job.start() // запуск корутины
       // job.start()
    }
}
suspend fun main() {
    coroutineScope {
        val result: Deferred<String> = async(start = CoroutineStart.LAZY) {
            getResult()
        }
        result.start() // запускаю корутину
        println("mess: ${result.await()}")
        println("finished!")
    }
}
suspend fun getResult() : String {
    delay(2000)
    return "Result string!"
}