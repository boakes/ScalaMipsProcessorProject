package proc

/**
 * @author boakes1
 */
class And extends Component{
  
  var data1:Wire = null
  var data2:Wire = null
  var output:Wire = new Wire
  
  def andop (a:Int, b:Int):Int = {
        a & b
    }
    
   override def wireChanged{
      output.setValue(andop(data1.value,data2.value))     
   }

}
