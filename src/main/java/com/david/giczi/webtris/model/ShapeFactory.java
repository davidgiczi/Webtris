
package com.david.giczi.webtris.model;

import com.david.giczi.webtris.model.shapes.RightGunShape;
import com.david.giczi.webtris.model.shapes.AbstractShape;
import com.david.giczi.webtris.model.shapes.TShape;
import com.david.giczi.webtris.model.shapes.LeftGunShape;
import com.david.giczi.webtris.model.shapes.PillarShape;
import com.david.giczi.webtris.model.shapes.LeftStairShape;
import com.david.giczi.webtris.model.shapes.RightStairShape;
import com.david.giczi.webtris.model.shapes.SquareShape;

/**
 *
 * @author GicziD
 */

public class ShapeFactory {
    
    private static final String[] SHAPE_COLOR = { "#d11141",
                                                  "#00b159",
                                                  "#00aedb",
                                                  "#f37735",
                                                  "#ffc425"
                                                  
    };
    
   
    public static AbstractShape getShape(){
        
        AbstractShape shape;
        int percentValue = (int) (Math.random() * 105);
        ShapePosition starterPosition = new ShapePosition((int) (Math.random() * 7 + 2), 0);

            if(0 <= percentValue && percentValue < 15) {
                
                shape = new PillarShape(starterPosition,
                        SHAPE_COLOR[(int) (Math.random() * SHAPE_COLOR.length)]);
            }
            else if(15 <= percentValue && percentValue < 30) {
                
                shape = new SquareShape(starterPosition,
                        SHAPE_COLOR[(int) (Math.random() * SHAPE_COLOR.length)]);
            }   
            else if(30 <= percentValue && percentValue < 45) {
                
                shape = new TShape(starterPosition,
                        SHAPE_COLOR[(int) (Math.random() * SHAPE_COLOR.length)]);
            }   
            else if(45 <= percentValue && percentValue < 60) {
                
                shape = new LeftGunShape(starterPosition,
                        SHAPE_COLOR[(int) (Math.random() * SHAPE_COLOR.length)]);
            }   
            else if(60 <= percentValue && percentValue < 75) {
                
                shape = new RightGunShape(starterPosition,
                        SHAPE_COLOR[(int) (Math.random() * SHAPE_COLOR.length)]);
            }   
            else if(75 <= percentValue && percentValue < 90) {
                
                shape = new LeftStairShape(starterPosition,
                        SHAPE_COLOR[(int) (Math.random() * SHAPE_COLOR.length)]);
            }
            else{
                
                shape = new RightStairShape(starterPosition,
                        SHAPE_COLOR[(int) (Math.random() * SHAPE_COLOR.length)]);
            }
            
        return shape;
    }
    
    
}
