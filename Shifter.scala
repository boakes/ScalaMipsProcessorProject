package proc

/**
 * @author boakes1
 */
class Shifter extends Component{
    
    var input:Wire = null
    var output:Wire = new Wire
    
    override def wireChanged{
      output.setValue(input.value << 2)
    }
}