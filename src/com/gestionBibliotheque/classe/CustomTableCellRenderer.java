/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionBibliotheque.classe;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        // Change the background color of the cell if the value equals "example"
        if("Ok".equals(value)){
            cellComponent.setForeground(Color.green);
        }else if("En cours".equals(value)){
            cellComponent.setForeground(Color.gray);
        }else if("En retard".equals(value)){
            cellComponent.setForeground(Color.red);
        }
        return cellComponent;
    }
}