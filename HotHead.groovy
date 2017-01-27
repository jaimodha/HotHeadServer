@Grab('io.ratpack:ratpack-groovy:1.3.3')

import static ratpack.groovy.Groovy.ratpack
import groovy.util.Expando
import groovy.json.*
ratpack {
  handlers {
    get {
      def scriptPath = "/home/pi/proj/Adafruit_Python_MAX31855/examples/simpletest.py"
      def command = "python $scriptPath"
      def result = command.execute().text as Integer
      def cylinder = new Cylinder(result)
      def e = new Expando()
      e.CHT1 = cylinder.cht[0]
      e.CHT2 = cylinder.cht[1]
      e.CHT3 = cylinder.cht[2]
      e.CHT4 = cylinder.cht[3]
      e.CHT5 = cylinder.cht[4]
      e.CHT6 = cylinder.cht[5]
      e.EGT1 = cylinder.egt[0]
      e.EGT2 = cylinder.egt[1]
      e.EGT3 = cylinder.egt[2]
      e.EGT4 = cylinder.egt[3]
      e.EGT5 = cylinder.egt[4]
      e.EGT6 = cylinder.egt[5]
      render new JsonBuilder(e).toPrettyString()
    }
  }
}

public class Cylinder {

  public Cylinder(Integer newBaseValue) {
    cht = cht.collect { it + newBaseValue + Math.abs(new Random().nextInt() % 3) + 1 }
    egt = egt.collect { it + (newBaseValue * 4) + Math.abs(new Random().nextInt() % 10) + 1 }
  }

  List<Integer> cht = [0, 5, 7, 10, 15, 20]
  List<Integer> egt = [20, 130, 85, 50, 65, 100]
}
