
package com.david.giczi.webtris.model;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author GicziD
 */
public class ShapePosition {
    
    private final int displayer_x;
    private final int displayer_y;
    private final int  logicBoardIndex;
    public static final int HR_SHIFT = 24;
    public static final int VR_SHIFT = 2;

    public int getDisplayer_x() {
        return displayer_x;
    }

    public int getDisplayer_y() {
        return displayer_y;
    }

    public int getLogicBoardIndex() {
        return logicBoardIndex;
    }
     
    public ShapePosition(int displayer_x, int displayer_y) {
       
        this.displayer_x = displayer_x;
        this.displayer_y = displayer_y;
        this.logicBoardIndex =
        convertDisplayerCoordsToLogicBoardIndex(displayer_x, displayer_y);
    }

    private int convertDisplayerCoordsToLogicBoardIndex(int x, int y){
       
       int index = -1;
       
       int logic_x = x - HR_SHIFT;
       int logic_y = y - VR_SHIFT;
       
//       if(HR_SHIFT <= x && VR_SHIFT <= y && 
//               x < HR_SHIFT + Displayer.LENGTH_OF_BOARD && 
//               y < VR_SHIFT + Displayer.LENGTH_OF_BOARD){
//           
//       index = logic_y * Displayer.WIDTH_OF_BOARD + logic_x/2;     
//       }
      
       return index;
   }
     
    public ShapePosition(int logicBoardIndex) {
        
       this.logicBoardIndex = logicBoardIndex;
       this.displayer_x =
               convertLogicBoardIndexToDisplayerCoords(logicBoardIndex).get(0);
       this.displayer_y =
               convertLogicBoardIndexToDisplayerCoords(logicBoardIndex).get(1);
       
    }
     
    private List<Integer> convertLogicBoardIndexToDisplayerCoords(int index){
       
       List<Integer> coords = new ArrayList<>();
       
//       int disp_x = index % Displayer.WIDTH_OF_BOARD * 2 + HR_SHIFT;
//       int disp_y = index / Displayer.WIDTH_OF_BOARD + VR_SHIFT;
//       
//       if(0 <= index && index < 200){   
//           coords.add(disp_x);
//           coords.add(disp_y);
//       }
//       else{
//           coords.add(-1);
//           coords.add(-1);
//       }
             
       return coords;
   }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.displayer_x;
        hash = 71 * hash + this.displayer_y;
        hash = 71 * hash + this.logicBoardIndex;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ShapePosition other = (ShapePosition) obj;
        if (this.displayer_x != other.displayer_x) {
            return false;
        }
        if (this.displayer_y != other.displayer_y) {
            return false;
        }
        return this.logicBoardIndex == other.logicBoardIndex;
    }
 
    
    
    @Override
    public String toString() {
        return "("+displayer_x + ", " + displayer_y + ") <--> " + logicBoardIndex;
    }
    
    
    
}
