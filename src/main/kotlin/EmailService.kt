import kotlinx.coroutines.*

data class User(
    val name: String,
    val surname: String,
    val age: Int
)

data class UserInfo(
    val telephoneNumber: String,
    val address: String
)

data class AggregatedUserData(
    val user: User,
    val info: UserInfo?
)

class UserService {
    private val users = listOf(
        User("Vasya", "Pupkin", 25),
        User("Olya", "Limonova", 40),
        User("Andrey", "Latushev", 20),
        User("Alexey", "Ivanov", 63)
    ).withIndex().associate { it.index to it.value }

    suspend fun getUser(id: Int): User {
        delay(3000)

        return users[id] ?: error("User with id: $id not found")
    }
}

class UserInfoService  {
    private val usersInfo = listOf(
        UserInfo("8-921-000-00-00", "Кантемировская ул. д.2")
    ).withIndex().associate { it.index to it.value }

    suspend fun getUserInfo(id: Int): UserInfo? {
        delay(3000)

        return usersInfo[id]
    }
}

class UserDataAggregationService(
    private val userService: UserService,
    private val userInfoService: UserInfoService
) {
    suspend fun aggregateUserData(id: Int): AggregatedUserData = coroutineScope {
        val user = async { userService.getUser(id) }
        val userInfo = async { userInfoService.getUserInfo(id) }

        AggregatedUserData(user.await(), userInfo.await())
    }
}

fun main() {
    runBlocking {
        val userService = UserService()
        val infoService = UserInfoService()
        val aggregatedUserService = UserDataAggregationService(userService, infoService)

        println(aggregatedUserService.aggregateUserData(0))
        println(aggregatedUserService.aggregateUserData(1))
    }
}