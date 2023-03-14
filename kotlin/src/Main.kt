import java.util.ArrayList

fun main(args: ArrayList<String>) {
    readFileLineByLineUsingForEachLine()
}



fun readFileLineByLineUsingForEachLine(fileName: String)
        = File("/home/e/IdeaProjects/kotlin/src/main/input.txt").forEachLine { println(it) }
class Node(
    var value: Int,
    var right: Node? = null,
    var left: Node? = null,

    fun insert(value : Int){
    if (value > this.value) {
        if (this.right == null) {
            this.right = Node(value)
        } else {
            this.right.insert(value)
        }
    } else if (value < this.value) {
        if (this.left == null) {
            this.left = Node(value)
        } else {
            this.left.insert(value)
        }
    }
}
)

fun get_bst(root: Node){
    if (Node? == null){

    }
}


fun get(int: smth){
    return smth + 2
}