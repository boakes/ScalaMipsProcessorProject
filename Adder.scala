package proc

/**
 * @author boakes1
 */
class Adder extends Component {
   
  var wire1:Wire = null
  var wire2:Wire = null
  var output:Wire = new Wire
  
  def add(a:Int, b:Int):Int = {
      a+b
    }
  
   override def wireChanged{
     output.setValue(add(wire1.value,wire2.value))
   }
}