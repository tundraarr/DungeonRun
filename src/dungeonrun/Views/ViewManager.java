/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun.Views;

import dungeonrun.BattleState;
import dungeonrun.Controllers.*;
import dungeonrun.GraveyardState;
import dungeonrun.IntermissionState;
import dungeonrun.MainContainer;
import dungeonrun.MainMenuState;
import dungeonrun.NewGameState;

/**
 *
 * @author Liam
 */
//Used for intializing all views for this application and adding them to the main container within the frame
public class ViewManager {
    
    public void IntializeViews()
    {
        MainMenuView mmView = new MainMenuView();
        MainMenuState mmModel = new MainMenuState();
        MainMenuController mmCon = new MainMenuController(mmModel, mmView);
        mmModel.addObserver(mmView);
        
        GraveyardView gView = new GraveyardView();
        GraveyardState gModel = new GraveyardState();
        GraveyardController gCon = new GraveyardController(gModel, gView);
        gModel.addObserver(gView);
        
        NewGameView ngView = new NewGameView();
        NewGameState ngModel = new NewGameState();
        NewGameController ngCon = new NewGameController(ngModel, ngView);
        ngModel.addObserver(ngView);
        
        IntermissionView imView = new IntermissionView();
        IntermissionState imModel = new IntermissionState();
        IntermissionController imCon = new IntermissionController(imModel, imView);
        imModel.addObserver(imView);
        
        BattleView bView = new BattleView();
        BattleState bModel = new BattleState();
        BattleController bCon = new BattleController(bModel, bView);
        bModel.addObserver(bView);
        
        MainContainer.container.add(mmView, "MainMenuView");
        MainContainer.container.add(gView, "GraveyardView");
        MainContainer.container.add(ngView, "NewGameView");
        MainContainer.container.add(imView, "IntermissionView");
        MainContainer.container.add(bView, "BattleView");

    }
}
