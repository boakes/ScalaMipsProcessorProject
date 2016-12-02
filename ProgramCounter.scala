package proc

/**
 * @author boakes1
 */
class ProgramCounter extends Component{
     
     var input:Wire = null
     var output:Wire = new Wire
     var data = 0x0000
     
     output.input = this
     
     
     
     def ClockChanged{
         data = input.value
         output.setValue(data)
     }
}