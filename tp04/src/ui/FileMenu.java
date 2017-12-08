package ui ;

import javax.swing.* ;

public class FileMenu extends JMenu
{
   //...
   private final QuitMenuItem quitMenuItem ;

   public FileMenu(DrawingApp drawingApp)
   {
      super("File") ; // Text of the menu

      // Create and add menu items
      //...
      add(quitMenuItem = new QuitMenuItem(drawingApp)) ;
   }

}