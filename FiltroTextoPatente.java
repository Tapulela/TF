/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author usuario
 */

class FiltroTextoPatente extends DocumentFilter {
   private static final String REMOVE_REGEX = "[^[^a-zA-Z]-\\d]";
   private boolean filter = true;

   public boolean isFilter() {
      return filter;
   }

   public void setFilter(boolean filter) {
      this.filter = filter;
   }

   @Override
   public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
      if (filter) {
         text = text.replaceAll(REMOVE_REGEX, "");
      }
      super.insertString(fb, offset, text, attr);

   }

   @Override
   public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
      if (filter) {
         text = text.toUpperCase();
         text = text.replaceAll(REMOVE_REGEX, "");
      }
      super.replace(fb, offset, length, text, attrs);

   }
}
