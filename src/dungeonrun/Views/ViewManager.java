/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun.Views;

import dungeonrun.Controllers.MainMenuController;
import dungeonrun.Controllers.NewGameController;
import dungeonrun.MainContainer;
import dungeonrun.MainMenuState;
import dungeonrun.NewGameState;

/**
 *
 * @author Liam
 */
public class ViewManager {
    
    public void IntializeViews()
    {
        MainMenuView mmView = new MainMenuView();
        MainMenuState mmModel = new MainMenuState();
        MainMenuController mmCon = new MainMenuController(mmModel, mmView);
        mmModel.addObserver(mmView);
        
        NewGameView ngView = new NewGameView();
        NewGameState ngModel = new NewGameState();
        NewGameController ngCon = new NewGameController(ngModel, ngView);
        ngModel.addObserver(ngView);
        
        IntermissionView imView = new IntermissionView();
        
        MainContainer.container.add(mmView, "MainMenuView");
        MainContainer.container.add(ngView, "NewGameView");
        MainContainer.container.add(imView, "IntermissionView");

    }
}
