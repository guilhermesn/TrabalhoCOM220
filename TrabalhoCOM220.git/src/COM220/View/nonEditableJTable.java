package COM220.View;


import javax.swing.table.DefaultTableModel;

public class nonEditableJTable extends DefaultTableModel{
    // Não deixar células da DefaultTableModel serem alteradas com clique duplo
    @Override
    public boolean isCellEditable(int row, int column){  
          return false;  
    }
}