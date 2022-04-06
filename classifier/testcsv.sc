import com.github.tototoshi.csv._
import java.io.{ File, StringReader }

val name = "positive.csv"
val reader = CSVReader.open(new File (name))
