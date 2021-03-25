interface HumanDao {
    fun getHuman(id: Int): Human?
    fun getHumans(): List<Human>
}