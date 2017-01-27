@Grab('io.ratpack:ratpack-groovy:1.3.3')

import static ratpack.groovy.Groovy.ratpack

ratpack {
  handlers {
    get {
      def scriptPath = "/home/pi/proj/Adafruit_Python_MAX31855/examples/simpletest.py"
      def command = "python $scriptPath"
      render """{
          'CHT': ${command.execute().text}
      }"""
    }
  }
}
